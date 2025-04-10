﻿# web-distribu-
Projet de Gestion de Construction
Ce projet est une application de gestion de construction qui utilise une architecture basée sur des microservices. Il intègre plusieurs technologies modernes telles que Spring Boot, Eureka Server, API Gateway, et Angular.

Architecture
L'architecture du projet est composée des éléments suivants :

Eureka Server : Un serveur de découverte de services qui permet aux microservices de se découvrir et de communiquer entre eux sans nécessiter de configuration manuelle.

API Gateway : Une passerelle qui redirige les requêtes vers les microservices appropriés tout en gérant les aspects de sécurité, de routage, et de filtrage.

Backend Microservices : Deux instances de microservices Spring Boot qui gèrent les fonctionnalités CRUD et la logique métier avancée.

Load Balancer : Utilisé pour répartir le trafic entrant entre les deux instances de microservices.

Frontend : Un client Angular qui consomme l'API fournie par les microservices.

Prérequis

Avant de commencer, assurez-vous d'avoir les éléments suivants installés sur votre machine :

Java 17 ou version supérieure

Spring Boot

Docker

Node.js

Angular CLI

Structure du Projet
1. Eureka Server
Le serveur Eureka est responsable de la découverte des services dans l'architecture microservices. Chaque microservice s'enregistre auprès de ce serveur lors de son démarrage.
2. API Gateway
L'API Gateway gère le routage des requêtes vers les microservices et gère la sécurité, l'authentification, et le filtrage.
L'API Gateway sera accessible à l'adresse suivante : http://localhost:8093
3. Backend Microservices
Les microservices backend sont deux instances de Spring Boot qui gèrent les fonctionnalités CRUD et la logique métier avancée de l'application. Les services sont répartis sur deux instances et un load balancer permet de répartir le trafic entre elles.
Les services seront accessibles respectivement à :

http://localhost:8089 pour le premier microservice

http://localhost:8090 pour le deuxième microservice

Le load balancer (géré par Spring Cloud) permettra de répartir les requêtes entre ces deux instances.

4. Frontend - Angular
Le frontend de l'application est développé en Angular et consomme les API fournies par les microservices. Il permet aux utilisateurs de visualiser, créer, mettre à jour et supprimer des projets de construction.
Routes API
Voici les routes principales de l'API exposée par le backend :

GET /api/projets : Liste des projets

POST /api/projets : Créer un nouveau projet

PUT /api/projets/{id} : Mettre à jour un projet

DELETE /api/projets/{id} : Supprimer un projet

Sécurité
Le projet peut être sécurisé à l'aide de JWT et OAuth2 via Spring Security. L'API Gateway gère l'authentification et la gestion des tokens d'accès pour sécuriser l'accès aux microservices.

Docker
Le projet peut être conteneurisé à l'aide de Docker pour faciliter le déploiement dans un environnement cloud.

Construction et démarrage via Docker
Construire les images Docker :

docker-compose build
Démarrer les services avec Docker Compose :

docker-compose up
Le projet sera alors accessible via les adresses définies dans le fichier docker-compose.yml.

Contributions
Les contributions sont les bienvenues ! Pour contribuer, suivez ces étapes :

Forkez le projet

Créez une branche pour votre fonctionnalité (git checkout -b feature/ma-fonctionnalité)

Committez vos modifications (git commit -m 'Ajout de ma fonctionnalité')

Poussez votre branche (git push origin feature/ma-fonctionnalité)

Ouvrez une pull request

Licence
Ce projet est sous la licence MIT.

Assurez-vous d’adapter les chemins et les configurations en fonction de vos spécificités techniques. Ce modèle de README fournit un cadre pour documenter votre projet de manière structurée et complète.
