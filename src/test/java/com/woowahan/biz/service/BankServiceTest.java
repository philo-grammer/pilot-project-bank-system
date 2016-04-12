package com.woowahan.biz.service;

import com.woowahan.biz.PilotProjectBankSystemApplication;
import com.woowahan.biz.domain.Account;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

/**
 * Created by sykim on 2016. 4. 11..
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = PilotProjectBankSystemApplication.class)
@Transactional
public class BankServiceTest {

    @Autowired
    AccountService accountService;

    @Autowired
    BankService bankService;

    @Test
    public void testDeposit() throws Exception {
        //Given
        Account account = new Account();
        account.setDepositorId("philo-grammer");
        account.setPassword("12345");
        Long accountId = accountService.registerAccount(account);
        Account afterAccount = accountService.findAccount(accountId);

        //When
        bankService.deposit(accountId, 10000L);
        bankService.deposit(accountId, 3000L);

        //Then
        assertEquals("입금액이 더해져야 한다.",
                Long.valueOf(13000L), afterAccount.getBalance());
    }

    @Test
    public void testWithdrawal() throws Exception {
        //Given
        Account account = new Account();
        account.setDepositorId("philo-grammer");
        account.setPassword("12345");
        Long accountId = accountService.registerAccount(account);
        bankService.deposit(accountId, 13000L);

        //When
        Account afterAccount = bankService.withdrawal(accountId, 3000L);

        //Then
        assertEquals("출금액이 차감되어야 한다.",
                Long.valueOf(10000L), afterAccount.getBalance());
    }
}