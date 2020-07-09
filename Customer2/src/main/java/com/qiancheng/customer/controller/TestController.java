package com.qiancheng.customer.controller;

import com.qiancheng.customer.client.ITestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 作者：qiwj
 * 时间：2020/7/9
 */
@RestController
@RequestMapping("customer")
public class TestController {
    @Autowired
    private ITestClient testClient;
    @RequestMapping("/getCustomer")
    public String getCustomer()
    {
        String name="sfdsfdsf";
       return testClient.getName();
    }
}
