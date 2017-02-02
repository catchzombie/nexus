# nexus

This project contains api part for nexus which contains core functionalities of catchzombie.

## Run using mvn

```
mvn spring-boot:run
```

## Run as jar
- Create a jar by running: `mvn clean install -P jar`


## Deploy as war
- Create a war by running: `mvn clean install -P war`
- Copy the generated war in `target` directory to tomcat's `webapps` folder