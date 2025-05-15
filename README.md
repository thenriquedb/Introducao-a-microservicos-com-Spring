# Introdução a microserviços com Spring

Este projeto é um estudo prático de arquitetura de microserviços utilizando **Spring Boot**, **RabbitMQ**, **MySQL** e envio de **e-mail automático** com **Spring mail**. A aplicação é composta por dois microserviços que se comunicam de forma assíncrona via fila RabbitMQ.

## Estrutura dos Microserviços

- `ms_users`: Responsável por receber dados de usuários e armazená-los no banco de dados.
- `ms_email`: Consumer da fila que envia e-mails para os usuários recém-criados.

---

## 🔧 Tecnologias Utilizadas

- Java 21
- Spring Boot
  - Spring Web
  - Spring Data JPA
  - Spring AMQP (RabbitMQ)
  - Spring Mail
- Lombok
- RabbitMQ
- MySQL
- [Mailtrap](https://mailtrap.io/) (para testes de envio de e-mail)
- Docker (para RabbitMQ e MySQL)


---

## 📌 Funcionalidades

### `ms_users`

- **Endpoint:** `POST /users`
- **Descrição:** Cria um novo usuário, salva no banco de dados MySQL e envia seus dados para a fila `users.v1`.

- **Body JSON:**
  ```json
  {
    "name": "João Silva",
    "email": "joao@email.com"
  }

## Como funciona o fluxo

1. O ms_users recebe uma requisição POST `/users` com nome e e-mail.
2. Os dados são salvos no banco de dados MySQL.
3. Os dados do usuário são enviados para a fila **default.email** no RabbitMQ.
4. O ms_email consome a mensagem da fila e envia um e-mail de boas-vindas para o usuário.
