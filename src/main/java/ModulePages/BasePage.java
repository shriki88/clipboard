package ModulePages;

import Configuration.Configuration;
import UserInformation.UserInformation;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.openqa.selenium.WebDriver;

public class BasePage {

    public static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
    public static Configuration configuration;
    public static PropertiesConfiguration propertiesConfiguration;
    public static UserInformation userInformation;
    public static LandingPage landingPage;
    public static TelevisionPage televisionPage;
    public static WebDriver wait;
    public static String P;

    public WebDriver driver()
    {
        return driver.get();


    }

}
