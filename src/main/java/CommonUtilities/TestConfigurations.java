package CommonUtilities;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

public class TestConfigurations {
    private static PropertiesConfiguration configuration;

    public static String URL = "url";
    public static String EXECUTION_PLATFORM = "executionPlatform";
    public static String ALLURE_COMMAND="allureCommand";
    public static String SERVER_COMMAND="serverCommand";
    public static String KILL_COMMAND="killCommand";

    static {
        try {
            configuration = new PropertiesConfiguration("test.properties");
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
    }

    public String getProperty(String key) {
        return configuration.getString(key);
    }
}
