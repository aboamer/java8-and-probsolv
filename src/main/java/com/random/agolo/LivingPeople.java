package com.random.agolo;

/**
 * Copyright (c) Jumia.
 */
public class LivingPeople {

    public static Integer findYearWithMostLivingPeople( Integer[] birthYears, Integer[] deathYears ) {

        Integer yearWithMostLivingPeople = birthYears[0];
        Integer livingMinusDead = 0;
        Integer maxLivingMinusDead = 0;
        Integer deadPointer = 0;

        for(int i = 0; i < birthYears.length; i++) {

            if(birthYears[i] <= deathYears[deadPointer])
                livingMinusDead++;
            else
                deadPointer++;

            if(livingMinusDead > maxLivingMinusDead) {
                maxLivingMinusDead = livingMinusDead;
                yearWithMostLivingPeople = birthYears[i];
            }
        }

        return yearWithMostLivingPeople;
    }
}
