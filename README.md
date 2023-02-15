# URL Shortener HTTP Service

This project demonstrates the implementation of url shortening service using Spring Boot 3.0 framework. It includes the following features:

### Features

* User registration and login with JWT authentication
* Password encryption using BCrypt
* Role-based authorization with Spring Security
* URL registration and shortening with redirection and statistics

### Technologies

* Java 17.0.6
* Spring Boot 3.0.2
* Spring Security 6
* JSON Web Tokens (JWT) 
* Maven
* H2 in memory database

### Installation and startup instructions

To get started with this project, you will need to have the following on your local machine:

* JDK 17+
* In System variables you should have JAVA_HOME variable pointed to the JDK installed folder
* PATH system variable should be updated to %JAVA_HOME%\bin

To build and run the project, execute this command:

    java -jar jarfilename.jar

This Java JAR run command assumes the JAR in question is located in the current folder. 
If the JAR file to run is located in a different folder, you’ll need to provide a full path to the file.

### How to use application

* First you should create account with accountId. If it doesn't exist, account is created and you get generated password for login.
* Log via accountId and password. If it is successful you get generated JWT token for further authentication. (In Postman Authorization -> Bearer Token)

-> The application will be available at http://localhost:8080.
