package com.walle.cplatform.service.impl;

import com.walle.cplatform.common.RestResult;
import com.walle.cplatform.entity.User;
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
        User user = userMapper.findAll();
        OutputUserInfo out = new OutputUserInfo();
        out.setName(user.getName());
        out.setMobile(user.getMobile());
        return RestResult.success().setData(out);
    }
}
