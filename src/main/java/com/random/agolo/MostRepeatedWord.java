package com.random.agolo;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Copyright (c) Jumia.
 */
public class MostRepeatedWord {

    public static String findMostRepeatedWord( String text ) {

        String[] wordsArr = text.split(" ");

        wordsArr = Arrays.stream(wordsArr).map(word -> word.replaceAll("^[^a-zA-Z]+$", "")).toArray(String[]::new);

        Map<String, Integer> wordsMap = new HashMap<>();

        for (String s : wordsArr) {

            wordsMap.merge(s, 1, Integer::sum);
        }

        String wordWithMaxOccurence = "";

        Integer maxOccurence = 0;

        for (Map.Entry<String, Integer> entry : wordsMap.entrySet()) {

            if(entry.getValue() > maxOccurence) {
                wordWithMaxOccurence = entry.getKey();
                maxOccurence = entry.getValue();
            }
        }

        return wordWithMaxOccurence;
    }

    public static void main(String[] args) {

        System.out.println(findMostRepeatedWord("this is this what we, should this is"));
    }
}
