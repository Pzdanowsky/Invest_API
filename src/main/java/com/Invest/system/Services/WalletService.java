package com.Invest.system.Services;


import com.Invest.system.Objects.Transactions;
import com.Invest.system.Objects.Wallets;
import com.Invest.system.Repositories.TransactionRepository;
import com.Invest.system.Repositories.WalletRepository;
import com.Invest.system.Services.Extractors.Extractor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WalletService {

    private final WalletRepository walletRepository;
    private final TransactionRepository transactionRepository;


    public Set<Wallets> getWallets(){
        return walletRepository.findAllWallets();
    }

    public Set<Wallets> gettWalletsByOwner(long onwer_id){
        return  walletRepository.findAllByOwnerId(onwer_id);
    }

    public Set<Wallets> getWalletsWithTransactions() {
        Set<Wallets> allWallets = walletRepository.findAllWallets();
        return getTransactionsToWallets(allWallets);
    }


    public Set<Wallets> getWalletWithTransactionsByOwner(long owner_id) {
        Set<Wallets> allWaletsOwner = walletRepository.findAllByOwnerId(owner_id);
        return getTransactionsToWallets(allWaletsOwner);

    }


    public Set<Wallets> getTransactionsToWallets(Set<Wallets> allWallets){
        Set<Long> ids = allWallets.stream()
                .map(Wallets::getWallet_id)
                .collect(Collectors.toSet());
        Set<Transactions> transactions = transactionRepository.findAllByWalletId(ids);
        allWallets.forEach(wallet -> wallet.setTransactions(Extractor.extractTransactions(transactions, wallet.getWallet_id())));
        return allWallets;
    }

    public Wallets addWallet(Wallets wallet) {
        return walletRepository.save(wallet);
    }

    @Transactional
    public Wallets editWallet(Wallets wallet)  {
        Wallets editedWallet = walletRepository.findAllByWalletId(wallet.getWallet_id());
        if(editedWallet != null) {
            editedWallet.setWallet_name(wallet.getWallet_name());
            editedWallet.setPublic_status(wallet.isPublic_status());
            editedWallet.setOwner_id(wallet.getOwner_id());
        }
      return null;
    }

    public void deleteWallet(long id) {
        walletRepository.deleteById(id);
    }
}
