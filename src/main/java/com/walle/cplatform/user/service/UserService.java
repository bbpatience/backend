package com.walle.cplatform.user.service;

import com.walle.cplatform.common.RestResult;
import com.walle.cplatform.user.bean.UserBean;

public interface UserService {
    RestResult login(String username, String password);

    RestResult logout();

    UserBean getUserByUsername(String username);
}
