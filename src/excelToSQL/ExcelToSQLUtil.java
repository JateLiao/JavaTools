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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
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
    @SuppressWarnings("unused")
    public static String toInsertSql(String path) {
        path = "D:/Test/华住托管SQL导表.xlsx";
        String fileName = "D:/Test/insert_sql.txt";
        FileInputStream in = null;
        Workbook book = null;
        List<String> vals = new ArrayList<>(5000);  
        Map<String, String> fieldTypeMap = new HashMap<>(50); // 字段对应类型map
        
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
                Sheet sheet = book.getSheetAt(i);
                Row firstRow = null; // 首行字段
                Row secondRow = null; //sheet.getRow(1); // 次行类型
                int rows = sheet.getPhysicalNumberOfRows(); // 获取所有行数
                for (int j = 0; j < rows; j++) {
                    Row row = sheet.getRow(j);
                    if (row != null && firstRow != null) { // 第一个非空行作为首行
                        firstRow = row;
                        secondRow = sheet.getRow(j + 1);
                        handleFieldType(firstRow, secondRow, fieldTypeMap);
                        continue;
                    }
                    if (row != null) {
                        int columns = firstRow.getPhysicalNumberOfCells(); // 总列数
                    }
                }
                
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return "true";
    }
    
    /** TODO 获取字段和对应类型的map，如keyid字段对应varchar.
     */
    private static void handleFieldType(Row firstRow, Row secondRow, Map<String, String> fieldTypeMap) {
        // TODO Auto-generated method stub
        
    }

    public static void main(String[] args) {
        toInsertSql("");
    }
}
