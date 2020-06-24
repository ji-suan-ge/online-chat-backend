FROM maven:3.6.3-jdk-8

LABEL maintainer="xuewenG" \
        site="https://github.com/xuewenG/online-chat-backend"

WORKDIR /root

ARG JAR_FILE=target/*.jar

RUN set -x \
    && mvn package

COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java", "-jar", "./app.jar"]
