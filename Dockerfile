# Stage 1: Build com Maven e Java 17
FROM maven:3.9.5-eclipse-temurin-17 AS build
WORKDIR /app

# Copia o pom e o código fonte
COPY pom.xml .
COPY src ./src

# Compila o projeto e gera o JAR
RUN mvn clean package -DskipTests

# Stage 2: Runtime com Java 17
FROM eclipse-temurin:17-jre
WORKDIR /app

# Copia o JAR do build
COPY --from=build /app/target/SysTrack2-0.0.1-SNAPSHOT.jar app.jar

# Expondo a porta que o Spring Boot usa
EXPOSE 8080

# Comando para iniciar a aplicação
ENTRYPOINT ["java","-jar","app.jar"]
