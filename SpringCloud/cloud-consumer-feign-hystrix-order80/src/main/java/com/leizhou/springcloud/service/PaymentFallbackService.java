package com.leizhou.springcloud.service;

import org.springframework.stereotype.Component;

@Component
public class PaymentFallbackService implements PaymentHystrixService {
    @Override
    public String paymentInfo_OK(Integer id) {
        return "payment fallback service fall back OK";
    }

    @Override
    public String paymentInfo_Timeout(Integer id) {
        return "payment fallback service fall back Timeout";
    }
}
