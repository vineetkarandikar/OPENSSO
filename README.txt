This OPENSSO project is implementation of single sign on utility.

Technology Stack Required
1.	Java 1.8
2.	Redis
3.	Maven

Important points –
1.	Use the following classes as it is if you are using spring else use the code logic-
•	com.open.sso.model.User
•	com.open.sso.model.Role
•	com.open.sso.service.TokenService
•	com.open.sso.service.RedisService
•	com.open.sso.common.SSOConstants

2.	Default expiry time for token is set in application properties by key token.default.time and its value as 600 seconds i.e 60 mins.

Installation instruction-
1.	Checkout source code from Git.
2.	Change properties in application.properties file for Redis server i.e. hostname, Server port and also change the http port accordingly based upon user choice.
3.	mvn clean install
4.	spring-boot:run

That’s it.