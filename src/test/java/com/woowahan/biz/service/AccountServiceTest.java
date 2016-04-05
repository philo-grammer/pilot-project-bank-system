package com.woowahan.biz.service;

import static org.junit.Assert.assertEquals;

import com.woowahan.biz.PilotProjectBankSystemApplication;
import com.woowahan.biz.domain.Account;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;


/**
 * Created by sykim on 2016. 4. 5..
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = PilotProjectBankSystemApplication.class)
@Transactional
public class AccountServiceTest {

    @Autowired
    AccountService accountService;

    @Test
    public void testRegisterAccount() throws Exception {

        //Given
        Account account = new Account();
        account.setDepositorId("philo-grammer");
        account.setPassword("1234");

        //When
        Long accountId = accountService.registerAccount(account);

        //Then
        assertEquals(account, accountService.findOne(accountId));

    }
}