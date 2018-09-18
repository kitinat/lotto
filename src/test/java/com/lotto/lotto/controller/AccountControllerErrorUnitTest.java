package com.lotto.lotto.controller;

import com.lotto.lotto.category.UnitTest;
import com.lotto.lotto.controller.response.AccountResponse;
import com.lotto.lotto.exception.MyAccountNotFoundException;
import com.lotto.lotto.repository.AccountRepository;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
@Category(UnitTest.class)
public class AccountControllerErrorUnitTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

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

    @Test
    public void getByIdWithAccountNotFound2() {
        //Stub
        given(accountRepository.findById(2)).willReturn(Optional.empty());

        //Action
        accountController = new AccountController(accountRepository);
        thrown.expect(MyAccountNotFoundException.class);
        thrown.expectMessage("Account id=[2] not found");

        AccountResponse response = accountController.getById(2);
    }
}