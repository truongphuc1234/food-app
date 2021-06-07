package com.example.demo.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "account")
public class Account {

    @Id
    @Column(name = "account_id")
    private Integer accountId;

    @Column(name = "account_name")
    private String accountName;

    @Column(name = "account_password")
    private String accountPassword;

    @Column(name = "account_email")
    private String accountEmail;

    @ManyToOne
    @JoinColumn(referencedColumnName = "account_status_id", name = "account_status_id")
    private AccountStatus accountStatus;

    @Column(name = "account_register_time")
    private LocalDate accountRegisterTime;

    @Column(name = "account_login_time")
    private LocalDate accountLoginTime;

    @ManyToOne
    @JoinColumn(referencedColumnName = "customer_id", name = "customer_id")
    private Customer customer;

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    @JsonIgnore
    public String getAccountPassword() {
        return accountPassword;
    }

    @JsonProperty
    public void setAccountPassword(String accountPassword) {
        this.accountPassword = accountPassword;
    }

    @JsonIgnore
    public String getAccountEmail() {
        return accountEmail;
    }

    @JsonProperty
    public void setAccountEmail(String accountEmail) {
        this.accountEmail = accountEmail;
    }

    public AccountStatus getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(AccountStatus accountStatus) {
        this.accountStatus = accountStatus;
    }

    public LocalDate getAccountRegisterTime() {
        return accountRegisterTime;
    }

    public void setAccountRegisterTime(LocalDate accountRegisterTime) {
        this.accountRegisterTime = accountRegisterTime;
    }

    public LocalDate getAccountLoginTime() {
        return accountLoginTime;
    }

    public void setAccountLoginTime(LocalDate accountLoginTime) {
        this.accountLoginTime = accountLoginTime;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
