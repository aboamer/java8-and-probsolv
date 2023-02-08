package com.java8.optionals;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.java8.optionals.helperclasses.jackson.Book;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Copyright (c) Jumia.
 */
class JacksonTests {

    @Test
    void serialize_whenOptional_weirdResponse() throws JsonProcessingException {

        Book book = new Book();
        book.setTitle("Oliver Twist");
        book.setSubTitle(Optional.of("The Parish Boy's Progress"));

        String result = new ObjectMapper().writeValueAsString(book);

        System.out.println(result);
    }

    @Test
    public void givenFieldWithValue_whenDeserializing_thenThrowException() {

        String bookJson = "{ \"title\": \"Oliver Twist\", \"subTitle\": \"foo\" }";

        Assertions.assertThrows(JsonMappingException.class, () -> new ObjectMapper().readValue(bookJson, Book.class));
    }

    // What we want, is for Jackson to treat an empty Optional as null, and to treat a present Optional as a field representing its value.
    // this problem has been solved for us. Jackson has a set of modules that deal with JDK 8 datatypes, including Optional.
    // https://github.com/FasterXML/jackson-modules-java8

    @Test
    void serialize_whenOptional_fixWeirdResponse() throws JsonProcessingException {

        Book book = new Book();
        book.setTitle("Oliver Twist");
        book.setSubTitle(Optional.of("The Parish Boy's Progress"));

        String serializedBook = getMapper().writeValueAsString(book);

        System.out.println(serializedBook);

//        assertThat(from(serializedBook).getString("subTitle"))
//                .isEqualTo("The Parish Boy's Progress");

        // serialize null

        book.setSubTitle(Optional.empty());
        serializedBook = getMapper().writeValueAsString(book);

        System.out.println(serializedBook);
    }

    @Test
    public void givenFieldWithValue_whenDeserializing_thenSuccess() throws JsonProcessingException {

        String bookJson = "{ \"title\": \"Oliver Twist\", \"subTitle\": \"The Parish Boy's Progress\" }";

        Book newBook = getMapper().readValue(bookJson, Book.class);

        assertThat(newBook.getSubTitle()).isEqualTo(Optional.of("The Parish Boy's Progress"));
    }

    private static ObjectMapper getMapper() {

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new Jdk8Module());

        return mapper;
    }
}
