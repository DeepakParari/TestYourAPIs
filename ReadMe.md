# A simple framework to test REST API's

## Framework elements
+ Rest Assured Library
+ Junit 4 ( Framework and Test runner )
+ Hamcrest (For managing asserts )
+ Framework logging using Log4J
+ RestAssured Request-Response logging

## How to run the tests
PetTest class contains tests (Smoke Tests) to perform the CRUD operation on the /pet endpoint.

+ You can run from the IDE by running the BaseTest class.
+ Using maven command mvn clean test (Optionally you can provide the profile using -P eg mvn clean test -Pdev).

Outputs:
+ Surefire reports under /target/surefire-reports
+ Request and Response logging under /target/request-response.txt
+ Framework logging under /target/logs/testlogging.log

## Components
Helper Classes
+ ConfigFileReader - Reads value from properties file. Resource filtering is used to facilitate working with multiple environments. (Find properties file under /main/resources/env/)
+ RequestLoader - Loads request body from the /test/resources/payloads folder.
+ SpecBuilder - Creates common request specification to be used by all tests.
+ JSONHelper - Manipulate JSON request / Read value from response.

## Tests
Test Package
+ BaseTest class for the setup.
+ PetTest - CRUD tests.

### Next Actions
+ Integrate in the pipeline.
+ Create POJO classes for setting up complex requests.
+ Use DataProvider for data driving the tests.
+ Create separate methods for CRUD operations and use it for creating functional tests.
+ Use JUnit asserts.
+ Allure reporting or similar.



