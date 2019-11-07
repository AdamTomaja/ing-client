package com.cydercode.ing.client;

import com.cydercode.ing.client.history.HistoryRequest;
import com.cydercode.ing.client.history.HistoryResponse;
import com.cydercode.ing.client.history.HistoryResponseData;
import com.cydercode.ing.client.login.LoginResponse;

import java.util.Arrays;

public class App {
    public static void main(String[] args) {
        Configuration configuration = Configuration.builder()
                .url("https://login.ingbank.pl/mojeing/rest/")
                .login("") // user login
                .password("") // user password
                .build();

        IngClient ingClient = new IngClient(configuration);
        LoginResponse token = ingClient.getToken();
        HistoryResponse history = ingClient.getHistory(token.getData(), HistoryRequest.builder()
                .rach(Arrays.asList("")) // account number
                .search("")
                .maxTrn(34)
                .skipTrn(0)
                .maxsug(5)
                .conx("T")
                .fromDate("2019-11-07")
                .toDate("2019-11-07")
                .build());

        history.getData().getTrns().forEach(w -> {
            HistoryResponseData.Transaction m = w.getM();
            System.out.println(String.format("%s | %s | %s | %s", m.getT1(),m.getM1(), m.getAmt(), m.getDate()));
        });
    }
}
