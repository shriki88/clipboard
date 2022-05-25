package CommonUtilities;

import ModulePages.BasePage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.Reporter;
import java.util.*;
import java.util.concurrent.TimeUnit;
import static Configuration.Configuration.*;

public class CommonUtilities extends BasePage {


    public WebDriver launchBrowserAndGetURL() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.get(configuration.getProperty(URL));
        tDriver.set(driver);
        sleep(1000);
        Reporter.log("URL accessed successfully", true);
        return getDriver();
    }

    public WebElement findByElement(By elementBy) {
        return getDriver().findElement(elementBy);
    }

    public List<WebElement> findByElements(By elementBy) {
        return getDriver().findElements(elementBy);
    }

    public WebDriver clickElement(By elementBy) {
        getDriver().findElement(elementBy).click();
        sleep(3000);
        Reporter.log("Click operation performed successfully", true);
        return getDriver();
    }

    public boolean isDisplayed(By elementBy) {
        boolean result = getDriver().findElement(elementBy).isDisplayed();
        Reporter.log("Display/Visibility of the element is verified successfully", true);
        sleep(2000);
        return result;
    }

    public WebDriver getCurrentURL(String section) {
        String URL = getDriver().getCurrentUrl();
        if (URL.contains(section)) {
            Reporter.log(section + " is displayed successfully", true);
        } else {
            Reporter.log(section + " is not displayed", true);
        }
        Reporter.log(section + " Page is displayed successfully", true);
        return getDriver();
    }

    public WebDriver waitForPageLoad() {
        getDriver().manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
        return getDriver();
    }

    public WebDriver sleep(long sleepTime) {
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return getDriver();
    }

    public WebDriver moveToElement(By elementBy) {
        Actions a = new Actions(getDriver());
        WebElement element = findByElement(elementBy);
        a.moveToElement(element).build().perform();
        Reporter.log("Moved to " + element.getText() + " successfully", true);
        return getDriver();
    }

    public WebDriver selectOption(By elementBy, String option) {
        Select s = new Select(findByElement(elementBy));
        s.selectByValue(option);
        Reporter.log("Element selected successfully", true);
        return getDriver();
    }

    public WebDriver sortPrice(By elementBy) {
        List<WebElement> list = findByElements(elementBy);
        List<String> price = new ArrayList<>();
        for (WebElement w : list) {
            price.add(w.getText());
        }
        productPrice = price.get(1);
        Reporter.log("The second highest Television price is: " + productPrice, true);
        return getDriver();
    }

    public WebDriver switchToWindow(By elementBy1, By elementBy2) {
        String parentWindow = getDriver().getWindowHandle();
        clickElement(elementBy1);
        Set<String> s = getDriver().getWindowHandles();
        Iterator<String> i = s.iterator();
        while (i.hasNext()) {
            String childWindow = i.next();
            if (!parentWindow.equalsIgnoreCase(childWindow)) {
                getDriver().switchTo().window(childWindow);
                waitForPageLoad();
                isDisplayed(elementBy1);
                Reporter.log("Switched to child window successfully", true);
                Assert.assertEquals(isDisplayed(elementBy2), true);
                getDriver().close();
            }
        }
        getDriver().switchTo().window(parentWindow);
        return getDriver();
    }

    public WebDriver closeBrowser() {
        getDriver().close();
        return getDriver();
    }
}
