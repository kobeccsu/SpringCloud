package com.leizhou.springcloud.myhandler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.leizhou.springcloud.entities.CommonResult;
import com.leizhou.springcloud.entities.Payment;
import org.springframework.stereotype.Component;

@Component
public class CustomerBlockHandler {

    public  static CommonResult handlerException1(BlockException ex){
        return new CommonResult(444, "GLOBAL HANDLER 1");
    }

    public  static CommonResult handlerException2(BlockException ex){
        return new CommonResult(444, "GLOBAL HANDLER 2");
    }
}
