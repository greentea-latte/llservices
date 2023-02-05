# LL Services

A microservices application for registering patient accounts and verifying their identity.
The application was designed for a fictitious private health company.

## Technologies

This application was designed and built with reference to Spring and Maven technologies:
- Spring Cloud - https://spring.io/projects/spring-cloud
- Spring Boot - https://spring.io/projects/spring-boot
- Maven - https://maven.apache.org/

Spring Boot and Spring Cloud support the development and configuration of the services.
Maven is used for the build tool.

## Setup

### macOS
1. Install Maven
  ```sh
brew install maven
  ```
2. Install Docker Desktop for Mac (https://docs.docker.com/get-docker/)

### Windows 10
1. Install Java 17 JDK (https://www.oracle.com/uk/java/technologies/downloads/#jdk19-windows)
2. Install Maven (https://maven.apache.org/download.cgi)
3. Install Docker Desktop for Windows (https://docs.docker.com/get-docker/)
4. If you have any questions regarding Docker Desktop permission requirements, see - https://docs.docker.com/desktop/windows/permission-requirements/
5. It may also be required to configure JAVA_HOME, please see (https://devwithus.com/install-maven-windows/)

### Following installation
1. Verify Maven is installed by running:
  ```sh
mvn --version
  ```
You should see details of the installed Maven version without errors.

2. Load LL Services into your preferred IDE.
3. Ensure Java 17 is used for the project settings.
4. Open up Docker Desktop
5. Switch to your terminal/command line and make sure you are in the root folder of llservices
6. Build the Docker image and create the configuration by running: 
  ```sh
docker compose up -d 
  ```
7. You should see details of the running containers:
  ```
   [+] Running 3/3
   ⠿ Network llservices_postgres  Created                                    0.1s
   ⠿ Container pgadmin            Started                                    1.8s
   ⠿ Container postgres           Started                                    1.9s
  ```
8. Navigate to http://localhost:5050/browser/ on your browser. You should see the pdAdmin UI
9. It will prompt you for a master password, enter 'password'
10. Click on Create Server and enter name as 'llservices', Host name/address as 'postgres', username as 'lliu', Port as '5432' and the password as 'password', click 'Save'
11. Click on Create Database and call it 'patient' and save
12. Click on Create Database again and call it 'verification' and save

## Running the microservices
1. Run the main function on PatientApplication.java
2. You should see details like this without any errors
  ```
 .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::                (v2.5.7)

2023-02-05 09:55:21.608  INFO 47215 --- [           main] com.lila.app.patient.PatientApplication  : Starting PatientApplication using Java 17.0.5 on C02FQ6ZYML7H with PID 47215 (/Users/lliu/Documents/llservices/patient/target/classes started by lliu in /Users/lliu/Documents/llservices)
2023-02-05 09:55:21.610  INFO 47215 --- [           main] com.lila.app.patient.PatientApplication  : No active profile set, falling back to default profiles: default
2023-02-05 09:55:22.524  INFO 47215 --- [           main] .s.d.r.c.RepositoryConfigurationDelegate : Bootstrapping Spring Data JPA repositories in DEFAULT mode.
2023-02-05 09:55:22.630  INFO 47215 --- [           main] .s.d.r.c.RepositoryConfigurationDelegate : Finished Spring Data repository scanning in 87 ms. Found 1 JPA repository interfaces.
2023-02-05 09:55:23.457  INFO 47215 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat initialized with port(s): 8080 (http)
  ```
3. You should also see details showing the patient table creation
```
Hibernate: 
    
    create table patient (
       id int4 not null,
        email varchar(255),
        full_name varchar(255),
        primary key (id)
    )
```
4. Run the main function on VerificationApplication and you should see similar output
```

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::                (v2.5.7)

2023-02-05 09:58:13.841  INFO 47228 --- [           main] c.l.a.v.VerificationApplication          : Starting VerificationApplication using Java 17.0.5 on C02FQ6ZYML7H with PID 47228 (/Users/lliu/Documents/llservices/verification/target/classes started by lliu in /Users/lliu/Documents/llservices)
2023-02-05 09:58:13.845  INFO 47228 --- [           main] c.l.a.v.VerificationApplication          : No active profile set, falling back to default profiles: default
2023-02-05 09:58:14.685  INFO 47228 --- [           main] .s.d.r.c.RepositoryConfigurationDelegate : Bootstrapping Spring Data JPA repositories in DEFAULT mode.
2023-02-05 09:58:14.754  INFO 47228 --- [           main] .s.d.r.c.RepositoryConfigurationDelegate : Finished Spring Data repository scanning in 55 ms. Found 1 JPA repository interfaces.
2023-02-05 09:58:15.365  INFO 47228 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat initialized with port(s): 8082 (http)
```
```
Hibernate: 
    
    create table verification (
       id int4 not null,
        patient_id int4,
        type varchar(255),
        primary key (id)
    )
```
## Test the microservices
You can test the services by sending a POST request to http://localhost:8080/api/v1/patients with parameters.
For example, on the loadtesting tool K6 this script is used:
```
export default function () {
  const url = 'http://localhost:8080/api/v1/patients';
  const payload = JSON.stringify({
    fullName: 'Test User',
    email: 'user@test.org',
  });

  const params = {
    headers: {
      'Content-Type': 'application/json',
    },
  };

  http.post(url, payload, params);
  sleep(1);
}
```
You can verify that the request has been successful through the details in your terminal:
e.g.
```
Hibernate: 
    insert 
    into
        patient
        (email, full_name, id) 
    values
        (?, ?, ?)
2023-02-05 10:02:24.715  INFO 47215 --- [nio-8080-exec-2] com.lila.app.patient.PatientController   : new patient registration PatientAccountCreationRequest[fullName=Test User, email=user@test.org]
Hibernate: 
    insert 
    into
        patient
        (email, full_name, id) 
    values
        (?, ?, ?)
2023-02-05 10:02:24.724  INFO 47215 --- [nio-8080-exec-1] com.lila.app.patient.PatientController   : new patient registration PatientAccountCreationRequest[fullName=Test User, email=user@test.org]
2023-02-05 10:02:24.724  INFO 47215 --- [nio-8080-exec-9] com.lila.app.patient.PatientController   : new patient registration PatientAccountCreationRequest[fullName=Test User, email=user@test.org]
2023-02-05 10:02:24.724  INFO 47215 --- [nio-8080-exec-8] com.lila.app.patient.PatientController   : new patient registration PatientAccountCreationRequest[fullName=Test User, email=user@test.org]
2023-02-05 10:02:24.726  INFO 47215 --- [io-8080-exec-10] com.lila.app.patient.PatientController   : new patient registration PatientAccountCreationRequest[fullName=Test User, email=user@test.org]
```
You can also check on pgAdmin and run a query to see if the records have been persisted:
Right click on the 'Schemas' section of the 'patient' database and select 'Query Tool'
Run the following query:
```
SELECT * FROM patient;
```
You should see the patient records returned in a table.


