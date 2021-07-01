package com.programming.itjobspro.controller;


import com.programming.itjobspro.model.Account;
import com.programming.itjobspro.model.Transactions;
import com.programming.itjobspro.serviceImpl.AccountServiceImpl;
import com.programming.itjobspro.serviceImpl.TransactionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@RestController
public class AppController {


    @Autowired
    AccountServiceImpl accountServiceImpl;

    @Autowired
    TransactionServiceImpl transactionServiceImpl;


    //check the api's working correctly api
    @RequestMapping(value="/ping", method=RequestMethod.GET)
    public String healthCheck() {
        return "The application is running well...";
    }

    // ********************************* Accounts ***************************************************//

    //Create a new account
    @RequestMapping(value="/newaccount", method=RequestMethod.POST)
    @ResponseBody
    public String createNewAccount(@RequestBody  Account account) {
        return accountServiceImpl.addNewAccount(account);
    }

    //get all created accounts
    @RequestMapping(value="/getaccounts", method=RequestMethod.GET)
    @ResponseBody
    public List<Account> getAllAccounts() {
        return accountServiceImpl.getAllAccounts();
    }

    //delete selected account
    @RequestMapping(value="/deleteaccount", method=RequestMethod.GET)
    @ResponseBody
    public String deleteAccount(@RequestParam(value = "id") int id ) {
        return accountServiceImpl.deleteAccountById(id);
    }

    //give an account number and return latest balances
    @RequestMapping(value="/getAccountBalance", method=RequestMethod.GET)
    @ResponseBody
    public String getAccountBalance(@RequestParam(value = "accountNumber") String accountNumber ) {
        return accountServiceImpl.getAccountBalanceByAccountNo(accountNumber);
    }




    // ********************************* Transactions ***************************************************//

    //deposit or withdraw from the account
    @RequestMapping(value="/newtransaction", method=RequestMethod.POST)
    @ResponseBody
    public String addNewTransaction(@RequestBody Transactions transaction) {
        return transactionServiceImpl.addNewTransaction(transaction);
    }

    //get all transaction
    @RequestMapping(value="/getalltransactions", method=RequestMethod.GET)
    @ResponseBody
    public List<Transactions> getAllTransactions() {
        return transactionServiceImpl.getAllTransactions();
    }

    //get all transactions with the transaction type
    @RequestMapping(value="/transactionsWithType", method=RequestMethod.GET)
    @ResponseBody
    public List<Transactions> getAllTransactionsWithType(@RequestParam(value = "type") String type ) {
        return transactionServiceImpl.getAllTransactionsWithType(type);
    }


    @RequestMapping(value="/transactionsWithDateRange", method=RequestMethod.GET)
    @ResponseBody
    public List<Transactions> getAllTransactionsWithDateRange(@RequestParam(value = "accountNumber") String accountNumber, @RequestParam(value = "date1") String date1, @RequestParam(value = "date2") String date2) {
        DateFormat formatter = new SimpleDateFormat("dd-MMM-yy");
        Date convertedDate1 = null,convertedDate2 = null;
        try {
            convertedDate1 = formatter.parse(date1);
            convertedDate2 = formatter.parse(date2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return transactionServiceImpl.getAllTransactionsWithDateRange(accountNumber,convertedDate1,convertedDate2);
    }







}
