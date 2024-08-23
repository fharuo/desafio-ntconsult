# Sistema de Reservas de Hotéis

## Descrição
Este é um sistema de reservas de hotéis desenvolvido em Java com o framework Spring Boot. O sistema permite a criação, cancelamento, check-in, e check-out de reservas, além de oferecer funcionalidades para comparação de hotéis baseadas em critérios como preço, localização, comodidades e avaliação.

## Requisitos
- **JDK**: 21
- **Maven**: 3.6.0 ou superior
- **Docker**: Para execução dos serviços de suporte como RabbitMQ, Prometheus, Grafana, Redis, e PostgreSQL

## Instruções para Build
### Clone o repositório
```
git clone https://github.com/fharuo/desafio-ntconsult.git
cd desafio-ntconsult
```

## Executar containers com Docker Compose
O projeto está configurado para utilizar Docker Compose para orquestrar múltiplos serviços:
1. **Inicie os serviços Docker:**
```
docker-compose up -d
```

2. **Acesse os serviços as URLs abaixo:**
   - **Prometheus:** http://localhost:9090
   - **RabbitMQ:** http://localhost:15672
     - **Login:** guest
     - **Senha:** guest
   - **Grafana:** http://localhost:3000
     - **Login:** admin
     - **Senha:** admin
   - **PostgreSQL:** Está acessível internamente no docker na porta 5432
     - **Login:** root
     - **Senha:** root
    
3. **Crie um banco de dados**
A aplicação está configurada para se conectar a base dados *ntconsult*. Para isso, acesse o terminal da sua imagem do PostgreSQL e digite os seguintes comandos:

```
psql
create database ntconsult;
```

> [!NOTE]
> Ao reiniciar a aplicação, uma nova instancia do banco de dados deve ser gerada.


## Build da Aplicação
1. **Certifique-se de que você está usando a JDK 21:**
```
java -version
```
  A saída deve mostrar a versão 21.

2. **Compile o projeto:**
```
mvn clean install
```

3. **Executar a aplicação:**

   Após compilar, você pode rodar a aplicação diretamente pelo Maevn:
```
mvn spring-boot:run
```
       
## Testes
Para rodar os testes unitários:
```
mvn test
```

## Contato
Em caso de dúvidas, entre em contato.
   
