package com.java8.collections.mapops;

import java.util.function.BiFunction;

/**
 * Copyright (c) Jumia.
 */
public class MyBiFunction implements BiFunction<String, String, String> {

    @Override
    public String apply(String t, String u) {
        if (t != null)
            return t + u;
        else
            return u;
    }
}