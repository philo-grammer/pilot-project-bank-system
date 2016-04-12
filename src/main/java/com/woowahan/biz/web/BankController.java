package com.woowahan.biz.web;

import com.woowahan.biz.service.BankService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by sykim on 2016. 4. 11..
 */
@RestController
@RequestMapping("bank")
@Slf4j
public class BankController {

    @Autowired
    BankService bankService;

    /**
     * 입금
     */
    @RequestMapping(value = "/deposit",method = POST)
    public String deposit(@RequestParam("accountId") Long accountId,
                          @RequestParam("depositAmount") Long depositAmount) {
        bankService.deposit(accountId, depositAmount);
        log.info("deposit(accountId:{}, depositAmount:{})",
                accountId, depositAmount);
        return "OK";
    }

    /**
     * 출금
     */
    @RequestMapping(value = "/withdrawal", method = POST)
    public String withdrawal(@RequestParam("accountId") Long accountId,
                             @RequestParam("amount") Long amount) {
        bankService.withdrawal(accountId, amount);
        log.info("withdrawal(accountId:{}, amount:{})", accountId, amount);
        return "OK";
    }
}
