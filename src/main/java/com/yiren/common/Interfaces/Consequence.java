package com.yiren.common.Interfaces;

/**
 * author  : wl
 * email   : vieper0714@outlook.com
 * date     : 2023/2/14 22:49
 * desc     :
 */
public interface Consequence {

    public void consequence();

    default void showConsequence(){
        consequence();
    }


}
