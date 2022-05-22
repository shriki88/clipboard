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
        driver.set(new ChromeDriver());
        driver().manage().window().maximize();
        //driver().manage().deleteAllCookies();
        driver().get(configuration.getProperty(URL));
        sleep(3000);
        Reporter.log("URL accessed successfully", true);

        return driver();
    }

    public WebElement findByElement(By elementBy) {
        return driver().findElement(elementBy);
    }

    public List<WebElement> findByElements(By elementBy) {
        return driver().findElements(elementBy);
    }

    public WebDriver clickElement(By elementBy) {
        driver().findElement(elementBy).click();
        Reporter.log("Element clicked successfully");
        return driver();
    }

    public boolean isDisplayed(By elementBy) {
        boolean result = driver().findElement(elementBy).isDisplayed();
        Reporter.log("Element is displayed as expected");
        return result;
    }

    public WebDriver getCurrentURL(String section) {
        String URL = driver().getCurrentUrl();
        if (URL.contains(section)) {
            Reporter.log(section + " is displayed successfully");
        } else {
            Reporter.log(section + " is not displayed");
        }
        Reporter.log(section + " Page is displayed successfully");
        return driver();
    }

    public WebDriver waitForPageLoad() {
        driver().manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
        return driver();
    }

    public WebDriver sleep(long sleepTime) {
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return driver();
    }

    public WebDriver moveToElement(By elementBy) {
        Actions a = new Actions(driver());
        WebElement element = findByElement(elementBy);
        a.moveToElement(element).build().perform();
        Reporter.log("Moved to " + element + " successfully");
        return driver();
    }

    public WebDriver selectOption(By elementBy, String option) {
        Select s = new Select(findByElement(elementBy));
        s.selectByValue(option);
        Reporter.log("Element selected successfully");
        return driver();
    }

    public WebDriver sortPrice(By elementBy) {
        List<WebElement> list = findByElements(elementBy);
        List<String> price = new ArrayList<>();
        for (WebElement w : list) {
            price.add(w.getText());
        }
        Collections.reverse(price);
        P = price.get(1);
        return driver();
    }

    public WebDriver switchToWindow(By elementBy1, By elementBy2) {
        String parentWindow = driver().getWindowHandle();
        Set<String> s = driver().getWindowHandles();
        Iterator<String> i = s.iterator();
        while (i.hasNext()) {
            String childWindow = i.next();
            if (!parentWindow.equalsIgnoreCase(childWindow)) {
                driver().switchTo().window(childWindow);
                waitForPageLoad();
                isDisplayed(elementBy1);
                Reporter.log("Switched to child window successfully");
                if (isDisplayed(elementBy2)) {
                    Reporter.log("Element is displayed successfully", true);
                } else {
                    Reporter.log("Element is not displayed", true);
                }

            }
        }

        return driver();
    }

    public WebDriver closeBrowser() {
        driver().close();
        return driver();
    }
}
