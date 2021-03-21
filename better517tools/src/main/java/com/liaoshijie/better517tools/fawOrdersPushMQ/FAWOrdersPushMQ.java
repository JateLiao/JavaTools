package com.liaoshijie.better517tools.fawOrdersPushMQ;

import com.liaoshijie.better517tools.utils.MQUtils;
import com.liaoshijie.tools.common.utils.GsonUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

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
     * Business Type.
     */
    public static final String HOTEL_TYPE = "1";
    public static final String AIR_TICKET_TYPE = "2";
    public static final String CAR_TYPE = "4";

    /**
     * SLEEP_THRESHOLD:休眠阈值，每推送50就休眠1s
     */
    private static final int SLEEP_THRESHOLD = 50;

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
            ConfigParams.initConfigs(); // 加载配置文件
            List<String> pushOrders = loadOrders();
            if (CollectionUtils.isEmpty(pushOrders)) {
                System.out.println("任务量为空了，还搞个蛋啊~~~");
                return;
            }

            System.out.println("任务量：" + pushOrders.size());
            int count = 0;
            // String mqurl = MessageFormat.format (url, vhost);
            for (String orderInfo : pushOrders) {
                if (StringUtils.isEmpty(orderInfo)) {
                    continue;
                }

                OrderVo vo = GsonUtil.getGson().fromJson(orderInfo, OrderVo.class);
                if (HOTEL_TYPE.equals(vo.getOrderType())) {
                    // postToMQ (mqurl, orderInfo, vhost, routingKeyHotel);
                    MQUtils.pushToMQOri(orderInfo, ConfigParams.exchangeNameHotel, ConfigParams.routingKeyHotel, ConfigParams.queueHotel);
                } else if (AIR_TICKET_TYPE.equals(vo.getOrderType())) {
                    MQUtils.pushToMQOri(orderInfo, ConfigParams.exchangeNameAir, ConfigParams.routingKeyAir, ConfigParams.queueAir);
                } else if (CAR_TYPE.equals(vo.getOrderType())) {
                    MQUtils.pushToMQOri(orderInfo, ConfigParams.exchangeNameCar, ConfigParams.routingKeyCar, ConfigParams.queueCar);
                }

                if (++count % SLEEP_THRESHOLD == 0) { // 位运算取模
                    System.out.println("**************** 已推[ " + SLEEP_THRESHOLD + " ]条，休眠1s: " + count);
                    Thread.sleep(TimeUnit.SECONDS.toMillis(1));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("全部推送完成！！！");
        System.exit(0);
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
        System.out.println("订单推送文件路径：" + ConfigParams.filePath);
        return FileUtils.readLines(new File(ConfigParams.filePath));
    }
}
