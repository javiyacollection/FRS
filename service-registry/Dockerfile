FROM openjdk:8-jdk-alpine
ADD target/service-registry.jar service-registry.jar
ENTRYPOINT ["java","-jar", "/service-registry.jar"]