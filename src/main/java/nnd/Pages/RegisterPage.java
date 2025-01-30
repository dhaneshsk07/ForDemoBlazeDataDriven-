package nnd.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RegisterPage {
	 WebDriver driver;
	
	WebElement myAccount;
	WebElement registerBtn;
	WebElement firstName;
	WebElement lastName;
	WebElement email;
	WebElement password;
	WebElement privacyAgree;
	WebElement continueBtn;
	WebElement continueBtn2;
	WebElement myAccountVerifyLabel;

	public RegisterPage(WebDriver driver)  {
	        this.driver = driver;
	        
	}
	
	public void RegisterNewUser() throws InterruptedException {

	// My account btn click
	myAccount=driver.findElement(By.xpath("//span[text()='My Account']"));

	myAccount.click();

	JavascriptExecutor js = (JavascriptExecutor) driver;
	registerBtn=driver.findElement(By.xpath("/html/body/nav/div/div[2]/ul/li[2]/div/ul/li[1]/a"));
	js.executeScript("arguments[0].click();",registerBtn);

	// First Name
	firstName=driver.findElement(By.xpath("//input[@id='input-firstname']"));firstName.sendKeys("Dhan");

	// Lastname
	lastName=driver.findElement(By.xpath("//input[@id='input-lastname']"));lastName.sendKeys("One");

	// Register email
	email=driver.findElement(By.xpath("//input[@id='input-email']"));email.sendKeys("dhan6000@gmail.com");

	// password
	password=driver.findElement(By.xpath("//input[@id='input-password']"));password.sendKeys("Dhan@6000");



	// scroll issue
	privacyAgree=driver.findElement(By.xpath("//input[@name='agree']"));
	((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);",privacyAgree);
	Thread.sleep(500); // Small																																						// visibility
	privacyAgree.click();

	// continue btn
	continueBtn=driver.findElement(By.xpath("//button[contains(text(), 'Continue')]"));continueBtn.click();

	// next page continue on userc created page
	continueBtn2=driver.findElement(By.xpath("//a[contains(text(), 'Continue')]"));continueBtn2.click();

	// to verify the login using label "my account"
	myAccountVerifyLabel=driver.findElement(By.xpath("//h2[(text()= 'My Account')]"));

	String verify = myAccountVerifyLabel.getText().toString().toLowerCase();
	String expected = "my account";

	// Assert.assertEquals("my account", verify, "Account creation is success and
	// verified ");

	if(verify.equals(expected))
	{
		System.out.println("The Account creation verified successfully");
	}else
	{
		System.out.println("The Account creation failed");
	}

	driver.quit();
}

}
