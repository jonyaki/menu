FROM adoptopenjdk:17-jdk-hotspot

# Establece el directorio de trabajo en /app
WORKDIR /app

# Copia el archivo pom.xml y verifica las dependencias
COPY pom.xml .
RUN mvn -B dependency:resolve dependency:resolve-plugins

# Copia los archivos del proyecto (excluyendo los que estén en el .dockerignore)
COPY src ./src

# Compila la aplicación
RUN mvn clean package

# Expone el puerto 8080 (o el puerto que esté configurado en tu aplicación Spring Boot)
EXPOSE 8080

# Comando para ejecutar la aplicación al iniciar el contenedor
CMD ["java", "-jar", "target/myMenu*.jar"]
