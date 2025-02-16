package nnd.Pages;



import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;


public class DB_LoginPage {
	WebDriver driver;
	private WebElement loginlbl;
	private WebElement usrname;
	private WebElement pwd;
	private WebElement loginBtn;
	private WebElement loginClosebtn;

	private ExtentReports extent;
	private ExtentTest test;

	private static final Logger logger = LoggerFactory.getLogger(DB_LoginPage.class);

	// Locators
	// private By usernameField = By.id("username");
	// private By passwordField = By.id("password");
	// private By loginButton = By.id("loginBtn");

	// Constructor
	public DB_LoginPage(WebDriver driver) { 
		this.driver = driver;

	}

	// signup to register
	public void loginlblClick() {
		loginlbl = driver.findElement(By.xpath("//a[text()='Log in']"));
		loginlbl.click();
	}

	public void enterUsername(String username) {

		// Username
		usrname = driver.findElement(By.xpath("//input[@id='loginusername']"));

		usrname.sendKeys(username);

		// WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		// usrname =
		// wait.until(ExpectedConditions.elementToBeClickable(By.id("loginusername")));
		// usrname.click();

	}

	public void enterPassword(String password) {

		// Password
		pwd = driver.findElement(By.xpath("//input[@id='loginpassword']"));
		pwd.sendKeys(password);
	}

	public void clickLogin() throws InterruptedException {

		// Login button
		loginBtn = driver.findElement(By.xpath("//button[text()='Log in']"));
		loginBtn.click();
		Thread.sleep(5000);
		
		

	}
	
	public void verifyLogin(String username) {
		
		WebElement verifyElement =driver.findElement(By.xpath("//a[@id='nameofuser']"));
		String actual =verifyElement.getText();
		
		String expected ="Welcome " + username ;
		
		Assert.assertEquals(actual,expected ,"Login failed");
		
	}
	
	public void loginCloseBtn() {
		
		loginClosebtn = driver.findElement(By.xpath("(//button[text()='Close'])[2]"));

		loginClosebtn.click();
		
	}
	
	public void loginAlertHandle() {
		
		try {
		    Alert alert = driver.switchTo().alert();
		    String actual = alert.getText();
		    System.out.println("Alert Text: " + actual);
		    Thread.sleep(3000);
		    
		    // Accept the alert
		    alert.accept();
		    
		    // Assert the alert message
		    String expected = "User does not exist.";
		    Assert.assertEquals(actual, expected, "Log in failed");
		    
		} catch (NoAlertPresentException e) {
		    System.out.println("No alert was present.");
		    Assert.fail("Test failed due to missing alert.");
		} catch (UnhandledAlertException e) {
		    System.out.println("Unhandled alert detected.");
		    Assert.fail("Test failed due to an unhandled alert.");
		} catch (Exception e) {
		    System.out.println("An unexpected error occurred: " + e.getMessage());
		    Assert.fail("Test failed due to an unexpected error.");
		}

		
	}
	
	
	

}
