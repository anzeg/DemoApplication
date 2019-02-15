# DemoApplication
Demo application for backend coding challenge

Your final product should be a backend application for movies and actors management via REST API, described in details in the following sections. Keep in mind that application can be used by multiple clients at the same time and all data needs to be persisted somewhere (preferred H2 database). UI is not part of this demo application.

## Optional:
* implement HTTP cache mechanisms where is needed
* implement request counter for all REST calls and save it in the local file

## Movies
Movies have properties as title, year, description, list of actors, some pictures, etc. (as identifier use imdbID). Implement REST operations to support basic UI requirements:
* list all movies
* list movies with pagination support
* search of movie
* CRUD operations

## Actors
Users have properties as first name, last name, born date, list of movies, etc. Implement REST operations to support basic UI requirements:
* list all actors
* list actors with pagination support
* CRUD operations
