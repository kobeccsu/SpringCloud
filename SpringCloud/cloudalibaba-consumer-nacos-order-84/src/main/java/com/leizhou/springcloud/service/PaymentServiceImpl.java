package com.leizhou.springcloud.service;

import com.leizhou.springcloud.entities.CommonResult;
import com.leizhou.springcloud.entities.Payment;
import org.springframework.stereotype.Component;

@Component
public class PaymentServiceImpl implements PaymentService {
    @Override
    public CommonResult<Payment> paymentSQL(Long id) {
        return new CommonResult(444, "service low down", new Payment(id, "1234") );
    }
}
