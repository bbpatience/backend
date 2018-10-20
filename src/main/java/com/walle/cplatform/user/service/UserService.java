package com.walle.cplatform.user.service;

import com.walle.cplatform.common.RestResult;
import com.walle.cplatform.user.bean.UserBean;
import com.walle.cplatform.user.pojos.InputUserCreate;

public interface UserService {
    RestResult login(String username, String password);

    RestResult logout();

    RestResult createUser(InputUserCreate userInfo);

    UserBean getUserByUsername(String username);
}
