

[![CircleCI](https://circleci.com/gh/DeepakParari/TestYourAPIs.svg?style=shield)](https://circleci.com/gh/DeepakParari/TestYourAPIs)

# A simple framework to test REST API's

## Framework elements
+ Rest Assured Library
+ Junit 4 ( Framework and Test runner )
+ Hamcrest (For managing asserts )
+ Framework logging using Log4J and JUnit RunListener
+ RestAssured Request-Response logging
+ Integrated with CircleCI

## How to run the tests

+ You can run from the IDE by running the BaseTest class. Mention the test classes to be run in the suite.
+ Using maven command mvn clean test (Optionally you can provide the profile using -P eg mvn clean test -Pdev).
+ Integrated with CircleCI + GitHub

Outputs:
+ Surefire reports under /target/surefire-reports
+ Request and Response logging under /target/request-response.txt
+ Framework logging under /target/logs/testlogging.log

## Components
Helper Classes
+ ConfigFileReader - Reads value from properties file. Resource filtering is used to facilitate working with multiple environments. (Find properties file under /main/resources/env/)
+ SpecBuilder - Creates common request specification to be used by all tests.
+ CommonListener - JUnit RunListener for real time execution status.

## Tests
Test Package
+ BaseTest class for the setup.
+ Users, Posts, Comments classes for specific service tests. Refer: https://jsonplaceholder.typicode.com

