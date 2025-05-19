# Usamos una imagen oficial de Maven para construir el proyecto
FROM maven:3.8.7-eclipse-temurin-17 AS build

# Directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiamos los archivos del proyecto al contenedor
COPY pom.xml .
COPY src ./src

# Construimos el proyecto y empaquetamos el jar
RUN mvn clean package -DskipTests

# Segunda etapa: imagen para correr la app (más liviana)
FROM eclipse-temurin:17-jre

# Directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiamos el jar generado en la etapa de build
COPY --from=build /app/target/*.jar app.jar

# Exponemos el puerto que usa Spring Boot (según tu config es el 8081)
EXPOSE 8081

# Comando para arrancar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
