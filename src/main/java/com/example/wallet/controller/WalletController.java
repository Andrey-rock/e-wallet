package com.example.wallet.controller;

import com.example.wallet.dto.WalletUpdateDTO;
import com.example.wallet.service.WalletService;
import org.springframework.http.ResponseEntity;
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
    ResponseEntity<String> changeBalance (@RequestBody WalletUpdateDTO wallet) {

        walletService.changeBalance(wallet);
        return ResponseEntity.ok("Баланс успешно изменен");
    }

    @GetMapping("/wallets/{WALLET_UUID}")
    ResponseEntity<BigDecimal> getBalance (@PathVariable String WALLET_UUID) {

        BigDecimal balance = walletService.getBalance(WALLET_UUID);
        return ResponseEntity.ok(balance);
    }
}
