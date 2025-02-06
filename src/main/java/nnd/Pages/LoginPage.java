package nnd.Pages;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import nnd.Utilities.ExcelReader;

public class LoginPage {
	WebDriver driver;
	private WebElement homePageLogin;
	private WebElement usrname;
	private WebElement pwd;
	private WebElement loginBtn;
	private String username,password;
	private String expectedUrl,actualUrl;
	
	private static final Logger logger = LoggerFactory.getLogger(LoginPage.class);

	// Constructor
	public LoginPage(WebDriver driver) {
		this.driver = driver;

	}

	public void loginUser() throws InvalidFormatException, IOException {

		//Login dropdown 
		JavascriptExecutor js = (JavascriptExecutor) driver;
		homePageLogin = driver.findElement(By.xpath("//a[@class='dropdown-item' and text()='Login']"));
		js.executeScript("arguments[0].click();",homePageLogin);
 
		//Reade Data from excel
		readExcelDataUsernamePassword();

        //Email
		usrname = driver.findElement(By.xpath("//input[@name='email']"));
		usrname.sendKeys(username);   //username.sendKeys("dhan6000@gmail.com"); 
		
		//Password
		pwd = driver.findElement(By.xpath("//input[@name='password']"));
		pwd.sendKeys(password);       //password.sendKeys("Dhan@6000");
		
		//Login button
		loginBtn = driver.findElement(By.xpath("//button[text()='Login']"));
		loginBtn.click();
		
		//Verify URL -assertion
		verifyUrl();
        	
	}
	
	public void readExcelDataUsernamePassword() throws InvalidFormatException, IOException {
		
		ExcelReader rd =new ExcelReader();
		
		//Fetch username and password from Excel "TestData.xlsx"
		
        String[] credentials = rd.readExcelDataForLogin();
        username = credentials[0];
        password = credentials[1];
	}
	
	public void verifyUrl() {
		
		//Assert the URL to confirm successful login
        expectedUrl = "https://demo.opencart.com/en-gb?route=account/login"; // Replace with the actual URL
        actualUrl = driver.getCurrentUrl();
        
        //System.out.println("current url is : " + actualUrl);
        logger.info("current url is : " + actualUrl);
        
       
        Assert.assertEquals(actualUrl, expectedUrl, "Login Success. URL matched.");
		
	}
	
	
	
	
}

