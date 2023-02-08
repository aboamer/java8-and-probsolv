package com.java8.interfaces.defaults;

/**
 * Copyright (c) Jumia.
 */
public interface MyData {

    default void print(String str) {
        if (!isNull(str))
            System.out.println("MyData Print::" + str);
    }

    // cannot be overridden unless it is changed to default
    static boolean isNull(String str) {
        System.out.println("Interface Null Check");

        return str == null ? true : "".equals(str) ? true : false;
    }
}
