# Guide de démarrage rapide de l'application back-end AltenShop

Ce projet est une API Rest permettant de gérer les données d'une base de données composés de produit. 

## Pré requis

- Java 21
- Maven 4.0.0 ou supérieur
- Docker pour l'hébergement de la base de données
- PostgreSQL 16.0 (si souhait d'hebergement local de la bdd)

### Configuration Docker

- Le projet contient à la racine un `docker-compose.yaml`permettant de lancer la base de données sur un conteneur docker. Pour l'utiliser, il est nécessaire d'ajouter les informations relatives à la bdd :

```yaml
     environment:
      POSTGRES_DB: NOM_BASE_DE_DONNNES
      POSTGRES_USER: UTILISATEUR_BDD
      POSTGRES_PASSWORD: MOT_DE_PASSE_BDD
    ports:
      - "XXXX(Choix du port, 5432 par défaut):5432"
```

- Le fichier `application.properties` situé dans le dossier main/resources doit également être modifié:

```
spring.datasource.url=jdbc:postgresql://localhost:XXXX/NOM_DB
spring.datasource.username=UTILISATEUR_DB
spring.datasource.password=MOT_DE_PASSE_DB
spring.datasource.driver-class-name=org.postgresql.Driver
```

### Configuration Postgres

Pour ceux qui préfèrent héberger la base de données PostgreSQL localement plutôt que dans un conteneur Docker, voici les étapes à suivre :

- Après l'installation de Postgres, ouvrir un terminal PostgreSQL et creer une nouvelle base de données en utilisant la commande suivante :

```
CREATE DATABASE NOM_BASE_DE_DONNNES;
CREATE USER UTILISATEUR_BDD WITH PASSWORD 'MOT_DE_PASSE_BDD';
GRANT ALL PRIVILEGES ON DATABASE NOM_BASE_DE_DONNNES TO UTILISATEUR_BDD;
```

- Mettre à jour le fichier applications.properties avec les nouvelles informations

### Lancement du projet 

- Cloner le projet
- Pour modifier le port de l'application back-end, ajouter `server.port=XXXX` au fichier `applications.properties`. Par défaut, l'application se lancera sur le port 8080.
- Si utilisation de docker, utiliser la commande `docker-compose up` pour lancer le conteneur docker relatif à la base de données (ou `docker-compose up -d`pour un lancement en fond)
- Lancer la commande `mvn spring-boot:run` pour lancer l'application back-end

### Migration base de données

- Le projet contient la dépendance Flyway qui se charge de construire la base de données à partir des fichiers contenus dans main/java/com/example/altenshop/ressources/db/migration
- La base est construite au lancement de la commande `mvn spring-boot:run` 
- Ajouter une migration en respectant le format de nommage de la migration d'initialisation pour faire évoluer la base de données


### Swagger

Le projet dispose d'un swagger intégré à l'application qui documente l'API et les différents endpoints.

Le swagger est accessible à l'adresse `http://localhost:8080/swagger-ui.html`

Il est également possible de tester les endpoints via le swagger en cliquant sur `Try it out`après avoir entré les paramètres nécessaires le cas échéant.