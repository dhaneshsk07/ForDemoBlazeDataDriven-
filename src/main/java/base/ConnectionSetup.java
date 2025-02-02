package base;

import java.lang.reflect.Method;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.testng.ITestContext;
import org.testng.annotations.Listeners;

//import com.aventstack.extentreports.ExtentReports;
//import com.aventstack.extentreports.ExtentTest;

public class ConnectionSetup {
	protected WebDriver driver;
	WebDriverWait wait;

	@BeforeMethod
	public void setUp(Method method, ITestContext context) {

		// Set up ChromeDriver automatically using WebDriverManager
		WebDriverManager.chromedriver().setup();

		// Initialize the ChromeDriver
		driver = new ChromeDriver();

		driver.manage().window().maximize();

		// for screenshot
		context.setAttribute("driver", driver); // Store the driver in the context

		// Set Implicit Wait for 10 seconds
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

		// explicit wait
		waitForSomeTime();

		driver.get("https://demo.opencart.com");
	}

	@AfterMethod
	public void tearDown() {

		// Adding a delay using WebDriverWait before closing the browser
		waitForSomeTime();

		// Quit the WebDriver after each test
		if (driver != null) {
			 driver.quit();
		}

	}

	// Custom method to simulate a delay
	public void waitForSomeTime() {
		// This condition just waits for an element to be present in the DOM (you can
		// use any trivial condition)
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("html")));
	}

}
