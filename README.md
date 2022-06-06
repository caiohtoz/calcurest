# calcurest
REST API for basic math operations, using a 2-module structure (rest and calculator), Spring Boot and RabbitMQ for intermodule messaging.


## Requirements
- [RabbitMQ Server](https://www.rabbitmq.com/download.html)
- [Java 17 JDK](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- [Docker Desktop](https://docs.docker.com/desktop/windows/install/) (optional)

## Running
1. Run your installed RabbitMQ Server
   - If you don't have RabbitMQ installed, you can execute the following command in CMD/PowerShell instead: `docker run -it --rm --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3.10-management` **(installing and running Docker Desktop is required for this step)**.
2. Run this API through CMD/PowerShell with the command ./mvnw spring-boot:run, like most Spring Boot applications.
3. The API runs on localhost:8080 by default.

## Usage
This API works via HTTP requests (GET method), with the following available basic arithmetic operations:

- http://localhost:8080/calcurest/add
- http://localhost:8080/calcurest/subtract
- http://localhost:8080/calcurest/multiply
- http://localhost:8080/calcurest/divide

It accepts 2 operands only, for all operations, and follows this format: **?a="operand1"&b="operand2"**

Example: http://localhost:8080/calcurest/add?a=1&b=2

The result of each request is formatted in a JSON notation, as such: {"result":"3"}
