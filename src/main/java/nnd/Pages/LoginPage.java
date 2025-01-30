package nnd.Pages;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import nnd.Utilities.ExcelReader;

public class LoginPage {
	WebDriver driver;

	private WebElement homePageLogin;
	private WebElement usrname;
	private WebElement pwd;
	private WebElement loginBtn;
	

	// Constructor
	public LoginPage(WebDriver driver) {
		this.driver = driver;

	}

	public void loginUser() throws InvalidFormatException, IOException {

		//login
		JavascriptExecutor js = (JavascriptExecutor) driver;
		homePageLogin = driver.findElement(By.xpath("//a[@class='dropdown-item' and text()='Login']"));
		js.executeScript("arguments[0].click();",homePageLogin);

		ExcelReader rd =new ExcelReader();
		// Fetch username and password from Excel
        String[] credentials = rd.readeExcel();
        String username = credentials[0];
        String password = credentials[1];
		
        //email
		usrname = driver.findElement(By.xpath("//input[@name='email']"));
		usrname.sendKeys(username);   //username.sendKeys("dhan6000@gmail.com"); 
		
		//password
		pwd = driver.findElement(By.xpath("//input[@name='password']"));
		pwd.sendKeys(password);       //password.sendKeys("Dhan@6000");
		
		//login button
		loginBtn = driver.findElement(By.xpath("//button[text()='Login']"));
		loginBtn.click();
		
		 // Assert the URL to confirm successful login
        String expectedUrl = "https://demo.opencart.com/en-gb?route=account/login"; // Replace with the actual URL
        String actualUrl = driver.getCurrentUrl();
        System.out.println("current url is : " + actualUrl);
        Assert.assertEquals(actualUrl, expectedUrl, "Login Success. URL matched.");
        	
	}
	
	
}

