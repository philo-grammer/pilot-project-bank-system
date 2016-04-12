package com.woowahan.biz.service;

import com.woowahan.biz.domain.Account;
import com.woowahan.biz.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by sykim on 2016. 4. 11..
 */
@Service
@Transactional
public class BankService {

    @Autowired
    AccountRepository accountRepository;

    /**
     * 입금
     */
    public Account deposit(Long accountId, Long depositAmount) {
        Account account = accountRepository.findOne(accountId);
        Long balance = account.getBalance();
        Long sumAmount = balance + depositAmount;
        account.setBalance(sumAmount);
        return accountRepository.save(account);
    }

    /**
     * 출금
     */
    public Account withdrawal(Long accountId, Long amount) {
        Account account = accountRepository.findOne(accountId);
        Long balance = account.getBalance();

        //TODO : 유효성체크(잔액이 출금금액보다 적어야한다.)

        Long totalAmount = balance - amount;
        account.setBalance(totalAmount);
        return accountRepository.save(account);
    }
}