package com.walle.cplatform.controller;

import com.walle.cplatform.common.RestResult;
import com.walle.cplatform.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

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

	@GetMapping(path="/all")
	public @ResponseBody
    RestResult getAllUsers() {
        logger.info("Get UserBean All called.");
		return userService.findAll();
	}

    @GetMapping(path="/exception")
    public @ResponseBody void exception() throws Exception {
        throw new IllegalAccessException("IllegalAccessException");
    }
}
