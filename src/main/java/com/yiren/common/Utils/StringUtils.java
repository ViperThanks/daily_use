package com.yiren.common.Utils;

/**
 * author  : wl
 * email   : vieper0714@outlook.com
 * date     : 2023/1/13 13:40
 * desc     :
 */
public class StringUtils {

    public static boolean isEmpty(String s) {
        if (s == null)
            return true;
        return s.length() == 0;
    }

    public static boolean equals(String s1, String s2) {
        if (s1 == null)
            return s2 == null;
        return s1.equals(s2);
    }
}
