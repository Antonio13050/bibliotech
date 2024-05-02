# Bibliotech

Este projeto tem como objetivo praticar o desenvolvimento de aplicações usando uma variedade de tecnologias populares. 

## Funcionalidades Implementadas até o momento:

### Backend:

1. **Autenticação e Autorização:**

- Utilização do Spring Security e OAuth2 para implementar autenticação e autorização na aplicação.
- Autenticação baseada em JWT (JSON Web Tokens) para gerar tokens seguros de autenticação para os usuários.
- Definição de roles para os usuários, permitindo diferentes níveis de acesso aos recursos da aplicação.

2. **Endpoints Públicos:**

- **Criação de Usuário:** Um endpoint público (`/users`) permite que novos usuários se inscrevam no sistema, fornecendo informações como nome de usuário e senha.
- **Login:** Um endpoint público (`/login`) permite que os usuários façam login no sistema, verificando suas credenciais e gerando um token JWT válido em caso de sucesso.

3. **Endpoint Privado:**

- **Consulta de Usuários:** Um endpoint privado (`/users`) permite a consulta de informações de usuários cadastrados no sistema. Este endpoint só é acessível por usuários autenticados com a função de administrador, garantindo a segurança dos dados sensíveis.

4. **Comunicação entre serviços:**

- Implementação de uma API de notificações como um microserviço para envio de e-mails aos usuários.
- Utilização do RabbitMQ para a comunicação entre os serviços, garantindo o envio e recebimento de mensagens de forma assíncrona e escalável.

### Frontend:

1. **Integração com o Backend:**

- Implementação de integração completa com o backend, permitindo o envio e recebimento de dados de forma eficiente e segura.

- Utilização de requisições HTTP para interagir com os endpoints fornecidos pelo backend, como autenticação de usuários e registro de novos usuários.

2. **Tratamento de Exceções:**

- Implementação de tratamento de exceções para lidar com erros provenientes das requisições ao backend.

- Exibição de mensagens de erro personalizadas para os usuários, proporcionando uma experiência mais amigável em caso de problemas durante a interação com a aplicação.

3. **Avisos Personalizados aos Usuários:**

- Exibição de avisos personalizados aos usuários em diferentes cenários, como sucesso no registro, falha no login, etc.

- Utilização de mensagens claras e informativas para orientar os usuários sobre as ações realizadas e os resultados obtidos durante o uso da aplicação.

4. **Responsividade:**

- Todas as telas foram desenvolvidas com responsividade utilizando Tailwind CSS, garantindo uma experiência consistente em diferentes dispositivos e tamanhos de tela.

5. **Tema Light/Dark:**

- Implementação de uma opção para mudar entre temas light e dark, oferecendo aos usuários a possibilidade de escolher a aparência preferida da aplicação.

6. **Gerenciamento de Rotas:**

- Utilização do React Router DOM para o gerenciamento de rotas públicas e privadas.

- Rotas públicas permitem acesso a páginas como tela de login e tela de registro, enquanto rotas privadas só são acessíveis por usuários autenticados, como a área do usuário logado.

### Infraestrutura:

1. **Docker:**

 - Todo o backend está em Docker, incluindo:
    - A API principal.
    - A API de notificações.
    - O PGAdmin para administração do banco de dados PostgreSQL.
    - O RabbitMQ para gerenciamento de mensagens.
    - O PostgreSQL como banco de dados principal para armazenamento de dados.

## Tecnologias Utilizadas:

### Backend:

- **Spring Boot:** O framework Spring Boot é utilizado para simplificar o desenvolvimento da aplicação, fornecendo configurações e funcionalidades pré-configuradas.
- **Spring Security:** Implementa a segurança da aplicação, permitindo a autenticação e autorização dos usuários.
- **Spring Mail:** Usado para envio de e-mails aos usuários.
- **Spring AMQP (Advanced Message Queuing Protocol):** Utilizado para comunicação assíncrona entre serviços, integrando-se com o RabbitMQ.
- **OAuth2:** Utilizado em conjunto com o Spring Security para fornecer autenticação segura baseada em tokens.
- **JWT (JSON Web Tokens):** Utilizado para gerar tokens de autenticação seguros para os usuários, proporcionando um meio eficaz de autenticação.
- **PGAdmin:** Utilizado para administração do banco de dados PostgreSQL.
- **RabbitMQ:** Utilizado para gerenciamento de mensagens e comunicação assíncrona entre serviços.
- **PostgreSQL:** Um banco de dados PostgreSQL é utilizado para armazenar informações, incluindo os dados dos usuários. 

### Frontend:

- **React:** Biblioteca JavaScript utilizada para construir a interface do usuário interativa e responsiva.
- **React Hook Form:** Utilizado para criação e validação de formulários no frontend.
- **React Router DOM:** Utilizado para gerenciamento de rotas públicas e privadas no frontend.
- **Tailwind CSS:** Framework CSS utilizado para estilização e responsividade das telas.
- **Zod:** Utilizado para validação de dados no frontend.
A- **xios:** Biblioteca utilizada para realizar requisições HTTP ao backend.