# gueestbook

## Features

   * Any visitor can post their name and a comment to the Guestbook.
   * All visitors can see a list of every entry in the Guestbook.

## API
  The API can be found at <host>/docs/index.html

## Create database first using docker(local)
```dockerfile
  $ docker pull postgres
  $ docker network create —driver bridge my-net
  $ docker run --name guestbook-pg --network my-net -p 5432:5432 -e POSTGRES_PASSWORD=open -d postgres
  $ docker container inspect guestbook-pg
```
  
## How to build
For Unix/Linux:
```dockerfile
  $ chmod u+x gradlew
  $ ./gradlew build
```

For Windows
```dockerfile
  $ .\gradlew.bat build
```
  
## How to create container(local)
  On Unix/Linux:
  Edit DockerFile Comment out line 4, and 10
  #RUN apt-get update && apt-get install -y dos2uni
  #RUN dos2unix gradle
  
  Edit src/main/resources/application.properties file on line 6.
  Change 172.17.0.2 to your hostname.
```dockerfile
  $ docker build -t guestbook:latest .
```
