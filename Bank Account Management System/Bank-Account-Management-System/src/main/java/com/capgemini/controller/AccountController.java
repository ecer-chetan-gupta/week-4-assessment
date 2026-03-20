package com.capgemini.controller;


import com.capgemini.entity.Account;
import com.capgemini.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountService service;

    @PostMapping
    public Map<String, Object> createAccount(@RequestBody Account account) {
        return service.createAccount(account);
    }

    @GetMapping("/{id}")
    public Account getAccount(@PathVariable int id) {
        return service.getAccountById(id);
    }

    @GetMapping
    public List<Account> getAllAccounts() {
        return service.getAllAccounts();
    }

    @PutMapping("/{id}")
    public Map<String, String> updateAccount(@PathVariable int id, @RequestBody Account account) {
        return service.updateAccount(id, account);
    }

    @DeleteMapping("/{id}")
    public Map<String, String> deleteAccount(@PathVariable int id) {
        return service.deleteAccount(id);
    }

    @PostMapping("/deposit")
    public Map<String, Object> deposit(@RequestBody Map<String, Object> req) {
        int accountId = (int) req.get("accountId");
        double amount = Double.parseDouble(req.get("amount").toString());
        return service.deposit(accountId, amount);
    }

    @PostMapping("/withdraw")
    public Map<String, Object> withdraw(@RequestBody Map<String, Object> req) {
        int accountId = (int) req.get("accountId");
        double amount = Double.parseDouble(req.get("amount").toString());
        return service.withdraw(accountId, amount);
    }
}