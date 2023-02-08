package com.java8.multiplevalidators;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Copyright (c) Jumia.
 */
public class CartRules {

    private static Map<Predicate, Function> cartRules = new HashMap<>();

    // added by me
    private static Map<Predicate, String> whatToPrint = new HashMap<>();

    static {
        cartRules.put(CartPredicateRule.CART_NEW, CartDiscountFunction.DISCOUNT_05);
        cartRules.put(CartPredicateRule.CART_SILVER, CartDiscountFunction.DISCOUNT_15);
        cartRules.put(CartPredicateRule.CART_GOLD, CartDiscountFunction.DISCOUNT_25);

        whatToPrint.put(CartPredicateRule.CART_NEW, "this is new");
        whatToPrint.put(CartPredicateRule.CART_SILVER, "this is silver");
        whatToPrint.put(CartPredicateRule.CART_GOLD, "this is gold");
    }

    public static Function getRuleFor(Cart cart) {

        return cartRules.entrySet()
                .stream()
                .filter(rule -> rule.getKey().test(cart))
                .map(entry -> entry.getValue())
                .findFirst()
                .get();
    }

    public static String printFor(Cart cart) {

        return whatToPrint.entrySet()
                .stream()
                .filter(rule -> rule.getKey().test(cart))
                .map(entry -> entry.getValue())
                .findFirst()
                .get();
    }
}
