package com.liaoshijie.tools.tinman.luban;

import com.liaoshijie.tools.common.utils.HttpUtils;

import java.io.IOException;

/**
 * @author liaoshijie@jojoreading.com
 * @since 2021/6/24 下午3:56
 */
public class UserMatchModify {

    private static final String URL = "https://api.xjjj.co/api/luban-um/test/modifyMatch?wxId=%s&userId=%s";

    /**
     * main method.
     **/
    public static void main(String[] args) throws IOException {
        String msg = "12167941----zyj15233074286\n" +
                "12060603----terryterrymiao\n" +
                "1387245----li8660\n" +
                "11864525----zengming0129\n" +
                "7895074----applifish\n" +
                "11324592----wxid_39q6bdb2alft11\n" +
                "11599867----boey951\n" +
                "11690930----liyuanw1987\n" +
                "11488498----zhengxiaoxiao131417\n" +
                "10904634----Emma_liu672\n" +
                "11697555----qq30972714\n" +
                "12003581----wxid_e5e5diw0m75n22";
        String[] arr = msg.split("\n");
        boolean uidFirst = true;
        for (int i = 0; i < arr.length; i++) {
            String s = arr[i];
            String[] arr2 = s.split("----");
            String url = uidFirst ? String.format(URL, arr2[1], arr2[0]) : String.format(URL, arr2[0], arr2[1]);
            System.out.println(url);
            String response = HttpUtils.post(url, "", null);
            if (!"success".equals(response)) {
                System.out.println("异常了：" + url);
            }
        }
    }
}
