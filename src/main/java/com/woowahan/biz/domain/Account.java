package com.woowahan.biz.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
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


/*
    public static Account registerAccount(String depositor, String password) {
        Account account = new Account();
        account.setDepositor(depositor);
        account.setPassword(password);

        return account;
    }
*/


}
