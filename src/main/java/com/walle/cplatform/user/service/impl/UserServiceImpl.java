package com.walle.cplatform.user.service.impl;

import com.walle.cplatform.common.RestResult;
import com.walle.cplatform.user.mapper.UserMapper;
import com.walle.cplatform.user.service.UserService;
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
