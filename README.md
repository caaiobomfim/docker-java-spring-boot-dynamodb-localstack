# Spring Boot API with Docker and DynamoDB (LocalStack) 🚀

This repository contains a **Spring Boot API** running inside a **Docker container**, integrated with **DynamoDB** using **LocalStack**.

## 📦 Features
- Built with Java 17 and Spring Boot 3.
- Uses AWS SDK v2 for DynamoDB integration.
- Containerized with Docker.
- Includes Docker Compose for easy setup.
- Uses LocalStack to simulate AWS DynamoDB.

## 🔧 Tech Stack
- Java 17.
- Spring Boot 3.
- DynamoDB (via LocalStack).
- AWS SDK v2.
- Docker & Docker Compose.
- Maven.

## 🚀 Getting Started

### 📥 **Cloning the Repository**
To get started, first **clone the repository**:

```sh
git clone https://github.com/caaiobomfim/docker-java-spring-boot-dynamodb-localstack.git
```

### 🔥 Running the Application with Docker Compose
Run the following command to build and start the containers:

```sh
cd docker-java-spring-boot-dynamodb-localstack
docker-compose up -d --build
```

### 🌍 Testing the API
Once the containers are running, you can test the API.

📌 Check if the application is running:

```sh
curl -X GET http://localhost:8080/actuator/health
```

Expected response:

```sh
{"status":"UP"}
```

📌 Create a new message:

```sh
curl -X POST http://localhost:8080/api/messages \
     -H "Content-Type: application/json" \
     -d '{"text": "Hello, LocalStack!"}'
```

Expected response:

```sh
{
  "id": "123e4567-e89b-12d3-a456-426614174000",
  "text": "Hello, LocalStack!"
}
```

📌 Retrieve a message by ID:

```sh
curl -X GET http://localhost:8080/api/messages/123e4567-e89b-12d3-a456-426614174000
```

Expected response:

```sh
{
  "id": "123e4567-e89b-12d3-a456-426614174000",
  "text": "Hello, LocalStack!"
}
```