package com.walle.cplatform.customer.service;


import com.walle.cplatform.common.RestResult;
import com.walle.cplatform.customer.pojos.InputCustomerCreate;

public interface CustomerApplication {
    RestResult createCustomer(InputCustomerCreate data);
    RestResult updateCustomer(String uid, InputCustomerCreate data);
    RestResult deleteCustomer(String uid);
    RestResult getCustomer(String uid);
    RestResult getCustomerList(Integer state);
}
