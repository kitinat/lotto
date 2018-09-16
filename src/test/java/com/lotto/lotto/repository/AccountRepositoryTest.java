package com.lotto.lotto.repository;

import com.lotto.lotto.model.Account;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class AccountRepositoryTest {

    @Autowired
    private AccountRepository accountRepository;

    @Test
    public void createNewAccount(){
        Account newAccount = new Account();
        newAccount.setUserName("Kitinat");
        newAccount.setPassword("P@ssw0rd");
        newAccount.setSalary(10000);
        Account account =accountRepository.save(newAccount);

        assertTrue(account.getId() > 0);
        assertEquals("Account size should be 1",1,accountRepository.count());

    }

    @Test
    public void createNewAccount2(){
        Account newAccount = new Account();
        newAccount.setUserName("Kitinat");
        newAccount.setPassword("P@ssw0rd");
        newAccount.setSalary(10000);
        accountRepository.save(newAccount);

        Account newAccount2 = new Account();
        newAccount2.setUserName("Kitinat");
        newAccount2.setPassword("P@ssw0rd");
        newAccount2.setSalary(10000);
        accountRepository.save(newAccount2);

        assertEquals("Account size should be 2",2,accountRepository.count());

    }

}