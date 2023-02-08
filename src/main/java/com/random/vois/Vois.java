package com.random.vois;

import java.util.List;

/**
 * Copyright (c) Jumia.
 */
public class Vois {

    static int findSmallestDistance(List<Integer> numbers) {

        int min = Integer.MAX_VALUE;

        for (int i = 0; i < numbers.size(); i++) {
            for (int j = 0; j < numbers.size(); j++) {

                if (i != j) {

                    int currentDiff = numbers.get(i) - numbers.get(j);

                    if (min > Math.abs(currentDiff)) {

                        min = Math.abs(currentDiff);
                    }
                }

            }
        }

        return min;
    }

    public static void main(String[] args) {

        List<Integer> numbers = List.of(-2, 9, 55, 0, 15, 99, 7, 98, 65, 105);

        System.out.println(findSmallestDistance(numbers));
    }
}
