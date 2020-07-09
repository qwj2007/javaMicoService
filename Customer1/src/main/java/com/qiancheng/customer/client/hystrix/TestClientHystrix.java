package com.qiancheng.customer.client.hystrix;

import com.qiancheng.customer.client.ITestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 作者：qiwj
 * 时间：2020/7/9
 */
@Component
public class TestClientHystrix implements ITestClient {
    private static final Logger logger  = LoggerFactory.getLogger(TestClientHystrix.class);
    @Override
    public String getName() {
       logger.error("getName 列表 调用失败");
        return "调用出错";
    }
}
