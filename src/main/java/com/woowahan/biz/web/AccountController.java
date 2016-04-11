package com.woowahan.biz.web;

import com.woowahan.biz.domain.Account;
import com.woowahan.biz.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by sykim on 2016. 4. 10..
 */
@RestController
@RequestMapping("accounts")
@Slf4j
public class AccountController {

    @Autowired
    AccountService accountService;

    /**
     * 계좌조회(전체)
     */
    @RequestMapping(method = GET)
    public List<Account> accounts() {
        List<Account> accounts = accountService.findAccounts();
        log.info("Get all accounts");
        return accounts;
    }

    /**
     * 계좌조회(개별)
     */
    @RequestMapping(value = "{accountId}", method = GET)
    public Account getAccount(@PathVariable("accountId") Long accountId) {
        Account account = accountService.findAccount(accountId);
        log.info("Get an account : {}", account);
        return account;
    }

    /**
     * 계좌등록
     */
    @RequestMapping(method = POST)
    @ResponseStatus(HttpStatus.CREATED)
    public String register(@RequestBody Account account) {
        accountService.registerAccount(account);
        log.info("Register account : {}", account);
        return "OK";
    }

    /**
     * 계좌삭제
     */
    @RequestMapping(value = "{accountId}/delete", method = DELETE)
    public String delete(@PathVariable("accountId") Long accountId) {
        accountService.deleteAccount(accountId);
        Account account = accountService.findAccount(accountId);
        log.info("Delete account : {}", account);
        return "redirect:/accounts";
    }

}
