

# API BootCamp



Here is the for BootCamp Notes from Notion [link](https://www.notion.so/mehmetct/API-Bootcamp-acfa82435ff54563917df7d12480debc
)


Rest Assured  [link](https://rest-assured.io)

JUnit5  [link](https://junit.org/junit5/docs/current/user-guide/)

- Today Plan
    - Verifications
    - Serilization vs Deserilizations 
    - Authorization vs Authentication
    - JsonSchemaValidations
    - MockAPI
    - Postman 
      - Variables
      - Running Collections
      - DDT
      - Automation 



## Creating Maven Project
```xml
<dependencies>
        <!--JUnit5 to set up our framework it is same thing but different features with TestNG    -->
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter</artifactId>
            <version>5.8.2</version>
        </dependency>

        <!-- Selenium -->

        <dependency>
            <groupId>org.seleniumhq.selenium</groupId>
            <artifactId>selenium-java</artifactId>
            <version>3.141.59</version>
        </dependency>

        <!-- BoniGarcia -->

        <dependency>
            <groupId>io.github.bonigarcia</groupId>
            <artifactId>webdrivermanager</artifactId>
            <version>5.0.2</version>
        </dependency>

        <!--Rest Assured   -->

        <dependency>
            <groupId>io.rest-assured</groupId>
            <artifactId>rest-assured</artifactId>
            <version>4.4.0</version>
        </dependency>

        <dependency>
            <groupId>com.github.javafaker</groupId>
            <artifactId>javafaker</artifactId>
            <version>1.0.2</version>
        </dependency>

        <!--        RestAssured json schema dependency -->
        <dependency>
            <groupId>io.rest-assured</groupId>
            <artifactId>json-schema-validator</artifactId>
            <version>4.4.0</version>
        </dependency>

        <!--        For Serialization and De-Serialization -->
        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-databind</artifactId>
            <version>2.13.0</version>
        </dependency>

        <!--        Lombok for pojo -->

        <dependency>

            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>1.18.22</version>

        </dependency>
    
    </dependencies>
```
### The Structure of RestAssured Method Chaining
```
given() ....   --> RequestSpecification 
     .header  accept() contentType()
     .queryParam
     .pathParam
     .body
     .log
     .auth..
when()       --->> RequestSender
   .get() ------>> Response Object 
   .post()
   .put()
   .delete()
then()      -----> ValidatableResponse  
        This is where assertion happen 
    .statusCode
    .header   accept() contentType()
    .body( matchers goes here)
    .log

```
### Questions

1. What do we have in the request and response?
    - Request
        - URL
        - HTTP METHOD (GET - POST - PUT - PATCH - DELETE )
        - Headers
            - Content Type --> To specify what kind of data you are sending
            - Accept --> To specify what kind of data you want to get
            - Authorization --> To provide tokens
            - Custom Headers --> Generally used for based on doc
        - Params
            - Query --> Key Value pair to filter results based on your query params
            - Path  --> Used to identify single resource
    - Response
        - Body
        - Headers
        - Status Codes

2. How do I do test API Testing without Swagger ?
    - Try to ask for information people  (PO or whoever has knowledge )
    - Open UI and try to figure it out ( Depends on application structure )
    - Ask Developers to do your job better

3. What kind of testing framework you worked on?
    - Junit5
    - Karate
    - Cucumber
      NOTE -- > Framework does not matter.We can use always restassured


      

#### VERIFY RESPONSE
    1- Contains 
    2- Response.path()
    3- JsonPath
    4- HamCrest Matchers
    5- De-Serialization

#### SENDING DATA
    6- Serialization


### JsonPath
- JsonPath is seperate class  to get data from response with the help of jsonPath() method
- it will give as response as a jsonpath Object and jsonPath helps us to reading specific datatypes,reading List etc

### What is Hamcrest Matchers?
### Do I need to extra dependency for it ?

    - It is an assertion library that can be used additionally to make assertions
      more readable and it comes with a lot of pre-written matchers to make it easier to write 
    - The method chain of RestAssured then section use the hamcrest matchers library extensively to assert 
      the response in one chain 
    - It comes with Rest Assured library and no need to add another dependency for it 
    - All we need is to add static imports and use it 
            import static org.hamcrest.Matchers.*;

- Here is the doc [link](http://hamcrest.org/JavaHamcrest/javadoc/2.2/org/hamcrest/Matchers.html)

### Respanse.path() vs Response.JsonPath()

- There are two ways to get the response and extract json data

    - path("your jsonpath goes here") return type is T(generic) and decided by your variable data type you store
      - int myId = response.path("id")

    - There is a type(class) in RestAssured : JsonPath
        - that have lots of methods to extract json body from the response like getInt getString getDouble getObject getList and so on.....
        - In order to get JsonPath object out of response , we call a method called jsonPath() from the response
  
        - For example :
          - JsonPath jp =  response.jsonPath("your acual path goes here")  ;
          - jp.getInt()  jp.getLong() and so on

        - The meaning of the word json path when we use in differnet places
          - json path : -->> when inside the " " means the actual path to get the value (like xpath)
          - JsonPath  : -->> the RestAssured class that have lots of methods
          - jsonPath() : -->> the method of Response object to obtain JsonPath object out of response

### Questions

1- What is difference between assert and verify ?

    Assert :if the assert condition is not met, test case execution will be aborted.
            The remaining tests are skipped, and the test case is marked as failed.
            These assertions are used as checkpoints for testing or validating business-critical transactions.

    Verify : In case of verify, tests will continue to run until the last test is executed even if assert conditions are not met.
            Verify or Soft Asserts will report the errors at the end of the test. Simply put, tests will not be aborted if any condition is not met.
               Testers need to invoke the assertAll() method to view the results.




Add Dependecy for Serialization/Deserialization
```xml
<!--        For Serialization and De-Serialization -->
        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-databind</artifactId>
            <version>2.13.0</version>
        </dependency>
       
        <!--        Lombok for pojo -->
        <dependency>

            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>1.18.22</version>

        </dependency>
```

1. POST / PUT /  PATCH
2. Serialization--> Converting from Java OBJECT To JSON
3. Deserialization --> Converting from JSON To Java Object

### POJO Class

This class is meant to be blueprint for Creating Spartan pojo to represent json data with 3 fields name, gender, phone

### Questions

1. What are the collections you use to read the Json file and store?
2. How can you convert from Json to Java Object and Java Object to Json?
3. What is difference between authentication and authorization ?



#### Why developed a Rest API ?

Application in my current projects needs to itegrated to other internal/external applications
For integration our team developed it and I am testing it

#### How do you test REST API ?

- Read SWAGGER or docs
- Use POSTMAN to test your endpoint manually
- Use Rest Assured to test with automation
    - I send PUT/POST/DELETE/GET request and verify status code , headers and body
    - To verify I am using Response.path() / Hamcrest / JsonPath / POJO
    - I am using Jackson  databind library to make serialization/deserialization


#### USING TOKENS WITH REST API

- So for all API endpoints that we practiced worked without tokens
- In real project most likely you will need to get token to do actions in REST

#### HOW DOES IT WORKS ?

- Each doc has their own rule to get access in related API.
- According to docs requirement we will send request to deicated endpoint for get token
- After getting token we will use this in our request as Header - Authorization - Parameter



#### JSON Schema Validation
It is a process to verify if json response matches with expected schema.
I verify each key datatypes must exactly matches JSON vs SCHEMA  

```xml
<!--        RestAssured json schema dependency -->
<dependency>
  <groupId>io.rest-assured</groupId>
  <artifactId>json-schema-validator</artifactId>
  <version>4.4.0</version>
</dependency>
```




### Links

- [Find JsonPath](https://jsonpathfinder.com/)

- [Create POJO](https://www.jsonschema2pojo.org/)

- [Create Schema](https://www.jsonschema.net/home)

- [Validate Schema](https://www.jsonschemavalidator.net/)

- [Mock API](https://app.mocklab.io/mock-apis/eq22k/stubs/83378e52-5c12-45f2-a4c6-428b952d7705)

