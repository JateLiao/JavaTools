package com.liaoshijie.tools.littletests;

import java.util.Optional;

/**
 * @author liaoshijie@jojoreading.com
 * @since 2021/3/26 上午11:11
 */
public class OptionalTest {

    /**
     * main method.
     **/
    public static void main(String[] args) {
        Optional optional = Optional.ofNullable(null);
        System.out.println(optional.isPresent());
    }
}
