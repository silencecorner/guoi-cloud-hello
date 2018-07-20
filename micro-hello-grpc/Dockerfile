FROM frolvlad/alpine-oraclejdk8
LABEL maintainer="https://github.com/conanchen"
VOLUME /tmp
ARG JAR_FILE
ADD ${JAR_FILE} app.jar
ENV SPRING_PROFILES_ACTIVE = "default"
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Dspring.profiles.active=${SPRING_PROFILES_ACTIVE}","-jar","/app.jar"]