package base;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ConnectionSetup {
	protected WebDriver driver;
	WebDriverWait wait;
	
	@BeforeMethod
	public void setUp() {
	// Set up ChromeDriver automatically using WebDriverManager
			WebDriverManager.chromedriver().setup();

			// Initialize the ChromeDriver
			 driver = new ChromeDriver();

			driver.manage().window().maximize();

			// Set Implicit Wait for 10 seconds
			driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
			
			// Create WebDriverWait object with a timeout of 10 seconds
			//wait = new WebDriverWait(driver, Duration.ofSeconds(20));

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
        // This condition just waits for an element to be present in the DOM (you can use any trivial condition)
        wait = new WebDriverWait(driver,Duration.ofSeconds(20));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("html")));
    }

}
