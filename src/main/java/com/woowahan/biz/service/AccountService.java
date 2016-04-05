package com.woowahan.biz.service;

import com.woowahan.biz.domain.Account;
import com.woowahan.biz.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by sykim on 2016. 4. 5..
 */
@Service
@Transactional
public class AccountService {

    @Autowired
    AccountRepository accountRepository;

    public Long registerAccount(Account account) {
        //중복검사
        accountRepository.save(account);
        return account.getId();
    }

    public Account findOne(Long accountId) {
        return accountRepository.findOne(accountId);
    }
}
