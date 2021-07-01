package com.programming.itjobspro.service;

import com.programming.itjobspro.model.Account;

import java.util.List;

public interface IAccountService {

    List<Account> getAllAccounts();
    String addNewAccount(Account account);
    String deleteAccountById(int id);
    String getAccountBalanceByAccountNo(String accountNo);
}
