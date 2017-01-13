/*
 * 文件名：DeleteDownloadedMoviesExtraString.java
 * 版权：Copyright 2007-2016 KOBE Tech. Co. Ltd. All Rights Reserved. 
 * 描述： DeleteDownloadedMoviesExtraString.java
 * 修改人：KOBE
 * 修改时间：2016年9月10日
 * 修改内容：新增
 */
package DeleteDownloadedMoviesExtraString;

import java.io.File;
import java.util.LinkedList;

/**
 * TODO 把下载的电影的多余的前缀去掉，类似[电影天堂www.dy2018.com]彗星来的那一夜BD中英双字.rmvb 则 去掉[电影天堂www.dy2018.com] 和 BD中英双字，
 * 
 * 彗星来的那一夜.rmvb.
 * 
 * @author KOBE
 */
public class DeleteDownloadedMoviesExtraString {
    /**
     * TODO 添加方法注释.
     * 
     * @param args
     */
    public static void main(String[] args) {
        Integer count = 0;

        try {
            String path = "F://TDDownload";
            File file = new File(path);
            if (file.exists() && file.isDirectory()) {
                System.out.println("开始查找电影...");

                LinkedList<File> folderList = new LinkedList<>(); // 文件夹list
                File[] files = file.listFiles();
                for (File f : files) {
                    if (f.isDirectory()) {
                        System.out.println("目录：" + f.getName());
                        folderList.add(f);
                    } else {
                        handleFileName(f, count);
                    }
                }

                File tmpFile = null;
                while (!folderList.isEmpty()) {
                    tmpFile = folderList.removeFirst();
                    files = tmpFile.listFiles();
                    for (File f : files) {
                        if (f.isDirectory()) {
                            System.out.println("目录：" + f.getName());
                            folderList.add(f);
                        } else {
                            handleFileName(f, count);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("处理完毕，本次共处理数量：" + count);
    }

    /**
     * TODO 文件处理.
     * 
     * @param f
     *            f.
     */
    private static void handleFileName(File f, Integer count) {
        System.out.println(f.getName());
        String fileName = f.getName();
        String extraName = getExtName(fileName);
        if (null != extraName && !extraName.isEmpty()) {
            if (extraName.contains("td") || extraName.contains("cfg")) { // 先排除迅雷还在下载的文件
                System.err.println(fileName + "为下载中文件，暂不处理！");
            } else if (extraName.contains("tdl") || extraName.contains("qrs")) {
                System.err.println(fileName + "为下载中文件，暂不处理！");
            } else {
                fileName = fileName.replaceAll("\\[.*?\\]", "").replaceAll("【.*?】", "");
                fileName = fileName.replaceAll("BD(.*?)高清", "").replaceAll("BD(.*?)幕", "").replaceAll("BD(.*?)字", "");
                fileName = fileName.replaceAll("HD(.*?)幕", "").replaceAll("HD(.*?)字", "");
                fileName = fileName.replaceAll("TDDownload\\.\\.", "").replaceAll("TDDownload\\.", "").replaceAll("TDDownload", "");
                System.err.println("处理后的命名：" + fileName);
                f.renameTo(new File(f.getParentFile() + "/" + fileName)); // 重命名
                count++;
            }
        }
    }

    /**
     * TODO getExtName.
     * 
     * @param s
     *            .
     * @param split
     *            .
     * @return .
     */
    private static String getExtName(String fileName) {
        String[] arr = fileName.split("\\.");
        if (arr.length > 0) {
            return arr[arr.length - 1];
        }

        return null;
    }
}
