package com.halfacode.CoreBankAuthentication.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.halfacode.CoreBankAuthentication.dto.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class ApiKeyAuthenticationFilter extends OncePerRequestFilter {
    private static final String API_KEY_HEADER = "X-API-Key";
    private static final String VALID_API_KEY = "your_valid_api_key_here";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String apiKey = request.getHeader(API_KEY_HEADER);
        if (apiKey != null && apiKey.equals(VALID_API_KEY)) {
            // API key is valid, continue with the request
            filterChain.doFilter(request, response);
        } else {
            // Invalid API key, return ApiResponse with an error message
            ApiResponse<Boolean> apiResponse = new ApiResponse<>(false, "Unauthorized");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.getWriter().write(new ObjectMapper().writeValueAsString(apiResponse));
        }
    }
}