package com.liaoshijie.tools.jdkstudy.function;

import java.util.Random;
import java.util.function.Supplier;

/**
 * @author liaoshijie@jojoreading.com
 * @since 2021/7/17 上午11:58
 */
public class SupplierTest {
    /**
     * main method.
     **/
    public static void main(String[] args) {
        // Supplier作为容器，获取一个元素
        Supplier<Emp> supplier = Emp::new;
        Emp emp = supplier.get();
        emp.setName("xxxx");
        System.out.println(emp.name);

        Supplier<Integer> s2 = () -> new Random().nextInt();
        System.out.println(s2.get());

        Supplier<Double> s3 = Math::random;
        System.out.println(s3.get());
    }

    public static void show(String s) {
        System.out.println(s);
    }

    public static class Emp {
        private String name;

        public Emp() {

        }

        public Emp(String name) {
            super();
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

    }
}
