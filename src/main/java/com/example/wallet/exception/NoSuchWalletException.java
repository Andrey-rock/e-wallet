package com.example.wallet.exception;

public class NoSuchWalletException extends RuntimeException{

    public NoSuchWalletException(String message){
        super(message);
    }
}
