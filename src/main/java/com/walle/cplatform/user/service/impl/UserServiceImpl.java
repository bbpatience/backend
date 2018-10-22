package com.walle.cplatform.user.service.impl;

import com.walle.cplatform.shiro.util.RedisCacheSessionDao;
import com.walle.cplatform.user.enums.UserState;
import com.walle.cplatform.user.bean.UserBean;
import com.walle.cplatform.user.mapper.UserMapper;
import com.walle.cplatform.user.pojos.InputUserCreate;
import com.walle.cplatform.user.service.UserService;
import com.walle.cplatform.utils.AuthenticationUtils;
import com.walle.cplatform.utils.DateTimeUtils;
import com.walle.cplatform.utils.RestResultCode;
import com.walle.cplatform.utils.ShiroUtils;
import com.walle.cplatform.utils.ShortUuid;
import java.util.List;
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
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

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
    public RestResultCode login(String username, String password) {
        UserBean user = getUserByUsername(username);

        if (user == null) {
            logger.info("login fail.");
            return RestResultCode.USER_USER_NOT_FOUND;
        }

        if (!checkUserState(user)) {
            logger.info("login fail.");
            return RestResultCode.USER_USER_DISABLED;
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
        return rspCode;
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
    public RestResultCode logout() {
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

        return RestResultCode.COMMON_SUCCESS;
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public String createUser(InputUserCreate data) {

        String uid = new ShortUuid.Builder().build().toString();
        String salt = new ShortUuid.Builder().build().toString();

        // default password
        String inputPassword;
        if (StringUtils.isEmpty(data.getPassword())) {
            inputPassword = data.getUsername().substring(5); // last 6 chars as password.
        } else {
            inputPassword = data.getPassword();
        }
        String password = AuthenticationUtils.encryptPassword(inputPassword, salt);
        UserBean userBean = new UserBean();
        userBean.setUsername(data.getUsername());
        userBean.setGender(data.getGender());
        userBean.setBirthday(data.getBirthday());
        userBean.setName(data.getName());
        userBean.setMobile(data.getUsername());

//        userBean.setKeyword();
        userBean.setPassword(password);
        userBean.setPwdsalt(salt);
        userBean.setState(UserState.NORMAL.getState());
        userBean.setType(data.getUserType());
        userBean.setCreate_dt(DateTimeUtils.currentUTC());
        userBean.setUpdate_dt(DateTimeUtils.currentUTC());
        userBean.setUid(uid);

        userMapper.insert(userBean);
        return uid;
    }

    @Override
    public RestResultCode updateUser(String uid, InputUserCreate data) {
        UserBean user = getUserByUid(uid);
        if (user == null) {
            return RestResultCode.USER_USER_NOT_FOUND;
        }
        UserBean userBean = new UserBean();
        if (!StringUtils.isEmpty(data.getPassword())) {
            String password = AuthenticationUtils
                .encryptPassword(data.getPassword(), user.getPwdsalt());
            userBean.setPassword(password);
        }
        if (data.getGender() != null) {
            userBean.setGender(data.getGender());
        }
        if (data.getBirthday() != null) {
            userBean.setBirthday(data.getBirthday());
        }
        if (!StringUtils.isEmpty(data.getName())) {
            userBean.setName(data.getName());
//            userBean.setKeyword();
        }
        userBean.setUpdate_dt(DateTimeUtils.currentUTC());

        Example example = new Example(UserBean.class);
        example.createCriteria().andEqualTo("uid", uid);
        userMapper.updateByExampleSelective(userBean, example);
        return RestResultCode.COMMON_SUCCESS;
    }

    @Override
    public RestResultCode deleteUser(String uid) {
        UserBean user = getUserByUid(uid);
        if (user == null) {
            return RestResultCode.USER_USER_NOT_FOUND;
        } else if (user.getState() == UserState.DELETED.getState()){
            return RestResultCode.USER_USER_DISABLED;
        }
        return updateUserState(uid, UserState.DELETED.getState());
    }

    @Override
    public UserBean getUser(String uid) {
        return getUserByUid(uid);
    }

    @Override
    public List<UserBean> getUserList(Integer type, Integer state) {
        Example example = new Example(UserBean.class);
        Criteria criteria = example.createCriteria();
        if (type != null) {
            criteria.andEqualTo("type", type);
        }
        if (state != null) {
            criteria.andEqualTo("state", state);
        }
        return userMapper.selectByExample(example);
    }

    @Override
    public RestResultCode updateUserState(String uid, Integer state) {
        UserBean user = getUserByUid(uid);
        if (user == null) {
            return RestResultCode.USER_USER_NOT_FOUND;
        }
        if (state > UserState.DELETED.getState() || state < UserState.NORMAL.getState()) {
            return RestResultCode.COMMON_INVALID_PARAMETER;
        }
        UserBean userBean = new UserBean();
        userBean.setState(state);
        userBean.setUpdate_dt(DateTimeUtils.currentUTC());

        Example example = new Example(UserBean.class);
        example.createCriteria().andEqualTo("uid", uid);
        userMapper.updateByExampleSelective(userBean, example);
        return RestResultCode.COMMON_SUCCESS;
    }

    private UserBean getUserByUid(String uid) {
        if (StringUtils.isEmpty(uid)) {
            return null;
        }
        UserBean userBean = new UserBean();
        userBean.setUid(uid);
        return userMapper.selectOne(userBean);
    }
}
