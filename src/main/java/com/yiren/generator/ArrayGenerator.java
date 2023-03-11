package com.yiren.generator;

import java.lang.reflect.Array;
import java.util.function.Supplier;

/**
 * author  : wl
 * email   : vieper0714@outlook.com
 * date     : 2023/3/11 17:02
 * desc     :
 */
public class ArrayGenerator {

    //public static <T> T[] generate(int length, Supplier<T> supplier) {
    //    T[] array = (T[]) Array.newInstance(Object.class, length);
    //    for (int i = 0; i < length; i++) {
    //        array[i] = supplier.get();
    //    }
    //    return array;
    //}

    @SuppressWarnings("unchecked")
    public static <T> T[] generate(int length, Supplier<T> supplier) {
        T[] array = (T[]) Array.newInstance(Object.class, length);
        for (int i = 0; i < length; i++) {
            Object obj = supplier.get();
            if (obj != null && !array.getClass().getComponentType().isAssignableFrom(obj.getClass())) {
                throw new IllegalArgumentException("Supplied object is not an instance of the component type of the array");
            }
            array[i] = (T) obj;
        }
        return array;
    }
}
