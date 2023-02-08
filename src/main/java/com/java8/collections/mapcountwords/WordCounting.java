package com.java8.collections.mapcountwords;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Copyright (c) Jumia.
 */
public class WordCounting {

    public static void main(String[] args) {

        countUsingNormalMap();
        countUsingPutIfAbsent();
        countUsingComputeIfPresent();
        countUsingCompute();
        countUsingMerge();
        countUsingComputeIfAbsent();

    }

    static List<String> getWords() {

        return List.of("Foo", "Bar", "Foo", "Buzz", "Foo", "Buzz", "Fizz", "Fizz");
    }

    static void countUsingNormalMap() {

        Map<String, Integer> wordsCount = new HashMap<>();

        getWords().forEach(word -> {
            Integer previous = wordsCount.get(word);
            if(previous == null) {
                wordsCount.put(word, 1);
            }
            else {
                wordsCount.put(word, previous + 1);
            }
        });
        System.out.println("countUsingNormalMap");
        System.out.println(wordsCount);
        System.out.println();
    }

    static void countUsingPutIfAbsent() {

        Map<String, Integer> wordsCount = new HashMap<>();

        getWords().forEach(word -> {
            wordsCount.putIfAbsent(word,0);
            wordsCount.put(word, wordsCount.get(word) + 1);
        });
        System.out.println("countUsingPutIfAbsent");
        System.out.println(wordsCount);
        System.out.println();
    }

    static void countUsingComputeIfPresent() {      // this will work only when we have at least one entry for a given key

        Map<String, Integer> wordsCount = new HashMap<>();

        getWords().forEach(word -> {
            wordsCount.putIfAbsent(word,0);
            wordsCount.computeIfPresent(word, (wordKey, previous) -> previous + 1);
        });
        System.out.println("countUsingComputeIfPresent");
        System.out.println(wordsCount);
        System.out.println();
    }

    static void countUsingCompute() {

        Map<String, Integer> wordsCount = new HashMap<>();

        getWords().forEach(word -> {
            wordsCount.compute(word, (wordKey, previous) -> previous != null? previous + 1 : 1);
        });
        System.out.println("countUsingCompute");
        System.out.println(wordsCount);
        System.out.println();
    }

    static void countUsingMerge() {

        Map<String, Integer> wordsCount = new HashMap<>();

        getWords().forEach(word -> {
            wordsCount.merge(word, 1, (previous, one) -> previous + one);
        });
        System.out.println("countUsingMerge");
        System.out.println(wordsCount);
        System.out.println();
    }

    static void countUsingComputeIfAbsent() {       // this will not work at all - it will compute only one time when key is absent

        Map<String, Integer> wordsCount = new HashMap<>();

        getWords().forEach(word -> {
//            wordsCount.putIfAbsent(word,0);
            wordsCount.computeIfAbsent(word, (wordKey) -> wordsCount.get(wordKey) == null ? 1 :wordsCount.get(wordKey) + 1);
        });
        System.out.println("countUsingComputeIfAbsent");
        System.out.println(wordsCount);
        System.out.println();
    }
}
