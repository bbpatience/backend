package com.walle.cplatform;

import com.walle.cplatform.common.RestResult;
import com.walle.cplatform.utils.Constants;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;
    @Test
    public void testUserAll(){
        String content = this.restTemplate.getForObject("/user/all", String.class);
        assert(content).equals("[]");
    }

    @Test
    public void testException(){
        RestResult response = this.restTemplate.getForObject("/user/exception", RestResult.class);
        assert(response.getRspCode()).equals(Constants.SYS_FAIL_FLAG);
        assert(response.getRspMsg()).equals(Constants.SYS_FAIL_MSG);
    }
}