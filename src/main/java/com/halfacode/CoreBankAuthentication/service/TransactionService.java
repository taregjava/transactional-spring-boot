package com.halfacode.CoreBankAuthentication.service;

import com.halfacode.CoreBankAuthentication.entity.Account;
import com.halfacode.CoreBankAuthentication.entity.Transaction;
import com.halfacode.CoreBankAuthentication.exceptions.AccountNotFoundException;
import com.halfacode.CoreBankAuthentication.exceptions.InsufficientBalanceException;
import com.halfacode.CoreBankAuthentication.repoistory.AccountRepository;
import com.halfacode.CoreBankAuthentication.repoistory.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;

    public TransactionService(TransactionRepository transactionRepository, AccountRepository accountRepository) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
    }

    @Transactional
    public Transaction createTransaction(Transaction transaction) {
        // Perform any business logic or validations here
        // For example, you might want to check if the sender and receiver accounts are valid and have sufficient balance, etc.

        // Get the sender and receiver account IDs from the transaction
        Long senderAccountId = transaction.getSenderAccount().getId();
        Long receiverAccountId = transaction.getReceiverAccount().getId();

        // Fetch the full sender and receiver Account objects from the database
        Account senderAccount = accountRepository.findById(senderAccountId)
                .orElseThrow(() -> new AccountNotFoundException("Sender account not found"));
        Account receiverAccount = accountRepository.findById(receiverAccountId)
                .orElseThrow(() -> new AccountNotFoundException("Receiver account not found"));

        // Check if the sender account has sufficient balance to perform the transaction
        double transactionAmount = transaction.getAmount();
        double senderAccountBalance = senderAccount.getBalance();

        if (senderAccountBalance < transactionAmount) {
            // If the sender's account does not have enough balance, you can handle the error here.
            // For example, you can throw a custom exception or return an error response to the client.
            throw new InsufficientBalanceException("Insufficient balance in the sender's account");
        }

        // Update the sender's and receiver's account balance after the transaction
        senderAccount.setBalance(senderAccountBalance - transactionAmount);
        receiverAccount.setBalance(receiverAccount.getBalance() + transactionAmount);

        // Save the updated account balances in the database
        accountRepository.save(senderAccount);
        accountRepository.save(receiverAccount);

        // Save the transaction in the database using the transactionRepository
        return transactionRepository.save(transaction);
    }
    public List<Transaction> getTransactionsByAccountId(Long accountId) {
        // Implement the logic to fetch transactions for a given account from the repository
        return transactionRepository.findBySenderAccountIdOrReceiverAccountId(accountId, accountId);
    }

    // Add more methods as needed for transaction-related operations
}