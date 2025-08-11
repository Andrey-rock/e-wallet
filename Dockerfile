FROM openjdk:17-oracle
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} wallet.jar
ENTRYPOINT ["java","-jar","/wallet.jar"]