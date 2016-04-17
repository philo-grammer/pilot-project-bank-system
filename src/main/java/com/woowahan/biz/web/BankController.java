package com.woowahan.biz.web;

import com.woowahan.biz.domain.accountDetail.AccountDetail;
import com.woowahan.biz.dto.AccountDetailDto;
import com.woowahan.biz.service.BankService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
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
    public String deposit(@RequestParam("fromAccountId") Long fromAccountId,
                          @RequestParam("toAccountId") Long toAccountId,
                          @RequestParam("amount") Long amount) {
        bankService.transfer(fromAccountId, toAccountId, amount);
        log.info("deposit(fromAccountId:{}, toAccountId:{}, depositAmount:{})",
                fromAccountId, toAccountId, amount);
        return "OK";
    }

    /**
     * 출금
     */
    @RequestMapping(value = "/withdrawal", method = POST)
    public String withdrawal(@RequestParam("fromAccountId") Long fromAccountId,
                             @RequestParam("toAccountId") Long toAccountId,
                             @RequestParam("amount") Long amount) {
        bankService.transfer(toAccountId, fromAccountId, amount);
        log.info("withdrawal(toAccountId:{}, fromAccountId:{}, amount:{})",
                toAccountId, fromAccountId, amount);
        return "OK";
    }

    /**
     * 조회
     */
    @RequestMapping(value = "{accountId}", method = GET)
    public AccountDetailDto getAccountDetails(
            @PathVariable("accountId") Long accountId) {
        List<AccountDetail> details = bankService.getAccountDetails(accountId);
        Long totalCount = (long) details.size();
        Long balance = details.get(0).getAccount().getBalance();
        return new AccountDetailDto(totalCount, balance);
    }
}
