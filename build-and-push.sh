#!/bin/bash

./mvnw clean package -DskipTests
docker build -t chensabban/routing-rules:latest .
docker push chensabban/routing-rules:latest