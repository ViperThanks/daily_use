package com.yiren.algo;

import java.util.Arrays;

/**
 * author  : wl
 * email   : vieper0714@outlook.com
 * date     : 2023/2/9 18:15
 * desc     :
 * 快速排序
 * 原理 : 随机挑选一个基准元素 并且偏移左右指针,  遍历数组 , 一次遍历后 ,左边元素比基准元素小 ,右边反之, 递归这个步骤
 * 直到排序完成
 */
public class QuickSort {

    public static void main(String[] args) {

        int[] ints = AlgoUtils.getIntArr(20);
        System.out.println("随机生成20个数 : \n" + Arrays.toString(ints));
        quick_sort(ints, 0, ints.length - 1);
        System.out.println("\n快排后的结果 : \n" + Arrays.toString(ints));

    }


    public static void quick_sort(int[] arr, int l, int r) {
        if (l >= r) return;
        int x = arr[l + r >> 1], i = l - 1, j = r + 1;
        while (i < j) {
            do i++; while (arr[i] < x);
            do j--; while (arr[j] > x);
            if (i < j)
                AlgoUtils.swap(arr, i, j);
        }
        quick_sort(arr, l, j);
        quick_sort(arr, j + 1, r);
    }
}
