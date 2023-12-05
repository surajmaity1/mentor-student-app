# Mentor Student REST API


## How to Run

* Clone Repository
* TOOLS: JDK 1.8, Maven 3.x and MySQL
* AMAZON S3: Create a Bucket using name : ``mentor-student-s3-bucket``
* Go to IAM > Security credentials
* Generate Access key
* Go to ``application.properties`` and add those credentials:
```
#aws s3
cloud.aws.credentials.access-key=<ENTER access-key>
cloud.aws.credentials.secret-key=<ENTER secret-key>
cloud.aws.region.static=<ENTER region.static>
```

* Give your local mysql credentials:
```
spring.datasource.username=<ENTER username>
spring.datasource.password=<ENTER password>
```
* Create A DATABASE ```[name: mentor_student]```
* Assign Role for Students and Mentors
*RUN THESE COMMANDS IN MYSQL:
```roomsql
CREATE DATABASE mentor_student;
USE mentor_student;

CREATE TABLE roles (id BIGINT NOT NULL AUTO_INCREMENT, name VARCHAR(255), PRIMARY KEY (id)) engine=InnoDB;

INSERT INTO `mentor_student`.`roles` (`id`, `name`) VALUES ('1', 'ROLE_MENTOR');
INSERT INTO `mentor_student`.`roles` (`id`, `name`) VALUES ('2', 'ROLE_STUDENT');
```

* Build: ```mvn clean package```
* After Building, run this:

```
        java -jar mentor-student-app-0.0.1-SNAPSHOT.jar
or
        mvn spring-boot:run
```
See Application started on port 5000
```
Tomcat started on port 5000 (http) 
Started MentorStudentAppApplication
```


### OPEN ANY API PLATFORM [ like Postman ]


**CREATE STUDENT:**

POST http://localhost:5000/api/auth/signup

```json
{
  "name": "student",
  "username": "student",
  "email": "student@gmail.com",
  "password": "student"
}
```

**CREATE MENTOR:** 

POST http://localhost:5000/api/auth/signup

```json
{
  "name": "mentor",
  "username": "mentor",
  "email": "mentor@gmail.com",
  "password": "mentor"
}
```

**FIRST LOGIN AS A STUDEANT:**

POST http://localhost:5000/api/auth/login

```json
{
  "usernameOrEmail": "student@gmail.com",
  "password": "student"
}
```
OUTPUT LIKE THIS:

```json

{
    "accessToken": "eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJtZW50b3JAZ21haWwuY29tIiwiaWF0IjoxNzAxNzgxODEyLCJleHAiOjE3MDIzODY2MTJ9.uRXN_-sFiuYDuBypihN6UF4g6kq4KFHC-pqX9Xu2dkzGeF11hcssslPg0A234CJ1",
    "tokenType": "Bearer"
}
```
* copy access token's value
* paste inside authorization as a **BEARER TOKEN**


**POST A REVIEW AS A STUDENT TO MENTOR:**

POST http://localhost:5000/api/reviews/post

```json
{
  "username": "mentor",
  "rating": 5,
  "description": "Absolutely phenomenal mentor! A guiding light in my educational journey. Their profound knowledge, unwavering support, and strategic insights propelled me to heights I never thought possible. Each session was a transformative experience, leaving me not just well-informed but truly inspired. The epitome of mentorship excellence!"
}
```

**FETCH ALL REVIEW BY RATING WISE:**

GET http://localhost:5000/api/reviews/search/findByRating?rating=5


**FETCH ALL REVIEW BY MENTOR USERNAME:**

GET http://localhost:5000/api/reviews/search/findByUsername?username=mentor





**NOW LOGIN AS A MENTOR TO GIVE LETTER OF RECOMMENDATION TO STUDENT:**

POST http://localhost:5000/api/auth/login

```json
{
  "usernameOrEmail": "mentor@gmail.com",
  "password": "mentor"
}
```
OUTPUT LIKE THIS:

```json

{
    "accessToken": "eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJtZW50b3JAZ21haWwuY29tIiwiaWF0IjoxNzAxNzgxODEyLCJleHAiOjE3MDIzODY2MTJ9.uRXN_-sFiuYDuBypihN6UF4g6kq4KFHC-pqX9Xu2dkzGeF11hcssslPg0A234CJ1",
    "tokenType": "Bearer"
}
```
* copy access token's value
* paste inside authorization as a **BEARER TOKEN**

GIVE 
POST http://localhost:5000/api/recommend/student

**FOLLOW STEPS TO GIVE LOR AS A MENTOR TO STUDENTS:** 

* Inside body section of postman
* You can see key value pair
* create three section

**Structure:**

Key | Value                                    |
--- |------------------------------------------| 
file | SELECT PDF OR WORD FILE FROM YOUR SYSTEM | 
username | ENTER SUTDENT'S USER NAME                | 
recommendationText | ENTER COMMENT FOR STUDENT                |

**Example:**

Key | Value           |
--- |-----------------| 
file | student_doc.pdf | 
username | student         | 
recommendationText | Good            |

RESPONSE:

File uploaded. Check here :

https://mentor-student-s3-bucket.s3.amazonaws.com/LOR_student_doc.pdf


```Note: This file has been uploaded to Amazon S3 Bucket```