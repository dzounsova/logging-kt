# logging-kt
Logging Tech Talk Demo

Codebase used to demo logging concepts

## Contains
- Spring Boot Application CoffeeMachine, exposing REST API for making coffee and communicating with MilkTank over REST API
- Spring Boot Application MilkTank, exposing REST API for getting milk or milk foam for coffee
- Docker configuration for running Elastic stack (Elastic, Logstash, Beats and Kibana) and applications with docker compose

## Run apps locally

Use "dev" Spring Profile to run CoffeeMachine and MilkTank

## Run with Docker

Build docker image for CoffeeMachine

    cd ~/logging-kt/CoffeeMachine
    mvn clean install
    docker build -t coffeemachine .

Build docker image for MilkTank

    cd ~/logging-kt/MilkTank
    mvn clean install
    docker build -t milktank .

Run docker compose from file

    cd ~/logging-kt/docker
    docker-compose -f docker-compose.yml up

Access Kibana

    localhost:5601

## Exit
    docker-compose -f docker-compose.yml down
