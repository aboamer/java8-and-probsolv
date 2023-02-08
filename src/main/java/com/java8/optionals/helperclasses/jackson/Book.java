package com.java8.optionals.helperclasses.jackson;

import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

/**
 * Copyright (c) Jumia.
 */
@Getter
@Setter
public class Book {

    String title;
    Optional<String> subTitle;
}
