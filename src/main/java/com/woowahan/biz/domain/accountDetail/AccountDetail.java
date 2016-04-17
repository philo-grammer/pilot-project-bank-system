package com.woowahan.biz.domain.accountdetail;

import com.woowahan.biz.domain.Account;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import java.util.Date;
import javax.persistence.TemporalType;

/**
 * Created by sykim on 2016. 4. 14..
 */
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorColumn(name = "DTYPE")
public abstract class AccountDetail {

    @Id
    @GeneratedValue
    @Column(name = "ACCOUNTDETAIL_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ACCOUNT_ID")
    private Account account;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModified;

    /** 출금 계좌 */
    public abstract Account getFromAccount();

    /** 입금 계좌 */
    public abstract Account getToAccount();

    /** 입금 또는 출금 금액 */
    public abstract Long getAmount();

    @Override
    public String toString() {
        return "AccountDetail{" +
                "id=" + id +
                ", account=" + account.getDepositorId() +
                ", lastModified=" + lastModified +
                '}';
    }
}
