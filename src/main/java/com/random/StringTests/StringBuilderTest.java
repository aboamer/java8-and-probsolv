package com.random.StringTests;

/**
 * Copyright (c) Jumia.
 */
public class StringBuilderTest {

    public static void main(String[] args) {

        String a = "11";
        String b = "1";

        char[] aChars = a.toCharArray();
        char[] bChars = b.toCharArray();

        if (aChars.length < bChars.length) {

            StringBuilder sb = new StringBuilder(a);

            for (int i = aChars.length; i < bChars.length; i++) {

                sb.insert(0, '0');
            }
            aChars = sb.toString().toCharArray();
        } else if (bChars.length < aChars.length) {

            StringBuilder sb = new StringBuilder(b);

            for (int i = bChars.length; i < aChars.length; i++) {

                sb.insert(0, '0');
            }
            System.out.println(sb.toString());
            bChars = sb.toString().toCharArray();
        }
    }
}
