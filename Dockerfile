FROM gradle:jdk11 as build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle bootJar --no-daemon

FROM adoptopenjdk:11
EXPOSE 8080
COPY --from=build /home/gradle/src/build/libs/hosting-0.0.1-SNAPSHOT.jar /usr/src/hosting/app.jar
WORKDIR /usr/src/hosting/
CMD ["java", "-jar", "app.jar"]