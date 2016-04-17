package com.woowahan.biz.service;

import com.woowahan.biz.domain.Account;
import com.woowahan.biz.domain.accountDetail.AccountDetail;
import com.woowahan.biz.domain.accountDetail.Deposit;
import com.woowahan.biz.domain.accountDetail.Withdrawal;
import com.woowahan.biz.repository.AccountDetailRepository;
import com.woowahan.biz.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by sykim on 2016. 4. 11..
 */
@Service
@Transactional
public class BankService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    private AccountDetailRepository accountDetailRepository;

    /**
     * 이체
     * 예금과 출금일 경우는 예금자와 출금자가 ATM 계좌가 된다.
     * 예금일 경우 : toAccountId가 내 계좌번호이다.
     * 출금일 경우 : fromAccountId가 내 계좌번호이다.
     */
    public Account transfer(Long fromAccountId, Long toAccountId, Long amount) {

        Account fromAccount = accountRepository.findOne(fromAccountId);
        Account toAccount = accountRepository.findOne(toAccountId);

        //TODO : 유효성체크(출금되는 계좌의 잔액이 출금금액보다 많아야한다.)

        AccountDetail depositDetail =
                new Deposit(fromAccount, toAccount, amount);
        AccountDetail withdrawalDetail =
                new Withdrawal(fromAccount, toAccount, amount);

        accountDetailRepository.save(depositDetail);
        accountDetailRepository.save(withdrawalDetail);

        toAccount.addAccountDetail(depositDetail);
        fromAccount.addAccountDetail(withdrawalDetail);

        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);

        return toAccount;
    }

    /**
     * 조회
     */
    public List<AccountDetail> getAccountDetails(Long accountId) {
        Account account = accountRepository.findOne(accountId);
        return accountDetailRepository.findByAccountEquals(account);
    }
}