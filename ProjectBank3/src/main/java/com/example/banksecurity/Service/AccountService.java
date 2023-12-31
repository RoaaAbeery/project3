package com.example.banksecurity.Service;


import com.example.banksecurity.Api.ApiException;
import com.example.banksecurity.Model.Account;
import com.example.banksecurity.Model.User;
import com.example.banksecurity.Repository.AccountRepository;
import com.example.banksecurity.Repository.AuthRepository;
import com.example.banksecurity.Repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final AuthRepository authRepository;
    private final CustomerRepository customerRepository;





    public List getAllAccount(){

        return accountRepository.findAll();
    }

    public void addMyAccount(Account account, Integer auth){
        User user = authRepository.findUserById(auth);
        if(user==null){
            throw new ApiException("user id not found");
        }
        account.setCustomer(user.getCustomer());
        accountRepository.save(account);
    }
    public void updateAccount(Integer id , Account newAccount , Integer auth){
        Account oldAccount=accountRepository.findAccountById(id);
        User user=authRepository.findUserById(auth);
        if (oldAccount==null){
            throw new ApiException("Account not found");
        }else if(oldAccount.getCustomer().getUser().getId()!=user.getId()){
            throw new ApiException("Sorry , You do not have the authority to update this Account!");
        }

        newAccount.setId(id);
        newAccount.setCustomer(oldAccount.getCustomer());



        accountRepository.save(newAccount);
    }

    public void deleteAccount(Integer id, Integer auth){
        User user=authRepository.findUserById(auth);
        Account account=accountRepository.findAccountById(id);
        if (account==null){
            throw new ApiException("account not found");
        }else if(account.getCustomer().getUser().getId()!=user.getId()){
            throw new ApiException("Sorry , You do not have the authority to delete this Account!");
        }
        accountRepository.delete(account);
    }
    public List getMyAccounts(Integer auth){

        User user=authRepository.findUserById(auth);
        List<Account> accounts = accountRepository.findAllById(user);
        if(accounts.isEmpty()){
            throw new ApiException("Empty!");
        }
        return accounts;
    }

    public void activeBankAccount(Integer auth, Integer id){
        User user = authRepository.findUserById(auth);
        Account account = accountRepository.findAccountById(id);

        if(account.getCustomer().getUser().getId()!= user.getId()){
            throw new ApiException("");
        }
        account.setIsActive(true);
        accountRepository.save(account);
    }

    public void deposit(Integer auth, Integer id, Integer amount){
        User user=authRepository.findUserById(auth);
        Account account = accountRepository.findAccountById(id);
        if(account==null){
            throw new ApiException("account not found");
        }
        if(account.getCustomer().getUser().getId()!= user.getId()){
            throw new ApiException("not access account");
        }

        account.setBalance(account.getBalance()+amount);
        accountRepository.save(account);

    }

    public void withdraw(Integer auth,Integer id , Integer amount){
        Account account = accountRepository.findAccountById(id);
        User user=authRepository.findUserById(auth);
        if(account==null){
            throw new ApiException("account not found");
        }
        if(account.getCustomer().getUser().getId()!= user.getId()){
            throw new ApiException("not access account");
        }
        if(account.getBalance() < amount){
            throw new ApiException("amount");
        }
        account.setBalance(account.getBalance()-amount);
        accountRepository.save(account);
    }


    public void TransferBetweenAccounts(Integer auth, Integer account_id1, Integer account_id2, Integer amount){
        Account account1 = accountRepository.findAccountById(account_id1);
        Account account2 = accountRepository.findAccountById(account_id2);
        User user = authRepository.findUserById(auth);


        if(account1==null){
            throw new ApiException("account not found");
        }
        if(account2==null){
            throw new ApiException("account not found");
        }

        if(account1.getCustomer().getUser().getId()!= user.getId()){
            throw new ApiException("not access account");
        }

        if(account1.getBalance() < amount){
            throw new ApiException("amount");
        }
        account1.setBalance(account1.getBalance()-amount);
        account2.setBalance(account2.getBalance()+amount);
        accountRepository.save(account1);
        accountRepository.save(account2);
    }

}