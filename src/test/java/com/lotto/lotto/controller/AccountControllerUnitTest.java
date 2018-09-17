package com.lotto.lotto.controller;

import com.lotto.lotto.category.SlicingTest;
import com.lotto.lotto.category.UnitTest;
import com.lotto.lotto.controller.response.AccountResponse;
import com.lotto.lotto.model.Account;
import com.lotto.lotto.repository.AccountRepository;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
@Category(UnitTest.class)
public class AccountControllerUnitTest {

    @Mock
    private AccountRepository accountRepository;

    private AccountController accountController;

    @Test
    public void getById() {

        //Stub
        Account account = new Account();
        account.setUserName("user");
        account.setPassword("password");
        account.setSalary(1000);
        given(accountRepository.findById(1))
                .willReturn(Optional.of(account));

        accountController = new AccountController(accountRepository);
        AccountResponse response = accountController.getById(1);
        AccountResponse expected = new AccountResponse("user", "password", 1000);
        assertEquals(expected, response);
    }
}