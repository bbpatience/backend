package com.walle.cplatform.customer.controller;

import com.walle.cplatform.customer.service.CustomerApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/customer")
public class CustomerController {

	private static final Logger logger = LoggerFactory.getLogger(
        CustomerController.class);

	private CustomerApplication customerApplication;

    @Autowired
    public CustomerController(CustomerApplication customerApplication) {
        this.customerApplication = customerApplication;
    }
//
//	@PostMapping(path="/login")
//	public @ResponseBody RestResult userLogin(@RequestBody InputLogin data) {
//        logger.info("User login called. username:{}", data.getUsername());
//
//        if (StringUtils.isEmpty(data.getUsername())) {
//            return RestResult.generate(RestResultCode.USER_INVALID_USERNAME);
//        }
//
//        if (StringUtils.isEmpty(data.getPassword())) {
//            return RestResult.generate(RestResultCode.USER_INVALID_PASSWORD);
//        }
//		return userApplication.login(data.getUsername(), data.getPassword());
//	}
//
//    @PostMapping(path="/logout")
//    public @ResponseBody RestResult userLogout() {
//        logger.info("User logout called.");
//        return userApplication.logout();
//    }
//
//    @GetMapping(path="/exception")
//    @RequiresRoles(Constants.USER_SUPER_ADMIN)
//    public @ResponseBody void exception() throws Exception {
//        throw new IllegalAccessException("IllegalAccessException");
//    }
//
//    @PutMapping("/create")
//    @RequiresRoles(Constants.USER_SUPER_ADMIN)
//    public RestResult createUser(@RequestBody InputUserCreate data) {
//        return userApplication.createUser(data);
//    }
//
//    @PostMapping("/{uid}")
//    @RequiresRoles(Constants.USER_SUPER_ADMIN)
//    public RestResult updateUser(@RequestBody InputUserCreate data, @PathVariable("uid") String uid) {
//        return userApplication.updateUser(uid, data);
//    }
//
//    @PostMapping("/{uid}/state")
//    @RequiresRoles(Constants.USER_SUPER_ADMIN)
//    public RestResult updateUserState(
//        @RequestParam(value = "state", defaultValue = "0") Integer state
//        , @PathVariable("uid") String uid) {
//        return userApplication.updateUserState(uid, state);
//    }
//
//    @DeleteMapping("/{uid}")
//    @RequiresRoles(Constants.USER_SUPER_ADMIN)
//    public RestResult deleteUser(@PathVariable("uid") String uid) {
//        return userApplication.deleteUser(uid);
//    }
//
//    @GetMapping("/{uid}")
//    @RequiresRoles(Constants.USER_SUPER_ADMIN)
//    public RestResult getUser(@PathVariable("uid") String uid) {
//        return userApplication.getUser(uid);
//    }
//
//    @GetMapping("/list")
//    @RequiresRoles(Constants.USER_SUPER_ADMIN)
//    public RestResult getUserList(@RequestParam(value = "type", required = false) Integer type,
//        @RequestParam(value = "state", required = false) Integer state) {
//        return userApplication.getUserList(type, state);
//    }
}
