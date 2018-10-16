package com.walle.cplatform.user.service;

import com.walle.cplatform.common.RestResult;

public interface UserService {
    RestResult login(String username, String password);
}
