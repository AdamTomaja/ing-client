package com.cydercode.ing.client.history;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Builder
@Getter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HistoryRequest {

    private List<String> rach;
    private String search;
    private Integer maxTrn;
    private Integer skipTrn;
    private Integer maxsug;
    private String conx;
    private String fromDate;
    private String toDate;
    private String sign; // - / +
    private Double maxamt; // max ammount
    private Double minamt; // min ammount
    private String mask;
}
