/*
 * 文件名：extractDataToDoseen.java
 * 版权：Copyright 2007-2017 517na Tech. Co. Ltd. All Rights Reserved.
 * 描述： extractDataToDoseen.java
 * 修改人：tianzhong
 * 修改时间：2017年7月24日
 * 修改内容：新增
 */
package com.liaoshijie.better517tools.extractDataToDossen;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 * extract data for dossen.
 *
 * @author tianzhong
 */
public class extractDataToDoseen {
    public static void main(String[] args) {
        extractData();
    }

    public static void extractData() {
        String path = "E://tianzhong(田仲)//工作文档//09.本地工作文档//酒店//2017-07-21-协议酒店-提数据//提数据//sms.txt";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            String line = null;
            while ((line = reader.readLine()) != null) {
                line = line.replaceAll(" ", "");
                if (line.contains("$$")) {
                    String[] arr = line.split("\\$\\$");
                    if (arr[0].contains("，")) {
                        String[] nameArr = arr[0].split("，");
                        if (nameArr[0].contains("差旅壹号") || nameArr[0].length() > 10) {
                            continue;
                        }
                        System.out.println(nameArr[0] + "\t" + arr[1]);
                    }
                }
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
