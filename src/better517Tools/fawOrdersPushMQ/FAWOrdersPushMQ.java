package better517Tools.fawOrdersPushMQ;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FileUtils;
import util.GsonUtil;
import util.MQUtils;

import java.io.*;
import java.util.*;

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
     * filePath.
     */
    private static String filePath;
    
    /**
     * Business Type.
     */
    private static final String HOTEL_TYPE = "1";
    private static final String AIR_TICKET_TYPE = "2";
    
    /**
     * CHAR_SET.
     */
    // private static final String CHAR_SET = "utf-8";
    
    /**
     * Hotel
     */
    private static String exchangeNameHotel;
    private static String routingKeyHotel;
    private static String queueHotel;

    /**
     * Air
     */
    private static String exchangeNameAir;
    private static String routingKeyAir;
    private static String queueAir;
    
    /**
     * http post
     */
//    private static HttpToolKit httpToolKit = HttpToolKit.build ();
//    private static String url = "http://syq1.mq.517na.com/api/exchanges/{0}/amq.default/publish";
    // "Request URL: http://192.168.1.34:15672/api/exchanges/{0}/amq.default/publish";
    // http://syq1.mq.517na.com/api/exchanges/{0}/amq.default/publish";
    
    /**
     * fawPushOrders.
     */
    public static void fawPushOrders() {
        try {
            loadProperties (); // 加载配置文件
            List<String> pushOrders = loadOrders ();
            if (CollectionUtils.isEmpty (pushOrders)) {
                System.out.println ("任务量为空了，还搞个蛋啊~~~");
                return;
            }
            
            System.out.println ("任务量：" + pushOrders.size ());
            // String mqurl = MessageFormat.format (url, vhost);
            for (String orderInfo : pushOrders) {
                OrderVo vo = GsonUtil.getGson ().fromJson (orderInfo, OrderVo.class);
                if (HOTEL_TYPE.equals (vo.getOrderType ())) {
                    // postToMQ (mqurl, orderInfo, vhost, routingKeyHotel);
                    MQUtils.pushToMQOri (orderInfo, exchangeNameHotel, routingKeyHotel, queueHotel);
                } else if (AIR_TICKET_TYPE.equals (vo.getOrderType ())) {
                    MQUtils.pushToMQOri (orderInfo, exchangeNameAir, routingKeyAir, queueAir);
                }
            }
        } catch (Exception e) {
            e.printStackTrace ();
        }
        
        System.out.println ("全部推送完成！！！");
        System.exit (0);
    }
    
    private static void loadProperties(){
        Properties properties = new Properties ();
        InputStream inputStream = null;
        try {
            String absPath = FAWOrdersPushMQ.class.getProtectionDomain ().getCodeSource ().getLocation ().getPath ();
            // absPath = absPath.substring (1, absPath.length ()); // linux 下此行无用
            absPath = absPath.substring (0, absPath.lastIndexOf ("/"));
            StringBuilder filePathSb = new StringBuilder (absPath);
            filePathSb.append (File.separator);
            filePathSb.append ("config").append (File.separator);
            filePathSb.append ("param.properties");
            String propFilePath = filePathSb.toString ();
            System.out.println("配置文件路径：" + propFilePath);
            
            inputStream = new BufferedInputStream (new FileInputStream (new File (propFilePath)));
            properties.load (inputStream);
    
            /**
             * MQ
             */
            MQUtils.setHost (properties.getProperty ("host"));
            MQUtils.setVhost (properties.getProperty ("vhost"));
            MQUtils.setUser (properties.getProperty ("user"));
            MQUtils.setPassword (properties.getProperty ("password"));
            MQUtils.setPort (Integer.valueOf (properties.getProperty ("port", "5672")));
            
            filePath = properties.getProperty ("filePath");
    
            /**
             * Hotel
             */
            exchangeNameHotel = properties.getProperty ("exchangeNameHotel");
            routingKeyHotel = properties.getProperty ("routingKeyHotel");
            queueHotel = properties.getProperty ("queueHotel");
    
            /**
             * AirTicket
             */
            exchangeNameAir = properties.getProperty ("exchangeNameAir");
            routingKeyAir = properties.getProperty ("routingKeyAir");
            queueAir = properties.getProperty ("queueAir");
        } catch (IOException e) {
            System.out.println("配置文件加载异常");
            e.printStackTrace ();
        } finally {
            if (null != inputStream) {
                try {
                    inputStream.close ();
                } catch (IOException e) {
                    e.printStackTrace ();
                }
            }
        }
        
        System.out.println("配置文件加载完成!!!");
    }
    
    /**
     * port.
     * @param msg msg.
     * @param vhost vhost.
     * @param routingkey routingkey.
     */
//    private static void postToMQ(String url, String msg, String vhost, String routingkey) {
//        Date date = new Date ();
//        String sent_time = DateUtils.format (date);
//
//        Map<String, Object> param = new HashMap<> ();
//        param.put ("delivery_mode", "1");
//        param.put ("name", "amq.default");
//        param.put ("payload_encoding", "string");
//
//        param.put ("sent_time", sent_time);
//        param.put ("payload", msg);
//        param.put ("routing_key", routingkey);
//        param.put ("vhost", vhost);
//
//        Map<String, Object> headers = new HashMap<> ();
//        headers.put ("sent_time", sent_time);
//        param.put ("headers", headers);
//
//        String res = null;
//        try {
//            List<Header> headerList = new ArrayList<> ();
//            headerList.add (new BasicHeader ("Content-Type", "application/json"));
//            headerList.add (new BasicHeader ("Cache-Control", "no-cache"));
//            headerList.add (new BasicHeader ("authorization", "Basic Z3Vlc3Q6Z3Vlc3Q="));
//            httpToolKit.setHeaders (headerList);
//            res = httpToolKit.doSimplePost (url, GsonUtil.toJson (param), CHAR_SET);
//        } catch (Exception e) {
//            e.printStackTrace ();
//        }
//
//        System.out.println("Psuh Result: " + res);
//    }
    
    /**
     * loadOrders.
     * @return List<String>.
     */
    private static List<String> loadOrders() throws Exception {
        System.out.println("订单推送文件路径：" + filePath);
        return FileUtils.readLines (new File (filePath));
    }
}
