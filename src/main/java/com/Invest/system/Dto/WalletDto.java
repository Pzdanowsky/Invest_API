package com.Invest.system.Dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class WalletDto {
    private long wallet_id;

    private long owner_id;

    private String wallet_name;

    private boolean public_status;

}
