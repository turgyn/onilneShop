package com.example.task5.services;

import com.example.task5.entities.Account;
import com.example.task5.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    private PasswordEncoder passwordEncoder;

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
        passwordEncoder = new BCryptPasswordEncoder(7);
    }

    public Account findByUsername(String username) {
        Optional<Account> optAccount = accountRepository.findById(username);
        return optAccount.orElse(new Account());
    }

    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    public void save(Account account) {
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        accountRepository.save(account);
    }

    public void deleteByUsername(String username) {
        accountRepository.deleteById(username);
    }

}
