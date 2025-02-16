package nnd.Pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.aventstack.extentreports.Status;

public class DB_HomePage {
	WebDriver driver;
	private WebElement categorySelect ;
	private WebElement itemSelect ;
	private WebElement addToCartElement ;
	
	
	// Constructor
	public DB_HomePage(WebDriver driver) { 
		this.driver = driver;

	}
	
	public void selectCatogory() {
		categorySelect =driver.findElement(By.xpath("//a[text()='Phones']"));
		categorySelect.click();
	}
	
	public void selectItem() {
	itemSelect =driver.findElement(By.xpath("//a[text()='Samsung galaxy s6']"));
	itemSelect.click();
	}
	
	public void addToCart() throws InterruptedException {
		addToCartElement =driver.findElement(By.xpath("//a[text()='Add to cart']"));
		addToCartElement.click();
		Thread.sleep(5000);
	}
	
	public void verifyElementAddToCart() {
		
		/*
		Alert alert =driver.switchTo().alert();
		String actual=alert.getText();
		System.out.println("Cart alert :" + actual);
		String expected="Product added.";
		
		alert.accept();
		
		Assert.assertEquals(actual,expected ,"Fail to Add :  No Item added");
		*/
	
	
	
	
	try {
	    Alert alert = driver.switchTo().alert();
	    String actual = alert.getText();
	    System.out.println("Alert Text: " + actual);
	    
	    // Accept the alert
	    alert.accept();
	    
	    // Assert the alert message
	    String expected="Product added";
	    Assert.assertEquals(actual,expected ,"Fail to Add :  No Item added");
	    
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
