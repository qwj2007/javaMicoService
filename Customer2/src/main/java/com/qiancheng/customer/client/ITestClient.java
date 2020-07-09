package com.qiancheng.customer.client;

import com.qiancheng.customer.client.hystrix.TestClientHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 作者：qiwj
 * 时间：2020/7/9
 */

@FeignClient(value = "COM.QIANCHENG.PRODUCER", fallback = TestClientHystrix.class)
public interface ITestClient {
    @RequestMapping(value = "/producer/test/getName")
    String getName();

}
