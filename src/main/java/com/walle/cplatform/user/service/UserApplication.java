package com.walle.cplatform.user.service;

import com.walle.cplatform.common.RestResult;
import com.walle.cplatform.user.pojos.InputUserCreate;

public interface UserApplication {
    RestResult login(String username, String password);

    RestResult logout();

    RestResult createUser(InputUserCreate data);

    RestResult updateUser(String uid, InputUserCreate data);

    RestResult updateUserState(String uid, Integer state);

    RestResult deleteUser(String uid);

    RestResult getUser(String uid);

    RestResult getUserList(Integer type, Integer state);
}
