package com.leizhou.springcloud.service;

import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

public interface AccountService {
    void decrease(@RequestParam("userId") long userId, @RequestParam("money") BigDecimal money);
}
