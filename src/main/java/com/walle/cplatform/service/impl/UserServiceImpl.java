package com.walle.cplatform.service.impl;

import com.walle.cplatform.common.RestResult;
import com.walle.cplatform.bean.UserBean;
import com.walle.cplatform.mapper.UserMapper;
import com.walle.cplatform.pojos.OutputUserInfo;
import com.walle.cplatform.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public RestResult findAll() {
//        UserBean userBean = userMapper.findAll();
//        OutputUserInfo out = new OutputUserInfo();
//        out.setName(userBean.getName());
//        out.setMobile(userBean.getMobile());
        return RestResult.success();
    }
}
