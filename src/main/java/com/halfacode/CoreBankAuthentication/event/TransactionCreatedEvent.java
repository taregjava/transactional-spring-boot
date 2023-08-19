package com.halfacode.CoreBankAuthentication.event;

import com.halfacode.CoreBankAuthentication.entity.Transaction;
import org.springframework.context.ApplicationEvent;

public class TransactionCreatedEvent extends ApplicationEvent {
    private final Transaction transaction;

    public TransactionCreatedEvent(Object source, Transaction transaction) {
        super(source);
        this.transaction = transaction;
    }

    public Transaction getTransaction() {
        return transaction;
    }
}