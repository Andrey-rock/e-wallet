package com.example.wallet.controller;

import com.example.wallet.dto.WalletUpdateDTO;
import com.example.wallet.service.WalletService;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("api/v1")
public class WalletController {

    private final WalletService walletService;

    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @PostMapping("/wallet")
    void changeBalance (@RequestBody WalletUpdateDTO wallet) {

        walletService.changeBalance(wallet);
    }

    @GetMapping("/wallets/{WALLET_UUID}")
    BigDecimal getBalance (@PathVariable String WALLET_UUID) {

        return walletService.getBalance(WALLET_UUID);
    }
}
