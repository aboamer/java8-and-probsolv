package com.java8.optionals.helperclasses;

import lombok.Getter;
import lombok.Setter;

/**
 * Copyright (c) Jumia.
 */
@Getter
@Setter
public class Modem {

    private Double price;

    public Modem(Double price) {
        this.price = price;
    }
}
