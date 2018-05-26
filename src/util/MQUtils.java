package util;

import better517Tools.fawOrdersPushMQ.FAWOrdersPushMQ;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @Desc MQUtils
 * @ProjectName JavaTools
 * @Company com.lsj
 * @CreateTime 2018/5/25 21:19
 * @Author tianzhong
 */
public class MQUtils {
    private static ConnectionFactory connectionFactory;
    
    /**
     * CHAR_SET.
     */
    private static final String CHAR_SET = "utf-8";
    
    /**
     * MQ
     */
    private static String host;
    private static String vhost;
    private static String user;
    private static String password;
    private static int port;
    
    /**
     * TODO 添加方法注释.
     * @param info  .
     * @return boolean.
     */
    public static boolean pushToMQOri(String info, String exchangeName, String routingKey, String queueName) {
        Connection connection = null;
        Channel channel = null;
        try {
            connection = getNewConnection(); // 生产连接
            System.out.println("连接获取完成！！！");
            channel = connection.createChannel (); // 获得通道
            System.out.println("通道获取完成！！！");
            
            /*
             * 声明（创建）队列
             * 参数1：队列名称
             * 参数2：为true时server重启队列不会消失
             * 参数3：队列是否是独占的，如果为true只能被一个connection使用，其他连接建立时会抛出异常
             * 参数4：队列不再使用时是否自动删除（没有连接，并且没有未处理的消息)
             * 参数5：建立队列时的其他参数
             */
            // channel.queueDeclare (queueName, false, false, false, null);
            
            /*
             * 向server发布一条消息
             * 参数1：exchange名字，若为空则使用默认的exchange
             * 参数2：routing key
             * 参数3：其他的属性
             * 参数4：消息体
             * RabbitMQ默认有一个exchange，叫default exchange，它用一个空字符串表示，它是direct exchange类型，
             * 任何发往这个exchange的消息都会被路由到routing key的名字对应的队列上，如果没有对应的队列，则消息会被丢弃
             */
            Map<String, Object> hashMap = new HashMap<> ();
            hashMap.put ("sent_time", DateUtils.format (new Date ()));
    
    
            AMQP.BasicProperties bp = new AMQP.BasicProperties ("application/json", "utf-8", hashMap, 2, null, null, null, null,
                    UUID.randomUUID ().toString (), null, null, null, "fawPushOrders", null);
            channel.basicPublish (exchangeName, routingKey, bp, info.getBytes ());
            System.out.println("MQ Push Success");
            return true;
        } catch (Exception e) {
            e.printStackTrace ();
        } finally {
            try {
//                channel.close ();
//                connection.close ();
            } catch (Exception e) {
                e.printStackTrace ();
            }
        }
        
        return false;
    }
    
    /**
     * getNewConnection.
     * @return Connection.
     */
    private static Connection getNewConnection() throws Exception {
        if (null == connectionFactory) {
            synchronized (FAWOrdersPushMQ.class) {
                if (null == connectionFactory) {
                    System.out.println("开始初始化连接工厂：" + host + ", 端口：" + port);
                    // 连接工厂
                    connectionFactory = new ConnectionFactory ();
                    connectionFactory.setHost (host);
                    connectionFactory.setUsername (user);
                    connectionFactory.setPassword (password);
                    connectionFactory.setPort (port);
                    connectionFactory.setVirtualHost (vhost);
                    System.out.println("工厂初始化完成！！！");
                }
            }
        }
        return connectionFactory.newConnection ();
    }
    
    public static String getHost() {
        return host;
    }
    
    public static void setHost(String host) {
        MQUtils.host = host;
    }
    
    public static String getVhost() {
        return vhost;
    }
    
    public static void setVhost(String vhost) {
        MQUtils.vhost = vhost;
    }
    
    public static String getUser() {
        return user;
    }
    
    public static void setUser(String user) {
        MQUtils.user = user;
    }
    
    public static String getPassword() {
        return password;
    }
    
    public static void setPassword(String password) {
        MQUtils.password = password;
    }
    
    public static int getPort() {
        return port;
    }
    
    public static void setPort(int port) {
        MQUtils.port = port;
    }
}
