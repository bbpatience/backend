package com.walle.cplatform.customer.service;

import com.walle.cplatform.customer.bean.CustomerBean;
import com.walle.cplatform.customer.pojos.InputCustomerCreate;
import com.walle.cplatform.utils.RestResultCode;

import java.util.List;

public interface CustomerService {

    String createCustomer(String uid, InputCustomerCreate data);
    RestResultCode updateCustomer(String uid, InputCustomerCreate data);
    RestResultCode deleteCustomer(String uid);
    CustomerBean getCustomer(String uid);
    List<CustomerBean> getCustomerList(Integer state);
}
