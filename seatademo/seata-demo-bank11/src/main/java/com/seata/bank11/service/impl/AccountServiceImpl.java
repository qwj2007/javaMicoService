package com.seata.bank11.service.impl;

import com.seata.bank11.client.Ibank2ServiceClient;
import com.seata.bank11.mapper.bank1.AccountMapper;
import com.seata.bank11.pojo.Account;
import com.seata.bank11.service.IAccountService;


import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
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
    private Ibank2ServiceClient bank2;
    @Autowired
    private AccountMapper accountMapper;

    //*/
    @Override
    @Transactional//本地分布式事务
    @GlobalTransactional(rollbackFor = Exception.class)//分布式事务
    public String updateAccount(String accountName, double amount) {
String xid= RootContext.getXID();
        Account account = new Account();
        account.setAccountName(accountName);
        account.setAccountAmount((long) amount);
        logger.info("开始执行bank1转账方法,事务ID：" + xid);
        accountMapper.updateAccount(account);
        logger.info("开始调用Bank2转账方法,事务ID：" +xid);
        String result = bank2.updateBank2("2", amount);
        if ("fallback".equals(result)) {
            throw new RuntimeException(result+"bank2服务超时,XID:" +xid);
        }
        if (amount == 300.00) {
            throw new RuntimeException("bank1单笔300块钱,XID:" + xid);
        }
        return result;

    }
}
