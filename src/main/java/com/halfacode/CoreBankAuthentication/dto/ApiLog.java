package com.halfacode.CoreBankAuthentication.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiLog {
    private Date timestamp;
    private String method;
    private String uri;
    private int status;
    private String statusText;
    private String requestBody;
    private String responseBody;
}