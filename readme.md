# Inditex Technical Test
Technical test for Inditex: Using Java with Spring Framework

# 🏁 Getting Started

- Docker and docker-compose MUST be installed. https://docs.docker.com/get-docker/
- Run the following command to build and run the docker container

```sh
make docker/install
```

- This will have raised the API and the Swagger UI. You can check it on http://localhost:8080/health-check
  and http://localhost:777/
- The application tests run when the application is build but you can run it manually with

```sh
make test
```

- Check the Makefile to see all the commands