# Stage 1: Build the application
FROM maven:3.9.4-eclipse-temurin-21 AS builder
WORKDIR /app
COPY ../pom.xml .
COPY ../src ./src
#RUN mvn clean package -DskipTests=false
RUN --mount=type=cache,target=/root/.m2/repository mvn -e -B clean package -Dmaven.test.skip

# Stage 2: Run the application
FROM eclipse-temurin:21-jdk-alpine AS runner
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar
COPY --from=builder /app/opentelemetry-javaagent.jar /app/opentelemetry-javaagent.jar
ENV JAVA_TOOL_OPTIONS="-javaagent:/app/opentelemetry-javaagent.jar"
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]