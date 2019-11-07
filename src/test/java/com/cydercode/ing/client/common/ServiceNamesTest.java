package com.cydercode.ing.client.common;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ServiceNamesTest {

    @Test
    void shouldContainsActualNames() {
        assertAll(
                () -> assertEquals("renchecklogin", ServiceNames.RENCHECKLOGIN),
                () -> assertEquals("rengetfury", ServiceNames.RENGETFURY),
                () -> assertEquals("renlogin", ServiceNames.RENLOGIN)
        );
    }
}