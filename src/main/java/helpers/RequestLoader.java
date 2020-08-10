package helpers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;

public class RequestLoader {

    private static final Logger LOGGER = LoggerFactory.getLogger(RequestLoader.class);
    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static final String ERROR_MESSAGE = "Unable to load resource '";

    /**
     * Load a JSON file as a resource and map it to an object to be used in Rest Assured
     * RequestSpecification body method
     *
     * @param resource the resource JSON file to be mapped to an object
     * @return the object with the de-serialized resource Throws an IllegalArgumentException when the
     * file cannot be read
     */
    public Object loadJson(String resource) {
        Object result;
        try (InputStream inputStream = this.getClass().getResourceAsStream(resource)) {
            result = MAPPER.readValue(inputStream, Object.class);
        } catch (IOException e) {
            LOGGER.error(ERROR_MESSAGE + resource + "' (" + e.getMessage() + ")", e);
            throw new IllegalArgumentException(ERROR_MESSAGE + resource + "' (" + e.getMessage() + ")",
                    e);
        }
        return result;
    }
}
