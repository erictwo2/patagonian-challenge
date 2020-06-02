
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

El stack tecnológico y la base de datos elegidos son:

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