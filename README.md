# spring-boot-microservice
Microservice tutorial

Introduction to Spring Microservice

- Eureka Discovery Service: is an application that holds the information about all client-service applications. Every Micro service will register into the Eureka server and Eureka server knows all the client applications running on each port and IP address.
      - It helps to keep account of all services running in a microservice.
      - It knows the addresses of each service running.
      - It works with API gaetway and load balancer. 

- Discovery Service setup: https://github.com/Tohbey/spring-boot-microservice/tree/main/DiscoveryService

- Microservice Structure:
    Client App -> API Gateway -> Eureka -> other services i.e (Search Service, User Service etc)
Note: A service need to be registered with eureka discovery service before it can be accessed by the API gateway.

- User Service setup: https://github.com/Tohbey/spring-boot-microservice/tree/main/UserService
- Account management setup: https://github.com/Tohbey/spring-boot-microservice/tree/main/AccountManagement

- Eureka Discovery Service has a nice UI that display the services that have registered with eureka and are running.
- Starting up process
    - Discover Service.
    - User Service.
    - Account Management Service.
    - To view the eureka UI -> http://localhost:8010/dashboard

- Spring Cloud API gateway:
    - It servers as a single entry point into a microservice with the assistance of eureka service, it is able to point to the service and exact endpoint.
    - it has a load balancer built in it, to distribute request coming from clients application equally between running instances of our services.
    - There are filters to filter incoming and outgoing request, Custom filters can be created too.

- Spring Cloud API Gateway Service setup: https://github.com/Tohbey/spring-boot-microservice/tree/main/DiscoveryService


- New Starting up process
    - Discover Service.
    - Spring CLoud API Gateway Service.
    - User Service.
    - Account Management Service.
    - To view the eureka UI -> http://localhost:8010/dashboard

- To access an endpoint
	- get the Spring Cloud API Gateway url -> http://localhost:8082
	- get the service application name i.e user-ws
	- get the controller url i.e /user
	- get the request mapping to a particular method i.e /status/check
i.e http://localhost:8082/user-ws/user/status/check
