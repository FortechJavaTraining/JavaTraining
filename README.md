CONTENTS OF THIS FILE
---------------------

 * Introduction
 * Requirements
 * Installation
 * Configuration
 * Maintainers


INTRODUCTION
------------

The JavaTraining demo project was created as a training app to allow participants to build a 
Java Spring Boot application from the ground up.

It is used to teach newcomers into the Java Spring world best practices for developing enterprise applications,
to bring all the participants to a level where they can become active members of teams within the company working on 
commercial projects.


REQUIREMENTS
------------

Prior of cloning the project from the repository to your local drive, you need to take the following steps:
 * Download and install Docker for your environment from https://www.docker.com/products/docker-desktop;
 * Download and install Postgres image for Docker from https://hub.docker.com/_/postgres;
 * Download and install Postman from https://www.postman.com/.
   
INSTALLATION
------------

 * Create a working directory for your project then at from the command prompter clone the application from 
      github to the local drive created. (use git clone)
 * Modify the [.env file](.env) with the corresponding user and password to be used on your computer to start postgres 
   in the docker environment.
 * You should keep the content of [docker compose file](docker-compose.yaml) as is. From the directory where 
   docker-compose.yaml resides run "docker-compose up -d" to start the postgres db in your Docker environment.
 * Set your application environment variables SPRING_DATASOURCE_USERNAME=user, SPRING_DATASOURCE_PASSWORD=password.
   These are located in the [configuration section](src/main/resources/projectdocs/EditEnvConfiguration.png) for IntelliJ IDEA 2020.2.4. 
   User and password are the credentials for postgres set un in the .env file and will be used by the application to set the corresponding variables in 
   the [application properties file](src/main/resources/application.properties) file. 
 * For the development environment you can leave the defaults from the downloads for all the files provided above unless
   there is an overlap with an existing configuration in your environment.
   For QA and Production please enter the corresponding configurations.
  
   


RUN THE APPLICATION
-------------

    1. Run "docker-compose up -d" to start the postgres database in a 
       docker environment, using the file provided in the root directory, 
       using the command line
    2. Start the application from IntelliJ, Run ->DemoApplication
    3. In the Postman application run Post with http://localhost:8080/sign-up and then 
    http://localhost:8080/login to get a tocken that can be used to run other rest commands in Postman.  


MAINTAINERS
-----------

 * Fortech OnBoarding Team - Nov. + Dec., 2020
 * Supporting Trainers - for directions on application development


Supporting organization:

 * Fortech  - https://www.fortech.ro/

