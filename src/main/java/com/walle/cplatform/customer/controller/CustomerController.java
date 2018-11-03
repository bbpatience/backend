package com.walle.cplatform.customer.controller;

import com.walle.cplatform.common.RestResult;
import com.walle.cplatform.customer.pojos.InputCustomerCreate;
import com.walle.cplatform.customer.service.CustomerApplication;
import com.walle.cplatform.utils.Constants;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/create")
    @RequiresRoles(Constants.USER_SUPER_ADMIN)
    public @ResponseBody RestResult createCustomer(@RequestBody InputCustomerCreate data) {
        return customerApplication.createCustomer(data);
    }

    @PostMapping("/{uid}")
    @RequiresRoles(Constants.USER_SUPER_ADMIN)
    public @ResponseBody RestResult updateCustomer(@RequestBody InputCustomerCreate data, @PathVariable("uid") String uid) {
        return customerApplication.updateCustomer(uid, data);
    }


    @DeleteMapping("/{uid}")
    @RequiresRoles(Constants.USER_SUPER_ADMIN)
    public @ResponseBody RestResult deleteCustomer(@PathVariable("uid") String uid) {
        return customerApplication.deleteCustomer(uid);
    }

    @GetMapping("/{uid}")
    @RequiresRoles(Constants.USER_SUPER_ADMIN)
    public @ResponseBody RestResult getCustomer(@PathVariable("uid") String uid) {
        return customerApplication.getCustomer(uid);
    }

    @GetMapping("/list")
    @RequiresRoles(Constants.USER_SUPER_ADMIN)
    public @ResponseBody RestResult getCustomerList(@RequestParam(value = "state", defaultValue = "0", required = false) Integer state) {
        return customerApplication.getCustomerList(state);
    }
}
