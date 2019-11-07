package com.cydercode.ing.client.login;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class LoginRequest {
    private String pwdhash;
    private String login;
    private String di;
}