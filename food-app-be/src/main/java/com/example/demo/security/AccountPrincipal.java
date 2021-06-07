package com.example.demo.security;

import com.example.demo.model.entity.AccountStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class AccountPrincipal implements UserDetails {

    private final String accountName;
    private final String password;
    private boolean enabled;
    private final Collection<? extends GrantedAuthority> authorities;

    public AccountPrincipal(String accountName, String password, AccountStatus accountStatus, Collection<? extends GrantedAuthority> authorities) {
        this.accountName = accountName;
        this.password = password;
        this.authorities = authorities;
        this.enabled = accountStatus.getAccountStatusId() == 1;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return accountName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        return "AccountPrincipal{" +
                "accountName='" + accountName + '\'' +
                ", password='" + password + '\'' +
                ", enabled=" + enabled +
                ", authorities=" + authorities +
                '}';
    }
}