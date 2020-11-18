package com.example.task5.controllers;

import com.example.task5.entities.Account;
import com.example.task5.services.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/accounts")
public class AccountsController {

    private final Logger logger = LoggerFactory.getLogger(AccountsController.class);

    private final AccountService accountService;

    public AccountsController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public String getAccounts(Model model) {
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
            logger.error("VALIDATION ERROR: " +bindingResult.getFieldErrors().toString());
            return "accounts/signup";
        }
        if (!account.getPassword().equals(passConfirm)) {
            bindingResult.addError(new FieldError("password", "password", "Password confirmation failed"));
            logger.error("PASS CONFIRM ERROR for user: " + account.getUsername());
            return "accounts/signup";
        }
        if (!accountService.create(account)) {
            bindingResult.addError(new FieldError("username", "username", "username already exists"));
            logger.error("username exists: " + account.getUsername());
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
    public String updateAccount(@ModelAttribute("account") @Valid Account account, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            logger.error("VALIDATION ERROR: " + bindingResult.getFieldErrors().toString());
            return "accounts/edit";
        }
        accountService.save(account);
        return String.format("redirect:/accounts/%s", account.getUsername());
    }

    @DeleteMapping("/{username}")
    public String deleteAccount(@PathVariable String username) {
        accountService.deleteByUsername(username);
        return "redirect:/accounts";
    }
}
