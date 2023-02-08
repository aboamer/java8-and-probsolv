package com.java8.predicates;

import java.util.stream.Stream;

/**
 * Copyright (c) Jumia.
 */
public class UsingPeek {

    public static void main(String args[]) {

        Stream.iterate(1, (Integer n) -> n + 1)
                .peek(n -> System.out.println("number generated: - " + n))
                .filter(n -> (n % 2 == 0))
                .peek(n -> System.out.println("Even number filter passed for - " + n))
                .limit(5)
                .count();
    }
}
