package com.example.demo.service.impl;

import com.example.demo.model.entity.Account;
import com.example.demo.model.entity.AccountRole;
import com.example.demo.security.AccountPrincipal;
import com.example.demo.service.AccountRoleService;
import com.example.demo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class JwtAccountDetailService implements UserDetailsService {

    private AccountService accountService;
    private AccountRoleService accountRoleService;

    @Autowired
    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }

    @Autowired
    public void setAccountRoleService(AccountRoleService accountRoleService) {
        this.accountRoleService = accountRoleService;
    }

    @Override
    public UserDetails loadUserByUsername(String accountName) throws UsernameNotFoundException {
        Account account = accountService.findByAccountName(accountName);
        if(account == null) {
            throw new UsernameNotFoundException("Account not found with account name: " + accountName);
        }
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        String role;
        for (AccountRole accountRole: accountRoleService.findAllByAccountId(account.getAccountId())){
            role = accountRole.getRole().getRoleName();
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role);
            grantedAuthorities.add(grantedAuthority);
        }
        return  new AccountPrincipal(account.getAccountName(),account.getAccountPassword(),account.getAccountStatus(),grantedAuthorities);
    }
}
