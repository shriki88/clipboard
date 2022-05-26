package ModulePages;

import Configuration.Configuration;
import UserInformation.UserInformation;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.openqa.selenium.WebDriver;

public class BasePage {

    public static WebDriver driver;
    public static ThreadLocal<WebDriver> tDriver = new ThreadLocal<WebDriver>();
    public static LandingPage landingPage;
    public static TelevisionPage televisionPage;
    public static Configuration configuration;
    public static UserInformation userInformation;
    public static PropertiesConfiguration propertiesConfiguration;
    public static String productPrice;
    public static String executionPlatform;

    public static synchronized WebDriver getDriver() {
        return tDriver.get();
    }

}
