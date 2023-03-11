package com.yiren.test;

import java.util.Scanner;

/**
 * author  : wl
 * email   : vieper0714@outlook.com
 * date     : 2023/2/15 21:07
 * desc     :
 */
public class main1 {

    static int n;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        char[] s = sc.next().toCharArray();
        int res = 0;
        for (int i = 0; i < s.length; i ++){
            if (s[i] == 'x'){
                int cnt = 0;
                int j = i;
                while(j < s.length && s[j] == 'x'){
                    cnt ++;
                    j ++;
                }
                if (cnt >= 3) {
                    res += cnt - 2;
                }
                if (i != j) i = j;
            }
        }
        System.out.println(res);

    }
}
