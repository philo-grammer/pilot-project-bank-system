package com.woowahan.biz.repository;

import com.woowahan.biz.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by sykim on 2016. 4. 5..
 */
public interface AccountRepository extends JpaRepository<Account, Long> {

}
