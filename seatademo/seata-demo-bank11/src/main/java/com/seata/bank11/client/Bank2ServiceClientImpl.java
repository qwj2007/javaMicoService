package com.seata.bank11.client;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 作者：qiwj
 * 时间：2020/7/16
 */
@Component
public class Bank2ServiceClientImpl implements Ibank2ServiceClient {
    private static final Logger logger = LoggerFactory.getLogger(Bank2ServiceClientImpl.class);

    @Override
    public String updateBank2(String accountname, double amount) {
        return "fallback";
    }
}
