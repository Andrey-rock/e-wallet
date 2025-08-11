package com.example.wallet;

import com.example.wallet.dto.OperationType;
import com.example.wallet.dto.WalletUpdateDTO;
import com.example.wallet.exception.InsufficientFundsException;
import com.example.wallet.exception.NoSuchWalletException;
import com.example.wallet.model.Wallet;
import com.example.wallet.repository.WalletRepository;
import com.example.wallet.service.WalletService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;


@ExtendWith(MockitoExtension.class)
public class WalletServiceTest {

    @Mock
    private WalletRepository walletRepository;

    @InjectMocks
    private WalletService walletService;

    @Test
    public void testSuccessfulDeposit() {

        UUID uuid = UUID.randomUUID();

        WalletUpdateDTO updateDTO = new WalletUpdateDTO();
        updateDTO.setAmount(100);
        updateDTO.setWalletId(uuid);
        updateDTO.setOperationType(OperationType.DEPOSIT);

        Wallet wallet = new Wallet();
        wallet.setBalance(BigDecimal.ZERO);
        wallet.setWalletId(uuid);

        Wallet wallet2 = new Wallet();
        wallet2.setBalance(BigDecimal.valueOf(100.0));
        wallet2.setWalletId(uuid);

        Mockito.when(walletRepository.findById(uuid)).thenReturn(Optional.of(wallet));

        walletService.changeBalance(updateDTO);

        Mockito.verify(walletRepository).save(wallet2);
    }

    @Test
    public void testSuccessfulWithdraw() {

        UUID uuid = UUID.randomUUID();

        WalletUpdateDTO updateDTO = new WalletUpdateDTO();
        updateDTO.setAmount(9);
        updateDTO.setWalletId(uuid);
        updateDTO.setOperationType(OperationType.WITHDRAW);

        Wallet wallet = new Wallet();
        wallet.setBalance(BigDecimal.TEN);
        wallet.setWalletId(uuid);

        Wallet wallet2 = new Wallet();
        wallet2.setBalance(BigDecimal.valueOf(1.0));
        wallet2.setWalletId(uuid);

        Mockito.when(walletRepository.findById(uuid)).thenReturn(Optional.of(wallet));

        walletService.changeBalance(updateDTO);

        Mockito.verify(walletRepository).save(wallet2);
    }

    @Test
    public void When_there_are_not_enough_funds_Get_InsufficientFundsException() {

        UUID uuid = UUID.randomUUID();

        WalletUpdateDTO updateDTO = new WalletUpdateDTO();
        updateDTO.setAmount(100);
        updateDTO.setWalletId(uuid);
        updateDTO.setOperationType(OperationType.WITHDRAW);

        Wallet wallet = new Wallet();
        wallet.setBalance(BigDecimal.TEN);
        wallet.setWalletId(uuid);

        Mockito.when(walletRepository.findById(uuid)).thenReturn(Optional.of(wallet));

        Assertions.assertThrows(InsufficientFundsException.class, () -> walletService.changeBalance(updateDTO));
    }

    @Test
    public void When_wallet_not_found_Get_NoSuchWalletException1() {

        UUID uuid = UUID.randomUUID();

        WalletUpdateDTO updateDTO = new WalletUpdateDTO();
        updateDTO.setAmount(100);
        updateDTO.setWalletId(uuid);
        updateDTO.setOperationType(OperationType.WITHDRAW);

        Mockito.when(walletRepository.findById(uuid)).thenReturn(Optional.empty());

        Assertions.assertThrows(NoSuchWalletException.class, () -> walletService.changeBalance(updateDTO));
    }

    @Test
    public void testSuccessfulGetBalance() {

        UUID uuid = UUID.randomUUID();

        WalletUpdateDTO updateDTO = new WalletUpdateDTO();
        updateDTO.setAmount(9);
        updateDTO.setWalletId(uuid);
        updateDTO.setOperationType(OperationType.WITHDRAW);

        Wallet wallet = new Wallet();
        wallet.setBalance(BigDecimal.TEN);
        wallet.setWalletId(uuid);

        Mockito.when(walletRepository.findById(uuid)).thenReturn(Optional.of(wallet));

        walletService.getBalance(uuid.toString());

        Mockito.verify(walletRepository).findById(uuid);
    }

    @Test
    public void When_wallet_not_found_Get_NoSuchWalletException2() {

        String uuid = UUID.randomUUID().toString();

        Mockito.when(walletRepository.findById(UUID.fromString(uuid))).thenReturn(Optional.empty());

        Assertions.assertThrows(NoSuchWalletException.class, () -> walletService.getBalance(uuid));
    }
}
