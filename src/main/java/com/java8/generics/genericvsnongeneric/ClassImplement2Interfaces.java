package com.java8.generics.genericvsnongeneric;

import java.io.Serializable;

/**
 * Copyright (c) Jumia.
 */
public class ClassImplement2Interfaces implements Serializable, Runnable {
    @Override
    public void run() {

        System.out.println("any");
    }
}
