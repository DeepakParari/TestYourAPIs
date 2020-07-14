package my.testyourapi;

import io.restassured.specification.RequestSpecification;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tests.UsersTests;

import static helpers.SpecBuilder.setupRequestSpec;
import static io.restassured.RestAssured.*;

/**
 * This is the test runner.
 * Mention the Test classes to be run in the suite. You can add multiple test classes.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        UsersTests.class,
})
public class BaseTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseTest.class);
    public static RequestSpecification requestSpec;

    @BeforeClass
    public static void setUp(){

        LOGGER.info("Setting up.");

        enableLoggingOfRequestAndResponseIfValidationFails();

        //Get request specification from TestHelper class
        requestSpec = setupRequestSpec();
    }

}

