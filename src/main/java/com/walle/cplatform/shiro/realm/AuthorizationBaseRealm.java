package com.walle.cplatform.shiro.realm;

import com.walle.cplatform.shiro.service.ShiroService;
import com.walle.cplatform.utils.Constants;
import com.walle.cplatform.utils.ShiroUtils;
import java.util.Collection;
import java.util.Map;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

/**
 * @Author zxx
 * @Description
 * @Date Created on 2017/11/10
 */
public abstract class AuthorizationBaseRealm extends AuthorizingRealm {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    protected ShiroService shiroService;

    /**
     * 获取授权信息
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        logger.info("开始授权");
        if (!principals.isEmpty() && principals.fromRealm(getName()).size() > 0) {
            Object userid = ShiroUtils.getAttribute(ShiroUtils.USER_ID);
            if (userid != null) {
                SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
                // roles | perms
                Map<String, Collection<String>> rolesAndPerms = shiroService
                    .getUserRolesAndPerms(userid);
                Collection<String> roles = rolesAndPerms.get(Constants.DEFAULT_ROLES_KEY);
                Collection<String> perms = rolesAndPerms.get(Constants.DEFAULT_PERMS_KEY);
                if (!CollectionUtils.isEmpty(roles)) {
                    info.addRoles(roles);
                }
                if (!CollectionUtils.isEmpty(perms)) {
                    info.addStringPermissions(perms);
                }
                return info;
            } else {
                logger.error("id is empty!!!");
                return null;
            }
        } else {
            logger.debug("principals is empty!!!");
            return null;
        }
    }
}
