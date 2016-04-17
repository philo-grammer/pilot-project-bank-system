package com.woowahan.biz.service;

import com.woowahan.biz.domain.Account;
import com.woowahan.biz.domain.accountDetail.AccountDetail;
import com.woowahan.biz.domain.accountDetail.Deposit;
import com.woowahan.biz.domain.accountDetail.Withdrawal;
import com.woowahan.biz.repository.AccountDetailRepository;
import com.woowahan.biz.repository.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.when;

/**
 * Created by sykim on 2016. 4. 15..
 */
@Slf4j
@Transactional
public class BankServiceTestWithMock {


    @Mock
    private AccountRepository accountRepository;

    @Mock
    private AccountDetailRepository accountDetailRepository;

    @InjectMocks
    private BankService bankService;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testDeposit() {

        // Given
        Account myAccount = Account.register("philo","1234");
        Account depositor = Account.register("depositor", "2345");

        // WHen
        Account account = bankService.transfer(depositor.getId(),
                myAccount.getId(), 1000L);

        // Then
        assertEquals("잔액이 증가해야 한다.",
                Long.valueOf(1000L), account.getBalance());
        assertEquals("내역이 추가 되어야 한다.",
                1, account.getAccountDetails().size());

    }

    @Test
    public void testGetAccountDetails() {

        // Given
        Account myAccount = Account.register("philo","1234");
        Account depositor = Account.register("depositor", "2345");
        Account dissaver = Account.register("dissaver", "3456")
;
        AccountDetail detailDeposit = new Deposit(depositor, myAccount ,3000L);
        AccountDetail detailWithdrawal = new Withdrawal(myAccount, dissaver, 1000L);

        List<AccountDetail> mockAccountDetails = new ArrayList<>();
        mockAccountDetails.add(detailDeposit);
        mockAccountDetails.add(detailWithdrawal);

        when(accountRepository.findOne(isA(Long.class)))
                .thenReturn(myAccount);
        when(accountDetailRepository.findByAccountEquals(isA(Account.class)))
                .thenReturn(mockAccountDetails);

        // When
        List<AccountDetail> accountDetails = bankService.getAccountDetails(1L);

        // Then
        assertEquals(2, accountDetails.size());
        log.info(myAccount.toString());
        log.info(accountDetails.toString());
    }
}
