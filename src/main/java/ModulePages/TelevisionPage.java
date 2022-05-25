package ModulePages;

import CommonUtilities.CommonUtilities;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

public class TelevisionPage extends CommonUtilities {

    By BRANDS_LABEL = By.xpath("//*[@id='s-refinements']/div[18]/div/span");
    By CHECKBOX = By.xpath("//*[@id='s-refinements']/div[18]/ul/li[4]/span/a/div/label/i");
    By SORT_DROPDOWN = By.xpath("//*[@id='a-autoid-0-announce']");
    By LIST = By.xpath("//*[@id='s-result-sort-select_2']");
    By COST_PRICE = By.xpath("//*[@class='a-price-whole']");
    By ABOUT_THIS = By.xpath("//*[@id='feature-bullets']/h1");
    By PRICE = By.xpath("//*[contains(text()," + productPrice + ")]");

    public WebDriver Televisions() {
        getCurrentURL("television");
        Reporter.log("Television page displayed successfully",true);
        return getDriver();
    }

    public WebDriver selectSecondHighestPricedTelevision() {
        moveToElement(BRANDS_LABEL);
        waitForPageLoad();
        clickElement(CHECKBOX);
        waitForPageLoad();
        clickElement(SORT_DROPDOWN);
        clickElement(LIST);
        sortPrice(COST_PRICE);
        switchToWindow(PRICE, ABOUT_THIS);
        return getDriver();
    }


}
