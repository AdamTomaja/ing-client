package com.cydercode.ing.client.login;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class LoginRequest {
    private String pwdhash;
    private String login;
    private String di;
}