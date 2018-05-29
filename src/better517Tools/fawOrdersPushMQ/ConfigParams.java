package better517Tools.fawOrdersPushMQ;

import better517Tools.fawOrdersPushMQ.FAWOrdersPushMQ;
import util.MQUtils;

import java.io.*;
import java.util.Properties;

/**
 * @Desc ConfigParams
 * @ProjectName JavaTools
 * @Company com.lsj
 * @CreateTime 2018/5/29 14:42
 * @Author tianzhong
 */
public class ConfigParams {
    
    /**
     * filePath.
     */
    public static String filePath;
    
    /**
     * CHAR_SET.
     */
    // public static final String CHAR_SET = "utf-8";
    
    /**
     * Hotel
     */
    public static String exchangeNameHotel;
    public static String routingKeyHotel;
    public static String queueHotel;
    
    /**
     * Air
     */
    public static String exchangeNameAir;
    public static String routingKeyAir;
    public static String queueAir;
    
    /**
     * Car
     */
    public static String exchangeNameCar;
    public static String routingKeyCar;
    public static String queueCar;
    
    /**
     * initConfigs.
     */
    public static void initConfigs() {
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
        
            /**
             * Car
             */
            exchangeNameCar = properties.getProperty ("exchangeNameCar");
            routingKeyCar = properties.getProperty ("routingKeyCar");
            queueCar = properties.getProperty ("queueCar");
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
}
