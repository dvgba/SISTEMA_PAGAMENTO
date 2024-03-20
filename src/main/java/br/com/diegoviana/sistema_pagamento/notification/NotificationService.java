package br.com.diegoviana.sistema_pagamento.notification;

import org.springframework.stereotype.Service;

import br.com.diegoviana.sistema_pagamento.transaction.Transaction;

@Service
public class NotificationService {
    private final NotificationProducer notificationProducer;

    public NotificationService(NotificationProducer notificationProducer) {
        this.notificationProducer = notificationProducer;
    }
    public void notify(Transaction transaction) {
        notificationProducer.sendNotificattion(transaction);
    }
}
