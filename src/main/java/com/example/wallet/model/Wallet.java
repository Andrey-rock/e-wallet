package com.example.wallet.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "wallets")
public class Wallet {

    @Id
    @GeneratedValue
    @Column(name = "wallet_id")
    private UUID walletId;
    private BigDecimal balance;

    public void changeBalance(BigDecimal amount) {
        balance = balance.add(amount);
    }
}
