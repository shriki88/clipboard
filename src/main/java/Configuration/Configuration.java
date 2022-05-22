package Configuration;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

public class Configuration {

    private static PropertiesConfiguration configuration;

    public static final String URL = "url";

    static
    {
        try
        {
            configuration = new PropertiesConfiguration("test.properties");
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
    }

    public String getProperty(String key)
    {
        return configuration.getString(key);
    }
}
