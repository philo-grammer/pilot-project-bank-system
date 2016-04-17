package com.woowahan.biz.repository;

import com.woowahan.biz.domain.Account;
import com.woowahan.biz.domain.accountdetail.AccountDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by sykim on 2016. 4. 15..
 */
public interface AccountDetailRepository extends JpaRepository<AccountDetail, Long> {

    List<AccountDetail> findByAccountEquals(Account account);
}
