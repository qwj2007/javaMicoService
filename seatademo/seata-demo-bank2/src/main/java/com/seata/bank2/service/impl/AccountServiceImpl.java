package com.seata.bank2.service.impl;


import com.seata.bank2.mapper.bank2.AccountMapper;
import com.seata.bank2.pojo.Account;
import com.seata.bank2.service.IAccountService;


import io.seata.core.context.RootContext;
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

    @Override
    @Transactional//本地分布式事务
    public String updateAccount(String accountName, double amount) {
        logger.info("开始执行bank2转账方法,事务ID："+ RootContext.getXID());
        Account account = new Account();
        account.setAccountName(accountName);
        account.setAccountAmount((long) amount);
        accountMapper.updateAccount(account);
        if (amount == 200.00) {
            throw new RuntimeException("bank2单笔200块钱,xid:"+RootContext.getXID());
        }
        return "bank1"+amount;

    }
}
