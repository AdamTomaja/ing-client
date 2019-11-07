package com.cydercode.ing.client.login;

import lombok.Data;

@Data
public class LoginResponse {

    private String status;
    private String code;
    private String msg;
    private LoginResponseData data;
}
