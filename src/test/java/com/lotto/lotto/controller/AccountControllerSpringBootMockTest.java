package com.lotto.lotto.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lotto.lotto.category.IntegrationTest;
import com.lotto.lotto.controller.response.AccountResponse;
import com.lotto.lotto.model.Account;
import com.lotto.lotto.repository.AccountRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Category(IntegrationTest.class)
public class AccountControllerSpringBootMockTest {

    @Autowired
    private MockMvc mockMvc;

    private JacksonTester<AccountResponse> jsonTester;

    @MockBean
    private AccountRepository accountRepository;


    @Before
    public void initial() {
        JacksonTester.initFields(this, new ObjectMapper());
    }


    @Test
    public void getById() throws Exception {


        //Stub
        Account account = new Account();
        account.setUserName("user");
        account.setPassword("password");
        account.setSalary(1000);
        given(accountRepository.findById(1))
                .willReturn(Optional.of(account));

        MockHttpServletResponse response = mockMvc
                .perform(get("/account/1").accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
        assertEquals("Status should be 200", HttpStatus.OK.value(), response.getStatus());

        AccountResponse expected = new AccountResponse("user", "password", 1000);
        assertEquals("Response JSON should be equal", jsonTester.write(expected).getJson(), response.getContentAsString());
    }
}