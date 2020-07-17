package com.seata.bank11.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 作者：qiwj
 * 时间：2020/7/16
 */

//@FeignClient(value = "seata-demo-bank2", fallback = Bank2ServiceClientImpl.class)
@FeignClient(value = "seata-demo-bank2")
public interface Ibank2ServiceClient {
    @RequestMapping(value="/bank2/updateBank2")
    String updateBank2(@RequestParam("accountname") String accountname, @RequestParam("amount") double amount);
}
