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
        String msg = "923622----Stu_002 \n" +
                "6913786 ----T-040809  \n" +
                "8678213 ----hsqlcm\n" +
                "23097307----huangjiawei88\n" +
                "23246525----cml5211314";
        String[] arr = msg.split("\n");
        boolean uidFirst = true;
        for (int i = 0; i < arr.length; i++) {
            String s = arr[i].replaceAll(" ", "");
            String[] arr2 = s.split("----");
            String url = uidFirst ? String.format(URL, arr2[1], arr2[0]) : String.format(URL, arr2[0], arr2[1]);
            System.out.println(url);
            String response = HttpUtils.post(url, "", null);
            if (!"success".equals(response)) {
                System.err.println("异常了：" + url);
            }
        }
    }
}
