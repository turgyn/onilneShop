package com.example.task5.controllers;

import com.example.task5.entities.Account;
import com.example.task5.services.AccountService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/accounts")
public class AccountsController {

    private final AccountService accountService;

    public AccountsController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public String getAccounts(Model model) {
        System.err.println("GET /accounts");
        model.addAttribute("accounts", accountService.findAll());
        return "accounts/all";
    }

    @GetMapping("/{username}")
    public String detailAccount(@PathVariable String username, Model model) {
        model.addAttribute("account", accountService.findByUsername(username));
        return "accounts/detail";
    }

    @GetMapping("/new")
    public String getSignupForm(@ModelAttribute("account") Account account) {
        return "accounts/signup";
    }

    @PostMapping
    public String newAccount(@Valid Account account,
                             @RequestParam("password-confirm") String passConfirm,
                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "accounts/signup";
        }
        if (!account.getPassword().equals(passConfirm)) {
            bindingResult.addError(new FieldError("password", "password", "Password confirmation failed"));
            return "accounts/signup";
        }
        if (!accountService.create(account)) {
            bindingResult.addError(new FieldError("username", "username", "username already exists"));
            return "accounts/signup";
        }
        return "redirect:/accounts";
    }

    @GetMapping("{username}/edit")
    public String getEditForm(@PathVariable String username, Model model) {
        model.addAttribute("account", accountService.findByUsername(username));
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
