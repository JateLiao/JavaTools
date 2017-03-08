/*
 * 文件名：Test.java
 * 版权：Copyright 2007-2016 KOBE Tech. Co. Ltd. All Rights Reserved. 
 * 描述： Test.java
 * 修改人：KOBE
 * 修改时间：2016年9月10日
 * 修改内容：新增
 */
package deleteDownloadedMoviesExtraString;

import java.util.HashMap;
import java.util.Map;

/**
 * TODO 添加类的一句话简单描述.
 * 
 * @author     KOBE
 */
public class Test {

    public static void main(String[] args) {
        System.out.println("maxMemory:   " + Runtime.getRuntime().maxMemory());
        System.out.println("totalMemory: " + Runtime.getRuntime().totalMemory());
        System.out.println("freeMemory:  " + Runtime.getRuntime().freeMemory());
        
        // String s = "[电影天堂www.dy2018.net]纵横四海.1024x548.国粤双语.中文字幕.mkv";
        // System.out.println(s.replaceAll("\\[.*?\\]", ""));
        Map<String, String> map = new HashMap<>();
        System.out.println(map.size());
        String s = "TDDownload..试试.rmvb";
        System.out.println(s.replaceAll("TDDownload/./.", ""));
    }
}
