package com.walle.cplatform;

import com.walle.cplatform.common.RestResult;
import com.walle.cplatform.controller.UserController;
import com.walle.cplatform.service.UserService;
import com.walle.cplatform.service.impl.UserServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.when;
import org.mybatis.spring.boot.test.autoconfigure.AutoConfigureMybatis;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@AutoConfigureMybatis
@WebMvcTest(UserController.class)
public class MockMvcUserBeanCtrlTest {

    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp() {
        //获取mockmvc对象实例
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testCtrler() throws Exception {
//        given(this.modelProductService.get(anyLong())).willReturn(null);
//        String jsonStr = "{\"data\":{\"debit_account_balance_code\":40,\"credit_consume_count\":1,\"debit_start_age\":1,\"debit_consume_sum_code\":2,\"age\":38},\"modelProductId\":5}";
//
//        RequestBuilder requestBuilder = post("/user/all").contentType(MediaType.APPLICATION_JSON).content(jsonStr);
//        this.mockMvc.perform(requestBuilder).andExpect(status().isOk()).andExpect(
//            MockMvcResultMatchers.content().string("{}"));
    }

    @Test
    public void testUserAll() throws Exception {
        String expectStr = "{\"code\":\"10000\"}";
        when(userService.findAll()).thenReturn(RestResult.success());
        RequestBuilder requestBuilder = get("/user/all");
        this.mockMvc.perform(requestBuilder).andExpect(status().isOk()).andExpect(
            MockMvcResultMatchers.content().string(expectStr));
    }

    @Test
    public void testException() throws Exception {
        String expectStr = "{\"code\":\"10001\",\"msg\":\"Server Failed!\"}";
        RequestBuilder requestBuilder = get("/user/exception");
        this.mockMvc.perform(requestBuilder).andExpect(status().isOk()).andExpect(
            MockMvcResultMatchers.content().string(expectStr));
    }
}
