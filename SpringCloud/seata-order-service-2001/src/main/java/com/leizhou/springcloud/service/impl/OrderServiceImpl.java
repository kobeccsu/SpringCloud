package com.leizhou.springcloud.service.impl;

import com.leizhou.springcloud.dao.OrderDao;
import com.leizhou.springcloud.domain.Order;
import com.leizhou.springcloud.service.AccountService;
import com.leizhou.springcloud.service.OrderService;
import com.leizhou.springcloud.service.StorageService;
import io.seata.spring.annotation.GlobalTransactional;
import jdk.nashorn.internal.runtime.options.LoggingOption;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoProperties;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Resource
    private OrderDao orderDao;

    @Resource
    private StorageService storageService;

    @Resource
    private AccountService accountService;

    @Override
    @GlobalTransactional(name = "nima-strong", rollbackFor = Exception.class)
    public void create(Order order) {
        log.info("begin create order");

        orderDao.create(order);

        log.info("end create order");

        log.info("start minus order repo");

        storageService.decrease(order.getProductId(), order.getCount());

        log.info("end minus order repo ");

        log.info("start minus account money");
        accountService.decrease(order.getUserId(), order.getMoney());
        log.info("end minus account money");

        log.info("start update order status");
        orderDao.update(order.getUserId(), 0);
        log.info("end update order statue");

        log.info("finished order");
    }
}
