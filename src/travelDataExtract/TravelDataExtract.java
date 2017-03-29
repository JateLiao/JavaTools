/*
 * 文件名：TravelDataExtract.java
 * 版权：Copyright 2007-2017 517na Tech. Co. Ltd. All Rights Reserved. 
 * 描述： TravelDataExtract.java
 * 修改人：tianzhong
 * 修改时间：2017年3月29日
 * 修改内容：新增
 */
package travelDataExtract;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

/**
 * TODO 添加类的一句话简单描述.
 * <p>
 * TODO 详细描述
 * <p>
 * TODO 示例代码
 * <pre>
 * </pre>
 * 
 * @author     tianzhong
 */
public class TravelDataExtract {
    public static void main(String[] args) throws Exception {
        travelDataExtract();
    }

    public static void travelDataExtract() throws Exception {
        Map<String, String> allMap = extractAllData();
        
    }

    /**
     * TODO 添加方法注释.
     * 
     * @return
     */
    private static Map<String, String> extractAllData() throws Exception {
        Map<String, String> map = new HashMap<>();
        File file = new File("D:/Test/insert/Hotel.txt");
        BufferedReader read = new BufferedReader(new FileReader(file));
        String line = null;
        while ((line = read.readLine()) != null) {
            String[] val = line.split("\\|");
            map.put(val[0] + "-" + val[1] + "-" + val[2], val[3]);
        }
        read.close();
        return map;
    }
}
