FROM openjdk:8-jdk-alpine
ADD target/hystrix-dashboard.jar hystrix-dashboard.jar
ENTRYPOINT ["java","-jar", "/hystrix-dashboard.jar"]
