/*
 * 文件名：CommicCrawler.java
 * 版权：Copyright 2007-2017 Liao Shijie Tech. Co. Ltd. All Rights Reserved. 
 * 描述： CommicCrawler.java
 * 修改人：KOBE
 * 修改时间：2017年11月11日
 * 修改内容：新增
 */
package crawlers.commicrawler;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import crawlers.commicrawler.common.CommicStatics;
import crawlers.commicrawler.model.CommicVo;
import crawlers.commicrawler.task.CrawlerTask;
import util.CommonCheckUtils;

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
     * 要处理的漫画集合. key：海贼王，死神等； value：漫画对应的在该网站的序号
     */
    public static final Map<String, String> COMMIC_NO_MAP = new HashMap<>(10);

    /**
     * 要处理的漫画集合开始结尾集数. key：海贼王，死神等； value：漫画对应的开始结束集数，“-”分隔
     */
    public static final Map<String, String> COMMIC_START_END_MAP = new HashMap<>(10);

    static {
        try {
            COMMIC_NO_MAP.put("2", "海贼王-onepiece");
            COMMIC_NO_MAP.put("7", "死神-bleach");

            COMMIC_START_END_MAP.put("2", "700-705");
            COMMIC_START_END_MAP.put("7", "600-605");

            File f = new File(CommicStatics.BASE_FILE_PATH);
            if (!f.isDirectory() && !f.exists()) {
                f.mkdirs();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * TODO 添加方法注释.
     */
    public static void doMain() {
        int nThreads = 5;
        ExecutorService service = new ThreadPoolExecutor(nThreads, nThreads, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());

        if (CommonCheckUtils.isNotEmpty(COMMIC_NO_MAP) && CommonCheckUtils.isNotEmpty(COMMIC_START_END_MAP)) {
            for (Entry<String, String> entry : COMMIC_NO_MAP.entrySet()) {
                CommicVo commic = new CommicVo();
                commic.setCommicNo(entry.getKey());
                commic.setCommicName(entry.getValue());
                commic.setCommicChapterNo(COMMIC_START_END_MAP.get(entry.getKey()));

                service.execute(new CrawlerTask(commic));
            }
        }
    }
}
