package nnd.Pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
 

public class DB_RegisterPage {
	
	private WebDriver driver;
	
	private WebElement registerlbl;
	private WebElement usernameField;
	private WebElement passwordField;
	private WebElement signupBtn;
	private WebElement signupClosebtn;
	
	private static final Logger logger = LoggerFactory.getLogger(DB_RegisterPage.class);
	
	
	public DB_RegisterPage(WebDriver driver)  {
	        this.driver = driver; 
	        
	}
	
	//signup to register
	public void registerlblClick(){
	 registerlbl =driver.findElement(By.xpath("//a[text()='Sign up']"));
	 registerlbl.click();
	}
	
	//signup username
	public void enterUsername(String username){
		
		usernameField =driver.findElement(By.xpath("//input[@id='sign-username']"));
		usernameField.sendKeys(username);
	}
	
	//signup password
	public void enterPassword(String password){
		passwordField = driver.findElement(By.xpath("//input[@id='sign-password']"));
		passwordField.sendKeys(password);
	}
	
	//signup signup button
	public void clickSignUp() throws InterruptedException{
		
		signupBtn =driver.findElement(By.xpath("//button[text()='Sign up']"));
		
		signupBtn.click();
		Thread.sleep(10000);
		
		Alert alert = driver.switchTo().alert();
		System.out.println("Alert Text: " + alert.getText());
		String actual=alert.getText();
		alert.accept();
		
		String expected ="Sign up successful." ;
		
		Assert.assertEquals(actual,expected ,"Signup failed");
		
		//CLOSE BTN---------------------
		//signupClosebtn =driver.findElement(By.xpath("(//button[text()='Close'])[2]"));
		
		//signupClosebtn.click();
	}
	
	
	
		 
}
