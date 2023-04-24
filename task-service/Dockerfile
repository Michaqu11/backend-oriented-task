# Budowanie obrazu z maven i JDK
FROM maven:3.8.4-openjdk-17-slim AS builder

# Skopiuj pliki projektu do kontenera
COPY . /usr/src/myapp
WORKDIR /usr/src/myapp

# Buduj aplikację z wykorzystaniem Maven
RUN mvn clean package

# Obraz z JRE, który zostanie uruchomiony
FROM openjdk:17-jdk-slim-buster


EXPOSE 8080

# Skopiuj wygenerowany JAR z katalogu buildera do obrazu JRE
COPY --from=builder /usr/src/myapp/target/task-service-0.0.1-SNAPSHOT.jar /usr/app/myapp.jar


# Uruchom aplikację po uruchomieniu kontenera
CMD ["java", "-jar", "/usr/app/myapp.jar"]