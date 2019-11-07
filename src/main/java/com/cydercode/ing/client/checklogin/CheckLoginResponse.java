package com.cydercode.ing.client.checklogin;

import lombok.Data;

@Data
public class CheckLoginResponse {

    private String status;
    private CheckLoginResponseData data;

}
