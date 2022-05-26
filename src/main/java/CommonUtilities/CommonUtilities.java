package CommonUtilities;

import ModulePages.BasePage;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.Reporter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static Configuration.Configuration.*;

public class CommonUtilities extends BasePage {


    /**
     * This method is used to capture the system details to be added to allure report
     */
    public WebDriver captureSystemDetailsToAddInAllureProperties() {
        executionPlatform = userInformation.getExecutionPlatform();
        if (executionPlatform.equals("LOCAL")) {
            Capabilities caps = ((RemoteWebDriver) getDriver()).getCapabilities();
            String operatingSys = System.getProperty("os.name").toLowerCase();
            String browserName = caps.getBrowserName();
            String browserVersion = caps.getVersion();
            String exePlatform = executionPlatform;
            OutputStream outputStream;
            Properties prop = new Properties();
            prop.setProperty("OS", operatingSys);
            prop.setProperty("BROWSER_NAME", browserName);
            prop.setProperty("BROWSER_VERSION", browserVersion);
            prop.setProperty("EXECUTION_PLATFORM", exePlatform);
            try {
                outputStream = new FileOutputStream("allure-results/environment.properties");
                prop.store(outputStream, "Dynamic Property File");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            String operatingSys = System.getProperty("os.name").toLowerCase();
            String browserName = System.getProperty("browser");
            String browserVersion = System.getProperty("version");
            String exePlatform = executionPlatform;
            OutputStream outputStream;
            Properties prop = new Properties();
            prop.setProperty("OS", operatingSys);
            prop.setProperty("BROWSER_NAME", browserName);
            prop.setProperty("BROWSER_VERSION", browserVersion);
            prop.setProperty("EXECUTION_PLATFORM", exePlatform);
            try {
                outputStream = new FileOutputStream("allure-results/environment.properties");
                prop.store(outputStream, "Dynamic Property File");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return getDriver();
    }

    /**
     * This method is used to launch browser and access the application URL
     */
    public WebDriver launchBrowserAndGetURL() {
        executionPlatform = userInformation.getExecutionPlatform();
        if (executionPlatform.equals("LOCAL")) {
            Reporter.log("Test executing on LOCAL", true);
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            tDriver.set(driver);
            captureSystemDetailsToAddInAllureProperties();
            driver.manage().window().maximize();
            driver.manage().deleteAllCookies();
            driver.get(configuration.getProperty(URL));
            sleep(1000);
        } else {
            Reporter.log("Test executing on GRID", true);
            String browser = System.getProperty("browser");
            DesiredCapabilities capabilities = new DesiredCapabilities();
            switch (browser) {
                case "chrome":
                    capabilities.setPlatform(Platform.ANY);
                    capabilities.setBrowserName(BrowserType.CHROME);
                    break;
                case "firefox":
                    capabilities.setPlatform(Platform.ANY);
                    capabilities.setBrowserName(BrowserType.FIREFOX);
                    break;
                case "ie":
                    capabilities.setPlatform(Platform.ANY);
                    capabilities.setBrowserName(BrowserType.IE);
                    break;
                case "edge":
                    capabilities.setPlatform(Platform.ANY);
                    capabilities.setBrowserName(BrowserType.EDGE);
                    break;
                case "safari":
                    capabilities.setPlatform(Platform.ANY);
                    capabilities.setBrowserName(BrowserType.SAFARI);
                    break;
                case "default":
                    System.out.println("Invalid Choice!");
                    break;
            }
            try {
                tDriver.set(new RemoteWebDriver(new URL("http://192.168.31.202:4444/wd/hub"), capabilities));
                captureSystemDetailsToAddInAllureProperties();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        Reporter.log("URL accessed successfully", true);
        return getDriver();
    }

    /**
     * This method is used to find the reqired element on the UI
     */
    public WebElement findByElement(By elementBy) {
        return getDriver().findElement(elementBy);
    }

    /**
     * This method is used to find the required elements on the UI
     */
    public List<WebElement> findByElements(By elementBy) {
        return getDriver().findElements(elementBy);
    }

    /**
     * This method is used to perform click operations on the UI
     */
    public WebDriver clickElement(By elementBy) {
        getDriver().findElement(elementBy).click();
        sleep(3000);
        Reporter.log("Click operation performed successfully", true);
        return getDriver();
    }

    /**
     * This method is used to verify the element is displayed on the UI
     */
    public boolean isDisplayed(By elementBy) {
        boolean result = getDriver().findElement(elementBy).isDisplayed();
        Reporter.log("Display/Visibility of the element is verified successfully", true);
        sleep(2000);
        return result;
    }

    /**
     * This method is used to get the current URL
     */
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

    /**
     * This method is used to wait for till the element is identified
     */
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

    /**
     * This method is used to perform required action on the UI
     */
    public WebDriver moveToElement(By elementBy) {
        Actions a = new Actions(getDriver());
        WebElement element = findByElement(elementBy);
        a.moveToElement(element).build().perform();
        Reporter.log("Moved to " + element.getText() + " successfully", true);
        return getDriver();
    }

    /**
     * This method is used to select the required option from the list
     */
    public WebDriver selectOption(By elementBy, String option) {
        Select s = new Select(findByElement(elementBy));
        s.selectByValue(option);
        Reporter.log("Element selected successfully", true);
        return getDriver();
    }

    /**
     * This method is used to get the price of the product
     */
    public WebDriver getPrice(By elementBy) {
        List<WebElement> list = findByElements(elementBy);
        List<String> price = new ArrayList<>();
        for (WebElement w : list) {
            price.add(w.getText());
        }
        productPrice = price.get(1);
        Reporter.log("The second highest Television price is: " + productPrice, true);
        return getDriver();
    }

    /**
     * This method is used to switch between the windows
     */
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

    /**
     * This method is used to close the browser
     */
    @Step("Browser closed successfully")
    public WebDriver closeBrowser() {
        getDriver().close();
        return getDriver();
    }
}
