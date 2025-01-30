package nnd.TestProject;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
import org.apache.hc.core5.util.Asserts;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import base.ConnectionSetup;

public class OriginalCode {
	


	@Test
	public void test1() throws InterruptedException {
		
		
		
		
		
		// Set up ChromeDriver automatically using WebDriverManager
		WebDriverManager.chromedriver().setup();

		// Initialize the ChromeDriver
		WebDriver driver = new ChromeDriver();

		driver.manage().window().maximize();

		// Set Implicit Wait for 10 seconds
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		// Create WebDriverWait object with a timeout of 10 seconds
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

		driver.get("https://demo.opencart.com");
		System.out.println("Title: " + driver.getTitle());
		
		
		
		

		// My account btn click
		WebElement myAccount = driver.findElement(By.xpath("//span[text()='My Account']"));
		
		myAccount.click();

		

		// Register btn
		// WebElement register = driver.findElement(By.xpath("//a[contains(text(),
		// 'Register') and contains(@class,'dropdown-item')]"));

		
		//====CHECK THIS CODE BELOW
		// WebElement register =
		// driver.findElement(By.xpath("/html/body/nav/div/div[2]/ul/li[2]/div/ul/li[1]/a"));
		// String str =register.getText().toString();

		// System.out.println("THE UNEXPECTED ==>>>>>*****" + str);
		// ---------------------------------------------------------------------

		JavascriptExecutor js = (JavascriptExecutor) driver;
		Thread.sleep(15000);
		WebElement register = driver.findElement(By.xpath("/html/body/nav/div/div[2]/ul/li[2]/div/ul/li[1]/a"));
		js.executeScript("arguments[0].click();", register);

		

		// driver.navigate().to("https://demo.opencart.com/en-gb?route=account/register");
		// First Name
		WebElement firstName = driver.findElement(By.xpath("//input[@id='input-firstname']"));
		firstName.sendKeys("Dhan");

		// Lastname
		WebElement lastName = driver.findElement(By.xpath("//input[@id='input-lastname']"));
		lastName.sendKeys("One");

		// Register email
		WebElement email = driver.findElement(By.xpath("//input[@id='input-email']"));
		email.sendKeys("dhan6000@gmail.com");

		// password
		WebElement password = driver.findElement(By.xpath("//input[@id='input-password']"));
		password.sendKeys("Dhan@6000");

		// ------------------------------TO HERE--RUN
		// SUCCESSFULLY-------------------------

		// privacy policy
		/*WebElement privacyAgree = driver.findElement(By.xpath("//input[@name='agree']"));
		privacyAgree.click();
		*/
		
		
		
//		WebElement privacyAgree = driver.findElement(By.id("/html/body/main/div[2]/div/div/form/div/div/input"));
//		js.executeScript("arguments[0].click();", privacyAgree);
		
		//scroll issue
		WebElement element = driver.findElement(By.xpath("//input[@name='agree']"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		Thread.sleep(500); // Small delay to ensure visibility
		element.click();

		// continue btn
		WebElement continueBtn = driver.findElement(By.xpath("//button[contains(text(), 'Continue')]"));
		continueBtn.click();

		// next page continue on userc created page
		WebElement continueBtn2 = driver.findElement(By.xpath("//a[contains(text(), 'Continue')]"));
		continueBtn2.click();

		// to verify the login using label "my account"
		WebElement myAccountVerifyLabel = driver.findElement(By.xpath("//h2[(text()= 'My Account')]"));

		String verify = myAccountVerifyLabel.getText().toString().toLowerCase();
		String expected = "my account";

		// Assert.assertEquals("my account", verify, "Account creation is success and
		// verified ");

		if (verify.equals(expected)) {
			System.out.println("The Account creation verified successfully");
		} else {
			System.out.println("The Account creation failed");
		}

		driver.quit();

	}
}