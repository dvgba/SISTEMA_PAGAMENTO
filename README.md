# Sistema de Pagamento

O sistema de pagamento é uma aplicação desenvolvida em Java com Spring Boot, que permite realizar transações financeiras entre usuários. Ele oferece funcionalidades para criar transações, validar transações, autorizar transações e notificar os usuários sobre as transações.

## Arquitetura

O sistema segue uma arquitetura baseada em microsserviços, com os seguintes domínios:

- **Wallet (Carteira)**: Representa as contas dos usuários, incluindo informações como nome completo, CPF, e-mail, tipo de carteira e saldo.
- **Transaction (Transação)**: Representa uma transação financeira entre duas carteiras.
- **Notification (Notificação)**: Responsável por notificar os usuários sobre transações.
- **Authorization (Autorização)**: Responsável por autorizar transações.

## Componentes Principais

- **SistemaPagamentoApplication**: Classe principal que inicializa a aplicação Spring Boot.
- **Wallet**: Representa uma carteira de usuário.
- **Transaction**: Representa uma transação financeira.
- **Notification**: Representa uma notificação.
- **Authorization**: Representa uma autorização de transação.
- **TransactionController**: Controlador REST responsável por gerenciar endpoints relacionados a transações.
- **TransactionService**: Serviço responsável por lógica de negócios relacionada a transações.
- **WalletRepository**: Interface para acesso ao banco de dados de carteiras.
- **TransactionRepository**: Interface para acesso ao banco de dados de transações.
- **NotificationConsumer**: Consumidor de mensagens do Kafka responsável por receber notificações de transações.
- **NotificationProducer**: Produtor de mensagens do Kafka responsável por enviar notificações de transações.

## Funcionalidades Principais

- **Criação de Transação**: Os usuários podem criar transações entre suas carteiras.
- **Validação de Transação**: As transações são validadas para garantir que o pagador tenha saldo suficiente e não esteja transferindo para si mesmo.
- **Autorização de Transação**: As transações são autorizadas antes de serem concluídas.
- **Notificação de Transação**: Os usuários são notificados sobre as transações realizadas.