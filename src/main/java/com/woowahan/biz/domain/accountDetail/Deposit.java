package com.woowahan.biz.domain.accountDetail;

import com.woowahan.biz.domain.Account;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Created by sykim on 2016. 4. 14..
 */
@Data
@Entity
public class Deposit extends AccountDetail {

    @ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name = "ACCOUNT_ID")
    private Account depositor;

    private Long depositAmount;

    public Deposit() {

    }

    public Deposit(Account fromAccount, Account toAccount, Long depositAmount) {
        this.setAccount(toAccount);
        this.depositor = fromAccount;
        this.depositAmount = depositAmount;
    }

    @Override
    public Account getFromAccount() {
        return this.getDepositor();
    }

    @Override
    public Account getToAccount() {
        return this.getAccount();
    }

    @Override
    public Long getAmount() {
        return this.getDepositAmount();
    }
}
