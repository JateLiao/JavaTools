package better517Tools.fawOrdersPushMQ;

import com.better517na.javaMessageQueueHelper.messageQueueHelper.MessageQueueHelper;
import com.better517na.javaMessageQueueHelper.model.ContentType;
import com.better517na.javaMessageQueueHelper.model.Message;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FileUtils;
import util.DateUtils;
import util.GsonUtil;
import util.HttpToolKit;
import util.MQUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.text.MessageFormat;
import java.util.*;
import java.util.concurrent.TimeoutException;

/**
 * @Desc FAWOrdersPushMQ
 * @ProjectName JavaTools
 * @Company com.lsj
 * @CreateTime 2018/5/25 19:13
 * @Author tianzhong
 */
public class FAWOrdersPushMQ {
    /**
     * 一汽订单推送MQ
     */
    
    /**
     * Main Method: 请开始你的操作.
     *
     */
    public static void main(String[] args) {
        fawPushOrders ();
    }
    
    /**
     * FILE_PATH.
     */
    private static final String FILE_PATH = "D:\\Test\\faw\\orders.txt";
    
    /**
     * Business Type.
     */
    private static final int HOTEL_TYPE = 1;
    private static final int AIR_TICKET_TYPE = 2;
    private static ConnectionFactory connectionFactory;
    
    /**
     * CHAR_SET.
     */
    private static final String CHAR_SET = "utf-8";
    
    /**
     * MQ
     */
    private static String host = "172.21.21.5";
    private static String vhost = "cl-putting";
    private static String user = "guest";
    private static String password = "guest";
    private static int port = 5672;
    
    /**
     * Hotel
     */
    private static String exchangeNameHotel = "OrderDataExchange";
    private static String routingKeyHotel = "OrderDataHotelRKey";
    private static String queueHotel = "OrderDataHotelQueueFAW";
    
    /**
     * Air
     */
    private static String exchangeNameAir = "OrderDataExchange";
    private static String routingKeyAir = "OrderDataAirTicketRKey";
    private static String queueAir = "OrderDataAirTicketQueueFAW";
    
    /**
     * http post
     */
    private static HttpToolKit httpToolKit = HttpToolKit.build ();
    private static String url = "http://syq1.mq.517na.com/api/exchanges/{1}/amq.default/publish";
    
    /**
     * fawPushOrders.
     */
    public static void fawPushOrders() {
        try {
            List<String> pushOrders = loadOrders ();
            if (CollectionUtils.isEmpty (pushOrders)) {
                System.out.println ("任务量为空了，还搞个蛋啊~~~");
                return;
            }
            
            System.out.println ("任务量：" + pushOrders.size ());
            String mqurl = MessageFormat.format (url, vhost);
            for (String orderInfo : pushOrders) {
                OrderVo vo = GsonUtil.getGson ().fromJson (orderInfo, OrderVo.class);
                if (vo.getOrderType () == HOTEL_TYPE) {
                    
                    // httpToolKit.doSimplePost (url, , CHAR_SET);
                    // MQUtils.pushToMQOri (orderInfo, exchangeNameHotel, routingKeyHotel, queueHotel);
                } else if (vo.getOrderType () == AIR_TICKET_TYPE) {
                    // MQUtils.pushToMQOri (orderInfo, exchangeNameAir, routingKeyAir, queueAir);
                }
            }
        } catch (Exception e) {
            e.printStackTrace ();
        }
        
        System.out.println ("全部推送完成！！！");
    }
    
    /**
     * port.
     * @param msg msg.
     * @param vhost vhost.
     * @param routingkey routingkey.
     */
    private static void postToMQ(String msg, String vhost, String routingkey) {
        Date date = new Date ();
        String sent_time = DateUtils.format (date);
        
        Map<String, Object> param = new HashMap<> ();
        param.put ("delivery_mode", "1");
        param.put ("name", "amq.default");
        param.put ("payload_encoding", "string");
    
        param.put ("sent_time", sent_time);
        param.put ("payload", msg);
        param.put ("routing_key", routingkey);
        param.put ("vhost", vhost);
    
        Map<String, Object> headers = new HashMap<> ();
        headers.put ("sent_time", sent_time);
        param.put ("headers", headers);
    }
    
    /**
     * loadOrders.
     * @return List<String>.
     */
    private static List<String> loadOrders() throws Exception {
        return FileUtils.readLines (new File (FILE_PATH));
    }
}
