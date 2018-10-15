package com.walle.cplatform.utils;

public class Constants {

    /**
     * Response.
     */
    public static String SUCCESS_FLAG = "10000";
    public static String SUCCESS_MSG = "Success!";
    public static String SYS_FAIL_FLAG = "10001";
    public static String SYS_FAIL_MSG = "Server Failed!";

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
