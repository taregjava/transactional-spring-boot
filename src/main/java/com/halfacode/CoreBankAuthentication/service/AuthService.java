package com.halfacode.CoreBankAuthentication.service;

import com.halfacode.CoreBankAuthentication.entity.Account;
import com.halfacode.CoreBankAuthentication.entity.Customer;
import com.halfacode.CoreBankAuthentication.repoistory.AccountRepository;
import com.halfacode.CoreBankAuthentication.repoistory.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class AuthService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private HttpServletRequest request;

    public void login(String email, String password) {
        // Perform authentication logic...

        // Get user's IP address
        String ipAddress = getClientIP();

        // Update user's IP address in the database
        Customer customer = customerRepository.findByEmail(email);
        customer.setIpAddress(ipAddress);
        customerRepository.save(customer);
    }

    private String getClientIP() {
        String ipAddress = request.getHeader("X-Forwarded-For");
        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
        }
        return ipAddress;
    }
}