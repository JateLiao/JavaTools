package com.liaoshijie.tools.jdkstudy.juc;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author liaoshijie@jojoreading.com
 * @since 2021/4/25 上午11:32
 */
public class AQS {
    /**
     * main method.
     **/
    public static void main(String[] args) {
        ReentrantLock reentrantLock = new ReentrantLock();
        reentrantLock.tryLock();
        reentrantLock.isFair();
        reentrantLock.unlock();
    }
}
