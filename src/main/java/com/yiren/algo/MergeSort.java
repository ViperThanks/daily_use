package com.yiren.algo;

import java.util.Arrays;

/**
 * author  : wl
 * email   : vieper0714@outlook.com
 * date     : 2023/2/9 18:53
 * desc     : 归并排序 先递归再排序
 */
public class MergeSort {

    public static void main(String[] args) {
        int[] arr = AlgoUtils.getIntArr(20);
        System.out.println(Arrays.toString(arr));
        merge_sort(arr,0,arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    public static void merge_sort(int[] arr, int l, int r) {
        if (l >= r) return;
        int mid = l + r >> 1;

        merge_sort(arr, l, mid);
        merge_sort(arr, mid + 1, r);

        int[] tmp = new int[r - l + 1];
        int k = 0, i = l, j = mid + 1;

        while (i <= mid && j <= r) {
            if (arr[i] <= arr[j])
                tmp[k++] = arr[i++];
            else
                tmp[k++] = arr[j++];
        }

        while (i <= mid)
            tmp[k++] = arr[i++];
        while (j <= r)
            tmp[k++] = arr[j++];

        for (i = l, j = 0; i <= r; )
            arr[i++] = tmp[j++];


    }
}
