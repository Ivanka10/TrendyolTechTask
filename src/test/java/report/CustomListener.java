package report;

import com.trendyol.driver.DriverManager;
import io.qameta.allure.Attachment;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class CustomListener implements ITestListener {
    private final Logger logger = LogManager.getLogger(CustomListener.class);

    private static String getTestMethodName(ITestResult iTestResult) {
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }

    @Attachment(value = "Page screenshot", type = "image/png")
    public byte[] saveScreenshotPNG(WebDriver driver) {
        logger.debug("Taking screenshot....");
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    @Attachment(value = "{0}", type = "image/plain")
    public static String saveTextLog(String massage) {
        return massage;
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
        logger.debug("I am on start method " + getTestMethodName(iTestResult));
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        System.out.println("I am on test success " + getTestMethodName(iTestResult));
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        logger.debug("Test execution " + iTestResult.getMethod().getMethodName() + " failed.");
        WebDriver driver = DriverManager.getDriver();

        if (driver != null) {
            saveScreenshotPNG(driver);
        }
        saveTextLog(getTestMethodName(iTestResult) + " failed and screenshot was taken.");
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        logger.debug("I am on test skipped " + getTestMethodName(iTestResult));
    }

    @Override public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onStart(ITestContext iTestContext) {
        logger.debug("I am on test start " + iTestContext.getName());
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        logger.debug("I am on test finish " + iTestContext.getName());
    }
}
