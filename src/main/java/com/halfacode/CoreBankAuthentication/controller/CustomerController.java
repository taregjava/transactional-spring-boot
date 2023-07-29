package com.halfacode.CoreBankAuthentication.controller;

import com.halfacode.CoreBankAuthentication.dto.ApiResponse;
import com.halfacode.CoreBankAuthentication.entity.Customer;
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
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Customer>>> getAllCustomers(HttpServletRequest request) {
        ApiResponse<Boolean> apiKeyResponse = isValidApiKey(request);
        if (!apiKeyResponse.getData()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(createUnauthorizedApiResponse());
        }

        List<Customer> customers = customerService.getAllCustomers();
        return ResponseEntity.ok(new ApiResponse<>(true, "Customers retrieved successfully", customers));
    }
    @PostMapping
    public ResponseEntity<ApiResponse<Customer>> createCustomer(@Valid @RequestBody Customer customer, HttpServletRequest request) {
        ApiResponse<Boolean> apiKeyResponse = isValidApiKey(request);
        if (!apiKeyResponse.getData()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ApiResponse<>(false, "Unauthorized"));
        }

        Customer createdCustomer = customerService.createCustomer(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(true, "Customer created successfully", createdCustomer));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Customer>> updateCustomer(@PathVariable Long id, @RequestBody Customer customer, HttpServletRequest request) {
        ApiResponse<Boolean> apiKeyResponse = isValidApiKey(request);
        if (!apiKeyResponse.getData()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ApiResponse<>(false, "Unauthorized"));
        }

        Customer updatedCustomer = customerService.updateCustomer(id, customer);
        if (updatedCustomer != null) {
            return ResponseEntity.ok(new ApiResponse<>(true, "Customer updated successfully", updatedCustomer));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(false, "Customer not found"));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteCustomer(@PathVariable Long id, HttpServletRequest request) {
        ApiResponse<Boolean> apiKeyResponse = isValidApiKey(request);
        if (!apiKeyResponse.getData()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ApiResponse<>(false, "Unauthorized"));
        }

        customerService.deleteCustomer(id);
        return ResponseEntity.ok(new ApiResponse<>(true, "Customer deleted successfully"));
    }
    private ApiResponse<Boolean> isValidApiKey(HttpServletRequest request) {
        String apiKey = request.getHeader("X-API-Key");
        boolean isValid = apiKey != null && apiKey.equals("your_valid_api_key_here");
        return new ApiResponse<>(isValid, isValid ? "API key is valid" : "Unauthorized", isValid);
    }

    private ApiResponse<List<Customer>> createUnauthorizedApiResponse() {
        return new ApiResponse<>(false, "Unauthorized", new ArrayList<>());
    }
}