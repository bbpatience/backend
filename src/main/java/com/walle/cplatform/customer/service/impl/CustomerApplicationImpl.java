package com.walle.cplatform.customer.service.impl;

import com.walle.cplatform.customer.service.CustomerApplication;
import com.walle.cplatform.customer.service.CustomerService;
import com.walle.cplatform.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: bbpatience
 * @date: 2018/10/22
 * @description: UserApplicationImpl
 **/
@Service
public class CustomerApplicationImpl implements CustomerApplication {

    private CustomerService customerService;

    private UserService userService;

    @Autowired
    public CustomerApplicationImpl(CustomerService customerService, UserService userService) {
        this.userService = userService;
        this.customerService = customerService;
    }
}
