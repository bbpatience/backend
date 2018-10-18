package com.walle.cplatform;

import com.walle.cplatform.common.RestResult;
import com.walle.cplatform.user.controller.UserController;
import com.walle.cplatform.user.pojos.InputLogin;
import com.walle.cplatform.user.service.UserService;
import com.walle.cplatform.utils.JacksonUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
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
    public void testUserLogin() throws Exception {
        InputLogin input = new InputLogin();
        input.setUsername("superadmin");
        input.setPassword("patience123");

        RestResult expect = RestResult.success();
        when(userService.login("123", "123")).thenReturn(RestResult.success());
        RequestBuilder requestBuilder = post("/user/login")
            .contentType(MediaType.APPLICATION_JSON).content(JacksonUtils.toJSon(input));
        this.mockMvc.perform(requestBuilder).andDo(print()).andExpect(status().isOk());

// TODO   check
//    this.mockMvc.perform(requestBuilder).andDo(print()).andExpect(status().isOk()).andExpect(
//            MockMvcResultMatchers.content().string(JacksonUtils.toJSon(expect)));
    }

    @Test
    public void testUserLogout() throws Exception {

        RestResult expect = RestResult.success();
        when(userService.logout()).thenReturn(RestResult.success());
        RequestBuilder requestBuilder = post("/user/logout")
            .contentType(MediaType.APPLICATION_JSON);
        this.mockMvc.perform(requestBuilder).andDo(print()).andExpect(status().isOk()).andExpect(
            MockMvcResultMatchers.content().string(JacksonUtils.toJSon(expect)));
    }

    @Test
    public void testException() throws Exception {
        RestResult result = RestResult.failure();
        RequestBuilder requestBuilder = get("/user/exception");
        this.mockMvc.perform(requestBuilder).andExpect(status().isOk()).andExpect(
            MockMvcResultMatchers.content().string(JacksonUtils.toJSon(result)));
    }
}
