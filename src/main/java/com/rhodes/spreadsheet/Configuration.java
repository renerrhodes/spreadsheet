package com.rhodes.spreadsheet;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.log4j.Logger;

/**
 * @author      Rene Rhodes <address @ rene.rhodes@gmail.com/>
 * @version     1.0                 
  */
public class Configuration {

    private static final Logger LOGGER = Logger.getLogger(Configuration.class);

    /**
     * Input stream for loading properties file
     */
    private InputStream inputStream;

    /**
     * Application properties
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
                throw new FileNotFoundException("property file '" + propFileName
                        + " not found in the classpath");
            }

            // Print out the loaded property file name for the input data
            LOGGER.info("Application properties loaded. Input file ="
                    + prop.getProperty("app.data.inputfile"));

        } finally {
            inputStream.close();
        }
    }

    /**
     * Returns the value of the desired property based on the property name
     * entered in the configuration file.
     *
     * @param property
     *            name of the property requested
     * @return value of the property
     */
    public static String getProperty(final String property) {
        return prop.getProperty(property);
    }
}
