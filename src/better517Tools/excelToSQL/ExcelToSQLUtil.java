/*
 * 文件名：ExcelToSQLUtil.java
 * 版权：Copyright 2007-2017 Liao Shijie Tech. Co. Ltd. All Rights Reserved. 
 * 描述： ExcelToSQLUtil.java
 * 修改人：KOBE
 * 修改时间：2017年3月7日
 * 修改内容：新增
 */
package better517Tools.excelToSQL;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import util.CommonCheckUtils;
import util.DateUtils;

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
        System.out.println("\nDone!");
    }

    /**
     * TODO 导出insert文本.
     * 
     * excel格式，首行字段，第二行类型，第三行开始是数据(见模板).
     * excel单元格格式需要设置为文本
     * 
     * 2017.5.18:
     *  尽量兼容更多格式，可以一次指定需要处理的多个Sheet
     *  
     * 2017.5.21:
     *  为了便面数据量太大造成内存溢出，下一步：
     *      1.逐句打印不存文件；
     *      2.当积累一定量后就追加写入，然后继续处理，及时释放内存;
     *      3.一定量后就先存储进一个文件。
     * 
     * @param path
     * @return
     */
    @SuppressWarnings("unused")
    public static String toInsertSql(String path) {
        long start = System.currentTimeMillis();
        path = "E:/tianzhong(田仲)/工作文档/09.本地工作文档/酒店/2017-05-19-国际酒店/导数据/20170521/Sheet.xlsx"; 
        String targetSheet = "HotelRoomCN0"; // 要处理的表格，该变量指定值之后就只处理该表格
        String basePathName = "D:/Test/sql/" + DateUtils.format(new Date(), "");

        File file = new File(basePathName);
        if (!file.exists()) {
            file.mkdirs();
        }
        
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
                int allCount = 0;
                int tmp = 0;
                Sheet sheet = book.getSheetAt(i); // 表格
                String tableName = sheet.getSheetName(); // 表格名作为插表名
                if ((null != targetSheet && !"".equals(targetSheet)) && !targetSheet.equals(tableName)) {
                    continue;
                }
                System.out.println("当前处理Sheet: " + tableName);
                // String baseInsert = "";
                out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(basePathName + tableName + ".txt", false)));
                Row firstRow = null; // 首行字段
                Row secondRow = null; //sheet.getRow(1); // 次行类型
                int rows = sheet.getPhysicalNumberOfRows(); // 获取所有行数
                int index = 0;
                StringBuffer sb = new StringBuffer();
                for (int j = 0; j < rows; j++) {
                    sb.setLength(0); // 清空
                    Row row = sheet.getRow(j); // 行
                    if (row != null) {
                        if (firstRow == null) { // 第一个非空行作为首行
                            firstRow = row;
                            secondRow = sheet.getRow(j + 1);
                            index = handleFieldMaps(firstRow, secondRow, fieldTypeMap, fieldIndexMap, sb);
                            // baseInsert = sb.toString();
                            sb.setLength(0);
                            tmp++;
                            continue;
                        }
                        if (tmp++ < 2) {
                            continue;
                        }
                        allCount++;
                        sb.append("INSERT INTO " + tableName + "(");
                        // sb.append(baseInsert);
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
                            
                            String val = "";
                            switch (cell.getCellType()) {
                                case HSSFCell.CELL_TYPE_NUMERIC:
                                    val = String.valueOf(Double.valueOf(String.valueOf(cell.getNumericCellValue())).intValue());
                                    break;
                                case HSSFCell.CELL_TYPE_STRING:
                                    val = cell.getStringCellValue();
                                    break;
                                case HSSFCell.CELL_TYPE_FORMULA:
                                    val = cell.getCellFormula();
                                    break;
                                default:
                                    val = cell.getStringCellValue();
                                    break;
                            }
                            val = CommonCheckUtils.isNotEmpty(val) ? val.trim() : "";
                            // if (val.contains("'")) {
                            // System.out.println(val.replaceAll("'", "\\\\'"));
                            // }
                            val = val.replaceAll("'", "\\\\'").replaceAll("###", "").replace("\r\n", "");
                            
                            if (isCharType) {
                                sb.append("'" + val + "',");
                            } else {
                                sb.append(val + ",");
                            }
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
                System.err.println("表格" + tableName + "共生成INSERT SQL条数：" + allCount);
                // break;
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
        System.out.println("总耗时：" + (System.currentTimeMillis() - start));
        return "true";
    }
    
    /** TODO 获取字段和对应类型的map，如keyid字段对应varchar；
     *       获取字段对应的列位置.
     * @return 第一个字段出现的列.
     */
    private static int handleFieldMaps(Row firstRow, Row secondRow, Map<String, String> fieldTypeMap, Map<Integer, String> fieldIndexMap, StringBuffer sb) {
        int cells = firstRow.getPhysicalNumberOfCells();
        int index = 0;
        boolean overFirst = false;
        for (int i = 0; i < cells + index; i++) {
            Cell fieldCell = firstRow.getCell(i);
            if (fieldCell == null && !overFirst) {
                index++;
                continue;
            } else if (fieldCell == null && overFirst) {
                break;
            }
            overFirst = true;
            Cell typeCell = secondRow.getCell(i);
            fieldIndexMap.put(i, fieldCell.getStringCellValue());
            try {
                System.out.println(fieldCell.getStringCellValue());
                fieldTypeMap.put(fieldCell.getStringCellValue(), typeCell.getStringCellValue());
            } catch (Exception e) {
                e.printStackTrace();
               }
            sb.append(fieldCell.getStringCellValue()).append(",");
        }
        if (sb.toString().endsWith(",")) {
            sb.setLength(sb.length() - 1);
        }
        sb.append(") VALUES(");
        return index;
    }
}
