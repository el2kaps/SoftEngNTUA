### Software Engineering 
This project is part of the course "Software Engineering" <br>
School of ECE, National Technical University of Athens 2020-2021 <br>

**Team "SmartEMobility":** <br>
Achlatis Stefanos-Stamatis <br>
Christophidi Georgia <br>
Kapsali Eleni-Elpida <br>

## Subject: Full stack web application for Electric Vehicle charging management
REST API runs at base URL: https://localhost:8765/evcharge/api/ <br>

**Technology Stack**
* Java 11
* Groovy για το testing
* Gradle build tool
* Spock testing framework

**Back-end**
* Restlet framework
* MySQL Connector/J
* Spring JDBC
* To start back-end run:
```
cd back-end
./gradle build ./gradlew apprun
```
**CLI client**
* Picocli
* Junit
* Rest api client
* To start CLI run:
```
cd cli-client
./gradle build ./gradlew installDist ./build/install/evcharge_67/bin/evcharge_67 -h
```
**Front-end**
* React
* CRA (Create-React-App)
* To start front-end run:
```
cd front-end
yarn
yarn start
```
**System's Architecture (simplified)**
![image](https://user-images.githubusercontent.com/63153771/111076739-391daf00-84f6-11eb-93ea-23bcc1363c04.png)
