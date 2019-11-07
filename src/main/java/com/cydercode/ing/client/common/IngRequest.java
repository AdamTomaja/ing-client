package com.cydercode.ing.client.common;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class IngRequest {

    private String token;
    private String trace;
    private Object data;
    private String locale;

}
