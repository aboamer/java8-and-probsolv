package com.random.agolo;

import java.util.HashMap;
import java.util.Map;

/**
 * Copyright (c) Jumia.
 */
public class Anagram {

    public static Boolean isAnagram( String s1, String s2 ) {

        Map<Character, Integer> s1Map = new HashMap<>();

        Map<Character, Integer> s2Map = new HashMap<>();

        for (char c : s1.toCharArray()) {

            s1Map.merge(c, 1, Integer::sum);
        }

        for (char c : s2.toCharArray()) {

            s2Map.merge(c, 1, Integer::sum);
        }

        for (Map.Entry<Character, Integer> entry : s1Map.entrySet()) {

            if(!s2Map.get(entry.getKey()).equals(entry.getValue())) {

                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {

        System.out.println(isAnagram("axfa", "faax"));
    }
}
