package br.com.diegoviana.sistema_pagamento;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.TopicBuilder;

@SpringBootApplication
public class SistemaPagamentoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SistemaPagamentoApplication.class, args);
	}

	@Bean
	NewTopic notificationTopic() {
		return TopicBuilder.name("transaction-notification")
			.build();
	}
}
