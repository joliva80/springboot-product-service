FROM azul/zulu-openjdk:17
VOLUME /tmp
ADD ./target/springboot-product-service-0.0.1-SNAPSHOT.jar products-service.jar
ENTRYPOINT ["java","-jar","/products-service.jar"]