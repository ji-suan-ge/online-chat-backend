FROM maven:3.6.3-jdk-8

LABEL maintainer="xuewenG" \
        site="https://github.com/xuewenG/online-chat-backend"

WORKDIR /root

RUN set -x \
    && git clone git@github.com:xuewenG/online-chat-backend.git \
    && cd online-chat-backend \
    && mvn package \
    && mv target/*.jar ../app.jar \
    && cd .. \
    && rm -rf online-chat-backend

ENTRYPOINT ["java", "-jar", "./app.jar"]
