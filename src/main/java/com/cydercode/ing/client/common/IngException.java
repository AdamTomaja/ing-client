package com.cydercode.ing.client.common;

import lombok.Getter;

@Getter
public class IngException extends Exception {

    private final IngResponse response;

    public IngException(IngResponse response) {
        super(String.format("ING Error response: %s - %s", response.getCode(), response.getMsg()));
        this.response = response;
    }
}
