package ApplicationPageClasses;

import CommonUtilities.TestConfigurations;
import CommonUtilities.GettersAndSetters;
import org.openqa.selenium.WebDriver;

public class ObjectReferences {

    public static WebDriver driver;
    public static ThreadLocal<WebDriver> tDriver = new ThreadLocal<WebDriver>();
    public static ApplicationLandingPage applicationLandingPage;
    public static ProductPage productPage;
    public static TestConfigurations testConfigurations;
    public static GettersAndSetters gettersAndSetters;
    public static String productPrice;

    public static synchronized WebDriver getDriver() {
        return tDriver.get();
    }

}
