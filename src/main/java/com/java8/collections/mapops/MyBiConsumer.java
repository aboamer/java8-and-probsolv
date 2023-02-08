package com.java8.collections.mapops;

import java.util.function.BiConsumer;

/**
 * Copyright (c) Jumia.
 */
public class MyBiConsumer implements BiConsumer<String, String> {

    @Override
    public void accept(String s, String s2) {
        System.out.println("Key = " + s);
        System.out.println("Processing on value = " + s2);
    }
}
