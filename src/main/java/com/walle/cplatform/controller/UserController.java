package com.walle.cplatform.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.walle.cplatform.entity.User;
import com.walle.cplatform.repository.UserRepository;

@RestController
@RequestMapping(path="/user")
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	private UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping(path="/add")
	public @ResponseBody String addNewUser (@RequestParam String name
			, @RequestParam String email,  @RequestParam Integer age) {
		User n = new User();
		n.setName(name);
		n.setEmail(email);
		n.setAge(age);
		userRepository.save(n);
		return "Saved";
	}
	
	@GetMapping(path="/all")
	public @ResponseBody Iterable<User> getAllUsers() {
        logger.info("Get User All called.");
		return userRepository.findAll();
	}

    @GetMapping(path="/exception")
    public @ResponseBody void exception() throws Exception {
        throw new IllegalAccessException("IllegalAccessException");
    }
}
