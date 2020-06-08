package my.testyourapi;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.BeforeClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import static helpers.TestHelpers.setupRequestSpec;
import static io.restassured.RestAssured.enableLoggingOfRequestAndResponseIfValidationFails;

public class BaseTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseTest.class);
    public static RequestSpecification requestSpec;

    @BeforeClass
    public static void setUp(){

        LOGGER.info("Setting up.....");
        enableLoggingOfRequestAndResponseIfValidationFails();
        //Get request specification from TestHelper class
        requestSpec = setupRequestSpec();
    }



}

//TODO
/*
Create a Request specification
 */

