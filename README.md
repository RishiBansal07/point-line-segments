## REST API implementation for every line that contains N or more of the points

##########################################################################
### STEPS To Execute
##########################################################################

```
1. Import project in your workspace
2. mvn clean install
3. After Successful build, launch Spring build Application using command 
	mvn spring-boot:run or initiate your application using Intellij for Spring boot
4. once Spring boot initiated, launch Swagger using http://localhost:8082/swagger-ui.html#
5. Execute and perform your API Invocation
7. Cheers and Enjoy API Processing :)
```

###########################################################################
### Project Creation
###########################################################################

```
Project Created using Spring Initializer

- start.spring.io
- Maven Project 
- Spring-boot 2.5.2 - spring-boot-starter-parent
- Dependencies
 	- JUnit - spring-boot-starter-test
 	- Logging Slf4J and Apache Commons
 	- springfox-swagger-ui
```
 	
###########################################################################
### Design Thought
###########################################################################

```
1. Use of Simple Approach with diiferent layers of Access using Controller and Service layer.
2. There are four different rest end points:
    /api/lines/{numberOfPoints}    Get all line segments passing through at least N points !!! 
    /api/points                    Add points to the space !!!
    /api/remove                    Delte all points from the space !!!
    /api/space                     Get all points added in space !!!
3. Output source of /api/lines/{numberOfPoints} end point will be a list of points of differnet line
segments.
4. Possible Scenarios Cover in order and Keeping Future Approach of implementation
5. JUnit Followed Mocking of Object verification using Mockito and WebMvcTest  
6. To Test Swagger Configured to test and verify Output of the APIs
7. Maven for Build of project and Configuration of Dependencies
```


###########################################################################
### TODO
###########################################################################

```
Possible further implemenation if get more time

1. Tracing of API with use of Zipkin or any Tracing API
2. Cover more test cases
3. We can create different interface for the points calculation which are forming different lines.
4. Authorization of API
5. Jacoco for code coverage tool - execution can be defined as Min to 50%

```
###########################################################################
### Testing
###########################################################################

```
1. Postman
2. Swagger
3. Junit

```

###########################################################################
### Contact Email
###########################################################################

- brishi2806@gmail.com

###########################################################################
## Thank You
###########################################################################
