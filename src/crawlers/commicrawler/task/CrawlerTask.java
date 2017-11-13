/*
 * 文件名：CrawlerTask.java
 * 版权：Copyright 2007-2017 Liao Shijie Tech. Co. Ltd. All Rights Reserved. 
 * 描述： CrawlerTask.java
 * 修改人：KOBE
 * 修改时间：2017年11月12日
 * 修改内容：新增
 */
package crawlers.commicrawler.task;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

import crawlers.commicrawler.common.CommicStatics;
import crawlers.commicrawler.model.CommicVo;
import util.HttpToolKit;

/**
 * TODO 漫画爬取任务线程.
 * @author KOBE
 */
public class CrawlerTask implements Runnable {
    /**
     * 添加字段注释.
     */
    private CommicVo commic;

    /**
     * 添加字段注释.
     */
    HttpToolKit http = HttpToolKit.build();

    /**
     * 添加字段注释.
     * eg:img.src="http://p1.xiaoshidi.net/"
     */
    private Pattern p1 = Pattern.compile("img\\.src=.+\"");

    /**
     * 添加字段注释.
     * eg:var mhurl1 = \".+\"
     */
    private Pattern p2 = Pattern.compile("var mhurl = \".+\"");

    /**
     * {@inheritDoc}.
     */
    @Override
    public void run() {

        System.out.println("开始爬取【" + commic.getCommicName() + "】，要爬取的集数：[" + commic.getCommicChapterNo() + "]");
        String[] noArr = commic.getCommicChapterNo().split("\\-");
        int start = Integer.valueOf(noArr[0]);
        int end = Integer.valueOf(noArr[1]);

        // 循环处理每一集
        for (int index = start; index <= end; index++) {
            System.out.println("正在爬取【" + commic.getCommicName() + "】第 " + index + " 集~");
            String path = CommicStatics.BASE_FILE_PATH + "//" + commic.getCommicName() + "//" + index + "//";
            File f = new File(path);
            if (!f.exists()) {
                f.mkdirs();
            }

            // 获取页端资源
            boolean run = true;
            int page = 0;
            do {
                String tmpPicPath = path + page + ".jpg";
                File tmpFile = new File(tmpPicPath);
                if (tmpFile.exists()) {
                    page++;
                    continue;
                }
                
                // url:http://manhua.fzdm.com/2/700/index_55.html
                String url = CommicStatics.COMMIC_URL + commic.getCommicNo() + "/" + index + "/index_" + page + ".html";
                // url = "http://manhua.fzdm.com/2/700/index_88.html";
                String resource = null;
                try {
                    resource = http.doGetThrowE(url);
                    // System.err.println(resource);
                } catch (Exception e) {
                    if (e instanceof RuntimeException && e.getMessage().startsWith("HttpClient,error status code :")) {
                        System.out.println("【" + commic.getCommicName() + "】第 " + index + " 集爬取完成!!");
                    } else {
                        e.printStackTrace();
                    }
                    run = false;
                    continue;
                }
                if (StringUtils.isNotBlank(resource)) {
                    resource = resource.replaceAll("\r\n", "");
                    String picUrl = null; // 当前页漫画的核心图片url
                    // 解析网页资源
                    Matcher m1 = p1.matcher(resource); // img.src="http://p1.xiaoshidi.net/"
                    if (m1.find()) {
                        picUrl = m1.group(0).replaceAll("img.src=\"|\"", ""); // http://p1.xiaoshidi.net/
                        Matcher m2 = p2.matcher(resource); // img.src="http://p1.xiaoshidi.net/"
                        if (m2.find()) {
                            picUrl += m2.group(0).replaceAll("var mhurl = \"|\"", ""); // http://p1.xiaoshidi.net/
                        }
                    }
                    
                    // 获取到图片url后下载至本地
                    if (StringUtils.isNotBlank(picUrl)) {
                        saveCommicPicToLocal(picUrl, path, page);
                    }
                } else {
                    run = false;
                }
                page++;
                // run = false;
            } while (run);
        }

    }

    /**
     * TODO 下载图片至本地.
     * 
     * @param picUrl url.
     * @param page page.
     * @param path  path.
     */
    private void saveCommicPicToLocal(String picUrl, String path, int page) {
        String picPath = path + page + ".jpg"; // 图片全路径名
        File picF = new File(picPath);
        if (picF.exists()) {
            return;
        }
        
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
        FileOutputStream fileOut = null;
        HttpURLConnection conn = null;
        InputStream inputStream = null;

        try {
            // 建立链接
            URL httpUrl = new URL(picUrl);
            conn = (HttpURLConnection) httpUrl.openConnection();
            // 以Post方式提交表单，默认get方式
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            // post方式不能使用缓存
            conn.setUseCaches(false);
            // 连接指定的资源
            conn.connect();
            // 获取网络输入流
            inputStream = conn.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(inputStream);
            
            // 写入到文件（注意文件保存路径的后面一定要加上文件的名称）
            fileOut = new FileOutputStream(picPath);
            BufferedOutputStream bos = new BufferedOutputStream(fileOut);

            byte[] buf = new byte[4096];
            int length = bis.read(buf);
            // 保存文件
            while (length != -1) {
                bos.write(buf, 0, length);
                length = bis.read(buf);
            }
            bos.close();
            bis.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != conn) {
                    conn.disconnect();
                }
                if (null != fileOut) {
                    fileOut.close();
                }
                if (null != inputStream) {
                    inputStream.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    /**
     * 构造函数.
     */
    public CrawlerTask() {
    }

    /**
     * 构造函数.
     * 
     * @param vo
     *            .
     */
    public CrawlerTask(CommicVo vo) {
        this.commic = vo;
    }

    /**
     * 设置commic.
     * 
     * @return 返回commic
     */
    public CommicVo getCommic() {
        return commic;
    }

    /**
     * 获取commic.
     * 
     * @param commic
     *            要设置的commic
     */
    public void setCommic(CommicVo commic) {
        this.commic = commic;
    }

}
