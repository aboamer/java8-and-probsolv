package com.java8.collections.mapops;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * Copyright (c) Jumia.
 */
public class HashMapOps {

    public static void main(String[] args) {

        testForEach();
        testReplaceAll();
        testComputeIfAbsent();
        testComputeIfPresent();
        testCompute();
        testMerge();
    }

    static void testForEach() {

        System.out.println("testForEach");

        Map<String, String> map = new HashMap<>();
        map.put("1", "13");
        map.put("2", null);
        map.put(null, "100");

        BiConsumer<String, String> action = new MyBiConsumer();
        map.forEach(action);

        System.out.println("\nHashMap forEach lambda example\n");
        map.forEach((k, v) -> {
            System.out.println("Key = " + k + ", Value = " + v);
        });
        System.out.println();
    }

    static void testReplaceAll() {

        System.out.println("testReplaceAll");
        Map<String, String> map = new HashMap<>();
        map.put("1", "13");
        map.put("2", "27");
        map.put(null, "100");

        System.out.println("map before replaceAll = " + map);
        BiFunction<String, String, String> function = new MyBiFunction();
        map.replaceAll(function);
        System.out.println("map after replaceAll = " + map);

        // replaceAll using lambda expressions
        map.replaceAll((k, v) -> {
            if (k != null) return k + v;
            else return v;
        });
        System.out.println("map after replaceAll lambda expression = " + map);
        System.out.println();
    }

    static void testComputeIfAbsent() {

        System.out.println("testComputeIfAbsent");
        Map<String, String> map = new HashMap<>();
        map.put("1", "13");
        map.put("2", "27");
        map.put(null, "100");
        map.put("18", null);

        System.out.println("map before ComputeIfAbsent = " + map);

        Function<String, String> function = new MyFunction();
        map.computeIfAbsent("3", function); //key not present
        map.computeIfAbsent("2", function); //key already present - will not have effect
        map.computeIfAbsent("1", v -> {     //key already present - will not have effect
            return null;
        });

        map.computeIfAbsent("18", function);

        //lambda way
        map.computeIfAbsent("4", v -> {
            return v + "1";
        });
        map.computeIfAbsent("5", v -> { //null value won't get inserted
            return null;
        });
        map.computeIfAbsent("8", v -> {
            return v + "1";
        });
        map.computeIfAbsent("7", v -> {
            return "15" + "1";
        });

        map.put("10", null);
        System.out.println(map);
        System.out.println();
    }

    // If the function returns null, the mapping is removed.
    static void testComputeIfPresent() {

        System.out.println("testComputeIfPresent");
        Map<String, String> map = new HashMap<>();
        map.put("1", "13");
        map.put("2", "27");
        map.put(null, "100");
        map.put("10", null);

        System.out.println("map before computeIfPresent = " + map);

        BiFunction<String, String, String> function = new MyBiFunction1();
        for (String key : map.keySet()) {
            map.computeIfPresent(key, function);
        }

        System.out.println("map after computeIfPresent = " + map);
        map.computeIfPresent("1", (k, v) -> {
            return null;
        }); // mapping will be removed
        map.computeIfPresent("15", (k, v) -> {
            return k + v;
        });
        map.computeIfPresent("2", (k, v) -> {
            return k + v;
        });
        System.out.println("map after computeIfPresent = " + map);
        System.out.println();
    }

    static void testCompute() {

        System.out.println("testCompute");
        Map<String, String> map = new HashMap<>();
        map.put("1", "13");
        map.put("2", "27");
        map.put(null, "10");
        map.put("10", null);

        System.out.println("map before compute = " + map);

        for (String key : map.keySet()) {
            map.compute(key, (k, v) -> {
                return k + v;
            });
        }
        map.compute("5", (k, v) -> {
            return k + v;
        }); //key not present, v = null
        map.compute("1", (k, v) -> {        // will be removed
            return null;
        });
        map.compute("8", (k, v) -> {        // will not be inserted
            return null;
        });
        System.out.println("map after compute = " + map);
        System.out.println();
    }

    static void testMerge() {

        System.out.println("testMerge");
        Map<String, String> map = new HashMap<>();
        map.put("1", "13");
        map.put("2", "27");
        map.put(null, "10");
        map.put("10", null);

        System.out.println("map before merge = " + map);

        for (Map.Entry<String, String> entry : map.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            //merge throws NullPointerException if key or value is null
            if (key != null && value != null)
                map.merge(entry.getKey(), "xx",
                        (k, v) -> {
                            return k + v;
                        });
        }
        System.out.println(map);

        map.merge("5", "53", (k, v) -> {
            return k + v;
        }); // key not present
        System.out.println(map);

        map.merge("1", "1", (k, v) -> {
            return null;
        }); // method return null, so remove

        map.merge("10", "8000", (k, v) -> {
            return k + v;
        });
        map.merge("20", "8000", (k, v) -> {
            return null;
        });
        // the following throws NPE
//        map.merge("30", null, (k, v) -> {
//            return k + v;
//        });

        System.out.println(map);
    }
}
