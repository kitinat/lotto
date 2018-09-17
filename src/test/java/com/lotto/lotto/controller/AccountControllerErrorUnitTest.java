package com.lotto.lotto.controller;

import com.lotto.lotto.category.IntegrationTest;
import com.lotto.lotto.category.UnitTest;
import com.lotto.lotto.controller.response.AccountResponse;
import com.lotto.lotto.exception.MyAccountNotFoundException;
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
public class AccountControllerErrorUnitTest {

    @Mock
    private AccountRepository accountRepository;

    private AccountController accountController;

    @Test(expected = MyAccountNotFoundException.class)
    public void getByIdWithAccountNotFound() {
        //Stub
        given(accountRepository.findById(2)).willReturn(Optional.empty());

        //Action
        accountController = new AccountController(accountRepository);
        AccountResponse response = accountController.getById(2);

    }
}