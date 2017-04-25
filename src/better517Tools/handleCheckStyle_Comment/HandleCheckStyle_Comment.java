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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
            boolean isAnnotation = false; // 是否是注解行
            boolean isPublciDone = false; // public class XXX之前是否处理完毕
            while ((line = reader.readLine()) != null) {
                if ("".equals(line)) {
                    sb.append(line).append("\r\n");
                    continue;
                }
                if (isPublicClassLine(line, f)) { // public class XXX
                    isPublciDone = true;
                    isAnnotation = false;
                    sb.append("/**\r\n * @author tianzhong\r\n *\r\n */\r\n");
                    handleAnnotations(annotations, sb);
                    continue;
                }
                if (!isPublciDone) {
                    if (isImportOrPackageLine(line)) { // import package
                        sb.append(line).append("\r\n");
                        continue;
                    } 
                }
                if (isCommentLine(line)) { // 注释行
                    continue;   
                }
                if (isAnnotationLine(line)) { // 注解行
                    isAnnotation = true;
                }

                if (isFiledOrFunc(line) && isPublciDone) { // 字段行或者方法行
                    isAnnotation = false;
                }
                
                if (isAnnotation) {
                    annotations.add(line);
                    continue;
                } else {
                    handleFieldComment(line, sb);
                    handleAnnotations(annotations, sb);
                }
                
                sb.append(line).append("\r\n");
            }
            writeBackToFile(f, sb);
            reader.close();
            
            System.out.println(f.getName() + "处理完成！");
        }
    }

    /**
     * TODO 处理字段注释.
     * 
     * @param line
     * @param sb
     */
    private static void handleFieldComment(String line, StringBuffer sb) {
        // 添加默认字段注释
        sb.append("    /**").append("\r\n").append("     * ");
        sb.append("\r\n").append("     */").append("\r\n");
        
        Pattern p = Pattern.compile("\\s{1}\\S+;");
        Matcher m = p.matcher(line);
        if (m.find()) {
            sb.append(m.group(0).substring(1, m.group(0).length() - 1) + ".");
        } else {
            sb.append("添加字段注释.");
        }
    }

    /**
     * TODO 注释行匹配.
     * 
     * @param line
     * @return
     */
    private static boolean isCommentLine(String line) {
        Pattern p = Pattern.compile("^\\s*/\\*\\*|^\\s*\\*|^\\s*\\*\\/");
        Matcher match = p.matcher(line);
        if (match.find()) {
            return true;
        }
        
        return false;
    
    }

    /**
     * TODO 添加方法注释.
     * 
     * @param line
     * @return
     */
    private static boolean isFiledOrFunc(String line) {
        Pattern p = Pattern.compile("^\\s*private|^\\s*protected|^\\s*public");
        Matcher match = p.matcher(line);
        if (match.find()) {
            return true;
        }
        
        return false;
    
    }

    /**
     * TODO 注解行.
     * 
     * @param line
     * @return
     */
    private static boolean isAnnotationLine(String line) {
        Pattern p = Pattern.compile("^\\s*@");
        Matcher match = p.matcher(line);
        if (match.find()) {
            return true;
        }
        
        return false;
    }

    /**
     * TODO 是否是import行或者package行.
     * 
     * @param line
     * @return
     */
    private static boolean isImportOrPackageLine(String line) {
        Pattern p = Pattern.compile("^\\s*import|^\\s*package");
        Matcher match = p.matcher(line);
        if (match.find()) {
            return true;
        }
        
        return false;
    }

    /**
     * TODO 是否是public class行.
     * 
     * @param line
     * @param f 
     * @return
     */
    private static boolean isPublicClassLine(String line, File f) {
        Pattern p = Pattern.compile("\\s*public class " + f.getName().split("\\.")[0]);
        Matcher match = p.matcher(line);
        if (match.find()) {
            return true;
        }
        
        return false;
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
        if (!priorLines.isEmpty()) {
            for (String str : priorLines) {
                sb.append(str).append("\r\n");
            }
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
