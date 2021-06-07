package com.example.demo.service.impl;

import com.example.demo.model.entity.AccountRole;
import com.example.demo.repository.AccountRoleRepository;
import com.example.demo.service.AccountRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountRoleServiceImpl implements AccountRoleService {

    private AccountRoleRepository accountRoleRepository;

    @Autowired
    public void setAccountRoleRepository(AccountRoleRepository accountRoleRepository) {
        this.accountRoleRepository = accountRoleRepository;
    }

    @Override
    public Iterable<AccountRole> findAllByAccountId(Integer accountId) {
        return accountRoleRepository.findAllByAccountId(accountId);
    }
}
