package helpers;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class TestHelpers {
    private static final Logger LOGGER = LoggerFactory.getLogger(TestHelpers.class);

    public static RequestSpecification setupRequestSpec() {
        try {
            RequestSpecBuilder builder = new RequestSpecBuilder();
            builder.addHeader("Content-Type", "application/json");
            builder.setBaseUri("https://petstore.swagger.io/v2");
            //TODO Fetch BASEURI from properties file.
            return builder.build();
        } catch (Exception e) {
            LOGGER.error("Problem while setting up RequestSpecification", e);
        }
        return null;
    }

}
