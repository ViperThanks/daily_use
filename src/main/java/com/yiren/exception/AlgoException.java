package com.yiren.exception;

/**
 * author  : wl
 * email   : vieper0714@outlook.com
 * date     : 2023/2/9 18:38
 * desc     :
 */

public class AlgoException extends RuntimeException {

    public AlgoException(String message) {
        super(message);
    }

    public AlgoException(Error error,String cause){
        super("occurred a " + error.name().toLowerCase() + " error \n -> because : " + error.name().toLowerCase() + " " + cause);
    }


}

