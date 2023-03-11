package com.yiren.test;

import com.yiren.generator.Generator;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

/**
 * author  : wl
 * email   : vieper0714@outlook.com
 * date     : 2023/2/15 21:07
 * desc     :
 */
public class main1 {

    static int n;
    public static void main(String[] args) {
        Integer[] integers = Generator.aCustomizedArray(10, Integer[].class, () -> ThreadLocalRandom.current().nextInt(100));
        System.out.println(Arrays.toString(integers));
        String[] strings = Generator.aCustomizedArray(100, String[].class, () -> "hello");
        System.out.println(Arrays.toString(strings));
    }
}
