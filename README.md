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


- Load Balancer in microservice:
	- It helps to distribute reuqest coming from the client application equally between running instances of a service.
	- It prevent overloading on a particular instance of a service.
	- is the process of sharing, incoming network traffic in concurrent or discrete time between servers called a server farm or server pool.
	- Adding eureka instance id to your service, helps eureka distinguse between the different running instances.
	![image](https://user-images.githubusercontent.com/33703095/171837127-873efa9d-9b04-4f5d-aab3-b7351132f7f0.png)
	
- setting up multiple instances on your microservice.
	- Note: discovery and API gateway service only run on one instance.
	- https://github.com/Tohbey/spring-boot-microservice/commit/e4d90d37174ccd96bf18eb2c2a230c8e26a968ef
