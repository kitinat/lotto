package com.lotto.lotto.controller;

import com.lotto.lotto.controller.response.AccountResponse;
import com.lotto.lotto.model.Account;
import com.lotto.lotto.repository.AccountRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class AccountControllerTest2 {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @MockBean
    private AccountRepository accountRepository;

    @Test
    public void getById() {

        //Stub
        Account account = new Account();
        account.setUserName("user");
        account.setPassword("password");
        account.setSalary(1000);
        given(accountRepository.findById(1))
                .willReturn(Optional.of(account));

        ResponseEntity<AccountResponse> result = testRestTemplate.getForEntity("/account/1", AccountResponse.class);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        AccountResponse expected = new AccountResponse("user", "password", 1000);
        assertEquals(expected, result.getBody());
    }
}