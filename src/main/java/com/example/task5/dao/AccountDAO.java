package com.example.task5.dao;

import com.example.task5.models.Account;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AccountDAO {
    private List<Account> accounts = new ArrayList<>();

    {
        accounts.add(new Account(1, "darustur", "darustur", 9889));
        accounts.add(new Account(2, "meagain", "dar", 2));
        accounts.add(new Account(3, "asdf", "darustu", 1));
        accounts.add(new Account(4, "qwer", "darustu", 0));
    }

    public List<Account> selectAll() {
        return accounts;
    }

    public Account selectById(int id) {
        for (Account ac: accounts) {
            if (ac.getId() == id) {
                return ac;
            }
        }
        return null;
    }

    public Account insert(Account account) {
        accounts.add(account);
        return account;
    }

    public Account update(Account account) {
        for (Account ac: accounts) {
            if (ac.getId() == account.getId()) {
                ac = account;
                return ac;
            }
        }
        return null;
    }
}