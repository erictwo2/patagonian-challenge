
# Patagonian Challenge

Este proyecto resuelve la prueba técnica planteada por Patagonian para candidatos a Backend Developers.
Dicha prueba consiste en:

1. Escribir un script que cargue en una base de datos todas las canciones de una lista de ID's de artistas
(recibidas por parámetro) desde la API web pública de Spotify.
2. Implementar todos los endpoints descritos en la especificación de la API incluida.
3. Agregar paginación al endpoint */songs* endpoint que devuelve la lista de canciones de un
artista. Actualizar la especificación de la API para reflejar los cambios realizados en la API.
  
## Requerimientos

 Para el correcto despliegue del proyecto se necesita:
* GIT
* Última versión de Docker
* Última versión de Docker Compose 

## Frameworks

El stack tecnológico elegido es:

Back-end:
* Java 8
* Spring Boot
* Spring Data Mongo
* MongoDB

Script:
* Python
* Spotipy
* Pymongo

Deploy:
* Docker
* Docker Compose

## Despliegue API REST

Para desplegar la API REST debe ingresar los siguientes comandos en la terminal:

```bash
# crear nueva red
docker network create patagonian_net

# desplegar mongo-db y api-rest
docker-compose up --build -d
```

**Nota:** los puertos utilizados por MongoDB y Spring Boot son 27017 y 8080 respectivamente.

## Script

Para ejecutar el script que inserta las canciones de los artistas debe seguir los siguientes pasos:

**Nota:** antes de ejecutar los siguientes comandos en su terminal, asegúrese de haber desplegado la API REST.

1. Construir el contenedor para ejecutar el script (solo debe realizarse la primera vez).
	```bash 
	docker build -t python-script ./python-script-docker/. 
	```
2. Ejecutar el script ingresando el/los ID's de el/los artista/s. En caso de ingresar varios ID's, estos deben ir separador por un espacio. El siguiente ejemplo persiste las canciones de Charly García (3jO7X5KupvwmWTHGtHgcgo ) y Gustavo Cerati (1QOmebWGB6FdFtW7Bo3F0W)
	```bash 
	docker run --network=patagonian_net python-script -a 3jO7X5KupvwmWTHGtHgcgo 1QOmebWGB6FdFtW7Bo3F0W
	```

## Endpoints de ejemplo

A continuación se encuentran unos endpoint de ejemplo que puede probar con su navegador.

**Nota:** antes de ingresar a los siguientes endpoints asegúrese de haber deplegado la API REST y haber ejecutado el script con los ID's de ejemplo.


[http://localhost:8080/api/v1/songs?artistName=Gustavo%20Cerati](http://localhost:8080/api/v1/songs?artistName=Gustavo%20Cerati)

[http://localhost:8080/api/v2/songs?artistName=Gustavo%20Cerati&page=0&size=10&sort=asc](http://localhost:8080/api/v2/songs?artistName=Gustavo%20Cerati&page=0&size=10&sort=asc)

[http://localhost:8080/api/v2/songs/5ed6164b6416a02c8268c826](http://localhost:8080/api/v2/songs/5ed6164b6416a02c8268c826)
