package com.rhodes.spreadsheet;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.log4j.Logger;

/**
* Utility class for loading property configuration files.
*
* @author  R. Rhodes
* @version 1.0
* @since   2017-03-25
*/
public class Configuration {

    private static final Logger LOGGER = Logger.getLogger(Configuration.class);

    /**
     * Input stream for loading properties file.
     */
    private InputStream inputStream;

    /**
     * Properties for application access.
     */
    private static java.util.Properties prop = new java.util.Properties();

    /**
     * Loads the application properties from the configuration file
     * {@link config.properties} and displays the name of the input file to be
     * processed.
     *
     * @throws IOException
     */
    public void getPropValues() throws IOException {

        try {
            // Load properties from the properties file in the config directory
            String propFileName = "config.properties";
            inputStream = getClass().getClassLoader()
                    .getResourceAsStream(propFileName);

            if (inputStream != null) {
                prop.load(inputStream);
            } else {
                throw new FileNotFoundException("Property file '" + propFileName
                        + " not found in the classpath.");
            }

            // Print out the loaded property file name for the input data
            LOGGER.info("Application properties loaded.");

        } finally {
            inputStream.close();
        }
    }

    /**
     * Returns the property, based on the name in the config.properties file.
     *
     * @param property
     *            property name
     * @return property value
     */
    public static String getProperty(final String property) {
        return prop.getProperty(property);
    }
}
