/*
 * 文件名：CommicCrawler.java
 * 版权：Copyright 2007-2017 Liao Shijie Tech. Co. Ltd. All Rights Reserved. 
 * 描述： CommicCrawler.java
 * 修改人：KOBE
 * 修改时间：2017年11月11日
 * 修改内容：新增
 */
package crawlers.commicrawler;

import java.util.HashMap;
import java.util.Map;

/**
 * TODO 漫画爬虫. 爬取漫画网站的漫画图片至本地，借助Jsoup框架; 每个漫画设置3-5个线程，考虑IO
 * 
 * @author KOBE
 */
public class CommicCrawler {
    
    /**
     * TODO 添加方法注释.
     * @param args
     */
    public static void main(String[] args) {
        doMain();
    }
    /**
     * 特别鸣谢该网站.
     */
    public static final String COMMIC_URL = "http://manhua.fzdm.com/";

    /**
     * 漫画图片本地存档基础地址.
     */
    public static final String BASE_FILE_PATH = "F://Commics";

    /**
     * 要处理的漫画集合. key：海贼王，死神等； value：漫画对应的在该网站的序号
     */
    public static final Map<String, String> COMMIC_NO_MAP = new HashMap<>(10);

    /**
     * 要处理的漫画集合开始结尾集数. key：海贼王，死神等； value：漫画对应的开始结束集数，“-”分隔
     */
    public static final Map<String, String> COMMIC_START_END_MAP = new HashMap<>(10);

    static {
        COMMIC_NO_MAP.put("2", "海贼王/onepiece");
        COMMIC_NO_MAP.put("7", "死神/bleach");

        COMMIC_START_END_MAP.put("2", "700");
        COMMIC_START_END_MAP.put("7", "600");
    }

    /**
     * TODO 添加方法注释.
     */
    public static void doMain() {
        
    }
}
