package com.halfacode.CoreBankAuthentication.controller;

import com.halfacode.CoreBankAuthentication.dto.LoginRequest;
import com.halfacode.CoreBankAuthentication.entity.Customer;
import com.halfacode.CoreBankAuthentication.repoistory.CustomerRepository;
import com.halfacode.CoreBankAuthentication.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();

        // Perform authentication logic...

        authService.login(username, password);

        return ResponseEntity.ok("Login successful");
    }
}
