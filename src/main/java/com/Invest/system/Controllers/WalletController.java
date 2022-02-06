package com.Invest.system.Controllers;


import com.Invest.system.Dto.Mappers.WalletDtoMapper;
import com.Invest.system.Dto.WalletDto;
import com.Invest.system.Objects.Users;
import com.Invest.system.Objects.Wallets;
import com.Invest.system.Services.UserService;
import com.Invest.system.Services.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequiredArgsConstructor
public class WalletController {

    private final WalletService walletService;
    private final UserService userService;


   @GetMapping("/wallets")
    public Set<WalletDto> getWallets(@AuthenticationPrincipal UsernamePasswordAuthenticationToken user){
       Users tempUser = userService.getUserByName(
               SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString())
               .orElseThrow(() -> new UsernameNotFoundException("Not found this user"));
       return WalletDtoMapper.mapToWalletDtos(walletService.gettWalletsByOwner(tempUser.getUser_id()));
    }

    @GetMapping("/wallets/transactions")
    public Set<Wallets> getWalletsWithTransactions(@AuthenticationPrincipal UsernamePasswordAuthenticationToken user){
        Users tempUser = userService.getUserByName(
                SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString())
                .orElseThrow(()  ->  new UsernameNotFoundException("Not found this user"));
       return walletService.getWalletWithTransactionsByOwner(tempUser.getUser_id());
    }

    @GetMapping("/wallets/{owner_id}/transactions")
    public Set<Wallets> getWalletsWithTransactionsByOwner(@PathVariable long owner_id){
       return walletService.getWalletWithTransactionsByOwner(owner_id);
    }


    @GetMapping("/wallets/{owner_id}")
    public Set<WalletDto> getWalletsByOnwerId(@PathVariable long owner_id){
       return WalletDtoMapper.mapToWalletDtos(walletService.gettWalletsByOwner(owner_id));
    }

    @PostMapping("/wallets")
    public Wallets addWallet(@RequestBody Wallets wallet){
       return walletService.addWallet(wallet);
    }

    @PutMapping("/wallets")
    public Wallets editWallet(@RequestBody Wallets wallet)  {
       return walletService.editWallet(wallet);
    }

    @DeleteMapping("/wallets/{id}")
    public void deleteWallet(@PathVariable long id){
       walletService.deleteWallet(id);
    }




}
