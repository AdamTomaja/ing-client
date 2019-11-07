package com.cydercode.ing.client.accounts;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class AccountsResponseData {

    @Data
    @ToString
    public static class AccountsSummary {
        private List<Account> accts;
        private List<Total> total;
    }

    @Data
    @ToString
    public static class Accounts {
        private AccountsSummary cur;
        private AccountsSummary sav;
    }

    @Data
    @ToString
    public static class Account {
        private String acct;
        private String atrs;
        private Double avbal;
        private String curr;
        private String name;
        private Double plnbal;
        private String type;
        private String visible;
    }

    @Data
    @ToString
    public static class Total {
        private Double amt;
        private String curr;
    }

    private Accounts accts;
}
