package com.Invest.system.Repositories;

import com.Invest.system.Objects.Transactions;
import com.Invest.system.Objects.Wallets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.awt.*;
import java.util.ArrayList;
import java.util.Set;

@Repository
public interface TransactionRepository extends JpaRepository<Transactions,Long> {

    @Query("select t from Transactions t where t.type_transaction_id = ?1")
    ArrayList<Transactions> finnAllBy(long id);

    @Query("select w.wallet_name from Transactions t join Wallets w on t.wallet_id = w.wallet_id where t.type_transaction_id = ?1")
    ArrayList<Transactions> findWaletNameByType(long id);

    @Query("select t from Transactions t where t.wallet_id in (:ids)")
    Set<Transactions> findAllByWalletId(Set<Long> ids);

  //  Set<Transactions> findAllByWallet_idIn(Set<Long> ids);
}
