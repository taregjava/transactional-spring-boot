package com.halfacode.CoreBankAuthentication.repoistory;

import com.halfacode.CoreBankAuthentication.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account,Long> {
    List<Account> findByCustomerId(Long customerId);
}
