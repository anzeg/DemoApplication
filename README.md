# DemoApplication
Demo application for backend coding challenge

Your final product should be a backend application for movies and actors management via REST API, described in details in the following sections. Keep in mind that application can be used by multiple clients at the same time and all data needs to be persisted somewhere (preferred H2 database). UI is not part of this demo application.

## Optional:
* implement HTTP cache mechanisms where is needed
* implement request counter for all REST calls and save it in the local file

## Movies
Movies have properties as title, year, description, list of actors, some pictures, etc. (as identifier use imdbID). Implement REST operations to support basic UI requirements:
* list all movies
* list movies with pagination support (describe filtering, sorting, pagination support on https://github.com/kumuluz/kumuluzee-rest)
* search of movie
* CRUD operations

## Actors
Users have properties as first name, last name, born date, list of movies, etc. Implement REST operations to support basic UI requirements:
* list all actors
* list actors with pagination support
* CRUD operations

## Requirements

In order to run these examples as they are intended, you will need the following:

1. Java 8 (or newer), you can use any implementation:
    * If you have installed Java, you can check the version by typing the following in a command line:
        
        ```
        java -version
        ```

2. Maven 3.2.1 (or newer):
    * If you have installed Maven, you can check the version by typing the following in a command line:
        
        ```
        mvn -version
        ```
        
3. Git:
    * If you have installed Git, you can check the version by typing the following in a command line:
    
        ```
        git --version
        ```
4. Postgresql
	* Install postgresql database: (https://www.postgresql.org/download/)
	* Create database (https://www.postgresql.org/docs/9.0/sql-createdatabase.html)
	```
	CREATE DATABASE movies
	  WITH OWNER = postgres
		   ENCODING = 'UTF8'
		   TABLESPACE = pg_default
		   LC_COLLATE = 'Slovenian_Slovenia.1250'
		   LC_CTYPE = 'Slovenian_Slovenia.1250'
		   CONNECTION LIMIT = -1;
	```

### Install
Install the application using the following command:
```cmd
mvn clean install
```

### Run
Start the application using the following command:
```cmd
java -jar ${project.build.finalName}.jar
```
Example:
```cmd
java -jar target\DemoApplication-1.0-SNAPSHOT.jar
```

### TODO Write tests with Kumuluzee testing tool