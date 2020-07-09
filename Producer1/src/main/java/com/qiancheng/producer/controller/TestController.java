package com.qiancheng.producer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 作者：qiwj
 * 时间：2020/7/9
 */
@Controller
@RequestMapping("test")
public class TestController {
    @RequestMapping("/getName")
    @ResponseBody
    public String getTestName()
    {
        return "i am producer2";
    }
}
