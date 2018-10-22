package com.walle.cplatform.user.service;

import com.walle.cplatform.user.bean.UserBean;
import com.walle.cplatform.user.pojos.InputUserCreate;
import com.walle.cplatform.utils.RestResultCode;
import java.util.List;

public interface UserService {
    RestResultCode login(String username, String password);

    RestResultCode logout();

    String createUser(InputUserCreate data);

    RestResultCode updateUser(String uid, InputUserCreate data);

    RestResultCode updateUserState(String uid, Integer state);

    RestResultCode deleteUser(String uid);

    UserBean getUser(String uid);

    List<UserBean> getUserList(Integer type, Integer state);

    UserBean getUserByUsername(String username);
}
