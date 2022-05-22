package ModulePages;

import CommonUtilities.CommonUtilities;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

public class LandingPage extends CommonUtilities {

    By PAGE_LOGO = By.id("nav-logo-sprites");
    By HAMBURGER_ICON = By.xpath("//*[@id='nav-hamburger-menu']/i");
    By SHOP_BY_DEPT_LABEL = By.xpath("//*[@id='hmenu-content']/ul[1]/li[14]/div");
    By TV_APP_ELE_SECTION = By.xpath("//*[@id='hmenu-content']/ul[1]/li[16]/a/div");
    By TELEVISION_SECTION_LABEL = By.xpath("//*[@id='hmenu-content']/ul[9]/li[3]/a");


    public WebDriver loginToApplication() {
        landingPage.launchBrowserAndGetURL();
        if (isDisplayed(PAGE_LOGO)) ;
        {
            Reporter.log("Landing page displayed successfully", true);
        }
        sleep(3000);
        return driver();
    }

    public WebDriver navigateToTVSection() {
        clickElement(HAMBURGER_ICON);
        sleep(1000);
        Reporter.log("Sections are displayed successfully", true);
        isDisplayed(SHOP_BY_DEPT_LABEL);
        isDisplayed(TV_APP_ELE_SECTION);
        clickElement(TV_APP_ELE_SECTION);
        waitForPageLoad();
        isDisplayed(TELEVISION_SECTION_LABEL);
        clickElement(TELEVISION_SECTION_LABEL);
        waitForPageLoad();
        return driver();
    }

}
