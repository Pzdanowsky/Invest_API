package com.Invest.system.Controllers;


import com.Invest.system.Objects.Transactions;
import com.Invest.system.Services.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;
import java.util.ArrayList;
import java.util.Set;

@RestController
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @GetMapping("/transactions")
    public ArrayList<Transactions> getTransactions(@AuthenticationPrincipal UsernamePasswordAuthenticationToken user){
        return transactionService.getTransactions();
    }

    @GetMapping("/transactions/{type_id}")
    public ArrayList<Transactions> getAllByType(@PathVariable long type_id){
        return transactionService.getAllByType(type_id);
    }

    @GetMapping("/transactions/walletnames/{type_id}")
    public ArrayList<Transactions> gettAllWalletNameByType(@PathVariable long type_id){
        return transactionService.getAllWaletsNameByType(type_id);
    }

}
