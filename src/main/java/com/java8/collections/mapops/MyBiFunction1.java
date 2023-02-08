package com.java8.collections.mapops;

import java.util.function.BiFunction;

/**
 * Copyright (c) Jumia.
 */
public class MyBiFunction1 implements BiFunction<String, String, String> {

    @Override
    public String apply(String t, String u) {
        return t + u;
    }
}
