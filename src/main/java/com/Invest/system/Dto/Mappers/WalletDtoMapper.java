package com.Invest.system.Dto.Mappers;

import com.Invest.system.Dto.WalletDto;
import com.Invest.system.Objects.Wallets;

import java.util.Set;
import java.util.stream.Collectors;

public class WalletDtoMapper {

    private WalletDtoMapper() {
    }

    public static Set<WalletDto> mapToWalletDtos(Set<Wallets> wallets) {
        return  wallets.stream()
                .map(wallet -> mapToWalletDtos(wallet))
                .collect(Collectors.toSet());
    }

    private static WalletDto mapToWalletDtos(Wallets wallet) {
        return WalletDto.builder()
                .wallet_id(wallet.getWallet_id())
                .wallet_name(wallet.getWallet_name())
                .owner_id(wallet.getOwner_id())
                .public_status(wallet.isPublic_status())
                .build();

    }


}
