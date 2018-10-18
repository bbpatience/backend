package com.walle.cplatform.user.service.impl;

import com.walle.cplatform.common.RestResult;
import com.walle.cplatform.shiro.util.RedisCacheSessionDao;
import com.walle.cplatform.user.UserState;
import com.walle.cplatform.user.bean.UserBean;
import com.walle.cplatform.user.mapper.UserMapper;
import com.walle.cplatform.user.service.UserService;
import com.walle.cplatform.utils.RestResultCode;
import com.walle.cplatform.utils.ShiroUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    private UserMapper userMapper;
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    public UserServiceImpl(UserMapper userMapper, RedisTemplate<String, String> redisTemplate) {
        this.userMapper = userMapper;
        this.redisTemplate = redisTemplate;
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public RestResult login(String username, String password) {
        UserBean user = getUserByUsername(username);

        if (user == null) {
            logger.info("login fail.");
            return RestResult.generate(RestResultCode.USER_USER_NOT_FOUND);
        }

        if (!checkUserState(user)) {
            logger.info("login fail.");
            return RestResult.generate(RestResultCode.USER_USER_DISABLED);
        }
        RestResultCode rspCode = RestResultCode.COMMON_SUCCESS;
        AuthenticationToken token = new UsernamePasswordToken(username, password);
        try {
            ShiroUtils.getSubject().login(token);
            ShiroUtils.setAttribute(ShiroUtils.USER_ID, user.getId());
            ShiroUtils.setAttribute(ShiroUtils.USER_UID, user.getUid());
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

    private boolean checkUserState(UserBean user) {
        return user != null && user.getState() == UserState.NORMAL.getState();
    }

    @Override
    public UserBean getUserByUsername(String username) {
        if (StringUtils.isEmpty(username)) {
            return null;
        }
        UserBean userBean = new UserBean();
        userBean.setUsername(username);
        return userMapper.selectOne(userBean);
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public RestResult logout() {
        Subject subject = SecurityUtils.getSubject();
        if (subject != null) {
            String username = (String) subject.getPrincipal();

            String uid = (String) ShiroUtils.getAttribute(ShiroUtils.USER_UID);
            String sessionId = SecurityUtils.getSubject().getSession().getId().toString();
            subject.logout();

            SetOperations<String, String> setOperations = redisTemplate.opsForSet();
            String key = RedisCacheSessionDao.SESSION_KEY_PREFIX + uid;
            setOperations.remove(key, sessionId);
            logger.info("logout, username={}", username);
        }

        return RestResult.generate(RestResultCode.COMMON_SUCCESS);
    }
}
