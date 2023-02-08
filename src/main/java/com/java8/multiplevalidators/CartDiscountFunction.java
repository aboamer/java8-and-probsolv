package com.java8.multiplevalidators;

import java.util.function.Function;

/**
 * Copyright (c) Jumia.
 */
public class CartDiscountFunction {

    static Function<Cart, Double> DISCOUNT_15 = cart -> cart.getBillAmount()*0.15;
    static Function<Cart, Double> DISCOUNT_25 = cart -> cart.getBillAmount()*0.25;
    static Function<Cart, Double> DISCOUNT_05 = cart -> cart.getBillAmount()*0.05;
}
