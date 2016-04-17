package com.woowahan.biz.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by sykim on 2016. 4. 18..
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDetailDto {

    private Long totalCount;

    private Long balance;
}
