# Microservice Architecture with Spring Boot

This project demonstrates a microservice architecture using Spring Boot, implementing secure and resilient inter-service communication. It consists of multiple Spring Boot services for User, Rating, and Hotel management, along with essential microservice components such as Service Registry, Config Server, and API Gateway.

## Features
- **Service Registry**: Centralized service discovery using Eureka.
- **Config Server**: Centralized configuration management stored in a GitHub repository.
- **API Gateway**: Single entry point for external requests with routing and security.
- **User Service**: Handles user-related data, stored in a MySQL database.
- **Rating Service**: Manages user ratings in a MongoDB database.
- **Hotel Service**: Provides hotel data, stored in a PostgreSQL database.
- **Security**: OAuth2 authentication and authorization with resource servers.
- **Resilience**: Implemented Circuit Breaker, Retry, and Rate Limiter using Resilience4j.

## Architecture Overview
- Users interact with the **UserService** via the **API Gateway**.
- **UserService** communicates with **RatingService**, which in turn interacts with **HotelService**.
- Direct access to **RatingService** and **HotelService** is restricted for enhanced security.

### Tech Stack
- **Spring Boot**: Framework for building all services.
- **Spring Cloud**: Service Registry (Eureka), Config Server, Gateway.
- **Database**: MySQL (User Service), MongoDB (Rating Service), PostgreSQL (Hotel Service).
- **Security**: OAuth2, JWT, Spring Security.
- **Resilience**: Resilience4j for Circuit Breaker, Retry, and Rate Limiter.
- **External Config**: GitHub-based configuration using Config Server.

## Services Overview
### 1. Service Registry
- **Port**: `8761`  
A Eureka-based service discovery server that allows services to register and discover each other dynamically.

### 2. Config Server
- **Port**: `8085`  
Centralized configuration management storing `application.yml` files in a GitHub repository.  
- Configuration Repository: [Microservice Configuration Server](https://github.com/umeshtiwari02/microservice-configuration-server)

### 3. API Gateway
- **Port**: `8084`  
A Spring Cloud Gateway that routes requests to respective microservices and provides basic security.

### 4. UserService
- **Port**: `8081`  
Handles user-related operations, communicates with RatingService, and uses MySQL for data persistence.

### 5. RatingService
- **Port**: `8083`  
Manages user ratings, communicates with HotelService, and uses MongoDB for data storage.

### 6. HotelService
- **Port**: `8082`  
Provides hotel data and uses PostgreSQL for database storage.

## How to Run
To implement and see the results of this microservice architecture, all the services must be started individually. Ensure the following steps are completed:

1. Start the **Service Registry** first on port `8761`.  
   This is essential for all services to register themselves dynamically.

2. Start the **Config Server** on port `8085`.  
   Ensure the GitHub-based configuration repository is accessible.

3. Start the **API Gateway** on port `8084`.  
   It acts as a single entry point for all external requests.

4. Start the **UserService** on port `8081`.

5. Start the **RatingService** on port `8083`.

6. Start the **HotelService** on port `8082`.

### Note
- The order of starting the services is critical: **Service Registry**, followed by **Config Server**, and then other services.  
- Ensure that the databases (MySQL, MongoDB, and PostgreSQL) are up and running with the required schemas/tables.

## Testing
You can use **Postman** to test the APIs. Postman allows you to send HTTP requests to the API Gateway and validate the responses from the microservices.  
### Sample Endpoints
- API Gateway Base URL: `http://localhost:8084`
  - User Service:
    - For all users: `/users`
    - For particular user: `/users/{userId}/ `
  - Rating Service: Accessed indirectly via UserService.
  - Hotel Service: Accessed indirectly via RatingService.

### Postman Steps
1. Create a new request in Postman.
2. Set the method (GET, POST, PUT, DELETE) based on the API you are testing.
3. Use `http://localhost:8084` as the base URL for your requests.
4. Add necessary headers, body data, or parameters as required.

### Centralized Configuration
The `application.yml` configurations for all services are stored in a GitHub repository:  
[Microservice Configuration Server](https://github.com/umeshtiwari02/microservice-configuration-server)

## Key Dependencies
- **Spring Boot Starter Web**: For building RESTful APIs.
- **Spring Cloud Dependencies**: Eureka, Config Server, Gateway.
- **Spring Security**: For authentication and authorization.
- **Resilience4j**: For resilience patterns like Circuit Breaker and Retry.
- **Lombok**: For reducing boilerplate code (e.g., getters, setters).  

## Feign Client and RestTemplate Interceptors in UserService

In **UserService**, I used **FeignClientInterceptor** and **RestTemplateInterceptor** to manage headers, including OAuth2 tokens, ensuring secure communication between services.

### 1. **FeignClientInterceptor**
The **FeignClientInterceptor** adds the OAuth2 token to the headers of Feign client requests. This ensures that all outgoing requests from **UserService** to other services (like RatingService and HotelService) are authenticated with a Bearer token.

### 2. **RestTemplateInterceptor**
The **RestTemplateInterceptor** is applied to **RestTemplate** requests, adding the OAuth2 token to the request headers. This ensures secure communication between **UserService** and other services using **RestTemplate**.

## Token Generation and Authentication

To securely authenticate and obtain the OAuth2 token for communication between services, **Okta** is used for user login and token issuance.

1. **Login to Okta**:  
   Use the credentials of the mentioned user (which should be created in the Okta dashboard).

2. **Token Retrieval**:  
   Once logged in, Okta will issue an OAuth2 token, which is used for secure communication between services in the system.

3. **OAuth2 Authentication**:  
   The generated token is included in the **Authorization** header of the HTTP requests, ensuring secure access to the services.


## The architecture can be represented visually as follows (conceptually):


                                 +-------------------+
                                 |   API Gateway     |
                                 +-------------------+
                                         |
                        +----------------+----------------+
                        |                                 |
                  +-------------+                   +----------------+
                  | User Service|                   | Service Registry|
                  +-------------+                   +----------------+
                        |
            +-----------+-----------+
            |                       |
    +----------------+     +----------------+
    | Rating Service |     | Hotel Service  |
    +----------------+     +----------------+

