# Etapa de build: Folosim Maven pentru a compila aplicația
FROM maven:3.8.4-openjdk-17 AS build

# Copiem fișierele de configurare Maven în imagine
COPY pom.xml /tmp/
COPY src /tmp/src/

# Setăm directorul de lucru unde Maven va compila proiectul
WORKDIR /tmp/

# Compilăm și ambalăm aplicația, excluzând testele pentru a accelera procesul de build
RUN mvn package -DskipTests

# Etapa de rulare: Folosim o imagine de bază Java pentru a rula aplicația compilată
FROM openjdk:17-slim

# Variabila pentru a seta portul pe care aplicația îl va asculta
ARG APP_PORT=9090

# Expunem portul pe care aplicația îl va asculta
EXPOSE ${APP_PORT}

# Copiem fișierul .jar din etapa de build în directorul de lucru al containerului
COPY --from=build /tmp/target/grokking-interview-0.0.1-SNAPSHOT.jar /app/grokkin-interview.jar

# Setăm directorul de lucru în container
WORKDIR /app

# Comanda pentru a rula aplicația
CMD ["java", "-jar", "grokkin-interview.jar"]
