package helpers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;

    /**
     * This class reads the config file in a threadsafe way and returns its values.
     */
    public final class ConfigFileReader {

        private static final Logger LOGGER = LoggerFactory.getLogger(ConfigFileReader.class);
        private static final String PROPERTY_FILE_PATH = "/config.properties";

        static final Properties properties = new Properties();


        /**
         * Executes the readProperties method with default constructor. Taking the value from
         * PROPERTY_FILE_PATH as the location for the default property file. This method will be used
         * during runtime
         */
        public ConfigFileReader() {
            readProperties(PROPERTY_FILE_PATH);
        }

        private void readProperties(final String propertyFilePath) {
            try (InputStream inputStream = ConfigFileReader.class.getResourceAsStream(propertyFilePath)) {
                if (inputStream == null) {
                    throw new FileNotFoundException("Cannot find resource 'config.properties'");
                }
                properties.load(inputStream);
                LOGGER.info("Finished loading properties from {}", propertyFilePath);
                LOGGER.info(toString());
            } catch (IOException e) {
                LOGGER.error("Problem while loading properties from resource", e);
                throw new IllegalStateException(e.getMessage(), e);
            }
        }

        /**
         * This method gets a property from the configuration file
         *
         * @param propertyName the name of the property in the configuration file that is read
         * @return returns the value associated with the @param
         */
        public String getProperty(String propertyName) {
            Object value = properties.get(propertyName);

            if (value == null) {
                return null;
            }
            return value.toString();
        }

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder("\n");
            Enumeration<?> enumerationProperties = properties.propertyNames();
            while (enumerationProperties.hasMoreElements()) {
                String key = (String) enumerationProperties.nextElement();
                builder.append(key).append(" ==> ").append(properties.getProperty(key));
                builder.append("\n");
            }
            return builder.toString();
        }

}
