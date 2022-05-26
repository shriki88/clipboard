package AllureListeners;

import ModulePages.BasePage;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

public class AllureListener implements ITestListener {

    private static String getTestMethodName(ITestResult iTestResult) {
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }

    @Attachment(value = "{0}", type = "text/plain")
    public static String saveTextLog(String message) {
        return message;
    }

    @Attachment
    public byte[] saveFailureScreenShot(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    @Override
    public void onStart(ITestContext iTestContext) {
        Reporter.log("onStart method " + iTestContext.getName(), true);
        iTestContext.setAttribute("WebDriver", BasePage.getDriver());
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        Reporter.log("OnFinish method " + iTestContext.getName(), true);
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
        Reporter.log("onTestStart method " + getTestMethodName(iTestResult) + " started", true);
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        Reporter.log("onTestSuccess method " + getTestMethodName(iTestResult) + " succeed", true);
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        Reporter.log("onTestFailure method " + getTestMethodName(iTestResult) + " failed", true);
        Object testClass = iTestResult.getInstance();
        WebDriver driver = BasePage.getDriver();
        if (driver instanceof WebDriver) {
            System.out.println("Screenshot captured for test case:" + getTestMethodName(iTestResult));
            saveFailureScreenShot(driver);
        }
        saveTextLog(getTestMethodName(iTestResult) + " failed and screenshot taken");
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        Reporter.log("onTestSkipped method " + getTestMethodName(iTestResult) + " skipped", true);
    }
}
