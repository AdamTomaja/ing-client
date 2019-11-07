package com.cydercode.ing.client.checklogin;

import lombok.*;

@Data
public class CheckLoginResponseData {

    private String key;
    private String mask;
    private String mobimask;
    private String salt;
}
