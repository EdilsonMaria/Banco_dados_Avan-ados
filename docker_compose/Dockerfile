# Use uma imagem base do OpenJDK com Alpine
FROM openjdk:17-jdk-alpine

# Instale o Maven
RUN apk add --no-cache maven

# Crie o diretório de aplicação
RUN mkdir /app

# Defina o diretório de trabalho
WORKDIR /app

# Copie os arquivos de configuração do Maven (opcional, se houver)
COPY pom.xml /app/
COPY src /app/src

# Compile a aplicação usando Maven
RUN mvn clean package

# Exponha a porta da aplicação
EXPOSE 8080

# Copie o JAR da aplicação para o diretório de trabalho
COPY target/*.jar /app/app.jar

# Comando para executar a aplicação
CMD ["java", "-jar", "/app/app.jar"]