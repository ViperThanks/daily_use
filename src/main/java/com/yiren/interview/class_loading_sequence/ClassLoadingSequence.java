package com.yiren.interview.class_loading_sequence;

import com.yiren.common.Interfaces.Consequence;
import com.yiren.interview.class_loading_sequence.pojo.SonClass;

/**
 * author  : wl
 * email   : vieper0714@outlook.com
 * date     : 2023/2/14 22:31
 * desc     :
 */
public class ClassLoadingSequence implements Consequence{
    public static void main(String[] args) {
        new SonClass();
    }

    @Override
    public void consequence() {
        System.out.println(666);
    }
}
