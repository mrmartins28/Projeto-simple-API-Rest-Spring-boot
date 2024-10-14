# Use uma imagem base do OpenJDK
FROM openjdk:17-jdk-slim

# Defina o diretório de trabalho
WORKDIR /app

# Copie os arquivos do projeto para o diretório de trabalho
COPY . .

# Instale o Maven
RUN apt-get update && \
    apt-get install -y maven && \
    rm -rf /var/lib/apt/lists/*

# Execute o Maven para compilar o projeto
RUN mvn clean package -DskipTests

# Exponha a porta que a aplicação vai rodar
EXPOSE 8080

# Comando para executar a aplicação
CMD ["java", "-jar", "target/cursojdev-0.0.1-SNAPSHOT.jar"]