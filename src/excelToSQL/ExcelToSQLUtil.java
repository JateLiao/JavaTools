/*
 * 文件名：ExcelToSQLUtil.java
 * 版权：Copyright 2007-2017 Liao Shijie Tech. Co. Ltd. All Rights Reserved. 
 * 描述： ExcelToSQLUtil.java
 * 修改人：KOBE
 * 修改时间：2017年3月7日
 * 修改内容：新增
 */
package excelToSQL;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * TODO 在excel表格的数据，导出成sql语句.
 * 
 * 主要是导出为insert的sql语句.
 * 
 * @author     KOBE
 */
public class ExcelToSQLUtil {

    /**
     * TODO 导出insert文本.
     * 
     * excel格式，首行字段，第二行类型，第三行开始是数据(见模板).
     * 
     * @param path
     * @return
     */
    public static String toInsertSql(String path) {
        path = "D:/Test/华住托管SQL导表.xlsx";
        String fileName = "D:/Test/insert_sql.txt";
        FileInputStream in = null;
        Workbook book = null;
        List<String> vals = new ArrayList<>(5000);
        try {
            in = new FileInputStream(new File(path));
            try {
                book = new XSSFWorkbook(path);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(path);
                book = new HSSFWorkbook(in);
            }
            
            int sheets = book.getNumberOfSheets();
            for (int i = 0; i < sheets; i++) {
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
    
    public static void main(String[] args) {
        toInsertSql("");
    }
}
