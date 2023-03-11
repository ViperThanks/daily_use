package com.yiren.algo;

import com.yiren.generator.Convertor;
import com.yiren.generator.Generator;

/**
 * author  : wl
 * email   : vieper0714@outlook.com
 * date     : 2023/2/9 18:28
 * desc     :
 * 算法的基本函数 for example : swap 交换函数
 */
public class AlgoUtils {

    /**
     * 简单的交换函数 by temporary integer
     *
     * @param a 数组
     * @param i 需要交换的值
     * @param j 需要交换的值
     */
    public static void swap(int[] a, int i, int j) {
        int x = a[i];
        a[i] = a[j];
        a[j] = x;
    }

    public static void safeSwap(int[] a, int i, int j) {
        if (a == null || i == j)
            return;
        if ((i < 0 || i > a.length - 1) || (j < 0 || j > a.length - 1))
            return;
        swap(a, i, j);
    }

    /**
     * 基于   {@linkplain Generator#aArray(int)} 封装的方法
     *
     * @param size 数组长度
     * @return 返回 长度为 size 的 随机  (interface -> {@link java.util.concurrent.ThreadLocalRandom}) int 数组
     */
    public static int[] getIntArr(int size) {
        return Convertor.Integers2ints(Generator.aArray(size));

    }

    public static int[] getIntArr(int max, int size) {
        return Convertor.Integers2ints(Generator.aArray(max, size));
    }

    public static int[] getIntArr(int min, int max, int size) {
        return Convertor.Integers2ints(Generator.aArray(min, max, size));
    }
}
