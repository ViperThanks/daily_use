package com.yiren.test;

import com.yiren.exception.AlgoException;
import com.yiren.generator.ObjectFactory;

/**
 * author  : wl
 * email   : vieper0714@outlook.com
 * date     : 2023/3/10 19:50
 * desc     :
 */
public class Test2 {
    public static void main(String[] args) throws Exception {
        User user = ObjectFactory.createObject(User.class);
        System.out.println(" user : " + user);

    }


}


class A{
    public Object aaa() throws RuntimeException {
        return new Object();
    }
}
class B extends A{

    public String aaa() throws IndexOutOfBoundsException{
        return "super.aaa()";
    }
}
