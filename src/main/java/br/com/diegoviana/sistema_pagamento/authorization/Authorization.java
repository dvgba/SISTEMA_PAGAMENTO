package br.com.diegoviana.sistema_pagamento.authorization;

public record Authorization(
    String message
) {
    public boolean isAuthorized() {
        return message.equals("Autorizado");
    }
}
