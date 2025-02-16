package base;

import java.lang.reflect.Method;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import io.github.bonigarcia.wdm.WebDriverManager;
import nnd.Utilities.DB_ConfigReader;

import org.testng.Assert;
import org.testng.ITestContext;


//import com.aventstack.extentreports.ExtentReports;
//import com.aventstack.extentreports.ExtentTest;

public class DB_ConnectionSetup {
	protected WebDriver driver;
	WebDriverWait wait;

	@BeforeMethod
	public void setUp(Method method, ITestContext context) {

		 Logger root = (Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
	        root.setLevel(Level.ERROR); // Only show errors on console
		
		
		DB_ConfigReader config = new DB_ConfigReader();
        String browser = config.getProperty("browser");

        
        // browser setup using "switch- case" loop
        switch (browser.toLowerCase()) {
        case "chrome":
            //WebDriverManager.chromedriver().setup();
            WebDriverManager.chromedriver().clearResolutionCache().setup(); //checkin for solution for connection reset --soln1
            //driver = new ChromeDriver();
            
            // Add ChromeOptions for CDP and debugging
         // ✅ Correct ChromeOptions implementation
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--remote-allow-origins=*");
            options.addArguments("--disable-blink-features=AutomationControlled");
            options.addArguments("--disable-popup-blocking");
            options.setExperimentalOption("useAutomationExtension", false);
            options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
            options.setCapability("se:cdpVersion", "132"); // Force CDP version
            
            // ⚠️ Important: Use the constructor properly
            try {
                driver = new ChromeDriver(options);
            } catch (Exception e) {
                throw new RuntimeException("ChromeDriver initialization failed: " + e.getMessage());
            }
            break;
            
        case "firefox":
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
            break;
        case "edge":
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver(); 
            break;
        default:
            throw new IllegalArgumentException("Invalid browser: " + browser);
		
        }

		// Initialize the ChromeDriver
		//driver = new ChromeDriver();

		driver.manage().window().maximize();

		// for screenshot
		context.setAttribute("driver", driver); // Store the driver in the context

		// Set Implicit Wait for 10 seconds
		//driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		
		//checkin for solution for connection reset --soln2 
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		// explicit wait
		waitForSomeTime();

		
		//ADD THIS BELOW TO RUN OPENCART PROJECT
		//driver.get("https://www.demoblaze.com");
		
		//-------------CODE TO CHECK IF THE WEBSITE IS DOWN------------------
		try {
			driver.get("https://www.demoblaze.com");   // web site url 
		    String pageTitle = driver.getTitle();
		    
		    if (pageTitle.contains("Error") || pageTitle.contains("404") || pageTitle.isEmpty()) {
		        System.out.println("Website is down: " + pageTitle);
		        Assert.fail("Website is down or error page displayed.");
		    } else {
		        System.out.println("Website loaded successfully: " + pageTitle);
		    }
		    
		} catch (TimeoutException e) {
		    System.out.println("Page load timed out.");
		    Assert.fail("Website is not responding within the timeout.");
		} catch (WebDriverException e) {
		    System.out.println("WebDriver failed to load the page: " + e.getMessage());
		    Assert.fail("Website is unreachable.");
		}
 //------------------------------END-----------------------------
		
	}
	

	

	@AfterMethod
	public void tearDown() {

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
