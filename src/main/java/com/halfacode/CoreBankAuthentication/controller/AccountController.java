package com.halfacode.CoreBankAuthentication.controller;

import com.halfacode.CoreBankAuthentication.dto.ApiResponse;
import com.halfacode.CoreBankAuthentication.entity.Account;
import com.halfacode.CoreBankAuthentication.entity.Customer;
import com.halfacode.CoreBankAuthentication.service.AccountService;
import com.halfacode.CoreBankAuthentication.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/{customerId}")
    public ResponseEntity<ApiResponse<List<Account>>> getAccountsByCustomerId(@PathVariable Long customerId, HttpServletRequest request) {
        ApiResponse<Boolean> apiKeyResponse = isValidApiKey(request);
        if (!apiKeyResponse.getData()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ApiResponse<>(false, "Unauthorized"));
        }

        List<Account> accounts = accountService.getAccountsByCustomerId(customerId);
        return ResponseEntity.ok(new ApiResponse<>(true, "Accounts retrieved successfully", accounts));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Account>> createAccount(@Valid @RequestBody Account account, HttpServletRequest request) {
        ApiResponse<Boolean> apiKeyResponse = isValidApiKey(request);
        if (!apiKeyResponse.getData()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ApiResponse<>(false, "Unauthorized"));
        }

        Account createdAccount = accountService.createAccount(account);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(true, "Account created successfully", createdAccount));
    }

    @DeleteMapping("/{accountId}")
    public ResponseEntity<ApiResponse<Void>> deleteAccount(@PathVariable Long accountId, HttpServletRequest request) {
        ApiResponse<Boolean> apiKeyResponse = isValidApiKey(request);
        if (!apiKeyResponse.getData()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ApiResponse<>(false, "Unauthorized"));
        }

        accountService.deleteAccount(accountId);
        return ResponseEntity.ok(new ApiResponse<>(true, "Account deleted successfully"));
    }
    private ApiResponse<Boolean> isValidApiKey(HttpServletRequest request) {
        String apiKey = request.getHeader("X-API-Key");
        boolean isValid = apiKey != null && apiKey.equals("your_valid_api_key_here");
        return new ApiResponse<>(isValid, isValid ? "API key is valid" : "Unauthorized", isValid);
    }

    private ApiResponse<List<Customer>> createUnauthorizedApiResponse() {
        return new ApiResponse<>(false, "Unauthorized", new ArrayList<>());
    }
    // Add more endpoints and methods as needed for other account-related operations
}