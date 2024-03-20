package br.com.diegoviana.sistema_pagamento.authorization;

public class UnauthorizedTransactionException extends RuntimeException{
    
    public UnauthorizedTransactionException(String message) {
        super(message);
    }
}
