package com.walle.cplatform.user.service.impl;

import com.walle.cplatform.common.RestResult;
import com.walle.cplatform.user.mapper.UserMapper;
import com.walle.cplatform.user.service.UserService;
import com.walle.cplatform.utils.Constants;
import com.walle.cplatform.utils.RestResultCode;
import com.walle.cplatform.utils.ShiroUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    private UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public RestResult login(String username, String password) {
        RestResultCode rspCode = RestResultCode.COMMON_SUCCESS;
        AuthenticationToken token = new UsernamePasswordToken(username, password);
        try {
            ShiroUtils.getSubject().login(token);
        } catch (UnknownAccountException e) {
            rspCode = RestResultCode.USER_USER_NOT_FOUND;
        } catch (IncorrectCredentialsException unknown) {
            rspCode = RestResultCode.USER_NAME_PWD_NOT_MATCH;
        } catch (LockedAccountException incorrect) {
            rspCode = RestResultCode.USER_USER_DISABLED;
        } catch (AuthenticationException e) {
            rspCode = RestResultCode.USER_NAME_PWD_NOT_MATCH;
        } catch (Exception e) {
            logger.error("login failure", e);
            rspCode = RestResultCode.COMMON_SERVER_ERROR;
        }
        return RestResult.generate(rspCode);
    }
}
