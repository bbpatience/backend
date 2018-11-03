package com.walle.cplatform.customer.service.impl;

import com.walle.cplatform.customer.bean.CustomerBean;
import com.walle.cplatform.customer.mapper.CustomerMapper;
import com.walle.cplatform.customer.pojos.InputCustomerCreate;
import com.walle.cplatform.customer.service.CustomerService;
import com.walle.cplatform.utils.RestResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import java.util.List;

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

    @Override
    public String createCustomer(String userId, InputCustomerCreate data) {
        return null;
    }

    @Override
    public RestResultCode updateCustomer(String uid, InputCustomerCreate data) {
        return null;
    }

    @Override
    public RestResultCode deleteCustomer(String uid) {
        return null;
    }

    @Override
    public CustomerBean getCustomer(String uid) {
        return null;
    }

    @Override
    public List<CustomerBean> getCustomerList(Integer state) {
        return null;
    }
}
