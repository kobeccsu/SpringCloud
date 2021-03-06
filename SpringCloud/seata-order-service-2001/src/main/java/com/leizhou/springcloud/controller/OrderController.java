package com.leizhou.springcloud.controller;

import com.leizhou.springcloud.domain.CommonResult;
import com.leizhou.springcloud.domain.Order;
import com.leizhou.springcloud.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class OrderController {

    @Resource
    private OrderService orderService;

    @GetMapping("/order/create")
    public CommonResult create(Order order){
        orderService.create(order);

        return new CommonResult(200, "order created");
    }
}
