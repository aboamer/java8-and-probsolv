package com.java8.multiplevalidators;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Copyright (c) Jumia.
 */
public class RefactorIfElseLadder {

    public static void main(String[] args) {

        Cart cart1 = new Cart("NEW", 1000);
        Cart cart2 = new Cart("SILVER", 1000);
        Cart cart3 = new Cart("GOLD", 1000);
        List<Cart> carts = Arrays.asList(cart1, cart2, cart3);

        //Calculate Discount using if else based ladder
        carts.forEach(cart -> {

            System.out.println("For UserType " + cart.getUserType() + " " +
                    "Discount:  " + RefactorIfElseLadder.calculateDiscount(cart));
        });

        String type = "SILVER";

        System.out.println("this is result ------------ " + CartRules.printFor(cart2));

        //Calculate Discount using functional approach
        carts.forEach(cart -> {

            Object discount = CartRules.getRuleFor(cart).apply(cart);
            System.out.println("For UserType " + cart.getUserType() + " " + "Discount:  " + discount);
        });
    }

    public static double calculateDiscount(Cart cart) {

        if (cart.getUserType().equals("SILVER")) {
            //15%
            return cart.getBillAmount() * 0.15;
        } else if (cart.getUserType().equals("GOLD")) {
            //25%
            return cart.getBillAmount() * 0.25;
        } else if (cart.getUserType().equals("NEW")) {
            //5%
            return cart.getBillAmount() * 0.05;
        } else {
            //2%
            return cart.getBillAmount() * 0.02;
        }
    }
}
