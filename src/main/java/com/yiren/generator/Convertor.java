package com.yiren.generator;

import java.util.Arrays;

/**
 * author  : wl
 * email   : vieper0714@outlook.com
 * date     : 2023/2/9 18:42
 * desc     :
 * simplify my code
 */
public class Convertor {
    public static int[] Integers2ints(Integer[] arr) {
        return Arrays.stream(arr).
                mapToInt(Integer::intValue).
                toArray();
    }

    public static double[] Doubles2doubles(Double[] arr) {
        return Arrays.stream(arr).
                mapToDouble(Double::doubleValue).
                toArray();
    }

    public static long[] Longs2longs(Long[] arr){
        return Arrays.stream(arr).
                mapToLong(Long::longValue).
                toArray();
    }
}
