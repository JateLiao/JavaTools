/*
 * 文件名：TravelDataExtract.java
 * 版权：Copyright 2007-2017 517na Tech. Co. Ltd. All Rights Reserved. 
 * 描述： TravelDataExtract.java
 * 修改人：tianzhong
 * 修改时间：2017年3月29日
 * 修改内容：新增
 */
package travelDataExtract;

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
    public static void main(String[] args) {
        travelDataExtract();
    }

    public static void travelDataExtract(){
        Map<String, ?> allMap = extractAllData();
    }

    /**
     * TODO 添加方法注释.
     * 
     * @return
     */
    private static Map<String, ?> extractAllData() {
        Map<String, ?> map = new HashMap<>();
        
        return map;
    }
}
