package com.java8.generics.genericvsnongeneric;

import java.io.Serializable;

/**
 * Copyright (c) Jumia.
 */
public class ClassWorkingOnSomeClass {

    public Serializable performAction(final Serializable instance) {

        // do some staff

        return instance;
    }

    public <T extends Serializable> T performActionGeneric(final T instance) {

        // do some staff

        return instance;
    }

    public void testToShowWhyGenericIsUseful() {

        final SomeClass instance = new SomeClass();

        // Please notice a necessary type cast required

        final SomeClass modifiedInstance = (SomeClass) performAction(instance);


        final SomeClass instanceGen = new SomeClass();

        final SomeClass modifiedInstanceGen = performActionGeneric(instance);
    }
}
