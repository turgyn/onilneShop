package com.example.task5.repositories;

import com.example.task5.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {
    @Override
    Account getOne(String s);
}
