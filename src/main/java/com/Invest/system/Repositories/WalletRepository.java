package com.Invest.system.Repositories;


import com.Invest.system.Objects.Wallets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Set;

@Repository
public interface WalletRepository extends JpaRepository<Wallets, Long> {


    @Query("select w from Wallets w")
    Set<Wallets> findAllWallets();

    @Query("select w from Wallets w where w.owner_id = ?1")
    Set<Wallets> findAllByOwnerId(long owner_id);

    @Query("select w from Wallets w where w.wallet_id = ?1")
    Wallets findAllByWalletId(long wallet_id);

}
