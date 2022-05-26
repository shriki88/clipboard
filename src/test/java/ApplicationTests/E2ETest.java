package ApplicationTests;

import CommonUtilities.CommonUtilities;
import Configuration.Configuration;
import ModulePages.LandingPage;
import ModulePages.TelevisionPage;
import UserInformation.UserInformation;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class E2ETest extends CommonUtilities {

    @BeforeTest
    public void ObjectCreation()
    {
        userInformation = new UserInformation();
        landingPage = new LandingPage();
        televisionPage = new TelevisionPage();
        configuration = new Configuration();
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
