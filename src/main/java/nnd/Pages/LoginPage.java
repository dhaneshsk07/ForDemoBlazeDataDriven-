package nnd.Pages;

import java.io.IOException;
import java.time.Duration;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import nnd.Utilities.ExcelReader;

public class LoginPage {
	WebDriver driver;
	private WebElement loginlbl;
	private WebElement usrname;
	private WebElement pwd;
	private WebElement loginBtn;
	private WebElement loginClosebtn;

	private String expectedUrl, actualUrl;

	private static final Logger logger = LoggerFactory.getLogger(LoginPage.class);

	// Locators
	// private By usernameField = By.id("username");
	// private By passwordField = By.id("password");
	// private By loginButton = By.id("loginBtn");

	// Constructor
	public LoginPage(WebDriver driver) {
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
		// Switch to the alert popup
		Alert alert = driver.switchTo().alert();

		// Get alert text
		System.out.println("Popup Message: " + alert.getText());

		// Accept (click OK)
		alert.accept();

		loginClosebtn = driver.findElement(By.xpath("(//button[text()='Close'])[2]"));

		//loginClosebtn.click();

	}

}
