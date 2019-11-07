package com.cydercode.ing.client.accounts;

import com.cydercode.ing.client.common.IngResponse;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class AccountsResponse implements IngResponse {

    private String status;
    private AccountsResponseData data;
    private String code;
    private String msg;

}
