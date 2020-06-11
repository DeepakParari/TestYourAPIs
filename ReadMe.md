# A simple framework to test REST API's

## Framework elements
+ Rest Assured Library
+ Junit 4 ( Framework and Test runner )
+ Hamcrest 
+ Framework logging using Log4J (/target/logs/testlogging.log)
+ RestAssured Request-Response logging (/target/request-response.txt)

## How to run the tests
PetTest class contains tests (Smoke Tests) to perform the CRUD operation on the /pet endpoint.

+ You can run from the IDE by running the BaseTest class.
+ Using maven command mvn clean test (Optionally you can provide the profile using -P).

Outputs:
+ Surefire reports under /target/surefire-reports
+ Request and Response logging under /target/request-response.txt
##Framework components
Helper Classes
+ ConfigFileReader - Reads value from properties file. Resource filtering is used to facilitate working with multiple environments. 
+ RequestLoader - Loads request body from the /test/resources/payloads folder.
+ SpecBuilder - Creates common request specification to be used by all tests.
+ JSONHelper - Manipulate JSON request / Read value from response.

##Tests
Test Package
+ BaseTest class for the setup.
+ PetTest - CRUD tests.

###Next Actions
+ Create POJO classes for setting up complex requests.
+ Use DataProvider for data driving the tests.
+ Create separate methods for CRUD operations and use it for creating functional tests.
+ Use JUnit asserts.
+ Allure reporting or similar.



