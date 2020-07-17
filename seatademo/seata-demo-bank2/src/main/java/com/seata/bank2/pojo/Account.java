package com.seata.bank2.pojo;


public class Account {
    private Long id;

    private String accountName;

    private Long accountAmount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName == null ? null : accountName.trim();
    }

    public Long getAccountAmount() {
        return accountAmount;
    }

    public void setAccountAmount(Long accountAmount) {
        this.accountAmount = accountAmount;
    }
}