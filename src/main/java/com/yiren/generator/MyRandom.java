package com.yiren.generator;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * author  : wl
 * email   : vieper0714@outlook.com
 * date     : 2023/2/28 11:47
 * desc     :
 */
@SuppressWarnings("unused")
@Deprecated
public class MyRandom {

    static final Random DEFAULT_RANDOM_CLASS = ThreadLocalRandom.current();
    private Random randomInstance = DEFAULT_RANDOM_CLASS;

    public MyRandom(Random randomInstance) {
        this.randomInstance = randomInstance;
    }

    public Random getRandomInstance() {
        return randomInstance;
    }

    public void setRandomInstance(Random randomInstance) {
        this.randomInstance = randomInstance;
    }

    private int randomInteger(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max);
    }

    private double randomDouble(double min, double max) {
        return ThreadLocalRandom.current().nextDouble(min, max);
    }

    private long randomLong(long min, long max) {
        return ThreadLocalRandom.current().nextLong(min, max);
    }

}
