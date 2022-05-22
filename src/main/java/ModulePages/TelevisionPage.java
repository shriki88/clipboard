package ModulePages;

import CommonUtilities.CommonUtilities;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

public class TelevisionPage extends CommonUtilities {

    By BRANDS_LABEL = By.xpath("//*[@id='s-refinements']/div[18]/div/span");
    By CHECKBOX = By.xpath("//*[@id='s-refinements']/div[18]/ul/li[4]/span/a/div/label/i");
    By SORT_DROPDOWN = By.xpath("//*[@id='a-autoid-0']/span/i");
    By LIST = By.xpath("//*[@id='a-popover-2']/div/div/ul");
    By COST_PRICE = By.xpath("//*[@class='a-price-whole']");
    By ABOUT_THIS = By.xpath("//*[@id='feature-bullets']/h1");
    By PRICE = By.xpath("//*[contains(text(),'"+P+"')]");

    public WebDriver Televisions() {
        getCurrentURL("television");
        return driver();
    }

    public WebDriver selectSecondHighestPricedTelevision() {
        moveToElement(BRANDS_LABEL);
        waitForPageLoad();
        clickElement(CHECKBOX);
        sleep(1000);
        clickElement(SORT_DROPDOWN);
        selectOption(LIST, "Price: High to Low");
        sortPrice(COST_PRICE);
        switchToWindow(PRICE,ABOUT_THIS);
        return driver();
    }


}
