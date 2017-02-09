# nexus

This project contains api part for nexus which contains core functionalities of catchzombie.

#### Note:
Running this project requires an environment variable of name `czActiveProfile` to be set to the active profile that you are running the application in `dev | prodtest | prod`. 

## Add following dependency
- Create Database with name `catchzombie` or create your own and make changes in `nexus-api/src/main/resources/application.yml`.
- Install redis server and provide host, port, expire in `nexus-api/src/main/resources/application.yml`.
- For `dev` default value is localhost for both mysql and redis.
- For `dev` mysql port is `3306` and redis port is `6379`.

## Run within Intellij Idea
- To run in Intellij Idea, create a new run configuration by going to `Run > Edit Configurations > Add New Configuration > Application`. 
- Select the main class as `com.catchzombie.api.ApplicationMain` and module as `nexus-api`. 
- Under environment variables, add `czActiveProfile` and set it as `dev`.
- Save the configuration. Now you can run it using `Run > Run` in the menu. You can check the project running on your local server on port 9000 by default.  

http://localhost:9000


## Run using mvn

```
mvn spring-boot:run
```

## Run as jar
- Create a jar by running: `mvn clean install -P jar`


## Deploy as war
- Create a war by running: `mvn clean install -P war`
- Copy the generated war in `target` directory to tomcat's `webapps` folder
