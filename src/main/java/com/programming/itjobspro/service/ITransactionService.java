package com.programming.itjobspro.service;

import com.programming.itjobspro.model.Transactions;

import java.util.Date;
import java.util.List;

public interface ITransactionService {

    List<Transactions> getAllTransactions();
    String addNewTransaction(Transactions transaction);
    String deleteTransaction(int transactionId);
    List<Transactions> getAllTransactionsWithType(String type);
    List<Transactions> getAllTransactionsWithDateRange(String accountNumber, Date date1, Date date2);


}
