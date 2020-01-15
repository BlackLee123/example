FROM maven:3.6-jdk-8
ADD . /app
WORKDIR /app
RUN mvn clean package

FROM java:8
COPY --from=0 /app/target/example-0.0.1.jar app.jar
EXPOSE 9001
CMD java -jar app.jar --spring.profiles.active=dev