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
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import nnd.Utilities.Screenshot;
import nnd.Utilities.DB_ScreenshotExtent;

public class DB_MyTestListener implements ITestListener {
	private WebDriver driver;
	private ExtentReports extent;
	private ExtentTest test;

	@Override
	public void onTestStart(ITestResult result) {

		// extent report - Create a new test in ExtentReports when the test starts
		// test = extent.createTest(result.getMethod().getMethodName());

		// expeerimental 16022025
		// Create Extent Report test with name and description
		String description = result.getMethod().getDescription();
		String testName = result.getMethod().getMethodName();

		// Handle multiple invocations by appending invocation count
		int invocationCount = result.getMethod().getCurrentInvocationCount() + 1;
		String fullTestName = testName + " [TEST DATA SET #" + invocationCount + "]";

		test = extent.createTest(fullTestName);

		test.log(Status.INFO, " Test Started : " + result.getName()); // exp_code
		test.log(Status.INFO, " Test CREDENTIALS SET : " + invocationCount);
		test.log(Status.INFO, " Test DESCRIPTION : " + description); // exp_code

		test.log(Status.PASS, " Test PASSED: " + result.getName()); // exp_code
		test.log(Status.FAIL, " Test FAILED: " + result.getName()); // exp_code

		// for console
		System.out.println("Test Started : " + result.getName());
		System.out.println("Test Description : " + description);
		System.out.println("Test NAME WITH INVOCATION COUNT : " + fullTestName);
	}

	@Override
	public void onTestSuccess(ITestResult result) {

		test.pass("Test passed" + result.getName());

		test.log(Status.INFO, " Test Successfull : " + result.getName()); // exp_code

		// for console
		System.out.println("Test Passed : " + result.getName());
	}

	@Override
	public void onTestFailure(ITestResult result) {

		// Extent report
		test.fail("Test failed : " + result.getName());

		// Extent report
		test.fail("Failure Reason: " + result.getThrowable());

		// Ensure the driver is initialized, typically from your test setup
		driver = (WebDriver) result.getTestContext().getAttribute("driver"); // Retrieve driver from the context

		// FOR SCREENSHOT - CONNECT WITH BASE CLASS
		if (driver != null) {
			// Screenshot sc = new Screenshot(driver);
			DB_ScreenshotExtent sc = new DB_ScreenshotExtent(driver);
			try {
				//sc.takeScreenshot(result);

				// EXPERIEMENTAL 17022025
				// Capture screenshot and return path
				String screenshotPath = sc.takeScreenshot(result);

				// Attach Screenshot to Extent Report
				test.fail("Screenshot on Failure",
						MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());

			} catch (IOException e) {
				e.printStackTrace();
				test.fail("Failed to capture screenshot: " + e.getMessage());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				test.fail("Failed to capture SCREENSHOT Interrupted : " + e.getMessage());
			}
		} else {
			System.out.println("Driver is not available for taking the screenshot.");
		}

		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()); // Generates current timestamp
		String screenshotPath = "C:/Users/dhane/eclipse-workspace/OpenCart26012025/Failed Screenshots/Screenshot_test1_"
				+ timeStamp + ".png";

// Ensure the screenshot is actually saved before attaching
		// CODE TO SHOW SCREENSHOT IN EXTENT REPORT
		File screenshotFile = new File(screenshotPath);
		if (screenshotFile.exists()) {
			test.addScreenCaptureFromPath(screenshotPath);
		} else {
			System.out.println("extent Screenshot file not found: " + screenshotPath);
		}
		// --------------------

		// for console
		System.out.println("Test Failed: " + result.getName());
		System.out.println("Failure Reason: " + result.getThrowable());

	}

	@Override
	public void onTestSkipped(ITestResult result) {

		// extent report
		test.skip("Test Skipped : " + result.getName());

		// for console
		System.out.println("Test Skipped: " + result.getName());
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

		// extent report
		test.warning("Test failed but within success percentage : " + result.getName());

		// for console
		System.out.println("Test Failed but within success percentage: " + result.getName());
	}

	@Override
	public void onStart(ITestContext context) {

		// for genkins
		String buildNumber = System.getenv("BUILD_NUMBER");

		if (buildNumber != null) {

			System.out.println("the build number is : " + buildNumber);
			// String reportPath = System.getProperty("user.dir") + "/reports/ExtentReport_"
			// + buildNumber + ".html";
			String reportPath = System.getProperty("user.dir") + "\\" + "reports" + "\\" + "ExtentReport_" + buildNumber
					+ ".html";

			ExtentSparkReporter sparkReporterGenkins = new ExtentSparkReporter(reportPath);
			extent = new ExtentReports();
			extent.attachReporter(sparkReporterGenkins);

		} else {

			System.out.println("the build number is : " + buildNumber);
			// String reportPath = System.getProperty("user.dir") + "/reports/ExtentReport_"
			// + buildNumber + ".html";
			String reportPath = System.getProperty("user.dir") + "\\" + "reports" + "\\" + "ExtentReport_"
					+ "localBuild" + ".html";

			ExtentSparkReporter sparkReporterGenkins = new ExtentSparkReporter(reportPath);
			extent = new ExtentReports();
			extent.attachReporter(sparkReporterGenkins);
		}

		// for ECLIPSE
		String extentPath = "C:\\Users\\dhane\\eclipse-workspace\\DemoBlazeDataDriven14022025\\test-output\\extent-Reports\\";
		// Extent reports- Use ExtentSparkReporter instead of ExtentHtmlReporter
		// to removing duplicationof extentReport.html file
		String reportFilePath = "extent-report-" + System.currentTimeMillis() + ".html"; // or use build number
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(extentPath + reportFilePath);
		// extent = new ExtentReports();
		sparkReporter.config().setTheme(Theme.STANDARD);
		extent.attachReporter(sparkReporter);

		// extent report
		// test.info("Test Suite Started: " + context.getName());
		System.out.println("Test Suite Started: " + context.getName());
	}

	@Override
	public void onFinish(ITestContext context) {

		// extent report - Generate the report at the end of test execution
		extent.flush();

		test.info("Test Suite Finished: " + context.getName());

		// for console
		System.out.println("Test Suite Finished: " + context.getName());
	}
}
