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
}