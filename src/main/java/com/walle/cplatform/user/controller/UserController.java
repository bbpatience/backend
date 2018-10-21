package com.walle.cplatform.user.controller;

import com.walle.cplatform.common.RestResult;
import com.walle.cplatform.user.pojos.InputLogin;
import com.walle.cplatform.user.pojos.InputUserCreate;
import com.walle.cplatform.user.service.UserService;

import com.walle.cplatform.utils.Constants;
import com.walle.cplatform.utils.RestResultCode;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/user")
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

	@PostMapping(path="/login")
	public @ResponseBody RestResult userLogin(@RequestBody InputLogin data) {
        logger.info("User login called. username:{}", data.getUsername());

        if (StringUtils.isEmpty(data.getUsername())) {
            return RestResult.generate(RestResultCode.USER_INVALID_USERNAME);
        }

        if (StringUtils.isEmpty(data.getPassword())) {
            return RestResult.generate(RestResultCode.USER_INVALID_PASSWORD);
        }
		return userService.login(data.getUsername(), data.getPassword());
	}

    @PostMapping(path="/logout")
    public @ResponseBody RestResult userLogout() {
        logger.info("User logout called.");
        return userService.logout();
    }

    @GetMapping(path="/exception")
    @RequiresRoles(Constants.USER_SUPER_ADMIN)
    public @ResponseBody void exception() throws Exception {
        throw new IllegalAccessException("IllegalAccessException");
    }

    @PutMapping("/create")
    @RequiresRoles(Constants.USER_SUPER_ADMIN)
    public RestResult createUser(@RequestBody InputUserCreate data) {
        return userService.createUser(data);
    }

    @PostMapping("/{uid}")
    @RequiresRoles(Constants.USER_SUPER_ADMIN)
    public RestResult updateUser(@RequestBody InputUserCreate data, @PathVariable("uid") String uid) {
        return userService.updateUser(uid, data);
    }

    @PostMapping("/{uid}/state")
    @RequiresRoles(Constants.USER_SUPER_ADMIN)
    public RestResult updateUserState(
        @RequestParam(value = "state", defaultValue = "0") Integer state
        , @PathVariable("uid") String uid) {
        return userService.updateUserState(uid, state);
    }

    @DeleteMapping("/{uid}")
    @RequiresRoles(Constants.USER_SUPER_ADMIN)
    public RestResult deleteUser(@PathVariable("uid") String uid) {
        return userService.deleteUser(uid);
    }

    @GetMapping("/{uid}")
    @RequiresRoles(Constants.USER_SUPER_ADMIN)
    public RestResult getUser(@PathVariable("uid") String uid) {
        return userService.getUser(uid);
    }

    @GetMapping("/list")
    @RequiresRoles(Constants.USER_SUPER_ADMIN)
    public RestResult getUserList(@RequestParam(value = "type", required = false) Integer type,
        @RequestParam(value = "state", required = false) Integer state) {
        return userService.getUserList(type, state);
    }
}
