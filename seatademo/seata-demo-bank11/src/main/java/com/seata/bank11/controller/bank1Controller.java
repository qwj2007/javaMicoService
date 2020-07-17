package com.seata.bank11.controller;

import com.seata.bank11.service.impl.AccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 作者：qiwj
 * 时间：2020/7/16
 */
@RestController
@RequestMapping("bank1")
public class bank1Controller {
    @Autowired
    private AccountServiceImpl accountService;

    @RequestMapping("/updateBank2")
    public String GetBank2(String accountname, double amount) {

     return    accountService.updateAccount(accountname, amount);

    }
}
