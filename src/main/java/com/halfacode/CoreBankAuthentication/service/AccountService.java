package com.halfacode.CoreBankAuthentication.service;

import com.halfacode.CoreBankAuthentication.entity.Account;
import com.halfacode.CoreBankAuthentication.repoistory.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public List<Account> getAccountsByCustomerId(Long customerId) {
        // Implement the logic to fetch accounts for a given customer from the repository
        return accountRepository.findByCustomerId(customerId);
    }

    public Account createAccount(Account account) {
        // Implement the logic to create a new account in the repository
        return accountRepository.save(account);
    }

    public void deleteAccount(Long accountId) {
        // Implement the logic to delete an account from the repository
        accountRepository.deleteById(accountId);
    }

    // Add more methods as needed for account-related operations
}



