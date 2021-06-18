package com.leizhou.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@RestController
public class FlowLimitController {

    @GetMapping("/testA")
    public String testA(){

        return "------ testA";
    }

    @GetMapping("/testB")
    public String testB(){
        System.out.println(new Date() + " thread: " + Thread.currentThread().getName() );
        return "------- testB";
    }

    @GetMapping("/testD")
    public String testD(){
        int i=10/0;
        System.out.println("测试 D " + new Date() + " thread: " + Thread.currentThread().getName() );
        return "------- testB";
    }

    @GetMapping("/testHotKey")
    @SentinelResource(value = "testHotKey", blockHandler = "deal_HotKey")
    public String testHotKey(@RequestParam(value = "p1", required = false) String p1,
                             @RequestParam(value = "p2", required = false) String p2){
        return "----------testHotKey";
    }

    public String deal_HotKey(String p1, String p2, BlockException ex){
        return "-------- deal_HotKey";
    }
}
