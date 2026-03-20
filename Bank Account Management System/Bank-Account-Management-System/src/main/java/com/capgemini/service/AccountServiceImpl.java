package com.capgemini.service;


import com.capgemini.entity.Account;
import com.capgemini.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository repo;

    @Override
    public Map<String, Object> createAccount(Account account) {

        if (account.getBalance() < 0) {
            return Map.of("message", "Balance cannot be negative");
        }

        Account saved = repo.save(account);

        return Map.of(
                "message", "Account created successfully",
                "accountId", saved.getId()
        );
    }

    @Override
    public Account getAccountById(int id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found"));
    }

    @Override
    public List<Account> getAllAccounts() {
        return repo.findAll();
    }

    @Override
    public Map<String, String> updateAccount(int id, Account newAccount) {

        Account existing = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        if (newAccount.getBalance() < 0) {
            return Map.of("message", "Balance cannot be negative");
        }

        existing.setAccountHolderName(newAccount.getAccountHolderName());
        existing.setBalance(newAccount.getBalance());

        repo.save(existing);

        return Map.of("message", "Account updated successfully");
    }

    @Override
    public Map<String, String> deleteAccount(int id) {

        Account account = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        repo.delete(account);

        return Map.of("message", "Account deleted successfully");
    }

    @Override
    public Map<String, Object> deposit(int accountId, double amount) {

        if (amount <= 0) {
            return Map.of("message", "Invalid amount");
        }

        Account account = repo.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        account.setBalance(account.getBalance() + amount);

        repo.save(account);

        return Map.of(
                "message", "Deposit successful",
                "updatedBalance", account.getBalance()
        );
    }

    @Override
    public Map<String, Object> withdraw(int accountId, double amount) {

        if (amount <= 0) {
            return Map.of("message", "Invalid amount");
        }

        Account account = repo.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        if (amount > account.getBalance()) {
            return Map.of("message", "Insufficient balance");
        }

        account.setBalance(account.getBalance() - amount);

        repo.save(account);

        return Map.of(
                "message", "Withdrawal successful",
                "updatedBalance", account.getBalance()
        );
    }
}