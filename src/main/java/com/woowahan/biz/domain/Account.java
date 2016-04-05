package com.woowahan.biz.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by sykim on 2016. 4. 3..
 */
@Entity
@Getter
@Setter
public class Account {

    @Id
    @GeneratedValue
    @Column(name = "ACCOUNT_ID")
    private Long id;

    @Column(nullable = false, name = "DEPOSITOR_ID")
    private String depositorId;

    @Column(nullable = false, name = "PASSWORD")
    private String password;

    @Enumerated(EnumType.STRING)
    private AccountStatus status;

    /**
     * 계좌삭제
     */
    public void delete() {

        if(this.getStatus() == AccountStatus.DELETE) {
            throw new RuntimeException("이미 삭제된 계좌입니다.");
        }

        this.setStatus(AccountStatus.DELETE);
    }


/*
    public static Account registerAccount(String depositor, String password) {
        Account account = new Account();
        account.setDepositor(depositor);
        account.setPassword(password);

        return account;
    }
*/


}
