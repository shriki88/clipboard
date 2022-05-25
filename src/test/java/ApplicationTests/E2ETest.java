package ApplicationTests;

import CommonUtilities.CommonUtilities;
import Configuration.Configuration;
import ModulePages.LandingPage;
import ModulePages.TelevisionPage;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class E2ETest extends CommonUtilities {
    @BeforeTest
    public void objectCreation() throws ConfigurationException {
        landingPage = new LandingPage();
        televisionPage = new TelevisionPage();
        configuration = new Configuration();
        propertiesConfiguration = new PropertiesConfiguration("test.properties");
    }

    @Test(description = "verifyTextAboutThisItem")
    public void verifyTextAboutThisItem() {
        landingPage.loginToApplication();
        landingPage.navigateToTVSection();
        televisionPage.Televisions();
        televisionPage.selectSecondHighestPricedTelevision();
        closeBrowser();
    }
}
