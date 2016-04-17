package com.woowahan.biz.domain.accountDetail;

import com.woowahan.biz.domain.Account;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

/**
 * Created by sykim on 2016. 4. 14..
 */
@Data
@Entity
public class Withdrawal extends AccountDetail {

    @ManyToOne(fetch = FetchType.LAZY)
    private Account dissaver;

    private Long withdrawalAmount;

    public Withdrawal() {

    }

    public Withdrawal(Account fromAccount, Account toAccount, Long withdrawalAmount) {
        this.setAccount(fromAccount);
        this.dissaver = toAccount;
        this.withdrawalAmount = withdrawalAmount;
    }

    @Override
    public Account getFromAccount() {
        return this.getAccount();
    }

    @Override
    public Account getToAccount() {
        return this.getDissaver();
    }

    @Override
    public Long getAmount() {
        return this.getWithdrawalAmount() * -1;
    }
}
