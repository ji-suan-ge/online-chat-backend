FROM openjdk:8-jdk-stretch

LABEL maintainer="xuewenG" \
        site="https://github.com/xuewenG/online-chat-backend"

WORKDIR /root

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java", "-jar", "./app.jar"]
