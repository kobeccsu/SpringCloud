package com.leizhou.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.leizhou.springcloud.entities.CommonResult;
import com.leizhou.springcloud.entities.Payment;
import com.leizhou.springcloud.service.PaymentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
public class CircleBreakerController {
    @Value("${service-url.nacos-user-service}")
    public String SERVICE_URL;

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/consumer/fallback/{id}")
//    #@SentinelResource(value = "fallback", fallback = "handlerFallback")
    @SentinelResource(value = "byResource", fallback = "handlerFallback", blockHandler = "handleException",
    exceptionsToIgnore = {IllegalArgumentException.class})
    public CommonResult<Payment> fallback(@PathVariable("id") Long id){
        CommonResult result = restTemplate.getForObject(SERVICE_URL + "/paymentSQL/" + id, CommonResult.class, id);

        if (id == 4){
            throw new IllegalArgumentException("wrong args");
        } else if (result.getData() == null){
            throw new NullPointerException("no data found");
        }

        return result;
    }

    public CommonResult handlerFallback(@PathVariable("id") Long id, Throwable e){
        Payment payment = new Payment(id, "null");
        return new CommonResult(444, "Exception handler caught it" + e.getMessage(), payment);
    }

    public  CommonResult handleException(@PathVariable("id") Long id, BlockException ex){
        return new CommonResult(555, ex.getClass().getCanonicalName() + "\t service not available");
    }

    @Resource
    private PaymentService paymentService;

    @GetMapping(value = "/consumer/paymentSQL/{id}")
    public CommonResult<Payment> paymentSQL(@PathVariable("id") Long id){
        CommonResult<Payment> paymentCommonResult = paymentService.paymentSQL(id);
        return paymentCommonResult;
    }
}
