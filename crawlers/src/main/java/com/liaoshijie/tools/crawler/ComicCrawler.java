/*
 * 文件名：CommicCrawler.java
 * 版权：Copyright 2007-2017 Liao Shijie Tech. Co. Ltd. All Rights Reserved.
 * 描述： CommicCrawler.java
 * 修改人：KOBE
 * 修改时间：2017年11月11日
 * 修改内容：新增
 */
package com.liaoshijie.tools.crawler;

import com.liaoshijie.tools.common.utils.CommonCheckUtils;
import com.liaoshijie.tools.crawler.common.ComicStatics;
import com.liaoshijie.tools.crawler.model.ComicVo;
import com.liaoshijie.tools.crawler.task.CrawlerChapterTask;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * TODO 漫画爬虫. 爬取漫画网站的漫画图片至本地，借助Jsoup框架; 每个漫画设置3-5个线程，考虑IO
 *
 * @author KOBE
 */
public class ComicCrawler {

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
    public static final Map<String, String> COMIC_NO_MAP = new HashMap<>(10);

    /**
     * 要处理的漫画集合开始结尾集数. key：海贼王，死神等； value：漫画对应的开始结束集数，“-”分隔
     */
    public static final Map<String, String> COMIC_START_END_MAP = new HashMap<>(10);

    static {
        try {
            COMIC_NO_MAP.put("2", "海贼王-onepiece");
            COMIC_NO_MAP.put("7", "死神-bleach");

            COMIC_START_END_MAP.put("2", "700-890");
            COMIC_START_END_MAP.put("7", "600-686");

            File f = new File(ComicStatics.BASE_FILE_PATH);
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
        int nThreads = 10 * COMIC_NO_MAP.size();
        int counter = 0;
        ExecutorService service = new ThreadPoolExecutor(nThreads, nThreads, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());

        if (CommonCheckUtils.isNotEmpty(COMIC_NO_MAP) && CommonCheckUtils.isNotEmpty(COMIC_START_END_MAP)) {
            for (Entry<String, String> entry : COMIC_NO_MAP.entrySet()) {

                // service.execute(new CrawlerTask(commic));

                System.out.println("开始爬取【" + entry.getValue() + "】，要爬取的集数：[" + COMIC_START_END_MAP.get(entry.getKey()) + "]");
                String[] noArr = COMIC_START_END_MAP.get(entry.getKey()).split("\\-");
                int start = Integer.valueOf(noArr[0]);
                int end = Integer.valueOf(noArr[1]);

                // 循环处理每一集
                for (int index = start; index <= end; index++) {
                    counter++;
                    if (counter % 20 == 0) {
                        try {
                            Thread.sleep(TimeUnit.SECONDS.toMillis(10));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    ComicVo comic = new ComicVo();
                    comic.setComicNo(entry.getKey());
                    comic.setComicName(entry.getValue());
                    comic.setComicChapterNo(COMIC_START_END_MAP.get(entry.getKey()));
                    comic.setCurrentChapterNo(index);
                    service.execute(new CrawlerChapterTask(comic));
                }
            }
        }


        service.shutdown();
        while (!service.isTerminated()) {
            try {
                System.out.println("     *******小爬虫正在努力爬取中，客官请稍等...");
                Thread.sleep(TimeUnit.SECONDS.toMillis(30));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        System.out.println("所有漫画爬完啦，客官请移步到弹出的目录下细细阅读!!!");

        // 跳转文件夹
        String[] cmd = new String[5];
        cmd[0] = "cmd";
        cmd[1] = "/c";
        cmd[2] = "start";
        cmd[3] = " ";
        cmd[4] = ComicStatics.BASE_FILE_PATH;
        try {
            Runtime.getRuntime().exec(cmd);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
