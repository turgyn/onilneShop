package com.example.task5.dao;

import com.example.task5.models.Account;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AccountDAO {
    private List<Account> accounts = new ArrayList<>();

    {
        accounts.add(new Account("darustur", "darustur", 9889));
        accounts.add(new Account("meagain", "dar", 2));
        accounts.add(new Account("asdf", "darustu", 1));
        accounts.add(new Account("qwer", "darustu", 0));
    }

    public List<Account> selectAll() {
        return accounts;
    }

    public Account selectByUsername(String username) {
        for (Account ac: accounts) {
            if (ac.getUsername().equals(username)) {
                return ac;
            }
        }
        return null;
    }

    public Account save(Account account) {
        accounts.add(account);
        return account;
    }

    public Account update(Account account) {
        for (Account ac: accounts) {
            if (ac.getUsername().equals(account.getUsername())) {
                ac.setBalance(account.getBalance());
                ac.setPassword(account.getPassword());
                return ac;
            }
        }
        return null;
    }

    public void delete(String username) {
        for (Account ac: accounts) {
            if (ac.getUsername().equals(username)) {
                accounts.remove(ac);
                return;
            }
        }
    }
}