package com.walle.cplatform;

import static org.mockito.ArgumentMatchers.anyLong;
import com.walle.cplatform.controller.UserController;
import com.walle.cplatform.repository.UserRepository;
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
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class MockMvcUserCtrlTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository ur;

    @Before
    public void setUp() {
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
        RequestBuilder requestBuilder = get("/user/all");
        this.mockMvc.perform(requestBuilder).andExpect(status().isOk()).andExpect(
            MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    public void testException() throws Exception {
        String expectStr = "{\"rspCode\":\"10001\",\"rspMsg\":\"Server Failed!\"}";
        RequestBuilder requestBuilder = get("/user/exception");
        this.mockMvc.perform(requestBuilder).andExpect(status().isOk()).andExpect(
            MockMvcResultMatchers.content().string(expectStr));
    }
}
