package br.com.diegoviana.sistema_pagamento.wallet;

public enum WalletType {
    COMUM(1), LOJISTA(2);

    private int value;

    private WalletType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
