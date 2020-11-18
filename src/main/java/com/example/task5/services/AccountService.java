package com.example.task5.services;

import com.example.task5.entities.Account;
import com.example.task5.repositories.AccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    private final Logger logger = LoggerFactory.getLogger(AccountService.class);

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

    public boolean create(Account account) {
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        Account check = findByUsername(account.getUsername());
        logger.debug("checking for unique username: " + check);
        if (check.getUsername() != null) {
            logger.debug("user already exists");
            return false;
        }
        logger.debug("creating new user");
        accountRepository.save(account);
        return true;
    }

    public void save(Account account) {
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        accountRepository.save(account);
    }

    public void deleteByUsername(String username) {
        accountRepository.deleteById(username);
    }

    public boolean topUpBalance(Account account) {
        Account targetAccount = findByUsername(account.getUsername());
        if (targetAccount.getUsername() == null) {
            logger.debug("user doesn't exists: " + account.getUsername());
            return false;
        }
        targetAccount.setBalance(targetAccount.getBalance() + account.getBalance());
        accountRepository.save(targetAccount);
        return true;
    }

    public boolean updatePassword(String username, String curPass, String newPass) {
        curPass = passwordEncoder.encode(curPass);
        newPass = passwordEncoder.encode(newPass);
        Account account = findByUsername(username);
        if (account.getPassword().equals(curPass)) {
            return false;
        }
        account.setPassword(newPass);
        accountRepository.save(account);
        return true;
    }

}
