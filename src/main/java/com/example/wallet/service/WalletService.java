package com.example.wallet.service;

import com.example.wallet.dto.OperationType;
import com.example.wallet.dto.WalletUpdateDTO;
import com.example.wallet.exception.InsufficientFundsException;
import com.example.wallet.exception.NoSuchWalletException;
import com.example.wallet.model.Wallet;
import com.example.wallet.repository.WalletRepository;
import jakarta.transaction.Transactional;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

@Service
public class WalletService {

    private final WalletRepository walletRepository;

    public WalletService(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    @Transactional
    public void changeBalance(@NotNull WalletUpdateDTO walletDto) {

        Optional<Wallet> wallet = walletRepository.findById(walletDto.getWalletId());
        if (wallet.isPresent()) {
            Wallet walletEntity = wallet.get();
            if (walletDto.getOperationType() == OperationType.DEPOSIT) {
                walletEntity.changeBalance(BigDecimal.valueOf(walletDto.getAmount()));
            } else if (walletDto.getOperationType() == OperationType.WITHDRAW) {
                if (walletEntity.getBalance().compareTo(BigDecimal.valueOf(walletDto.getAmount())) < 0) {
                    throw new InsufficientFundsException("Недостаточно средств");
                }
                walletEntity.changeBalance(BigDecimal.valueOf(walletDto.getAmount() * -1.0));
            }
            walletRepository.save(walletEntity);
        } else {
            throw new NoSuchWalletException("Кошелёк не найден");
        }
    }

    @Transactional
    public BigDecimal getBalance(String uuid) {

        Optional<Wallet> wallet = walletRepository.findById(UUID.fromString(uuid));
        if (wallet.isPresent()) {
            return wallet.get().getBalance();
        } else {
            throw new NoSuchWalletException("Кошелёк не найден");
        }
    }
}
