package com.java8.optionals.helperclasses;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Optional;

/**
 * Copyright (c) Jumia.
 */
@Getter
@Setter
@NoArgsConstructor
public class Sock implements Serializable {

    private static final long serialVersionUID = 1L;

    Integer size = 0;
    // you must initialize to have the exception thrown for test: testSerializable_whenOptional_throwsNotSerializableException
    Optional<Sock> pair = Optional.empty();
}
