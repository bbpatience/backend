package com.walle.cplatform;

import com.walle.cplatform.common.RestResult;
import com.walle.cplatform.pojos.OutputUserInfo;
import com.walle.cplatform.utils.Constants;
import java.util.Map;
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
    public void testUserAll() {
        RestResult response = this.restTemplate.getForObject("/user/all", RestResult.class);
        Map user = (Map) response.getData();
        assert(user.get("name")).equals("superadmin");
    }

    @Test
    public void testException() {
        RestResult response = this.restTemplate.getForObject("/user/exception", RestResult.class);
        assert(response.getRspCode()).equals(Constants.SYS_FAIL_FLAG);
        assert(response.getRspMsg()).equals(Constants.SYS_FAIL_MSG);
    }
}