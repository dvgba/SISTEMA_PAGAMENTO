package br.com.diegoviana.sistema_pagamento.transaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.jdbc.repository.config.EnableJdbcAuditing;
import org.springframework.data.relational.core.mapping.Table;

@EnableJdbcAuditing
@Table("TRANSACTIONS")
public record Transaction (
    @Id Long id,
    Long payer,
    Long payee,
    BigDecimal value,
    @CreatedDate LocalDateTime createdAt) {
    
// Customização do construtor em relação a numero de casas decimais
    public Transaction {
        value = value.setScale(2);
    }    
}
