package com.cydercode.ing.client.history;

import lombok.Data;

@Data
public class HistoryResponse {

    private String status;
    private HistoryResponseData data;
    private String code;
    private String msg;
}
