package com.example.task5.controllers;

import com.example.task5.dao.AccountDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/accounts")
public class AccountsController {

    AccountDAO accountDAO;

    public AccountsController(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    @GetMapping
    public String getAccounts(Model model) {
        model.addAttribute("accounts", accountDAO.selectAll());
        return "accounts/all";
    }

    @GetMapping("/{id}")
    public String getAccount(Model model, @PathVariable int id) {
        model.addAttribute("account", accountDAO.selectById(id));
        return "accounts/detail";
    }
}
