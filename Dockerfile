FROM java:8
VOLUME /tmp
ADD target/spring-boot-neo4j-0.0.1-SNAPSHOT.jar  recommendationservice.jar
RUN bash -c 'touch /recommendationservice.jar'
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /recommendationservice.jar"]

   