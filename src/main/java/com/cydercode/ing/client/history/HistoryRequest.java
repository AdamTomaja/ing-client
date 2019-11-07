package com.cydercode.ing.client.history;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Builder
@Getter
@ToString
public class HistoryRequest {

    private List<String> rach;
    private String search;
    private Integer maxTrn;
    private Integer skipTrn;
    private Integer maxsug;
    private String conx;
    private String fromDate;
    private String toDate;
}
