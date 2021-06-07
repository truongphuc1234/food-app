package com.example.demo.service;

import com.example.demo.model.entity.Account;

public interface AccountService {
    Account findByAccountName(String accountName);
}
