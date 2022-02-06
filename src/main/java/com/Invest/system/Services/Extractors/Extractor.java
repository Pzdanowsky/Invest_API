package com.Invest.system.Services.Extractors;

import com.Invest.system.Objects.Transactions;

import java.util.Set;
import java.util.stream.Collectors;

public class Extractor {

    private Extractor() {
    }


    public static Set<Transactions> extractTransactions(Set<Transactions> transactions, long id) {
        return transactions.stream()
                .filter(transaction -> transaction.getWallet_id() == id)
                .collect(Collectors.toSet());
    }

}
