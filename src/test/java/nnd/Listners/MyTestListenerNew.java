package nnd.Listners;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import nnd.Utilities.Screenshot;

public class MyTestListenerNew implements ITestListener {
    private WebDriver driver;
    private static ExtentReports extent; // Ensure ExtentReports is a singleton
    private ExtentTest test;

    @Override
    public void onStart(ITestContext context) {
        if (extent == null) {
            System.out.println("Initializing ExtentReports...");
            
            String reportPath = System.getProperty("user.dir") + "\\reports\\ExtentReport_localBuild.html";
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
            sparkReporter.config().setTheme(Theme.STANDARD);
            
            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);
            
            System.out.println("✅ ExtentReports Initialized Successfully.");
        }
        
        System.out.println("🔵 Test Suite Started: " + context.getName());
    }

    @Override
    public void onTestStart(ITestResult result) {
        if (extent == null) {
            System.out.println("❌ ERROR: ExtentReports is NULL in onTestStart for: " + result.getMethod().getMethodName());
            return;
        }

        test = extent.createTest(result.getMethod().getMethodName());

        if (test == null) {
            System.out.println("❌ ERROR: ExtentTest instance is NULL for: " + result.getMethod().getMethodName());
        } else {
            System.out.println("✅ Test Started: " + result.getMethod().getMethodName());
            test.log(Status.INFO, "Test Started");
        }
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        if (test != null) {
            test.pass("✅ Test Passed: " + result.getName());
            test.log(Status.INFO, "Test Successfully Completed");
        }
        System.out.println("✅ Test Passed: " + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        if (test != null) {
            test.fail("❌ Test Failed: " + result.getName());
            test.log(Status.FAIL, result.getThrowable().toString());
        }

        driver = (WebDriver) result.getTestContext().getAttribute("driver");

        if (driver != null) {
            Screenshot sc = new Screenshot(driver);
            try {
                sc.takeScreenshot(result);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("⚠️ Driver is NULL. Cannot take screenshot.");
        }

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String screenshotPath = System.getProperty("user.dir") + "\\FailedScreenshots\\Screenshot_" + timeStamp + ".png";

        File screenshotFile = new File(screenshotPath);
        if (screenshotFile.exists() && test != null) {
            test.addScreenCaptureFromPath(screenshotPath);
        } else {
            System.out.println("⚠️ Screenshot file not found: " + screenshotPath);
        }

        System.out.println("❌ Test Failed: " + result.getName());
        System.out.println("Failure Reason: " + result.getThrowable());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        if (test != null) {
            test.skip("⚠️ Test Skipped: " + result.getName());
        }
        System.out.println("⚠️ Test Skipped: " + result.getName());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        if (test != null) {
            test.warning("⚠️ Test Failed but within success percentage: " + result.getName());
        }
        System.out.println("⚠️ Test Failed but within success percentage: " + result.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        if (extent != null) {
            extent.flush();
            System.out.println("📄 Extent Report Generated.");
        }
        System.out.println("✅ Test Suite Finished: " + context.getName());
    }
}
