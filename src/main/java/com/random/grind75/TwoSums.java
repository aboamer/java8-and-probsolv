package com.random.grind75;

import java.util.HashMap;
import java.util.Map;

/**
 * Copyright (c) Jumia.
 */
public class TwoSums {

    public static int[] twoSum(int[] nums, int target) {

        int[] arr = new int[2];
        Map<Integer, Integer> subtracts = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            subtracts.put(target - nums[i], i);
        }

        for (int i = 0; i < nums.length; i++) {
            if (subtracts.containsKey(nums[i]) && subtracts.get(nums[i]) != i) {
                arr[0] = i;
                arr[1] = subtracts.get(nums[i]);
                return arr;
            }
        }

        return arr;
    }

    public static void main(String[] args) {

        twoSum(new int[]{2, 7, 11, 15}, 9);
    }

}
