package com.example.wallet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor

public class WalletUpdateDTO {

    private UUID walletId;
    private OperationType operationType;
    private double amount;

}
