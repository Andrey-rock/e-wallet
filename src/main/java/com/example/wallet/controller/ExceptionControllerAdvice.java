package com.example.wallet.controller;

import com.example.wallet.exception.InsufficientFundsException;
import com.example.wallet.exception.NoSuchWalletException;
import com.example.wallet.model.WalletError;
import io.swagger.v3.oas.annotations.Hidden;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Hidden
@ControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(InsufficientFundsException.class)
    public ResponseEntity<WalletError> handleInsufficientFundsException(@NotNull InsufficientFundsException e) {
        WalletError walletError = new WalletError("400", e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(walletError);
    }

    @ExceptionHandler(NoSuchWalletException.class)
    public ResponseEntity<WalletError> handleNoSuchWalletException(@NotNull NoSuchWalletException e) {
        WalletError walletError = new WalletError("404", e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(walletError);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<WalletError> handleHttpMessageNotReadableException(@NotNull HttpMessageNotReadableException e) {
        WalletError walletError = new WalletError("400", e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(walletError);
    }
}
