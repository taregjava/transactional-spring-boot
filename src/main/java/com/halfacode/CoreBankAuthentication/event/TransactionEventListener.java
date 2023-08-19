package com.halfacode.CoreBankAuthentication.event;

import com.halfacode.CoreBankAuthentication.entity.Account;
import com.halfacode.CoreBankAuthentication.entity.Transaction;
import com.halfacode.CoreBankAuthentication.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

//@Component
public class TransactionEventListener implements ApplicationListener<TransactionCreatedEvent> {
    @Autowired
    private AccountService accountService;

    @Override
    public void onApplicationEvent(TransactionCreatedEvent event) {
        Transaction transaction = event.getTransaction();
        Account senderAccount = transaction.getSenderAccount();
        Account receiverAccount = transaction.getReceiverAccount();

        // Update account balances
        accountService.updateAccountBalance(senderAccount.getId(), -transaction.getAmount());
        accountService.updateAccountBalance(receiverAccount.getId(), transaction.getAmount());
    }

}
