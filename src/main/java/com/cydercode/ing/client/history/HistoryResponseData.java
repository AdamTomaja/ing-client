package com.cydercode.ing.client.history;

import lombok.Data;

import java.util.List;

@Data
public class HistoryResponseData {

    @Data
    public static class Totals {
        private Integer numdebs;
        private Double totdebs;
        private Integer numcred;
        private Double totcred;
        private String curr;
    }

    @Data
    public static class Summary {
        private Integer numcred;
        private Integer numdebs;
        private Totals tot;
    }

    @Data
    public static class TransactionWrapper {
        private Transaction m;
    }

    @Data
    public static class Transaction {
        private Double amt;
        private String aw;
        private String awn;
        private Double bal;
        private String cr;
        private String date;
        private String fl;
        private String ht;
        private String id;
        private String itt;
        private String jtr;
        private String keraaja;
        private String m1;
        private Integer pfi;
        private Integer pfs;
        private String rfl;
        private String st;
        private String t1;
        private String t2;
        private String tpn;
        private Integer typ;
        private String uo;
        private String w1;
        private String w2;
        private String w3;
    }

    private Integer numtrns;
    private Double minAmt;
    private Double maxAmt;
    private String attrs;
//    private Summary sum;
    private List<TransactionWrapper> trns;
}
