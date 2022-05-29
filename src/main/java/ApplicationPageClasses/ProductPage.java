package ApplicationPageClasses;

import CommonUtilities.CommonMethods;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

public class ProductPage extends CommonMethods {

    By BRANDS_LABEL = By.xpath("//*[@id='s-refinements']/div[18]/div/span");
    By CHECKBOX = By.xpath("//*[@id='s-refinements']/div[18]/ul/li[4]/span/a/div/label/i");
    By SORT_DROPDOWN = By.xpath("//*[@id='a-autoid-0-announce']");
    By LIST = By.xpath("//*[@id='s-result-sort-select_2']");
    By COST_PRICE = By.xpath("//*[@class='a-price-whole']");
    By ABOUT_THIS = By.xpath("//*[@id='feature-bullets']/h1");
    By PRICE = By.xpath("//*[contains(text()," + productPrice + ")]");

    @Step("Television page displayed successfully")
    public WebDriver verifyURL() {
        getCurrentURL("television");
        Reporter.log("Television page displayed successfully",true);
        return getDriver();
    }

    @Step("Verified the text successfully")
    public WebDriver verifyText() {
        moveToElement(BRANDS_LABEL);
        Reporter.log("Brands displayed successfully",true);
        waitForPageLoad();
        clickElement(CHECKBOX);
        Reporter.log("Brand selected successfully",true);
        waitForPageLoad();
        clickElement(SORT_DROPDOWN);
        clickElement(LIST);
        Reporter.log("Sorted HighestToLowest price successfully",true);
        getPrice(COST_PRICE);
        switchToWindow(PRICE, ABOUT_THIS);
        Reporter.log("Verified the text successfully",true);
        return getDriver();
    }


}
