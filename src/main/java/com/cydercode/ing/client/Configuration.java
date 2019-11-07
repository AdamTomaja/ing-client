package com.cydercode.ing.client;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Configuration {

    private String url;
    private String login;
    private String password;

}
