package com.cydercode.ing.client.common;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class IngRequest {

    private String token;
    private String trace;
    private Object data;
    private String locale;

}
