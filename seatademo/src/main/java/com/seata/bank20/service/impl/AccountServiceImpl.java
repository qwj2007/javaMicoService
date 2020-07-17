package com.seata.bank20.service.impl;


import com.seata.bank20.mapper.bank2.AccountMapper;
import com.seata.bank20.pojo.Account;
import com.seata.bank20.service.IAccountService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * 作者：qiwj
 * 时间：2020/7/16
 */
@Service

public class AccountServiceImpl implements IAccountService {
    private static final Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);
    @Autowired
    private AccountMapper accountMapper;


    public String updateAccount(String accountName, double amount) {
        return null;
    }
}
