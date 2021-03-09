FROM fabric8/java-alpine-openjdk11-jre
# run with user 1001 and be prepared for be running in OpenShift too
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar

RUN adduser -G root --no-create-home --disabled-password 1001 \
  && chown -R 1001 app.jar \
  && chmod -R "g+rwX" app.jar \
  && chown -R 1001:root app.jar
USER 1001

EXPOSE 8080

ENTRYPOINT ["java","-jar","/app.jar"]