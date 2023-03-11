package com.yiren.generator;


import com.yiren.common.Utils.StringUtils;
import com.yiren.exception.AlgoException;
import com.yiren.exception.Error;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;


/**
 * Author : wl
 * Email  : vieper0714@outlook.com
 * Date   : 2022/9/7 0:52
 * Description :  In order to maximize the generating of such as ListNode which we always use
 * I would like to that will improve my coding abilities
 * :)  <- smile
 */
@SuppressWarnings({"unused"})
public final class Generator {

    private Generator() {
        throw new AlgoException("No Generator instances for you!");
    }

    //默认随机阈值 即最大值 最小值
    private static final Object DEFAULT_RANDOM_VALUE_MAX = 10;
    private static final Object DEFAULT_RANDOM_VALUE_MIN = 0;
    private static final Integer DEFAULT_RANDOM_ROUND_NUM = 10;

    /**
     * 默认类型
     */
    private static final Class<Integer[]> DEFAULT_TYPE_CLASS = Integer[].class;

    /**
     * 字母表 常量
     */
    private static final char[] ALPHABET =

            {
                    'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
                    'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',

                    'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
                    'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',

                    '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'
            };


    //要反射的 支持类
    private static final Class<GeneratorSupport> aClass = GeneratorSupport.class;

    /**
     * 支持的类型(暂时)及其基本类型
     */
    private static final Class<?>[] SupportClass =

            {
                    Integer[].class,
                    Double[].class,
                    Long[].class,
            };


    /**
     * 验证参数的完整性 min 必须小于 max size 必须大于 0
     */
    private static void validateParameter(int min, int max, int size) {
        if (min > max)
            throw new AlgoException(Error.PARAMETER,"min greater than max ");
        if (size <= 0)
            throw new AlgoException(Error.PARAMETER, "size less than or equal zero");
    }

    //验证参数的完整性
    private static void validateParameter(Object min, Object max, int size, Class<?> type) {

        if (min == null || max == null)
            throw new AlgoException(Error.PARAMETER,"min or max is null !");

        if (StringUtils.isEmpty(String.valueOf(min)) || StringUtils.isEmpty(String.valueOf(max)) || type == null)
            throw new NullPointerException();

        if (new BigDecimal(String.valueOf(min)).compareTo(new BigDecimal(String.valueOf(max))) >= 0)
            throw new AlgoException(Error.PARAMETER, "min greater than max");

        if (size <= 0)
            throw new AlgoException(Error.PARAMETER, "size less than or equal zero");

        if (!type.isArray())
            throw new AlgoException(Error.PARAMETER, "type {" + type.getSimpleName() + "} is not array class!!!");
    }

    //简单的重载
    public static Integer[] aArray() {
        return randomArrayGenerator(DEFAULT_RANDOM_VALUE_MIN, DEFAULT_RANDOM_VALUE_MAX,
                DEFAULT_RANDOM_ROUND_NUM, DEFAULT_TYPE_CLASS);
    }

    //me too  😅:)
    public static Integer[] aArray(int size) {
        return randomArrayGenerator(DEFAULT_RANDOM_VALUE_MIN, DEFAULT_RANDOM_VALUE_MAX,
                size, DEFAULT_TYPE_CLASS);
    }


    public static <T> T aArray(Class<? extends T> type) {
        return randomArrayGenerator(DEFAULT_RANDOM_VALUE_MIN, DEFAULT_RANDOM_VALUE_MAX,
                DEFAULT_RANDOM_ROUND_NUM, type);
    }

    public static Integer[] aArray(int max, int size) {
        return randomArrayGenerator(DEFAULT_RANDOM_VALUE_MIN, max,
                size, DEFAULT_TYPE_CLASS);
    }

    public static Integer[] aArray(int min, int max, int size) {
        return randomArrayGenerator(min, max,
                size, DEFAULT_TYPE_CLASS);
    }

    //me too  😅:)
    public static <T> T aArray(int size, Class<? extends T> type) {
        return randomArrayGenerator(DEFAULT_RANDOM_VALUE_MIN, DEFAULT_RANDOM_VALUE_MAX,
                size, type);
    }

    //me too  😅:)
    public static <T> T aArray(Object max, int size, Class<? extends T> type) {
        return randomArrayGenerator(DEFAULT_RANDOM_VALUE_MIN, max, size, type);
    }

    //me too  :)
    public static <T> T aArray(Object min, Object max, int size, Class<? extends T> type) {
        return randomArrayGenerator(min, max, size, type);
    }

    @SuppressWarnings("unchecked")
    public static <T> T[] aCustomizedArray(int size, Class<? extends T[]> clazz, Supplier<T> supplier) {
        if (clazz == null || supplier == null || size < 0)
            throw new AlgoException(Error.PARAMETER,"fuck u!!");

        T[] array;
        try {
            array = (T[]) Array.newInstance(Class.forName(getBaseTypeName(clazz)), size);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        for (int i = 0; i < size; i++) {
            array[i] = supplier.get();
        }
        return array;
    }

    @SuppressWarnings("rawtypes")
    private static <T> T randomArrayGenerator(Object min, Object max, int size, Class<? extends T> type) {
        validateParameter(min, max, size, type);
        StringBuilder err_msg = new StringBuilder();
        for (Class aClass : SupportClass) {
            if (Objects.equals(type, aClass)) {
                return dynamicArrayGenerator(min, max, size, type);
            } else if (getBoxedTypeName(aClass).toLowerCase().contains(getBaseTypeName(type))) {
                return dynamicBaseArrayGenerator(min, max, size, type, aClass);
            }
            err_msg.append(getBoxedTypeName(aClass)).append("[].class ");
        }
        err_msg.append("and their primitive type's class ");
        throw new AlgoException(Error.PARAMETER,"type only contain " + err_msg);
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    private static <T> T dynamicBaseArrayGenerator(Object min, Object max, int size,
                                                   Class<? extends T> type, Class<?> boxedClass) {
        String typeName = getBoxedTypeName(boxedClass);
        String methodName = "random" + typeName + "s";

        T ans;
        try {
            Class paraTypeClass = Class.forName("java.lang." + typeName);

            min = paraTypeClass.getDeclaredConstructor(String.class).newInstance(String.valueOf(min));
            max = paraTypeClass.getDeclaredConstructor(String.class).newInstance(String.valueOf(max));

            ans = (T) aClass
                    .getDeclaredMethod(methodName, paraTypeClass, paraTypeClass, Integer.class)
                    .invoke(null, min, max, size);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException | ClassNotFoundException |
                 InstantiationException e) {
            if (e instanceof InvocationTargetException) {
                throw new AlgoException(Error.PARAMETER,"min and max not instanceof type or");
            }
            throw new RuntimeException(e);
        }
        return ans;

    }

    /**
     * Warning : I cannot promise that the data returned will be random enough ,
     * because I'm simply using third-party API({@linkplain ThreadLocalRandom#current()})
     * at the Underlying
     *
     * @param min  生成目标数组的最小值
     * @param max  生成目标数组的最大值
     * @param size 生成目标数组的长度
     * @param type 需要什么样的类型数组
     * @param <T>  parameter e.g : Integer[].class
     * @return 目标数组
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    private static <T> T dynamicArrayGenerator(Object min, Object max, int size, Class<? extends T> type) {

        String typeName = getBoxedTypeName(type);
        String methodName = "random" + typeName + "Array";

        T ans;
        try {
            Class paraTypeClass = Class.forName("java.lang." + typeName);

            min = paraTypeClass.getDeclaredConstructor(String.class).newInstance(min + "");
            max = paraTypeClass.getDeclaredConstructor(String.class).newInstance(max + "");

            ans = (T) aClass
                    .getDeclaredMethod(methodName, paraTypeClass, paraTypeClass, Integer.class)
                    .invoke(null, min, max, size);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException | ClassNotFoundException |
                 InstantiationException e) {
            throw new RuntimeException(e);
        }
        return ans;

    }


    private static String getBoxedTypeName(Class<?> type) {
        String s = type.getTypeName();
        int a;
        return s.substring(s.lastIndexOf('.') + 1, (a = s.lastIndexOf('[')) < 0 ? s.length() : a);
    }

    private static String getBaseTypeName(Class<?> type) {
        String a;
        return (a = type.getTypeName()).substring(0, a.indexOf('['));
    }


    public static String lowerLetters(int size) {
        return randomStringGenerator(0, 26, size, false);
    }

    public static String upperLetters(int size) {
        return randomStringGenerator(26, 52, size, false);
    }

    public static String aDigits(int size) {
        return randomStringGenerator(52, 62, size, false);
    }


    public static String aString(int size) {
        return aString(size, false);
    }

    public static String aString(int size, boolean isAscii) {
        return randomStringGenerator(0, isAscii ? 128 : 62, size, isAscii);
    }

    /**
     * @param fromIndex 起始
     * @param endIndex  末尾
     * @param size      长度
     * @param isAscii   是否为ASCII码
     * @return 随机字符串
     */
    private static String randomStringGenerator(int fromIndex, int endIndex, int size, boolean isAscii) {
        validateParameter(fromIndex, endIndex, size);
        StringBuilder sb = new StringBuilder();
        if (isAscii)
            for (int i = 0; i < size; i++)
                sb.append((char) aInteger(fromIndex, endIndex));
        else
            for (int i = 0; i < size; i++)
                sb.append(ALPHABET[aInteger(fromIndex, endIndex)]);
        return sb.toString();
    }

    private static final String ZERO = "0";

    public static int aInteger(Integer min, Integer max) {
        return GeneratorSupport.randomInteger(min, max);
    }

    public static int aInteger(Integer max) {
        return GeneratorSupport.randomInteger(Integer.parseInt(ZERO), max);
    }

    public static long aLong(Long min, Long max) {
        return GeneratorSupport.randomLong(min, max);
    }

    public static long aLong(Long max) {
        return GeneratorSupport.randomLong(Long.parseLong(ZERO), max);
    }

    public static double aDouble(Double min, Double max) {
        return GeneratorSupport.randomDouble(min, max);
    }

    public static double aDouble(Double max) {
        return GeneratorSupport.randomDouble(Double.parseDouble(ZERO), max);
    }


    private static final class GeneratorSupport {

        public static int[] randomIntegers(Integer min, Integer max, Integer size) {
            return Arrays.stream(randomIntegerArray(min, max, size))
                    .mapToInt(Integer::intValue)
                    .toArray();
        }

        public static double[] randomDoubles(Double min, Double max, Integer size) {
            return Arrays.stream(randomDoubleArray(min, max, size))
                    .mapToDouble(Double::doubleValue)
                    .toArray();
        }

        public static long[] randomLongs(Long min, Long max, Integer size) {
            return Arrays.stream(randomLongArray(min, max, size))
                    .mapToLong(Long::longValue)
                    .toArray();
        }


        public static Integer[] randomIntegerArray(Integer min, Integer max, Integer size) {
            Integer[] ints = new Integer[size];

            for (int i = 0; i < ints.length; i++) {
                ints[i] = randomInteger(min, max);
            }

            return ints;
        }

        public static Double[] randomDoubleArray(Double min, Double max, Integer size) {
            Double[] doubles = new Double[size];

            for (int i = 0; i < doubles.length; i++) {
                doubles[i] = randomDouble(min, max);
            }

            return doubles;
        }

        public static Long[] randomLongArray(Long min, Long max, Integer size) {
            Long[] longs = new Long[size];

            for (int i = 0; i < longs.length; i++) {
                longs[i] = randomLong(min, max);
            }

            return longs;
        }

        private static int randomInteger(int min, int max) {
            return ThreadLocalRandom.current().nextInt(min, max);
        }

        private static double randomDouble(double min, double max) {
            return ThreadLocalRandom.current().nextDouble(min, max);
        }

        private static long randomLong(long min, long max) {
            return ThreadLocalRandom.current().nextLong(min, max);
        }
    }


}

