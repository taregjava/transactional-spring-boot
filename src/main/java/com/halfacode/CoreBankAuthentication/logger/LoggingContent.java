package com.halfacode.CoreBankAuthentication.logger;

import lombok.*;

import java.util.Map;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LoggingContent {
    private String httpMethod;
    private String path;
    Map<String,String> header;
    private Object body;
    private Map<String, String> parameter;
}
