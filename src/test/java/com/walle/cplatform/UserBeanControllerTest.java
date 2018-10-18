package com.walle.cplatform;

import com.walle.cplatform.common.RestResult;
import com.walle.cplatform.user.pojos.InputLogin;
import com.walle.cplatform.utils.RestResultCode;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserBeanControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testUserLogin() {
        InputLogin input = new InputLogin();
        input.setUsername("superadmin");
        input.setPassword("patience123");

        RestResult response = this.restTemplate.postForObject("/user/login", input, RestResult.class);
        assert(response.getRspCode()).equals(RestResultCode.COMMON_SUCCESS.getCode());
        assert(response.getRspMsg()).equals(RestResultCode.COMMON_SUCCESS.getMsg());
    }

    @Test
    public void testException() {
        RestResult response = this.restTemplate.getForObject("/user/exception", RestResult.class);
        assert(response.getRspCode()).equals(RestResultCode.COMMON_SERVER_ERROR.getCode());
        assert(response.getRspMsg()).equals(RestResultCode.COMMON_SERVER_ERROR.getMsg());
    }

    @Test
    public void testUserLogout() {
        RestResult response = this.restTemplate.postForObject("/user/logout", null, RestResult.class);
        assert(response.getRspCode()).equals(RestResultCode.COMMON_SUCCESS.getCode());
        assert(response.getRspMsg()).equals(RestResultCode.COMMON_SUCCESS.getMsg());
    }

    @Test
    public void testClassesFail() {
        RestResult response = this.restTemplate.getForObject("/classes", RestResult.class);
        assert(response.getRspCode()).equals(RestResultCode.USER_USER_NOT_LOGIN.getCode());
        assert(response.getRspMsg()).equals(RestResultCode.USER_USER_NOT_LOGIN.getMsg());
    }

    @Test
    public void testClasses() {
        InputLogin input = new InputLogin();
        input.setUsername("superadmin");
        input.setPassword("patience123");

        RestResult response = this.restTemplate.postForObject("/user/login", input, RestResult.class);
        assert(response.getRspCode()).equals(RestResultCode.COMMON_SUCCESS.getCode());
        assert(response.getRspMsg()).equals(RestResultCode.COMMON_SUCCESS.getMsg());

//        TODO use cookie to get class
        response = this.restTemplate.getForObject("/classes", RestResult.class);
        assert(response.getRspCode()).equals(RestResultCode.USER_USER_NOT_LOGIN.getCode());
        assert(response.getRspMsg()).equals(RestResultCode.USER_USER_NOT_LOGIN.getMsg());
    }
}