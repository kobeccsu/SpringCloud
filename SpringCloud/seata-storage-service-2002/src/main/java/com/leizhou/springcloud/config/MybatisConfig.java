package com.leizhou.springcloud.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.leizhou.springcloud.dao")
public class MybatisConfig {
}
