package com.example.demo.service;

import com.example.demo.model.entity.AccountRole;

public interface AccountRoleService {
    Iterable<AccountRole> findAllByAccountId(Integer accountId);
}
