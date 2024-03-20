package br.com.diegoviana.sistema_pagamento.notification;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import br.com.diegoviana.sistema_pagamento.transaction.Transaction;

@Service
public class NotificationProducer {
    private final KafkaTemplate<String, Transaction> kafkaTemplate;

    public NotificationProducer(KafkaTemplate<String, Transaction> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendNotificattion(Transaction transaction) {
        kafkaTemplate.send("transaction-notification", transaction);
    }
}
