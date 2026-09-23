FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /barbershopBe

COPY pom.xml ./
RUN mvn -B dependency:go-offline

COPY src ./src
RUN mvn -B clean package -DskipTests

FROM eclipse-temurin:17-jre
WORKDIR /barbershopBe

COPY --from=build /barbershopBe/target/*.jar barbershopBe.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/barbershopBe/barbershopBe.jar"]
