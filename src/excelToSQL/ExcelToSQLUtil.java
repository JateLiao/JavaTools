/*
 * 文件名：ExcelToSQLUtil.java
 * 版权：Copyright 2007-2017 Liao Shijie Tech. Co. Ltd. All Rights Reserved. 
 * 描述： ExcelToSQLUtil.java
 * 修改人：KOBE
 * 修改时间：2017年3月7日
 * 修改内容：新增
 */
package excelToSQL;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * TODO 在excel表格的数据，导出成sql语句.
 * 
 * 主要是导出为insert的sql语句.
 * 
 * @author     KOBE
 */
public class ExcelToSQLUtil {

    public static void main(String[] args) {
        toInsertSql("");
        System.err.println("玩儿");
    }

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
        path = "D:/Test/华住托管SQL导表 - 副本.xlsx"; // 华住托管SQL导表    差旅壹号 华住托管SQL导表 - 副本
        String basePathName = "D:/Test/insert/";
        FileInputStream in = null;
        BufferedWriter out = null;
        Workbook book = null;
        List<String> vals = new ArrayList<>(5000);  
        Map<String, String> fieldTypeMap = new HashMap<>(50); // 字段对应类型map
        Map<Integer, String> fieldIndexMap = new HashMap<>(50); // 字段对应在第一行的列位置map
        
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
                Sheet sheet = book.getSheetAt(i); // 表格
                String tableName = sheet.getSheetName(); // 表格名作为插表名
                String baseInsert = "";
                out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(basePathName + tableName + ".txt", true)));
                Row firstRow = null; // 首行字段
                Row secondRow = null; //sheet.getRow(1); // 次行类型
                int rows = sheet.getPhysicalNumberOfRows(); // 获取所有行数
                int tmp = 0;
                int index = 0;
                StringBuffer sb = new StringBuffer("INSERT INTO " + tableName + "(");
                for (int j = 0; j < rows; j++) {
                    Row row = sheet.getRow(j); // 行
                    if (row != null) {
                        if (firstRow == null) { // 第一个非空行作为首行
                            firstRow = row;
                            secondRow = sheet.getRow(j + 1);
                            index = handleFieldMaps(firstRow, secondRow, fieldTypeMap, fieldIndexMap, sb);
                            baseInsert = sb.toString();
                            sb.setLength(0);
                            tmp++;
                            continue;
                        }
                        if (tmp++ < 2) {
                            continue;
                        }
                        sb.append(baseInsert);
                        int columns = firstRow.getPhysicalNumberOfCells(); // 总列数
                        for (int k = index; k < columns + index; k++) {
                            Cell cell = row.getCell(k); // 单元格
                            if (null == cell) {
                                continue;
                            }
                            String type = fieldTypeMap.get(fieldIndexMap.get(k)).toLowerCase();
                            boolean isCharType = false;
                            if (type.contains("char") || type.contains("text") || type.contains("date") || type.contains("time")) { // 常用类型
                                isCharType = true;
                            }
                            switch (cell.getCellType()) {
                                case HSSFCell.CELL_TYPE_NUMERIC:
                                    short format = cell.getCellStyle().getDataFormat();  
                                    if(format == 14 || format == 31 || format == 57 || format == 58){   //excel中的时间格式  
                                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");    
                                        double value = cell.getNumericCellValue();    
                                        Date date = DateUtil.getJavaDate(value);  
                                        sb.append("'" + sdf.format(date) + "',");
                                    } else if (HSSFDateUtil.isCellDateFormatted(cell)) {  // 判断当前的cell是否为Date
                                        //先注释日期类型的转换，在实际测试中发现HSSFDateUtil.isCellDateFormatted(cell)只识别2014/02/02这种格式。  
                                        // 如果是Date类型则，取得该Cell的Date值           
                                        // 对2014-02-02格式识别不出是日期格式  
                                        Date date = cell.getDateCellValue();  
                                        DateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
                                        sb.append("'" + formater.format(date) + "',");
                                    } else { // 如果是纯数字  
                                        // 取得当前Cell的数值  
                                        sb.append(NumberToTextConverter.toText(cell.getNumericCellValue()) + ",");
                                    } 
                                    break;
                                case HSSFCell.CELL_TYPE_STRING:
                                    sb.append("'" + cell.getStringCellValue() + "',");
                                    break;
                                case  HSSFCell.CELL_TYPE_BLANK: 
                                    sb.append(isCharType ? "''," : "0,"); // 数字类型默认0，char类类型默认''
                                    break;
                                default:
                                    break;
                            }
                            // if (isCharType) {
                            // sb.append("'" + cell.getStringCellValue() + "',");
                            // } else {
                            // sb.append(cell.getStringCellValue() + ",");
                            // }
                            // System.out.println(cell.getStringCellValue());
                            
                        }
                        if (sb.toString().endsWith(",")) {
                            sb.setLength(sb.length() - 1);
                        }
                        out.write(sb.append(");\r\n").toString());
                        sb.setLength(0);
                    }
                }
                fieldIndexMap.clear();
                fieldTypeMap.clear();
                
                break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // IO流关闭处理
            try {
                if (null != in) {
                    in.close();
                }
                if (null != out) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        return "true";
    }
    
    /** TODO 获取字段和对应类型的map，如keyid字段对应varchar；
     *       获取字段对应的列位置.
     * @return 第一个字段出现的列.
     */
    private static int handleFieldMaps(Row firstRow, Row secondRow, Map<String, String> fieldTypeMap, Map<Integer, String> fieldIndexMap, StringBuffer sb) {
        int cells = firstRow.getPhysicalNumberOfCells();
        int index = 0;
        for (int i = 0; i < cells + 1; i++) {
            Cell fieldCell = firstRow.getCell(i);
            if (fieldCell == null) {
                index++;
                continue;
            }
            Cell typeCell = secondRow.getCell(i);
            fieldIndexMap.put(i, fieldCell.getStringCellValue());
            fieldTypeMap.put(fieldCell.getStringCellValue(), typeCell.getStringCellValue());
            sb.append(fieldCell.getStringCellValue()).append(",");
        }
        if (sb.toString().endsWith(",")) {
            sb.setLength(sb.length() - 1);
        }
        sb.append(") VALUES(");
        return index;
    }
}
