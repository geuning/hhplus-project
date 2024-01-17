FROM openjdk:17
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} hhplus-project.jar
ENTRYPOINT ["java","-jar","/hhplus-project.jar"]
