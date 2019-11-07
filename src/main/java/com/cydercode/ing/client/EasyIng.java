package com.cydercode.ing.client;

import com.cydercode.ing.client.accounts.AccountsResponseData;
import com.cydercode.ing.client.common.IngException;
import com.cydercode.ing.client.history.HistoryRequest;
import com.cydercode.ing.client.history.HistoryResponseData;
import com.cydercode.ing.client.login.LoginResponseData;

import java.util.List;

public class EasyIng {

    private final IngConnector connector;

    private LoginResponseData loginData;

    public EasyIng(IngConnector connector) {
        this.connector = connector;
    }

    public AccountsResponseData getAccounts() throws IngException {
        return getConnector().getAccounts(loginData).getData();
    }

    public HistoryResponseData getTransactions(List<String> accounts,
                                               String search,
                                               String fromDate,
                                               String toDate,
                                               int limit) throws IngException {
        return getConnector().getHistory(loginData, HistoryRequest.builder()
                .rach(accounts)
                .search(search)
                .maxTrn(limit)
                .skipTrn(0)
                .maxsug(5)
                .conx("T")
                .fromDate(fromDate)
                .toDate(toDate)
                .build()).getData();
    }

    private IngConnector getConnector() throws IngException {
        if (loginData == null) {
            loginData = connector.login().getData();
        }

        return connector;
    }
}
