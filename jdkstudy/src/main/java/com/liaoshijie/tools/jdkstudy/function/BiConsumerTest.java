package com.liaoshijie.tools.jdkstudy.function;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

/**
 * @author liaoshijie@jojoreading.com
 * @since 2021/7/17 上午11:46
 */
public class BiConsumerTest {

    /**
     * main method.
     **/
    public static void main(String[] args) {
        BiConsumer<String, String> consumer = (s, s2) -> System.out.println(String.format("consumer: %s_%s", s, s2));

        Map<String, String> map = new HashMap<>();
        String s = "天王盖地虎";
        String s2 = "宝塔镇河妖";
        map.put(s, s2);
        map.forEach(consumer);
    }

}
