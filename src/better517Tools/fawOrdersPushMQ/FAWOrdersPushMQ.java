package better517Tools.fawOrdersPushMQ;

import java.io.File;
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
     * fawPushOrders.
     */
    public static void fawPushOrders() {
        List<String> pushOrders = loadOrders();
    }
    
    /**
     * loadOrders.
     * @return List<String>.
     */
    private static List<String> loadOrders(){
        List<String> orders = new ArrayList<> ();
    
        File orderFile = new File (FILE_PATH);
        
        
        return orders;
    }
}
