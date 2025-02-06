package nnd.Pages;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import nnd.Utilities.ExcelReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
 

public class RegisterPage {
	
	private WebDriver driver;
	private WebElement myAccount;
	private WebElement registerBtn;
	private WebElement firstName;
	private WebElement lastName;
	private WebElement email;
	private WebElement password;
	private WebElement privacyAgree;
	private WebElement continueBtnOne;
	private WebElement continueBtnTwo;
	private WebElement myAccountVerifyLabel;
	private WebDriverWait wait;
	
	private static final Logger logger = LoggerFactory.getLogger(RegisterPage.class);
	String dataFirstName,dataLastName,dataEmail,dataPassword;
	
	public RegisterPage(WebDriver driver)  {
	        this.driver = driver; 
	        
	}
	
	public void registerNewUser() throws InterruptedException, InvalidFormatException, IOException { 

	//My account btn click
		
	myAccount=driver.findElement(By.xpath("//span[text()='My Account']"));
	myAccount.click();  

	JavascriptExecutor js = (JavascriptExecutor) driver;
	registerBtn=driver.findElement(By.xpath("//*[contains(@class, 'dropdown-item') and contains(text(), 'Register')]"));
	Thread.sleep(5000);
	js.executeScript("arguments[0].click();",registerBtn);
	
	//Read Registration data from excel 
	readExcelDataRegistration();
	
	//First Name
	
	firstName=driver.findElement(By.xpath("//input[@id='input-firstname']"));
	firstName.sendKeys(dataFirstName);
	logger.info("CREATED FIRST NAME" + dataFirstName);
	//Lastname 
	
	lastName=driver.findElement(By.xpath("//input[@id='input-lastname']"));
	lastName.sendKeys(dataLastName);
	logger.info("CREATED LAST NAME" + dataLastName);

	//Register email
	
	email=driver.findElement(By.xpath("//input[@id='input-email']"));
	email.sendKeys(dataEmail);
	logger.info("CREATED EMAIL" + dataEmail);
	
	//Password
	
	password=driver.findElement(By.xpath("//input[@id='input-password']"));
	password.sendKeys(dataPassword);
	logger.info("CREATED PASSWORD" + dataPassword);

	//Scroll issue
	
	privacyAgree=driver.findElement(By.xpath("//input[@name='agree']"));
	((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);",privacyAgree);
	Thread.sleep(3000); // Small																																						// visibility
	privacyAgree.click();
	
	//Continue btn
	
	continueBtnOne=driver.findElement(By.xpath("//button[contains(text(), 'Continue')]"));continueBtnOne.click();
	Thread.sleep(2000);

	//Continue btn two
	
	wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	List<WebElement> elements = driver.findElements(By.xpath("//a[contains(text(),'Continue')]"));

	if (elements.size() > 0) {
		
		continueBtnTwo = wait.until(ExpectedConditions.elementToBeClickable(elements.get(0)));
	    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", continueBtnTwo);
	    
	} else {
		
	    System.out.println("Continue button not found!");
	} 
	
	//Verify the user with page title
	
	verifytheUserLogin();
	
	}
	 
	
	public void readExcelDataRegistration() throws InvalidFormatException, IOException {
		
		ExcelReader rd =new ExcelReader();
		
		//Fetch Sheet 1 data "TestData.xlsx"
		
        String[] credentials = rd.readExcelDataFoRegistration();
        dataFirstName = credentials[0];  	//FIRST NAME
        dataLastName = credentials[1];		//LAST NAME
        dataEmail = credentials[2];			//EMAIL
        dataPassword = credentials[3];		//PASSWORD
        
	}
	
	public void verifytheUserLogin() {
	
	//To verify the login using label "my account"
		
	myAccountVerifyLabel=driver.findElement(By.xpath("//h2[(text()= 'My Account')]"));

	//Alternate for assert
	
	String actualTitle = myAccountVerifyLabel.getText().toString().toLowerCase(); 
	String expectedTitle = "my account";
	
	Assert.assertEquals(actualTitle, expectedTitle, "Account creation is success and verified ");
	
	//Logger is the alternate for --System.out.print--
	
	logger.info("ACCOUNT CREATED"); 
	}
	
	
	//method create for checking dataprovider merthod testcase02 -07022025
	public void registerNewUserDataProvider(String firstName, String lastName, String email, String password) throws InterruptedException, InvalidFormatException, IOException { 

		//My account btn click
			
		WebElement myAccount1=driver.findElement(By.xpath("//span[text()='My Account']"));
		myAccount1.click();  

		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement registerBtn1=driver.findElement(By.xpath("//*[contains(@class, 'dropdown-item') and contains(text(), 'Register')]"));
		Thread.sleep(5000);
		js.executeScript("arguments[0].click();",registerBtn1);
		
		//Read Registration data from excel 
		readExcelDataRegistration();
		
		//First Name
		
		WebElement firstName1=driver.findElement(By.xpath("//input[@id='input-firstname']"));
		firstName1.sendKeys(firstName);
		logger.info("DATA PROVIDER CREATED FIRST NAME" + firstName);
		//Lastname 
		
		WebElement lastName1=driver.findElement(By.xpath("//input[@id='input-lastname']"));
		lastName1.sendKeys(lastName);
		logger.info("DATA PROVIDER CREATED LAST NAME" + lastName);

		//Register email
		
		WebElement email1=driver.findElement(By.xpath("//input[@id='input-email']"));
		email1.sendKeys(email);
		logger.info("DATA PROVIDER CREATED EMAIL" + email);
		
		//Password
		
		WebElement password1=driver.findElement(By.xpath("//input[@id='input-password']"));
		password1.sendKeys(password);
		logger.info("DATA PROVIDER CREATED PASSWORD" + password);

		//Scroll issue
		
		WebElement privacyAgree1=driver.findElement(By.xpath("//input[@name='agree']"));
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);",privacyAgree1);
		Thread.sleep(3000); // Small																																						// visibility
		privacyAgree1.click();
		
		//Continue btn
		
		WebElement continueBtnOne1=driver.findElement(By.xpath("//button[contains(text(), 'Continue')]"));continueBtnOne1.click();
		Thread.sleep(2000);

		
		
		}
		 
}
