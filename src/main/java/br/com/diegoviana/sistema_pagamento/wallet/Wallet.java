package br.com.diegoviana.sistema_pagamento.wallet;

import java.math.BigDecimal;

import org.springframework.data.annotation.Id;

public record Wallet (
    // PROPRIEDADES QUE REPRESENTAM A CARTEIRA
    @Id Long id,
    String fullName,
    Long cpf,
    String email,
    String password,
    int type,
    BigDecimal balance)
    {
    
}
