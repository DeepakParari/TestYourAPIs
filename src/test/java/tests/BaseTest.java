package tests;

import io.restassured.specification.RequestSpecification;
import org.junit.Assume;
import org.junit.AssumptionViolatedException;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static helpers.SpecBuilder.setupRequestSpec;
import static io.restassured.RestAssured.*;

/**
 * This is the test runner.
 * Mention the Test classes to be run in the suite. You can add multiple test classes.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        Users.class,
        Posts.class,
        Comments.class
})

public class BaseTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseTest.class);
    public static RequestSpecification requestSpec;

    @BeforeClass
    public static void setUp() {

        LOGGER.info("Setting up.");

        enableLoggingOfRequestAndResponseIfValidationFails();

        //Get request specification from TestHelper class
        try{
            requestSpec = setupRequestSpec();
            Assume.assumeNotNull(requestSpec);

        }catch (Exception e){
           if(e instanceof AssumptionViolatedException)
               throw e;
               LOGGER.error("Problem while setting up RequestSpecification", e);
        }


    }

}

