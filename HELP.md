# Read Me First
The following was discovered as part of building this project:

* The original package name 'com.mxfz.producer-service' is invalid and this project uses 'com.mxfz.producer_service' instead.

# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/3.2.7/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/3.2.7/maven-plugin/reference/html/#build-image)
* [Spring Reactive Web](https://docs.spring.io/spring-boot/docs/3.2.7/reference/htmlsingle/index.html#web.reactive)
* [Spring Data Reactive Redis](https://docs.spring.io/spring-boot/docs/3.2.7/reference/htmlsingle/index.html#data.nosql.redis)
* [Spring for Apache Kafka](https://docs.spring.io/spring-boot/docs/3.2.7/reference/htmlsingle/index.html#messaging.kafka)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a Reactive RESTful Web Service](https://spring.io/guides/gs/reactive-rest-service/)
* [Messaging with Redis](https://spring.io/guides/gs/messaging-redis/)

### Maven Parent overrides

Due to Maven's design, elements are inherited from the parent POM to the project POM.
While most of the inheritance is fine, it also inherits unwanted elements like `<license>` and `<developers>` from the parent.
To prevent this, the project POM contains empty overrides for these elements.
If you manually switch to a different parent and actually want the inheritance, you need to remove those overrides.

