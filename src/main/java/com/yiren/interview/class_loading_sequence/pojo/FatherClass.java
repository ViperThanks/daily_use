package com.yiren.interview.class_loading_sequence.pojo;

/**
 * author  : wl
 * email   : vieper0714@outlook.com
 * date     : 2023/2/14 22:12
 * desc     :
 */
@SuppressWarnings("all")
public class FatherClass {

    private byte age;
    private String name;

    static String className = FatherClass.class.getSimpleName();

    static {
        System.out.println(className + "'s static code block was running");
    }

    static void staticCode(){
        System.out.println(className + "'s static method was running");
    }

    {
        System.out.println(className + "'s normal code block was running");
    }

    void normalCode(){
        System.out.println(className + "'s normal method was running");
    }

    public FatherClass() {
        System.out.println(className + "'s construction was running without parameter");
    }



}
