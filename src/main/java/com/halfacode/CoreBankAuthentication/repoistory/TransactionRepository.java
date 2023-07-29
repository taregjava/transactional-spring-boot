package com.halfacode.CoreBankAuthentication.repoistory;

import com.halfacode.CoreBankAuthentication.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction,Long> {
    List<Transaction> findBySenderAccountIdOrReceiverAccountId(Long accountId, Long accountId1);
}
