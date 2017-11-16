FROM java:8
COPY target/eureka-service-0.0.1-SNAPSHOT.jar /opt/eureka/lib/eureka-service.jar
ENTRYPOINT ["/usr/bin/java"]
CMD ["-jar", "/opt/eureka/lib/eureka-service.jar"]
EXPOSE 8300
