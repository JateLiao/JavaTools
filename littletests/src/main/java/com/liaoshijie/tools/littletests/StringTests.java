package com.liaoshijie.tools.littletests;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.UUID;

/**
 * @author liaoshijie@jojoreading.com
 * @since 2021/5/24 上午10:42
 */
public class StringTests {
    /**
     * main method.
     **/
    public static void main(String[] args) {
        testSplit();
    }

    public static void testReplace1() throws UnsupportedEncodingException {
        String templates = "你说你是谁：{{temp_name}}!!!";
        System.out.println(templates.replace("{{temp_name}}", UUID.randomUUID().toString()));

        // url:
        String promotionLink = "//mall.tinman.cn/item/detail?linkCode=xxx";
        if (!promotionLink.startsWith("http") && promotionLink.startsWith("//")) {
            promotionLink = "https:" + promotionLink;
        }
        System.out.println(URLEncoder.encode(promotionLink, "UTF-8"));
    }

    public static void testSplit() {
        String s = "123,234 456，789";
        String[] arr = s.split("[ ,，]");
        Arrays.stream(arr).forEach(System.out::println);
    }
}
