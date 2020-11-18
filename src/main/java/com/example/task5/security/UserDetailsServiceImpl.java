package com.example.task5.security;

import com.example.task5.entities.Account;
import com.example.task5.services.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    Logger logger = LoggerFactory.getLogger(UserDetailsImpl.class);

    @Autowired
    private AccountService accountService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Account account = accountService.findByUsername(s);
        logger.debug("auth:account with username: " + account);
        UserDetailsImpl user = new UserDetailsImpl(account);
        logger.debug("auth: authorities: " + user.getAuthorities());
        return user;
    }
}
