package CommonUtilities;

import ApplicationPageClasses.ObjectReferences;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
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

public class CommonMethods extends ObjectReferences {

    /**
     * This method is used to get the test platform details
     */
    public String getTestPlatform() {
        String testPlatform = (String.format(gettersAndSetters.getExecutionPlatform(), System.getProperty("platform")));
        return testPlatform;
    }

    /**
     * This method is used to capture the system details to be added to allure report
     */
    public WebDriver captureSystemDetailsToAddInAllureProperties() {
        if (getTestPlatform().equals("GRID")) {
            String operatingSys = System.getProperty("os.name").toLowerCase();
            String browserName = System.getProperty("browser");
            String exePlatform = getTestPlatform();
            OutputStream outputStream;
            Properties prop = new Properties();
            prop.setProperty("OS", operatingSys);
            prop.setProperty("BROWSER_NAME", browserName);
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
            Capabilities capabilities = ((RemoteWebDriver) getDriver()).getCapabilities();
            String operatingSys = System.getProperty("os.name").toLowerCase();
            String browserName = capabilities.getBrowserName();
            String browserVersion = capabilities.getVersion();
            String exePlatform = (String.format(gettersAndSetters.getExecutionPlatform(), "LOCAL"));
            OutputStream outputStream;
            Properties properties = new Properties();
            properties.setProperty("OS", operatingSys);
            properties.setProperty("BROWSER_NAME", browserName);
            properties.setProperty("BROWSER_VERSION", browserVersion);
            properties.setProperty("EXECUTION_PLATFORM", exePlatform);
            try {
                outputStream = new FileOutputStream("allure-results/environment.properties");
                properties.store(outputStream, "Dynamic Property File");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return getDriver();
    }

    /**
     * This method is used to run selenium Standalone Server for Grid Execution
     */

    public void enableServerCommand() {
        String dir = System.getProperty("user.dir");
        String path = dir + "\\src\\test\\Server\\Server.bat";
        try {
            Runtime.getRuntime().exec("cmd.exe /C start " + path);
        } catch (IOException ex) {
        }
    }

    /**
     * This method is used to close open cmd window and any of its child windows
     */
    public void killOpenCmdWindow() {
        try {
            Runtime.getRuntime().exec("TASKKILL /F /IM cmd.exe /T");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is used to launch browser and access the application URL
     * This method supports both LOCAL and GRID execution based on the if condition
     */
    public WebDriver launchBrowserAndGetURL() {
        enableServerCommand();
        sleep(5000);
        if (getTestPlatform().equals("GRID")) {
            Reporter.log("Test executing on GRID", true);
            String browser = System.getProperty("browser");
            DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
            switch (browser) {
                case "chrome":
                    desiredCapabilities.setPlatform(Platform.ANY);
                    desiredCapabilities.setBrowserName(BrowserType.CHROME);
                    break;
                case "firefox":
                    desiredCapabilities.setPlatform(Platform.ANY);
                    desiredCapabilities.setBrowserName(BrowserType.FIREFOX);
                    break;
                case "default":
                    System.out.println("Invalid Choice!");
                    break;
            }
            try {
                driver = new RemoteWebDriver(new URL("http://localhost:4444"), desiredCapabilities);
                driver.manage().window().maximize();
                driver.manage().deleteAllCookies();
                driver.get(gettersAndSetters.getUrl());
                tDriver.set(driver);
                captureSystemDetailsToAddInAllureProperties();
                sleep(500);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        } else {
            killOpenCmdWindow();
            Reporter.log("Test executing on LOCAL", true);
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            //driver.manage().deleteAllCookies();
            driver.get(gettersAndSetters.getUrl());
            tDriver.set(driver);
            captureSystemDetailsToAddInAllureProperties();
            sleep(500);
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
        Actions actions = new Actions(getDriver());
        WebElement element = findByElement(elementBy);
        actions.moveToElement(element).build().perform();
        Reporter.log("Moved to " + element.getText() + " successfully", true);
        return getDriver();
    }

    /**
     * This method is used to get the price of the product
     */
    public WebDriver getPrice(By elementBy) {
        List<WebElement> list = findByElements(elementBy);
        List<String> price = new ArrayList<>();
        for (WebElement webElement : list) {
            price.add(webElement.getText());
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
        Set<String> set = getDriver().getWindowHandles();
        Iterator<String> iterator = set.iterator();
        while (iterator.hasNext()) {
            String childWindow = iterator.next();
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
        Reporter.log("Browser closed successfully", true);
        return getDriver();
    }
}
