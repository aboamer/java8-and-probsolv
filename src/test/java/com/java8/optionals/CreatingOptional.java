package com.java8.optionals;

import com.java8.optionals.helperclasses.Modem;
import com.java8.optionals.helperclasses.Sock;
import com.java8.optionals.helperclasses.Person;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.NotSerializableException;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Copyright (c) amer.
 */
class CreatingOptional {

    @Test
    public void whenCreatesEmptyOptional_thenCorrect() {

        Optional<String> empty = Optional.empty();
        assertFalse(empty.isPresent());
        assertTrue(empty.isEmpty());
    }

    @Test
    public void givenNonNull_whenCreatesNonNullable_thenCorrect() {

        String name = "baeldung";
        Optional<String> opt = Optional.of(name);
        assertTrue(opt.isPresent());
    }

    @Test
    public void givenNull_whenThrowsErrorOnCreate_thenCorrect() {

        String name = null;
        assertThrows(NullPointerException.class, () -> Optional.of(name));
    }

    @Test
    public void givenNull_whenCreatesNullable_thenCorrect() {

        String name = null;
        Optional<String> opt = Optional.ofNullable(name);
        assertFalse(opt.isPresent());
    }

    @Test
    public void givenOptional_whenIfPresentWorks_thenCorrect() {

        // instead of
        // if(name != null) {
        //    System.out.println(name.length());
        //}

        Optional<String> opt = Optional.of("baeldung");
        opt.ifPresent(name -> System.out.println("length is " + name.length()));

        Optional<String> optNull = Optional.ofNullable(null);
        optNull.ifPresent(name -> System.out.println("this is not printed" + name.length()));
    }

    @Test
    public void whenOrElseWorks_thenCorrect() {

        String nullName = null;
        String name = Optional.ofNullable(nullName).orElse("john");
        assertEquals("john", name);
    }

    @Test
    public void whenOrElseGetWorks_thenCorrect() {

        String nullName = null;
        String name = Optional.ofNullable(nullName).orElseGet(() -> "john");
        assertEquals("john", name);
    }

    // orElse vs orElseGet

    // when a method such as getMyDefault() has to make a web service call or even query a database, the cost becomes very obvious.

    @Test
    public void whenOrElseGetAndOrElseDiffer_thenCorrect() {

        String text = "Text present";

        System.out.println("Using orElseGet:");
        String defaultText
                = Optional.ofNullable(text).orElseGet(this::getMyDefault);
        assertEquals("Text present", defaultText);

        System.out.println("Using orElse:");
        defaultText = Optional.ofNullable(text).orElse(getMyDefault());
        assertEquals("Text present", defaultText);
    }

    private String getMyDefault() {

        System.out.println("Getting Default Value");
        return "Default Value";
    }

    @Test
    public void whenOrElseThrowWorks_thenCorrect() {

        String nullName = null;
        assertThrows(IllegalArgumentException.class, () -> Optional.ofNullable(nullName).orElseThrow(IllegalArgumentException::new));

        assertThrows(NoSuchElementException.class, () -> Optional.ofNullable(nullName).orElseThrow());
    }

    @Test
    public void givenOptionalWithNull_whenGetThrowsException_thenCorrect() {

        Optional<String> opt = Optional.ofNullable(null);
        assertThrows(NoSuchElementException.class, () -> opt.get());
    }

    // filter --> takes a predicate as an argument and returns an Optional object
    @Test
    public void whenOptionalFilterWorks_thenCorrect() {

        Integer year = 2016;
        Optional<Integer> yearOptional = Optional.of(year);
        boolean is2016 = yearOptional.filter(y -> y == 2016).isPresent();
        assertTrue(is2016);
        boolean is2017 = yearOptional.filter(y -> y == 2017).isPresent();
        assertFalse(is2017);
    }

    // no java 8 optionals = ugly code
    private boolean priceIsInRange1(Modem modem) {

        boolean isInRange = false;

        if (modem != null && modem.getPrice() != null
                && (modem.getPrice() >= 10
                && modem.getPrice() <= 15)) {

            isInRange = true;
        }
        return isInRange;
    }

    // using filter with map is much better
    public boolean priceIsInRange2(Modem modem2) {

        return Optional.ofNullable(modem2)
                .map(Modem::getPrice)
                .filter(p -> p >= 10)
                .filter(p -> p <= 15)
                .isPresent();
    }

    @Test
    public void whenFiltersWithoutOptional_thenCorrect() {
        assertTrue(priceIsInRange1(new Modem(10.0)));
        assertFalse(priceIsInRange1(new Modem(9.9)));
        assertFalse(priceIsInRange1(new Modem(null)));
        assertFalse(priceIsInRange1(new Modem(15.5)));
        assertFalse(priceIsInRange1(null));
    }

    @Test
    public void whenFiltersWithOptional_thenCorrect() {
        assertTrue(priceIsInRange2(new Modem(10.0)));
        assertFalse(priceIsInRange2(new Modem(9.9)));
        assertFalse(priceIsInRange2(new Modem(null)));
        assertFalse(priceIsInRange2(new Modem(15.5)));
        assertFalse(priceIsInRange2(null));
    }

    // map
    @Test
    public void givenOptional_whenMapWorks_thenCorrect() {

        List<String> companyNames = Arrays.asList(
                "paypal", "oracle", "", "microsoft", "", "apple");
        Optional<List<String>> listOptional = Optional.of(companyNames);

        int size = listOptional
                .map(List::size)
                .orElse(0);
        assertEquals(6, size);
    }

    // map with filter - map to trim, filter to verify password check
    @Test
    public void givenOptional_whenMapWorksWithFilter_thenCorrect() {

        String password = " password ";
        Optional<String> passOptional = Optional.of(password);
        boolean correctPassword = passOptional.filter(
                pass -> pass.equals("password")).isPresent();
        assertFalse(correctPassword);

        correctPassword = passOptional
                .map(String::trim)
                .filter(pass -> pass.equals("password"))
                .isPresent();
        assertTrue(correctPassword);
    }

    // using map with nested optional is ugly - use flat map instead
    @Test
    public void givenOptional_whenMapWorksButBadPractice_thenCorrect2() {

        Person person = Person.builder().name("john").age(26).build();
        Optional<Person> personOptional = Optional.of(person);

        Optional<Optional<String>> nameOptionalWrapper
                = personOptional.map(Person::getName);
        Optional<String> nameOptional
                = nameOptionalWrapper.orElseThrow(IllegalArgumentException::new);
        String name1 = nameOptional.orElse("");
        assertEquals("john", name1);

        String name = personOptional
                .flatMap(Person::getName)
                .orElse("");
        assertEquals("john", name);
    }

    @Test
    public void givenOptional_whenFlatMapWorks_thenCorrect2() {

        Person person = Person.builder().name("john").age(26).build();
        Optional<Person> personOptional = Optional.of(person);

        String name = personOptional
                .flatMap(Person::getName)
                .orElse("");
        assertEquals("john", name);
    }

    // chaining optional - get first not-empty

    private Optional<String> getEmpty() {
        return Optional.empty();
    }

    private Optional<String> getHello() {
        return Optional.of("hello");
    }

    private Optional<String> getBye() {
        return Optional.of("bye");
    }

    @Test
    public void givenThreeOptionals_whenChaining_thenFirstNonEmptyIsReturned() {
        Optional<String> found = Stream.of(getEmpty(), getHello(), getBye())
                .filter(Optional::isPresent)
                .map(Optional::get)
                .findFirst();

        assertEquals(getHello(), found);
    }

    // downside of above method - all of our get methods are always executed, regardless of where a non-empty Optional appears in the Stream.

    // lazy evaluation --> put breakpoints to tes

    @Test
    public void givenThreeOptionals_whenChaining_thenFirstNonEmptyIsReturnedAndRestNotEvaluated() {
        Optional<String> found =
                Stream.<Supplier<Optional<String>>>of(this::getEmpty, this::getHello, this::getBye)
                        .map(Supplier::get)
                        .filter(Optional::isPresent)
                        .map(Optional::get)
                        .findFirst();

        assertEquals(getHello(), found);
    }

    // the same test case, but we pass arguments. So we use lambda expressions

    private Optional<String> createOptional(String input) {
        if (input == null || "".equals(input) || "empty".equals(input)) {
            return Optional.empty();
        }
        return Optional.of(input);
    }

    @Test
    public void givenTwoEmptyOptionals_whenChaining_thenDefaultIsReturned() {
        String found = Stream.<Supplier<Optional<String>>>of(
                        () -> createOptional("empty"),
                        () -> createOptional("amer"),
                        () -> createOptional("yoyo")
                )
                .map(Supplier::get)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .findFirst()
                .orElseGet(() -> "default");

        assertEquals("amer", found);
    }

    // java 9
    // or() method for providing a supplier that creates an alternative Optional
    // ifPresentOrElse() method that allows executing an action if the Optional is present or another action if not
    // stream() method for converting an Optional to a Stream
    // https://www.baeldung.com/java-9-optional

    // don't use optional in these cases:   https://www.baeldung.com/java-optional-return

    // The intent of Java when releasing Optional was to use it as a return type, thus indicating that a method could return an empty value.
    // It is misused when it is passed as a parameter
    // using Optional in a serializable class will result in a NotSerializableException

    @Test
    void testSerializable_whenOptional_throwsNotSerializableException() {

        Sock sock = new Sock();

        assertThrows(NotSerializableException.class, () -> new ObjectOutputStream(new ByteArrayOutputStream()).writeObject(sock));
    }
}
