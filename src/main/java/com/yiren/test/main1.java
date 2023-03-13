package com.yiren.test;

import com.yiren.generator.ArrayGenerator;

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

        Integer[] array = ArrayGenerator.aCustomizedArray(10, Integer[].class,
                () -> ThreadLocalRandom.current().nextInt(10)
        );
        System.out.println("array : \n" + Arrays.toString(array));
        Double[] doubles = ArrayGenerator.aArray(Double[].class);
        System.out.println("doubles : \n" + Arrays.toString(doubles));
        System.out.println("Generator.aArray() : \n" + Arrays.toString(ArrayGenerator.aArray()));

    }
}
