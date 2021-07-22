FROM openjdk:latest
EXPOSE 5000
ADD target/my-customer-service.jar my-customer-service.jar
ENTRYPOINT ["sh", "-c", "java -jar /my-customer-service.jar"]