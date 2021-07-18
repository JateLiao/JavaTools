package com.liaoshijie.tools.jdkstudy.function;

import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

/**
 * @author liaoshijie@jojoreading.com
 * @since 2021/7/18 下午1:25
 */
public class ConsumerTest {
    /**
     * main method.
     **/
    public static void main(String[] args) {
        Consumer<String> consumer = new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println("consumer: " + s);
            }
        };

        String s = "test";
        consumer.accept(s);
        List<String> list = Collections.singletonList(s);
        list.forEach(consumer);
    }
}
