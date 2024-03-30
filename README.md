# Bibliotech

Este projeto tem como objetivo praticar o desenvolvimento de aplicações usando uma variedade de tecnologias populares. A parte inicial do projeto concentra-se no desenvolvimento do backend, com ênfase na segurança e autenticação.

## Funcionalidades Implementadas:

### Autenticação e Autorização:
- Utilização do Spring Security e OAuth2 para implementar autenticação e autorização na aplicação.
- Autenticação baseada em JWT (JSON Web Tokens) para gerar tokens seguros de autenticação para os usuários.
- Definição de roles para os usuários, permitindo diferentes níveis de acesso aos recursos da aplicação.

### Endpoints Públicos:
- **Criação de Usuário:** Um endpoint público (`/users`) permite que novos usuários se inscrevam no sistema, fornecendo informações como nome de usuário e senha.
- **Login:** Um endpoint público (`/login`) permite que os usuários façam login no sistema, verificando suas credenciais e gerando um token JWT válido em caso de sucesso.

### Endpoint Privado:
- **Consulta de Usuários:** Um endpoint privado (`/users`) permite a consulta de informações de usuários cadastrados no sistema. Este endpoint só é acessível por usuários autenticados com a função de administrador, garantindo a segurança dos dados sensíveis.

## Tecnologias Utilizadas:
- **Spring Boot:** O framework Spring Boot é utilizado para simplificar o desenvolvimento da aplicação, fornecendo configurações e funcionalidades pré-configuradas.
- **Spring Security:** Implementa a segurança da aplicação, permitindo a autenticação e autorização dos usuários.
- **OAuth2:** Utilizado em conjunto com o Spring Security para fornecer autenticação segura baseada em tokens.
- **JWT (JSON Web Tokens):** Utilizado para gerar tokens de autenticação seguros para os usuários, proporcionando um meio eficaz de autenticação.
- **Banco de Dados PostgreSQL em Docker:** Um banco de dados PostgreSQL é utilizado para armazenar as informações dos usuários. O PostgreSQL é executado em um contêiner Docker para facilitar o gerenciamento e a implantação do banco de dados.
