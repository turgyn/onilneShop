package com.example.task5.controllers;

import com.example.task5.models.Account;
import com.example.task5.services.AccountService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/accounts")
public class AccountsController {

    private final AccountService accountService;

    public AccountsController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public String getAccounts(Model model) {
        model.addAttribute("accounts", accountService.getAll());
        return "accounts/all";
    }

    @GetMapping("/{username}")
    public String detailAccount(@PathVariable String username, Model model) {
        model.addAttribute("account", accountService.getOne(username));
        return "accounts/detail";
    }

    @GetMapping("/new")
    public String getSignupForm(@ModelAttribute(value = "account") Account account) {
        return "accounts/signup";
    }

    @PostMapping
    public String newAccount(@ModelAttribute Account account) {
        accountService.save(account);
        return "redirect:/accounts";
    }

    @GetMapping("{username}/edit")
    public String getEditForm(@PathVariable String username, Model model) {
        model.addAttribute("account", accountService.getOne(username));
        return "accounts/edit";
    }

    @PatchMapping("/{username}")
    public String updateAccount(@ModelAttribute("account") Account account) {
        accountService.save(account);
        return String.format("redirect:/accounts/%s", account.getUsername());
    }

    @DeleteMapping("/{username}")
    public String deleteAccount(@PathVariable String username) {
        accountService.deleteByUsername(username);
        return "redirect:/accounts";
    }
}
