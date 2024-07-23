SHELL := /bin/bash

PROJECT_NAME := $(if $(APP_NAME),$(APP_NAME),"inditex-technical-test")
CURRENT_VERSION := $(if $(VERSION),$(VERSION),"1.0.0-SNAPSHOT")

COMPOSE=docker-compose -f docker/docker-compose.yml

### Docker ###
docker/install:
	$(COMPOSE) build
	make docker/up

docker/up:
	$(COMPOSE) up -d

docker/stop:
	$(COMPOSE) stop

docker/down:
	$(COMPOSE) down

bash:
	$(COMPOSE) exec -it java bash


### Gradlew ###
compile-app:
	./gradlew clean build

test:
	./gradlew clean test

cache-clear:
	./gradlew clean
