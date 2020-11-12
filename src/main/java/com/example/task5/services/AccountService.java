package com.example.task5.services;

import com.example.task5.models.Account;
import com.example.task5.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    public Account getOne(String username) {
        return accountRepository.getOne(username);
    }

    public void save(Account account) {
        System.out.println("Service: " + account.getUsername());
        accountRepository.save(account);
    }

    public void deleteByUsername(String username) {
        accountRepository.deleteById(username);
    }
}
