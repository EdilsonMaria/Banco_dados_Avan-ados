# WEB DEVELOPMENT

O projeto a seguir é uma aplicação web seguindo os padrões de API REST, se trata de uma aplicação de Aluno Online, um sistema universitária, com fuções:
    ºCoordenação: 
        Loga no Sistema
        Gerencia Professores
        Gerencia Disciplinas
        Gerencia Alunos;
    ºAluno: 
        Loga no Sistema
        Matricula
        Ver Notas / ver Historico
        Solicitar Trancamento;
    ºProfessor: 
        Loga no Sistema
        Vizualizar Disciplinas
        Gerencia suas Disciplinas;
O proposito dessa aplicação é seguir todos os parametros de CRUD(POST, GET, PUT e DELETE),para as funções propostas acima.

# Índice

- [Visão Geral](#visão-geral)
- [Tecnologias Utilizadas](#tecnologias-utilizadas)
- [Requisitos](#requisitos)
- [Instalação](#instalação)
- [Configuração](#configuração)
- [Uso](#uso)
- [Estrutura do Projeto](#estrutura-do-projeto)
- [Contribuição](#contribuição)
- [Licença](#licença)
- [Contato](#contato)

# Visão Geral

A aplicação da a oportunidade de entender melhor como funciona a chamada de uma API pelo frontEnd atravez do javaScript consumindo os endpoints passados pelo backEnd.

# Tecnologias Utilizadas

- **Backend:**
  - Java Spring Boot
  - PostgreSQL
- **Frontend:**
  - HTML
  - CSS
  - JavaScript

# Requisitos

- [Java Development Kit (JDK)](https://www.oracle.com/java/technologies/javase-downloads.html) 17
- [Maven](https://maven.apache.org/download.cgi)
- [PostgreSQL](https://www.postgresql.org/download/)
- [VS code](https://https://code.visualstudio.com/download) 

# Instalação

# Clonando o Repositório

bash
git clone https://github.com/EdilsonMaria/Banco_dados_Avancados.git
cd api


# Configuração 

- Configure o PostgreSQL e crie um banco de dados
- Atualize o arquivo application.properties em src/main/resources com suas credenciais do banco de dados.

spring.datasource.url=jdbc:postgresql://localhost:5432/seu-database
spring.datasource.username=seu-usuario
spring.datasource.password=sua-senha
spring.jpa.hibernate.ddl-auto=update

- Compile e inicie o backend:

./mvnw spring-boot:run

# Uso

# Estrutura do Projeto

WebDevelopment/
├── backend/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/
│   │   │   └── resources/
│   │   └── test/
│   └── pom.xml
├── frontend/
│   ├── view
│   ├── css
│   └── html
│   └── javaScript
└── README.md  

# Contribuição

Se você deseja contribuir, por favor siga as instruções abaixo:

Faça um fork do projeto
Crie uma branch para sua feature (git checkout -b feature/AmazingFeature)
Commit suas alterações (git commit -m 'Add some AmazingFeature')
Envie para a branch (git push origin feature/AmazingFeature)
Abra um Pull Request

## Licença

Este projeto está licenciado sob a [] - veja o arquivo LICENSE para detalhes.

# Contato
Nome - Edilson Maria da Silva Junior
Email - edilson.eng1@gmail.com

Link GitHub: https://https://github.com/EdilsonMaria
Link Linkedid https://www.linkedin.com/in/edilson-maria-a73477179/