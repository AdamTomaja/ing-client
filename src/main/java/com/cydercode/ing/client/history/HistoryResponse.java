package com.cydercode.ing.client.history;

import com.cydercode.ing.client.common.IngResponse;
import lombok.Data;

@Data
public class HistoryResponse implements IngResponse {

    private Status status;
    private HistoryResponseData data;
    private String code;
    private String msg;
}
