package com.capgemini.service;


import com.capgemini.entity.Account;
import java.util.List;
import java.util.Map;

public interface AccountService {

    Map<String, Object> createAccount(Account account);

    Account getAccountById(int id);

    List<Account> getAllAccounts();

    Map<String, String> updateAccount(int id, Account account);

    Map<String, String> deleteAccount(int id);

    Map<String, Object> deposit(int accountId, double amount);

    Map<String, Object> withdraw(int accountId, double amount);
}