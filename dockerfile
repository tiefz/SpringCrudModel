FROM openjdk:12
ADD productief.jar productief.jar
EXPOSE 8083
ENTRYPOINT ["java", "-jar", "productief.jar"]
