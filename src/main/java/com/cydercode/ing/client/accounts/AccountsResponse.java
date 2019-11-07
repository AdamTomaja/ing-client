package com.cydercode.ing.client.accounts;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class AccountsResponse {

    private String status;
    private AccountsResponseData data;
    private String code;
    private String msg;

}
