package com.walle.cplatform.user.service.impl;

import com.walle.cplatform.common.RestResult;
import com.walle.cplatform.pojos.OutputId;
import com.walle.cplatform.user.bean.UserBean;
import com.walle.cplatform.user.enums.UserState;
import com.walle.cplatform.user.enums.UserType;
import com.walle.cplatform.user.pojos.InputUserCreate;
import com.walle.cplatform.user.pojos.OutputUserInfo;
import com.walle.cplatform.user.service.UserApplication;
import com.walle.cplatform.user.service.UserService;
import com.walle.cplatform.utils.RestResultCode;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @author: bbpatience
 * @date: 2018/10/22
 * @description: UserApplicationImpl
 **/
@Service
public class UserApplicationImpl implements UserApplication {

    private UserService userService;

    @Autowired
    public UserApplicationImpl(UserService userService) {
        this.userService = userService;
    }


    @Override
    public RestResult login(String username, String password) {
        RestResultCode result = userService.login(username, password);
        if (RestResultCode.COMMON_SUCCESS == result) {
            return RestResult.success();
        } else {
            return RestResult.generate(result);
        }
    }

    @Override
    public RestResult logout() {
        RestResultCode result = userService.logout();
        if (RestResultCode.COMMON_SUCCESS == result) {
            return RestResult.success();
        } else {
            return RestResult.generate(result);
        }
    }

    @Override
    public RestResult createUser(InputUserCreate data) {
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
        return RestResult.success(new OutputId(userService.createUser(data)));
    }

    @Override
    public RestResult updateUser(String uid, InputUserCreate data) {
        RestResultCode result = userService.updateUser(uid, data);
        if (RestResultCode.COMMON_SUCCESS == result) {
            return RestResult.success();
        } else {
            return RestResult.generate(result);
        }
    }

    @Override
    public RestResult updateUserState(String uid, Integer state) {
        RestResultCode result = userService.updateUserState(uid, state);
        if (RestResultCode.COMMON_SUCCESS == result) {
            return RestResult.success();
        } else {
            return RestResult.generate(result);
        }
    }

    @Override
    public RestResult deleteUser(String uid) {
        RestResultCode result = userService.deleteUser(uid);
        if (RestResultCode.COMMON_SUCCESS == result) {
            return RestResult.success();
        } else {
            return RestResult.generate(result);
        }
    }

    @Override
    public RestResult getUser(String uid) {
        OutputUserInfo userInfo = new OutputUserInfo(userService.getUser(uid));
        return RestResult.success().setData(userInfo);
    }

    @Override
    public RestResult getUserList(Integer type, Integer state) {
        if (type != null && (type > UserType.CUSTOMER.getType() || type < UserType.ADMIN.getType()) ) {
            return RestResult.generate(RestResultCode.COMMON_INVALID_PARAMETER);
        }
        if (state != null && (state > UserState.DELETED.getState() || state < UserState.NORMAL.getState()) ) {
            return RestResult.generate(RestResultCode.COMMON_INVALID_PARAMETER);
        }
        List<OutputUserInfo> userInfos = new ArrayList<>();
        userService.getUserList(type, state).forEach(user -> {
            OutputUserInfo userInfo = new OutputUserInfo(user);
            userInfos.add(userInfo);
        });
        return RestResult.success().setData(userInfos);
    }
}
