# Builders - Backend Challenge

## 📝 Objetivo
Olá devs! 

Esse serviço propõem em fazer um gerenciamento de clientes aonde é possível:

- Criar 
- Alterar dados
- Remover
- Buscar por cliente
- Listar todos os clientes

Tudo isso utilizando uma arquitetura API REST, aonde você está comunicando com as API'S expostas no Swagger!


## :rocket: Acesso ao sistema 
O serviço esta deployado na nuvem do Heroku! Você pode estar encontrando esse serviço clicando [aqui](https://mcsv-clients.herokuapp.com/swagger-ui/)

## 💻 Tecnologia Utilizada:
- Javan 11
- Spring Boot - 2.5.2   
- JUnit 5
- Swagger
- Docker
- Postgres DB
- Heroku
- -IntelliJ

## :gear: Como inicializar

Para você desenvolvedor(a), deve ter instalado em sua máquina o **Java 11** para que a aplicação funcione.

Siga os passos asseguir para efetuar corretamente que sua aplicção rode em seu computador:
 
 - 1º Instalando as dependências
  
 ```
 mvn clean install
 ```
 - 2º Inicializa sua aplicação

  Sua aplicação, ao incializar, deve aparecer dessa forma. A porta do servidor está apontando para a 8081
  
  ![image](https://user-images.githubusercontent.com/30670185/126930948-1fe08dbe-8e69-4480-bac9-32b73e0445b9.png)

  
  
  - 3º Acessando as API'S
  
  Após inicializar vocè deve entrar na URL http://localhost:8081/swagger-ui/, que redirecionará para o Swagger da aplicação.
  
  
   - 4º Subir docker-compose 🐳 (Opcional)

  Caso queira executar um banco de dados sem precisar instalar o Postgres em sua máquina você pode utilizar o docker-compose que se encontra na paste Docker
  
  ```
  mcsv-clients/docker
  ```
  
  **Atenção!!**
  
  Para executar o docker é necessário a instalação do docker em sua máquina e também a instalação do docker compose.Você pode estar vendo essa instalação aqui,     próprio site oficial do Docker =>  https://docs.docker.com/get-docker/
  
  
  Tendo o seu docker configurado em sua máquina, vocẽ deve entrar na pasta Docker que se encontra no projeto e dar o seguinte comando:
  
  ```
  docker-compose up
  ```
  Logo em seguida você algo aparecido com esse log e isso significa que deu certo 😃 !
  
  Caso queira encerrar o container, apenas dê um **CTRL + C** que logo encerrará.
  
  

  ## Informações extra:
  
  Caso queira utilizar o banco de dados local, certifique de alterar o **application.properties** que se encontra na pasta */resources*, deixando da seguinte forma.
  
``` 
  spring.jpa.database=POSTGRESQL
  spring.sql.init.platform=postgres
  spring.jpa.show-sql=true
  spring.jpa.hibernate.ddl-auto=update
  spring.database.driverClassName=org.postgresql.Driver
  spring.datasource.url=jdbc:postgresql://localhost:5432/postgres
  spring.datasource.username=postgres
  spring.datasource.password=docker
```


 ALém disso verifique em ter criado o Schema no seu banco de dados com o nome **builders_db**,pois atualmente o próprio serviço irá criar tabelas em base desse Schema.
 
 
 Espero ter ajudado ! Qualquer informção deixa na aba **Issues** que GitHub disponibiliza para identificação de erros no sistema.
