package com.cydercode.ing.client;

import com.cydercode.ing.client.checklogin.CheckLoginResponseData;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PasswordEncoderTest {

    @Test
    void shouldCreatePasswordHash() {
        // given
        PasswordEncoder passwordEncoder = new PasswordEncoder();
        CheckLoginResponseData data = new CheckLoginResponseData();
        data.setMask("+++*+**+");
        data.setSalt("asdiuh213a");
        data.setKey("keykeykey");

        // when
        String encoded = passwordEncoder.createPwdHash("password", data);

        // then
        assertEquals("08999e5176296d7d1036ed9f61493182d66c77c3", encoded);
    }
}