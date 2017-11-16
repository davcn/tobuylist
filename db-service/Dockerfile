FROM java:8
COPY target/db-service-0.0.1-SNAPSHOT.jar /opt/db-service/lib/db-service.jar
ENTRYPOINT ["/usr/bin/java"]
CMD ["-jar", "/opt/db-service/lib/db-service.jar"]
EXPOSE 8301
