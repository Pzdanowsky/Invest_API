package com.Invest.system.Services;


import com.Invest.system.Objects.Transactions;
import com.Invest.system.Repositories.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.ArrayList;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;

    public ArrayList<Transactions> getTransactions() {
        return (ArrayList<Transactions>) transactionRepository.findAll();
    }

    public ArrayList<Transactions> getAllByType(long id){
        return transactionRepository.finnAllBy(id);
    }

    public ArrayList<Transactions> getAllWaletsNameByType(long id){
        return transactionRepository.findWaletNameByType(id);
    }
}
