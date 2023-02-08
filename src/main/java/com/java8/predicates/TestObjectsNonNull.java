package com.java8.predicates;

import java.util.Objects;
import java.util.function.Predicate;

/**
 * Copyright (c) Jumia.
 */
public class TestObjectsNonNull {

    public static void main(String... args) {
        Predicate<Boolean> nonNull = Objects::nonNull;
        System.out.println(nonNull.test(true));
        System.out.println(nonNull.test(false));
        System.out.println(nonNull.negate().test(false));
    }
}
