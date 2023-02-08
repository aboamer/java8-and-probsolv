package com.java8.multiplevalidators;

import java.util.function.Predicate;

/**
 * Copyright (c) Jumia.
 */
public class CartPredicateRule {

    static Predicate<Cart> CART_SILVER = cart -> cart.getUserType().equals("SILVER");
    static Predicate<Cart> CART_GOLD = cart -> cart.getUserType().equals("GOLD");
    static Predicate<Cart> CART_NEW = cart -> cart.getUserType().equals("NEW");
}
