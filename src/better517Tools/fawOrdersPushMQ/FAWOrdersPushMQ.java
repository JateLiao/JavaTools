package better517Tools.fawOrdersPushMQ;

import com.better517na.javaMessageQueueHelper.messageQueueHelper.MessageQueueHelper;
import com.better517na.javaMessageQueueHelper.model.ContentType;
import com.better517na.javaMessageQueueHelper.model.Message;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FileUtils;
import util.GsonUtil;

import java.io.File;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

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
        fawPushOrders();
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
    
    /**
     * CHAR_SET.
     */
    private static final String CHAR_SET = "utf-8";
    
    private static String host = "utf-8";
    private static String vhost = "utf-8";
    private static String exchangeNameHotel = "utf-8";
    private static String routingKeyHotel = "utf-8";
    private static String exchangeNameAir = "utf-8";
    private static String routingKeyAir = "utf-8";
    
    /**
     * 价格下架队列.
     */
    private static MessageQueueHelper mqh = MessageQueueHelper.getInstance(); // 参数为交换机名称
    
    /**
     * fawPushOrders.
     */
    public static void fawPushOrders() {
        try {
            List<String> pushOrders = loadOrders();
            if (CollectionUtils.isEmpty (pushOrders)) {
                System.out.println("任务量为空了，还搞个蛋啊~~~");
                return;
            }
            
            System.out.println("任务量：" + pushOrders.size ());
    
            for (String orderInfo: pushOrders) {
                OrderVo vo = GsonUtil.getGson ().fromJson (orderInfo, OrderVo.class);
                if (vo.getOrderType () == HOTEL_TYPE) {
                    pushToMQ (orderInfo, exchangeNameHotel, routingKeyHotel);
                } else if (vo.getOrderType () == AIR_TICKET_TYPE) {
                    pushToMQ (orderInfo, exchangeNameAir, routingKeyAir);                }
            }
            
        } catch (Exception e) {
            e.printStackTrace ();
        }
    }
    /**
     * TODO 添加方法注释.
     * @param info  .
     * @return boolean.
     */
    private static boolean pushToMQ(String info, String exchangeName, String routingKey) {
        Message message = new Message();
        // 需要封装消息体类型 参数为枚举（支持xml、json、二进制流、protobuf、文本）
        // message.setContentType(ContentType.MESSAGE_CONTENTTYPE_JSON);
        message.setBody(info.getBytes(Charset.forName(CHAR_SET)));
        message.setContentType(ContentType.MESSAGE_CONTENTTYPE_TEXT);
        // 发布消息 参数为消息对象和routingKey
        try {
            mqh.publish(exchangeName, message, routingKey);
            // mqhDl.ack(0L);
            return true;
        } catch (Exception e) {
            e.printStackTrace ();
        }
        return false;
    }
    
    /**
     * loadOrders.
     * @return List<String>.
     */
    private static List<String> loadOrders() throws Exception {
        return FileUtils.readLines (new File (FILE_PATH));
    }
}
