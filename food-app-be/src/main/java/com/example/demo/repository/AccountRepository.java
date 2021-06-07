package com.example.demo.repository;

import com.example.demo.model.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    @Query(nativeQuery = true,value = "SELECT * FROM account WHERE account_name = :accountName")
    Account findByAccountName(String accountName);
}
