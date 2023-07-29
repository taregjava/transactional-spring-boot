package com.halfacode.CoreBankAuthentication.controller;
import com.halfacode.CoreBankAuthentication.dto.ApiResponse;
import com.halfacode.CoreBankAuthentication.entity.Account;
import com.halfacode.CoreBankAuthentication.entity.Customer;
import com.halfacode.CoreBankAuthentication.entity.Transaction;
import com.halfacode.CoreBankAuthentication.service.AccountService;
import com.halfacode.CoreBankAuthentication.service.CustomerService;
import com.halfacode.CoreBankAuthentication.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public ResponseEntity<ApiResponse<Transaction>> createTransaction(@Valid @RequestBody Transaction transaction, HttpServletRequest request) {
        ApiResponse<Boolean> apiKeyResponse = isValidApiKey(request);
        if (!apiKeyResponse.getData()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ApiResponse<>(false, "Unauthorized"));
        }

        Transaction createdTransaction = transactionService.createTransaction(transaction);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(true, "Transaction created successfully", createdTransaction));
    }

    @GetMapping("/account/{accountId}")
    public ResponseEntity<ApiResponse<List<Transaction>>> getTransactionsByAccountId(@PathVariable Long accountId, HttpServletRequest request) {
        ApiResponse<Boolean> apiKeyResponse = isValidApiKey(request);
        if (!apiKeyResponse.getData()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ApiResponse<>(false, "Unauthorized"));
        }

        List<Transaction> transactions = transactionService.getTransactionsByAccountId(accountId);
        return ResponseEntity.ok(new ApiResponse<>(true, "Transactions retrieved successfully", transactions));
    }
    private ApiResponse<Boolean> isValidApiKey(HttpServletRequest request) {
        String apiKey = request.getHeader("X-API-Key");
        boolean isValid = apiKey != null && apiKey.equals("your_valid_api_key_here");
        return new ApiResponse<>(isValid, isValid ? "API key is valid" : "Unauthorized", isValid);
    }
    // Add more endpoints and methods as needed for other transaction-related operations
}