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
import java.util.List;

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
            List<String> priorLines = new ArrayList<>();

            String line = null;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("@")) {
                    priorLines.add(line);
                }

                if (line.startsWith("protected") || line.startsWith("private")) {
                    /**
                     * 添加字段注释.
                     */
                    for (String prior : priorLines) {
                        sb.append(prior).append("\r\n");
                    }
                    sb.append(line).append("\r\n");
                }
                sb.append(line).append("\r\n");
                if (line.equals("/**")) {
                    sb.append(".").append("\r\n");
                }
            }
            writeBackToFile(file, sb);
            reader.close();
        }
    }

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
