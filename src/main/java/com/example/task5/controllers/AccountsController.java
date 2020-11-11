package com.example.task5.controllers;

import com.example.task5.dao.AccountDAO;
import com.example.task5.models.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/accounts")
public class AccountsController {

    AccountDAO accountDAO;

    @Autowired
    public AccountsController(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    @GetMapping
    public String getAccounts(Model model) {
        model.addAttribute("accounts", accountDAO.selectAll());
        return "accounts/all";
    }

    @GetMapping("/{username}")
    public String detailAccount(@PathVariable String username, Model model) {
        model.addAttribute("account", accountDAO.selectByUsername(username));
        return "accounts/detail";
    }

    @GetMapping("/new")
    public String getSignupForm(@ModelAttribute(value = "account") Account account) {
        return "accounts/signup";
    }

    @PostMapping
    public String newAccount(@ModelAttribute Account account) {
        accountDAO.save(account);
        return "redirect:/accounts";
    }

    @GetMapping("{username}/edit")
    public String getEditForm(@PathVariable String username, Model model) {
        model.addAttribute("account", accountDAO.selectByUsername(username));
        return "accounts/edit";
    }

    @PatchMapping("/{username}")
    public String updateAccount(@ModelAttribute("account") Account account) {
        accountDAO.update(account);
        return "redirect:/accounts/" + account.getUsername();
    }

    @DeleteMapping("/{username}")
    public String deleteAccount(@PathVariable String username) {
        accountDAO.delete(username);
        return "redirect:/accounts";
    }
}
