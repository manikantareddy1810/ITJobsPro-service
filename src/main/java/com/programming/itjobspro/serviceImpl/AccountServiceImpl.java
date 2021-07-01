package com.programming.itjobspro.serviceImpl;

import com.programming.itjobspro.model.Account;
import com.programming.itjobspro.repository.AccountRespository;
import com.programming.itjobspro.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements IAccountService {

    @Autowired
    AccountRespository accountRepo;


    //to create a new account
    @Override
    public String addNewAccount(Account account) {
        try{
            Account accountAdded = accountRepo.findByAccountNumber(account.getAccountNumber());
            if(accountAdded != null){
                throw new Exception("The account is already in the system");
            }
            accountRepo.save(account);
            return "Create a new account successfully !";
        }
        catch(Exception e){
            e.printStackTrace();
            return "Error in saving the value";
        }
    }

    //delete the account
    @Override
    public String deleteAccountById(int id) {
        try{
            accountRepo.deleteById(id);
            return "Delete account successfully !";
        }
        catch(Exception e){
            e.printStackTrace();
            return "Error in deleting the value";
        }
    }


    //get account balance by account number
    @Override
    public String getAccountBalanceByAccountNo(String accountNo) {
        Account isAccountAlreadyInThere = accountRepo.findByAccountNumber(accountNo);
        if(isAccountAlreadyInThere != null){
            return "Account Balance is " + isAccountAlreadyInThere.getBalance();
        }
        else{
            return "Sorry, There is no account with " + accountNo;
        }
    }


    //get all created accounts
    @Override
    public List<Account> getAllAccounts() {
        return accountRepo.findAll();
    }
}
