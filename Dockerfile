# Stage 1: Build
# Usamos a imagem do Maven com Eclipse Temurin 17
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Run
# Usamos o JRE (Java Runtime Environment) que é mais leve que o JDK para rodar a app
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
# Copia o jar gerado no estágio anterior
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]