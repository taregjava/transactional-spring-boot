package com.halfacode.CoreBankAuthentication.logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.halfacode.CoreBankAuthentication.dto.ApiLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;

//@Component
public class ApiLoggerInterceptor implements HandlerInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(ApiLoggerInterceptor.class);
    private final ObjectMapper objectMapper = new ObjectMapper();
    private List<LoggingContent> apiLogs = new ArrayList<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // Wrap the request to cache the input stream
        CachedBodyHttpServletRequest requestWrapper = new CachedBodyHttpServletRequest(request);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        if (request instanceof CachedBodyHttpServletRequest) {
            CachedBodyHttpServletRequest cachedBodyRequest = (CachedBodyHttpServletRequest) request;
            String requestBody = new String(cachedBodyRequest.getCachedBody());
            System.out.println("Request: " + request.getMethod() + " " + request.getRequestURI());
            System.out.println("Request Body: " + requestBody);

            // Get the request headers and convert them to a Map
            Map<String, String> requestHeaders = new HashMap<>();
            Enumeration<String> headerNames = request.getHeaderNames();
            while (headerNames.hasMoreElements()) {
                String headerName = headerNames.nextElement();
                String headerValue = request.getHeader(headerName);
                requestHeaders.put(headerName, headerValue);
            }

            // Process or log the request headers as needed
            for (Map.Entry<String, String> entry : requestHeaders.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }
        } else {
            // If the request is not of type CachedBodyHttpServletRequest, handle it accordingly.
            // For example, you can log that the request body and headers logging are not available for this request.
            System.out.println("Request body and headers logging are not available for this request.");
        }

        // Continue with the normal flow of execution
    }

    // You can log additional information here if needed.
    private String getRequestBody(HttpServletRequest request) throws IOException {
        StringBuilder requestBodyBuilder = new StringBuilder();
        BufferedReader reader = request.getReader();
        String line;

        while ((line = reader.readLine()) != null) {
            requestBodyBuilder.append(line);
        }

        return requestBodyBuilder.toString();
    }

    private String getResponseBody(HttpServletResponse response) {
        // Implement a method to read the response body and return it as a string
        // You can use response.getWriter() to get the writer of the response body.
        // This step depends on your specific use case and how the response body is being written in your application.
        return "Response body goes here";
    }

    // Add a method to get the list of ApiLog objects
    public List<LoggingContent> getApiLogs() {
        return apiLogs;
    }

    private String getResponseHeaders(HttpServletResponse response) {
        // Implement a method to read the response headers and return them as a string
        // You can use response.getHeaderNames() and response.getHeader() methods to get headers.
        // This step depends on your specific use case and what information you want to log from the response.
        return "Response headers go here";
    }
}