package com.seata.bank20.controller;

import com.seata.bank2.service.impl.AccountServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 作者：qiwj
 * 时间：2020/7/16
 */
@RestController
@RequestMapping("bank2")
public class bank2Controller {
    private static final Logger logger  = LoggerFactory.getLogger(bank2Controller.class);
    @Autowired
    private AccountServiceImpl accountService;

    @RequestMapping(value="/updateBank2")
    public String GetBank2(String accountname, double amount) {
        accountService.updateAccount(accountname, amount);
        return "bank2:"+amount;
    }
}
