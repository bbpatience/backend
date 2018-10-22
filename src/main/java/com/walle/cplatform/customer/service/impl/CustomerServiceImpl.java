package com.walle.cplatform.customer.service.impl;

import com.walle.cplatform.customer.mapper.CustomerMapper;
import com.walle.cplatform.customer.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    private static final Logger logger = LoggerFactory.getLogger(
        CustomerServiceImpl.class);
    private CustomerMapper userMapper;
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    public CustomerServiceImpl(CustomerMapper userMapper, RedisTemplate<String, String> redisTemplate) {
        this.userMapper = userMapper;
        this.redisTemplate = redisTemplate;
    }

}
