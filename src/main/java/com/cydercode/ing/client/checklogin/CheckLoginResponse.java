package com.cydercode.ing.client.checklogin;

import com.cydercode.ing.client.common.IngResponse;
import lombok.Data;

@Data
public class CheckLoginResponse implements IngResponse {

    private Status status;
    private String msg;
    private String code;
    private CheckLoginResponseData data;

}
