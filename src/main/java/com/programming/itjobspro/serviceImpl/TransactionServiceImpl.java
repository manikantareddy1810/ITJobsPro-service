package com.programming.itjobspro.serviceImpl;

import com.programming.itjobspro.model.Account;
import com.programming.itjobspro.model.Transactions;
import com.programming.itjobspro.repository.AccountRespository;
import com.programming.itjobspro.repository.TransactionRepository;
import com.programming.itjobspro.service.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TransactionServiceImpl implements ITransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    AccountRespository accountRespository;


    //list all the transactions
    @Override
    public List<Transactions> getAllTransactions() {
        return transactionRepository.findAll();
    }

    //add new transaction
    @Override
    public String addNewTransaction(Transactions transactions) {
        try{
            Account alreadyInThere = accountRespository.findByAccountNumber(transactions.getAccountNumber());
            double initialValue = accountRespository.findByAccountNumber(transactions.getAccountNumber()).getBalance();
            if(alreadyInThere != null){
                switch(transactions.getType()) {
                    case "WITHDRAW":{
                        double reducingAmount = transactions.getAmount();
                        if(initialValue < reducingAmount){
                            return "Can not proceed as withdrawing amount is higher than the amount in the account";
                        }
                        else{
                            double difference = initialValue - reducingAmount;
                            accountRespository.updateAccountBalance(difference,transactions.getAccountNumber());
                        }
                        break;
                    }
                    case "DEPOSIT": {
                        double increasingAmount = transactions.getAmount();
                        double addedValue = initialValue + increasingAmount;
                        accountRespository.updateAccountBalance(addedValue,transactions.getAccountNumber());
                        break;
                    }
                }
                transactionRepository.save(transactions);
            }
            else{
                return "The account number not existing in the system..";
            }
            return "Transaction proceed successfully !";
        }
        catch(Exception e){
            e.printStackTrace();
            return "Error in saving the value";
        }
    }


    //delete the selected transaction
    @Override
    public String deleteTransaction(int id) {
        try{
            transactionRepository.deleteById(id);
            return "Delete transaction successfully !";
        }
        catch(Exception e){
            e.printStackTrace();
            return "Error in deleting the value";
        }
    }

    //get all the transactions
    @Override
    public List<Transactions> getAllTransactionsWithType(String type) {
        return transactionRepository.findAllByType(type);
    }

    //get all the transactions with in a date period and also with the account number
    @Override
    public List<Transactions> getAllTransactionsWithDateRange(String accountNumber, Date date1, Date date2) {
        return transactionRepository.getAllTransactionsWithInRange(accountNumber, date1,date2);
    }
}
