package com.java8.optionals.helperclasses;

import lombok.Builder;

import java.util.Optional;

/**
 * Copyright (c) Jumia.
 */
@Builder
public class Person {

    private String name;
    private int age;
    private String password;

    public Optional<String> getName() {
        return Optional.ofNullable(name);
    }

    public Optional<Integer> getAge() {
        return Optional.ofNullable(age);
    }

    public Optional<String> getPassword() {
        return Optional.ofNullable(password);
    }
}
