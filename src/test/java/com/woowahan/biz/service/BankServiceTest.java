package com.woowahan.biz.service;

import static org.junit.Assert.assertEquals;

import com.woowahan.biz.PilotProjectBankSystemApplication;
import com.woowahan.biz.domain.Account;
import com.woowahan.biz.domain.accountdetail.AccountDetail;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by sykim on 2016. 4. 11..
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = PilotProjectBankSystemApplication.class)
@Transactional
public class BankServiceTest {

    @Autowired
    AccountService accountService;

    @Autowired
    BankService bankService;

    @Test
    public void testTransfer() throws Exception {

        // Given
        Account fromAccount = Account.register("fromAccount", "2345");
        Account toAccount = Account.register("toAccount", "1234");

        Long fromAccountId = accountService.registerAccount(fromAccount);
        Long toAccountId = accountService.registerAccount(toAccount);

        // When
        bankService.transfer(fromAccountId, toAccountId, 1000L);

        // Then
        assertEquals("출금되는 계좌의 잔액은 감소해야 한다.",
                Long.valueOf(9000L), fromAccount.getBalance());
        assertEquals("출금되는 계좌의 내역이 추가 되어야 한다.",
                1, fromAccount.getAccountDetails().size());
        assertEquals("입금되는 계좌의 잔액은 증가해야 한다.",
                Long.valueOf(11000L), toAccount.getBalance());
        assertEquals("입금되는 계좌의 내역이 추가 되어야 한다.",
                1, toAccount.getAccountDetails().size());
    }

    @Test
    public void testGetAccountDetails() throws Exception {

        // Given
        Account fromAccount = Account.register("fromAccount", "2345");
        Account toAccount = Account.register("toAccount", "1234");

        Long fromAccountId = accountService.registerAccount(fromAccount);
        Long toAccountId = accountService.registerAccount(toAccount);

        bankService.transfer(fromAccountId, toAccountId, 1000L);
        bankService.transfer(fromAccountId, toAccountId, 1000L);
        bankService.transfer(fromAccountId, toAccountId, 1000L);
        bankService.transfer(toAccountId, fromAccountId, 1000L);

        // When
        List<AccountDetail> accountDetails =
                bankService.getAccountDetails(toAccountId);

        log.info("=========>> {}", toAccount.toString());
        log.info("==>> {}", accountDetails.get(0).getClass());
        log.info("==>> {}", accountDetails.get(1).getClass());
        log.info("==>> {}", accountDetails.get(2).getClass());
        log.info("==>> {}", accountDetails.get(3).getClass());

        // Then
        assertEquals("이체를 한 만큼 내역이 남아야 한다.",
                4, accountDetails.size());
        assertEquals("잔액 계산도 잘 되어야 한다.",
                Long.valueOf(12000L), toAccount.getBalance());
    }

}