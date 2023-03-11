package com.yiren.generator;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.concurrent.ThreadLocalRandom;

public class ObjectFactory {

    public static <T> T createObject(Class<T> clazz) throws Exception {
        T object = clazz.newInstance();

        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            Class<?> type = field.getType();

            Object value = getRandomValue(type);
            field.set(object, value);
        }

        return object;
    }

    private static Object getRandomValue(Class<?> type) {
        if (type == int.class || type == Integer.class) {
            return ThreadLocalRandom.current().nextInt();
        } else if (type == long.class || type == Long.class) {
            return ThreadLocalRandom.current().nextLong();
        } else if (type == float.class || type == Float.class) {
            return ThreadLocalRandom.current().nextFloat();
        } else if (type == double.class || type == Double.class) {
            return ThreadLocalRandom.current().nextDouble();
        } else if (type == boolean.class || type == Boolean.class) {
            return ThreadLocalRandom.current().nextBoolean();
        } else if (type == String.class) {
            return generateRandomString(10);
        } else {
            throw new IllegalArgumentException("Unsupported type: " + type);
        }
    }

    private static String generateRandomString(int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            char c = (char) ThreadLocalRandom.current().nextInt(97, 123);
            sb.append(c);
        }
        return sb.toString();
    }
}

