package com.walle.cplatform.utils;

import java.util.HashMap;
import java.util.Map;

public class Constants {

    /**
     * Response Code.
     */
    public static String SUCCESS_FLAG = "10000";
    public static String SYS_FAIL_FLAG = "10001";
    public static String SYS_FAIL_FLAG_INVALID_USERNAME = "10002";
    public static String SYS_FAIL_FLAG_INVALID_PASSWORD = "10003";
    public static String SYS_FAIL_FLAG_USER_NOT_FOUND = "10004";
    public static String SYS_FAIL_FLAG_NAME_PWD_NOT_MATCH = "10005";
    public static String SYS_FAIL_FLAG_USER_DISABLED = "10006";
    /**
     * Response Msg.
     */
    public static String SUCCESS_MSG = "Success!";
    public static String SYS_FAIL_MSG = "Server Failed!";
    public static String SYS_FAIL_MSG_INVALID_USERNAME = "Invalid Username";
    public static String SYS_FAIL_MSG_INVALID_PASSWORD = "Invalid Password";
    public static String SYS_FAIL_MSG_USER_NOT_FOUND = "User not found.";
    public static String SYS_FAIL_MSG_NAME_PWD_NOT_MATCH = "Username and Password not match.";
    public static String SYS_FAIL_MSG_USER_DISABLED = "User disabled.";
    private static final Map<String, String> map;
    static {
        map = new HashMap<>();
        map.put(SUCCESS_FLAG, SUCCESS_MSG);
        map.put(SYS_FAIL_FLAG, SYS_FAIL_MSG);
        map.put(SYS_FAIL_FLAG_INVALID_USERNAME, SYS_FAIL_MSG_INVALID_USERNAME);
        map.put(SYS_FAIL_FLAG_INVALID_PASSWORD, SYS_FAIL_MSG_INVALID_PASSWORD);
        map.put(SYS_FAIL_FLAG_USER_NOT_FOUND, SYS_FAIL_MSG_USER_NOT_FOUND);
        map.put(SYS_FAIL_FLAG_NAME_PWD_NOT_MATCH, SYS_FAIL_MSG_NAME_PWD_NOT_MATCH);
        map.put(SYS_FAIL_FLAG_USER_DISABLED, SYS_FAIL_MSG_USER_DISABLED);
    }

    public static String getRspMessage(String key) {
        return map.get(key);
    }

    public static final long GLOBAL_SESSION_TIMEOUT = 604800000L;

    /**
     * shiro
     */
    public static final String DEFAULT_ROLES_KEY = "roles";
    public static final String DEFAULT_PERMS_KEY = "perms";
    public static final String DEFAULT_ROLE = "user";
    public static final String DEFAULT_PWD_KEY = "password";
    // 用户身份唯一标识在Map中的key
    public static final String DEFAULT_IDENTITY_KEY = "username";
    public static final String DEFAULT_SALT_KEY = "salt";
}
