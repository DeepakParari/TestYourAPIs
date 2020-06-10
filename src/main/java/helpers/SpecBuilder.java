package helpers;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileOutputStream;
import java.io.PrintStream;

/**
 * This class is for building spec.
 */

public class SpecBuilder {
    private static final Logger LOGGER = LoggerFactory.getLogger(SpecBuilder.class);

    /**
     * This builds the request specification. Common header parameters, base URI are set here for all the requests.
     * @return RequestSpecification
     */

    public static RequestSpecification setupRequestSpec() {
        try {

            RequestSpecBuilder builder = new RequestSpecBuilder();
            PrintStream log = new PrintStream(new FileOutputStream("target/request-responces.txt"));
            builder.addHeader("Content-Type", "application/json");
            builder.setBaseUri(getConfiguration().getProperty("BASE_URI"));
            builder.addFilter(RequestLoggingFilter.logRequestTo(log));
            builder.addFilter(ResponseLoggingFilter.logResponseTo(log));
            return builder.build();
        } catch (Exception e) {
            LOGGER.error("Problem while setting up RequestSpecification", e);
        }
        return null;
    }

    /*
    Get properties from ConfigFileReader class
     */
    static ConfigFileReader getConfiguration() {
        return new ConfigFileReader();
    }
}
