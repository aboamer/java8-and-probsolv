package com.java8.generics;

import java.util.Collection;

/**
 * Copyright (c) Jumia.
 */
public class TestGenericTypeErasure {

    // 'sort(Collection<String>)' clashes with 'sort(Collection<Double>)'; both methods have same erasure
//    void sort(Collection<String> strings) {
//
//// Some implementation over strings heres
//    }

    void sort(Collection<Double> numbers) {
// Some implementation over numbers here
    }

    /**
     *
     *
     the following don't compile
     */
//    public <T> void action(final T action) {
//        if (action instanceof T) {
//// Do something here
//        }
//    }
//
//    public <T> void action(final T action) {
//        if (T.class.isAssignableFrom(Number.class)) {
//// Do something here
//        }
//    }
}
