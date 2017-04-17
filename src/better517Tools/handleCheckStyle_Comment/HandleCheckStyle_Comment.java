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
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

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
            
            reader.close();
        }
    }

}
