package com.walle.cplatform.user.controller;

import com.walle.cplatform.common.RestResult;
import com.walle.cplatform.user.pojos.InputLogin;
import com.walle.cplatform.user.service.UserService;

import com.walle.cplatform.utils.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
        logger.info("User login called.");

        if (StringUtils.isEmpty(data.getUsername())) {
            return RestResult.generate(Constants.SYS_FAIL_FLAG_INVALID_USERNAME);
        }

        if (StringUtils.isEmpty(data.getPassword())) {
            return RestResult.generate(Constants.SYS_FAIL_FLAG_INVALID_PASSWORD);
        }
		return userService.login(data.getUsername(), data.getPassword());
	}

    @GetMapping(path="/exception")
    public @ResponseBody void exception() throws Exception {
        throw new IllegalAccessException("IllegalAccessException");
    }
}
