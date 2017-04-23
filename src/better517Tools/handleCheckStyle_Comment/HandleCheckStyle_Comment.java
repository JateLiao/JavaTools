/*
 * 文件名：HandleCheckStyle_Comment.java
 * 版权：Copyright 2007-2017 517na Tech. Co. Ltd. All Rights Reserved. 
 * 描述： HandleCheckStyle_Comment.java
 * 修改人：tianzhong
 * 修改时间：2017年4月17日
 * 修改内容：新增
 */
package better517Tools.handleCheckStyle_Comment;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import util.DateUtils;

/**
 * TODO 某些特殊的C#model转javamodel时，CheckStyle不符合的修正.
 * 
 * 比如同目录下的 CabinInfo.java文件
 * 
 * <pre>
 * </pre>
 * 
 * @author tianzhong
 */
public class HandleCheckStyle_Comment {

    public static void main(String[] args) throws Exception {
        handleCheckStyle_Comment("D://Test//ticket");
    }

    public static void handleCheckStyle_Comment(String path) throws Exception {
        File file = new File(path);
        File[] files = file.listFiles();
        for (File f : files) {
            BufferedReader reader = new BufferedReader(new FileReader(f));
            StringBuffer sb = new StringBuffer();
            List<String> annotations = new ArrayList<>();

            createHeadComment(f, sb); // 头部注释
            
            String line = null;
            boolean isAnnotation = false;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("/**") || line.startsWith(" *")) {
                    continue;
                }
                if (line.startsWith("@")) {
                    isAnnotation = true;
                }
                if (line.startsWith("public")) {
                    isAnnotation = false;
                }

                if (line.startsWith("protected") || line.startsWith("private")) {
                    isAnnotation = false;
                    
                    // 添加默认字段注释
                    sb.append("/**").append("\r\n").append("     * 添加字段注释.");
                    sb.append("\r\n").append("     */").append("\r\n");
                } else if (line.startsWith("public")) {
                    // 类名上的注释
                    
                }
                
                if (isAnnotation) {
                    annotations.add(line);
                    continue;
                } else {
                    handleAnnotations(annotations, sb);
                }
                
                sb.append(line).append("\r\n");
            }
            writeBackToFile(f, sb);
            reader.close();
        }
    }

    /**
     * TODO 头部注释.
     * @param f 
     * @param sb
     */
    private static void createHeadComment(File f, StringBuffer sb) {
         sb.append("/*\r\n * 文件名：").append(f.getName()).append("\r\n");
         sb.append(" * 版权：Copyright 2007-2017 517na Tech. Co. Ltd. All Rights Reserved. ").append("\r\n");
         sb.append(" * 描述： ").append(f.getName()).append("\r\n");
         sb.append(" * 修改人：tianzhong").append("\r\n");
         sb.append(" * 修改时间：").append(DateUtils.format(new Date(), "yyyy年MM月dd日")).append("\r\n");
         sb.append(" * 修改内容：新增\r\n */\r\n");
    }

    /**
     * TODO 处理注解.
     * 
     * @param priorLines
     * @param sb
     */
    private static void handleAnnotations(List<String> priorLines, StringBuffer sb) {
        for (String str : priorLines) {
            sb.append(str).append("\r\n");
        }
        priorLines.clear();}

    /**
     * TODO 新内容写入源文件.
     * 
     * @param file
     * @param sb
     */
    private static void writeBackToFile(File file, StringBuffer sb) {
        BufferedWriter out = null;

        try {
            out = new BufferedWriter(new FileWriter(file));
            out.write(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != out) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
