package com.random.grind75;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Copyright (c) Jumia.
 */
public class ValidParentheses {

    public boolean isValid(String s) {

        Map<Character, Character> par = new HashMap<>();
        initializeParMap(par);

        Stack<Character> parStack = new Stack<>();

        for(Character c : s.toCharArray()) {

            if(parStack.isEmpty() && !isValidToPush(c)) {

                return false;
            }

            if(!parStack.isEmpty() && isValidToPush(par.get(parStack.peek())) && !isValidToPush(c) && par.get(parStack.peek()) != c) {

                return false;
            }

            if(!parStack.isEmpty() && par.get(parStack.peek()) == c) {

                parStack.pop();
            }
            else if (isValidToPush(c)) {
                parStack.push(c);
            }
            else
                return false;
        }

        return parStack.isEmpty();

    }

    void initializeParMap(Map<Character, Character> par) {

        par.put('{', '}');
        par.put('[', ']');
        par.put('(', ')');
    }

    boolean isValidToPush(Character c) {

        return c == '{' || c == '[' || c == '(';
    }
}
