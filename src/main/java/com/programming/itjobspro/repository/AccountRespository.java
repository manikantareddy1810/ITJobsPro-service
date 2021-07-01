package com.programming.itjobspro.repository;

import com.programming.itjobspro.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;


public interface AccountRespository extends JpaRepository<Account,Integer> {

    Account findByAccountNumber(String accountNo);
    Account deleteById(int id);

    @Transactional
    @Modifying
    @Query("UPDATE Account a SET a.balance = :balance WHERE a.accountNumber  = :accountNumber")
    void updateAccountBalance(@Param("balance") double balance, @Param("accountNumber") String accountNumber);
}

