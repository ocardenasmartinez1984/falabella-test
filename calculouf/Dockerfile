# ---- Etapa de build ----
FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app

# Cache de dependencias
COPY pom.xml .
COPY .mvn .mvn
COPY mvnw .
RUN mvn dependency:go-offline -B

# Compilacion + pruebas unitarias + reporte de cobertura JaCoCo.
# (No se usa -DskipTests: los tests corren aqui y JaCoCo genera el reporte.)
COPY src ./src
RUN mvn clean package -B

# ---- Etapa de runtime ----
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# Jar ejecutable
COPY --from=build /app/target/calculouf-0.0.1-SNAPSHOT.jar app.jar

# Reporte de cobertura JaCoCo generado durante el build, disponible en la imagen
# para poder extraerlo con `docker cp` (ver README).
COPY --from=build /app/target/site/jacoco /jacoco

EXPOSE 1984
ENTRYPOINT ["java", "-jar", "app.jar"]
