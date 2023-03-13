package com.yiren.test;

import com.yiren.algo.AlgoUtils;
import com.yiren.interview.class_loading_sequence.ClassLoadingSequence;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * author  : wl
 * email   : vieper0714@outlook.com
 * date     : 2023/2/12 15:24
 * desc     :
 */
public class Test1 {

    @Test
    void tst() {



    }

    public void tett(List<Integer> o){
        o.add(3);
        o.clear();
        o = null;
        System.gc();
    }

    @Test
    void test11() {
        ClassLoadingSequence.main(null);
    }

    @Test
    public void test001() {
        int N = 10010;
        int k = 3;
        for (int i = 0; i <= N; i++) {
            for (int j = i; j <= N; j++) {
                if ((j - i) % k == 0){
                    System.out.println(j % k == i % k);
                }
            }
        }


    }

    @Test
    public void test01() {
        AlgoUtils.swap(new int[]{1,2},0,1);

    }



}
