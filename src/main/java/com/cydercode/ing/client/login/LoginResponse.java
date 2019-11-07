package com.cydercode.ing.client.login;

import com.cydercode.ing.client.common.IngResponse;
import lombok.Data;

@Data
public class LoginResponse implements IngResponse {

    private Status status;
    private String code;
    private String msg;
    private LoginResponseData data;
}
