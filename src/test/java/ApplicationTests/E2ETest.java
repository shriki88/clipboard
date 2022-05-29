package ApplicationTests;

import CommonUtilities.CommonMethods;
import CommonUtilities.TestConfigurations;
import ApplicationPageClasses.ApplicationLandingPage;
import ApplicationPageClasses.ProductPage;
import CommonUtilities.GettersAndSetters;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class E2ETest extends CommonMethods {

    @BeforeTest
    public void ObjectCreation() {
        gettersAndSetters = new GettersAndSetters();
        applicationLandingPage = new ApplicationLandingPage();
        productPage = new ProductPage();
        testConfigurations = new TestConfigurations();
    }

    @Test(description = "verifyTextAboutThisItem")
    public void verifyTextAboutThisItem() {
        applicationLandingPage.accessApplication();
        applicationLandingPage.navigateToProductSection();
        productPage.verifyURL();
        productPage.verifyText();
        closeBrowser();
    }
}
