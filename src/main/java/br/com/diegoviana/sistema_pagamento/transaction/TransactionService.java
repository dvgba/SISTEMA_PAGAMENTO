package br.com.diegoviana.sistema_pagamento.transaction;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.diegoviana.sistema_pagamento.authorization.AuthorizerService;
import br.com.diegoviana.sistema_pagamento.notification.NotificationService;
import br.com.diegoviana.sistema_pagamento.wallet.Wallet;
import br.com.diegoviana.sistema_pagamento.wallet.WalletRepository;
import br.com.diegoviana.sistema_pagamento.wallet.WalletType;

//Responsavel por criar as operações
@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final WalletRepository walletRepository;
    private final AuthorizerService authorizerService;
    private final NotificationService notificationService;

    public TransactionService(
        TransactionRepository transactionRepository, 
        WalletRepository walletRepository, 
        AuthorizerService authorizerService, 
        NotificationService notificationService) {
            
        this.transactionRepository = transactionRepository;
        this.walletRepository = walletRepository;
        this.authorizerService = authorizerService;
        this.notificationService = notificationService;
    }

    @Transactional
    public Transaction create(Transaction transaction) {
        // 1 - Validação
        validate(transaction);
        // 2 - Criação a transação
        var newTransaction = transactionRepository.save(transaction);
        // 3 - Debitação da Carteira
        var wallet = walletRepository.findById(transaction.payer()).get();
        walletRepository.save(wallet.debit(transaction.value()));
        // 4 - Chamando serviço externo
        // 4.1 - autorização de transação
        authorizerService.authorize(transaction);
        // 4.2 - Notificação
        notificationService.notify(transaction);

        return newTransaction;
    }

    /* # Regras de negocio para saber se a transação é valida.

     * - O pagador tem a uma carteira comum
     * - O pagador tem saldo suficiente
     * - O Pagador não pode ser o recebedor
     */
    private void validate(Transaction transaction) {
        walletRepository.findById(transaction.payee())
            .map(payee -> walletRepository.findById(transaction.payer())
                .map(payer -> isTransactionValid(transaction, payer) ? transaction : null)
                .orElseThrow(
                    () -> new InvalidTransactionException("Invalid transaction - %s".formatted(transaction))))
            .orElseThrow(
                () -> new InvalidTransactionException("Invalid transaction - %s".formatted(transaction)));
    }

    private boolean isTransactionValid(Transaction transaction, Wallet payer) {
        return payer.type() == WalletType.COMUM.getValue() &&
            payer.balance().compareTo(transaction.value()) >= 0 &&
            !payer.id().equals(transaction.payee());
    }

    public List<Transaction> list() {
        return transactionRepository.findAll();
    }
    
}
