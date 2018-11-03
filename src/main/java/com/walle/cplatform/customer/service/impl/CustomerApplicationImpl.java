package com.walle.cplatform.customer.service.impl;

import com.walle.cplatform.common.RestResult;
import com.walle.cplatform.customer.bean.CustomerBean;
import com.walle.cplatform.customer.pojos.InputCustomerCreate;
import com.walle.cplatform.customer.pojos.OutputCustomerInfo;
import com.walle.cplatform.customer.service.CustomerApplication;
import com.walle.cplatform.customer.service.CustomerService;
import com.walle.cplatform.pojos.OutputId;
import com.walle.cplatform.user.bean.UserBean;
import com.walle.cplatform.user.enums.UserState;
import com.walle.cplatform.user.enums.UserType;
import com.walle.cplatform.user.pojos.OutputUserInfo;
import com.walle.cplatform.user.service.UserService;
import com.walle.cplatform.utils.RestResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: bbpatience
 * @date: 2018/10/22
 * @description: CustomerApplicationImpl
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

    @Override
    public RestResult createCustomer(InputCustomerCreate data) {
        if (StringUtils.isEmpty(data.getUsername()) || StringUtils.isEmpty(data.getName()) ||
                data.getUserType() > UserType.CUSTOMER.getType() ||
                data.getUserType() < UserType.ADMIN.getType() ) {
            return RestResult.generate(RestResultCode.COMMON_INVALID_PARAMETER);
        }
        String username = data.getUsername();
        UserBean user = userService.getUserByUsername(username);
        if (user != null) {
            return RestResult.generate(RestResultCode.USER_USER_NAME_EXIST);
        }
        String uid = userService.createUser(data);
        return RestResult.success(new OutputId(customerService.createCustomer(uid, data)));
    }

    @Override
    public RestResult updateCustomer(String uid, InputCustomerCreate data) {
        return null;
    }

    @Override
    public RestResult deleteCustomer(String uid) {
        RestResultCode result = userService.deleteUser(uid);
        if (RestResultCode.COMMON_SUCCESS == result) {
            RestResultCode resultCode = customerService.deleteCustomer(uid);
            if (RestResultCode.COMMON_SUCCESS == resultCode) {
                return RestResult.success();
            } else {
                return RestResult.generate(resultCode);
            }
        } else {
            return RestResult.generate(result);
        }
    }

    @Override
    public RestResult getCustomer(String uid) {
        UserBean user = userService.getUser(uid);
        CustomerBean customer = customerService.getCustomer(uid);
        OutputCustomerInfo customerInfo = new OutputCustomerInfo(customer, user);
        return RestResult.success().setData(customerInfo);
    }

    @Override
    public RestResult getCustomerList(Integer state) {
        if (state != null && (state > UserState.DELETED.getState() || state < UserState.NORMAL.getState()) ) {
            return RestResult.generate(RestResultCode.COMMON_INVALID_PARAMETER);
        }
        List<OutputCustomerInfo> infos = new ArrayList<>();
        userService.getUserList(UserType.CUSTOMER.getType(), state).forEach(user -> {
            CustomerBean customer = customerService.getCustomer(user.getUid());
            OutputCustomerInfo customerInfo = new OutputCustomerInfo(customer, user);
            infos.add(customerInfo);
        });
        return RestResult.success().setData(infos);
    }
}
