package com.lotto.lotto.controller;

import com.lotto.lotto.category.IntegrationTest;
import com.lotto.lotto.exception.MyAccountNotFoundException;
import com.lotto.lotto.exception.ResponseException;
import com.lotto.lotto.model.Account;
import com.lotto.lotto.repository.AccountRepository;
import org.junit.Test;
import org.junit.experimental.categories.Category;
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
import static org.junit.Assert.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Category(IntegrationTest.class)
public class AccountControllerErrorTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @MockBean
    private AccountRepository accountRepository;

    @Test
    public void getByIdWithAccountNotFound() {
        //init data
        Account account = new Account();
        account.setId(2);
        try {
            accountRepository.delete(account);
        } catch (Exception e) {

        }

        ResponseEntity<ResponseException> result = testRestTemplate.getForEntity("/account/2", ResponseException.class);
        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
        assertEquals("Account id=[2] not found", result.getBody().getMessage());
        assertNotNull(result.getBody().getDate());
    }

}