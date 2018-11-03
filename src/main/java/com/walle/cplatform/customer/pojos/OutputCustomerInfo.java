package com.walle.cplatform.customer.pojos;

import com.walle.cplatform.customer.bean.CustomerBean;
import com.walle.cplatform.user.bean.UserBean;
import com.walle.cplatform.user.pojos.OutputUserInfo;

public class OutputCustomerInfo extends OutputUserInfo {

    public OutputCustomerInfo(CustomerBean customer, UserBean user) {
        super(user);
    }
}
