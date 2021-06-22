package com.leizhou.springcloud.service.impl;

import com.leizhou.springcloud.dao.StorageDao;
import com.leizhou.springcloud.service.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.logging.Logger;

@Service
@Slf4j
public class StorageServiceImpl implements StorageService {

    @Resource
    private StorageDao storageDao;

    @Override
    public void decrease(Long productId, Integer count) {
        log.info("start minus storage .....");
        storageDao.decrease(productId, count);
        log.info("end minus storage ......");
    }
}
