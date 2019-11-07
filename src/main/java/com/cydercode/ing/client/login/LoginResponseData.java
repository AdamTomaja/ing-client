package com.cydercode.ing.client.login;

import lombok.Data;

@Data
public class LoginResponseData {

    @Data
    public static class Settings {

        private String name;
        private String lastBadLogin;
        private String lastLogged;

    }

    private String token;
    private Settings data;
}
