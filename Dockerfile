FROM openjdk:8

VOLUME /tmp

ADD target/dns-0.0.1-SNAPSHOT.jar dns-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","dns-0.0.1-SNAPSHOT.jar"]