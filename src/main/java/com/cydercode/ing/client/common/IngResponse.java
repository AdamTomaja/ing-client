package com.cydercode.ing.client.common;

public interface IngResponse {

    enum Status {
        ERROR, OK
    }

    Status getStatus();
    String getCode();
    String getMsg();

}
