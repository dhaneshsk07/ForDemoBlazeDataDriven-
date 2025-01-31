package nnd.Listners;

import java.io.IOException;
import java.util.Date;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import nnd.Utilities.Screenshot;

public class MyTestListener implements ITestListener {
	private WebDriver driver;
	private ExtentReports extent;
	private ExtentTest test;

	@Override
	public void onTestStart(ITestResult result) {

		// extent report - Create a new test in ExtentReports when the test starts
		test = extent.createTest(result.getMethod().getMethodName());
		System.out.println("Test Started: " + result.getName());
	}

	@Override
	public void onTestSuccess(ITestResult result) {

		test.pass("Test passed");
		System.out.println("Test Passed: " + result.getName());
	}

	@Override
	public void onTestFailure(ITestResult result) {

		// Extent report
		test.fail("Test failed");

		// Ensure the driver is initialized, typically from your test setup
		driver = (WebDriver) result.getTestContext().getAttribute("driver"); // Retrieve driver from the context

		//FOR SCREENSHOT - CONNECT WITH BASE CLASS
		if (driver != null) {
			Screenshot sc = new Screenshot(driver);
			try {
				sc.takeScreenshot(result);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Driver is not available for taking the screenshot.");
		}

		System.out.println("Test Failed: " + result.getName());
		System.out.println("Failure Reason: " + result.getThrowable());

	}

	@Override
	public void onTestSkipped(ITestResult result) {

		// extent report
		test.fail("Test failed");
		System.out.println("Test Skipped: " + result.getName());
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

		// extent report
		test.warning("Test failed but within success percentage");
		System.out.println("Test Failed but within success percentage: " + result.getName());
	}

	@Override
	public void onStart(ITestContext context) {

		// Extent reports- Use ExtentSparkReporter instead of ExtentHtmlReporter
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter("extentReport.html");
		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);

		System.out.println("Test Suite Started: " + context.getName());
	}

	@Override
	public void onFinish(ITestContext context) {

		// extent report - Generate the report at the end of test execution
		extent.flush();
		System.out.println("Test Suite Finished: " + context.getName());
	}
}
