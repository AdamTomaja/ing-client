package com.cydercode.ing.client.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class IngRequest {

    private String token;
    private String trace;
    private Object data;
    private String locale;

}
