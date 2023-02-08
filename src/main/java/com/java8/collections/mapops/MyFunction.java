package com.java8.collections.mapops;

import java.util.function.Function;

/**
 * Copyright (c) Jumia.
 */
public class MyFunction implements Function<String, String> {

    @Override
    public String apply(String t) {
        return t + "zz";
    }
}
