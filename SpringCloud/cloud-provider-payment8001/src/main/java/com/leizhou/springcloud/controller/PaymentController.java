package com.leizhou.springcloud.controller;

import com.leizhou.springcloud.entities.CommonResult;
import com.leizhou.springcloud.entities.Payment;
import com.leizhou.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class PaymentController {
    @Resource
    private PaymentService paymentService;

    @PostMapping(value = "/payment/create")
    public CommonResult create(Payment payment){
        int result = paymentService.create(payment);
        log.info("inserted result: " + result);

        if (result > 0){
            return new CommonResult(200, "insert successfully", result);
        }

        return new CommonResult(444, "insert fail", null);
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id){
        Payment payment = paymentService.getPaymentById(id);
        log.info("inserted result: " + payment);

        if (payment != null){
            return new CommonResult(200, "select successfully", payment);
        }

        return new CommonResult(444, "select fail", null);
    }
}
