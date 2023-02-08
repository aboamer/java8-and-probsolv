package com.jvm.performance;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (c) Jumia.
 */
public class Example {

    public static void main(String[] args) {

        List<Integer> list = new ArrayList<>();
        int i = 0;

        while (true) {

            list.add(3);
            if (i > 1000) {

                list = new ArrayList<>();
                i = 0;
            }
            i++;
        }
    }
}
