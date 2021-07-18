package com.liaoshijie.tools.jdkstudy.function;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @author liaoshijie@jojoreading.com
 * @since 2021/7/18 下午1:32
 */
public class FunctionTest {
    /**
     * main method.
     **/
    public static void main(String[] args) {
        Function<String, Integer> function = Integer::valueOf;
        System.out.println(function.apply("12333"));

        Consumer<Integer> consumer = System.out::println;

        List<String> list = Arrays.asList("434", "6992", "3196");
        for (String s : list) {
            consumer.accept(function.apply(s));
        }
    }
}
