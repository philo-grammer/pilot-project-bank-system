package com.woowahan.biz.domain;

import com.woowahan.biz.domain.accountDetail.AccountDetail;
import com.woowahan.biz.domain.accountDetail.Deposit;
import com.woowahan.biz.domain.accountDetail.Withdrawal;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sykim on 2016. 4. 3..
 */
@Data
@Entity
@Table(name = "ACCONUTS")
public class Account {

    @Id
    @GeneratedValue
    @Column(name = "ACCOUNT_ID")
    private Long id;

    @Column(nullable = false, name = "DEPOSITOR_ID")
    private String depositorId;

    @Column(nullable = false, name = "PASSWORD")
    private String password;

    @Column(nullable = false, name = "BALANCE")
    private Long balance;

    @Enumerated(EnumType.STRING)
    private AccountStatus status;

    @OneToMany(mappedBy = "account")
    private List<AccountDetail> accountDetails = new ArrayList<>();

    /**
     * 계좌등록
     */
    public static Account register(String depositorId, String password) {

        // TODO : 중복 계좌 체크

        Account account = new Account();
        account.setDepositorId(depositorId);
        account.setPassword(password);
        account.setBalance(0L);
        account.setStatus(AccountStatus.LIVE);
        return account;
    }

    /**
     * 계좌삭제
     */
    public void delete() {

        if (this.getStatus() == AccountStatus.DELETE) {
            throw new RuntimeException("이미 삭제된 계좌입니다.");
        }

        this.setStatus(AccountStatus.DELETE);
    }

    //== 연관관계 메소드 ==//
    // 이체
    public void transfer(Account targetAccount, Long amount) {

        AccountDetail withdrawal = new Withdrawal(this, targetAccount, amount);
        this.addAccountDetail(withdrawal);
        
        AccountDetail deposit = new Deposit(this, targetAccount, amount);
        targetAccount.addAccountDetail(deposit);

    }

    // 이체기록 추가
    public void addAccountDetail(AccountDetail accountDetail) {
        Long balance = this.getBalance();
        Long totalBalance = balance + accountDetail.getAmount();
        this.setBalance(totalBalance);
        accountDetails.add(accountDetail);
        accountDetail.setAccount(this);
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", depositorId='" + depositorId + '\'' +
                ", password='" + password + '\'' +
                ", balance=" + balance +
                ", status=" + status +
                ", accountDetailsSize=" + accountDetails.size() +
                '}';
    }



}
