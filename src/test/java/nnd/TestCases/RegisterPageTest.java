package nnd.TestCases;

import io.github.bonigarcia.wdm.WebDriverManager;
import nnd.Pages.RegisterPage;

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
import org.testng.Assert;
import org.testng.annotations.Test;

import base.ConnectionSetup;

public class RegisterPageTest extends ConnectionSetup{
	 //WebDriver driver;

	@Test
	public void test1() throws InterruptedException {
		
		RegisterPage rp=new RegisterPage(driver);
		rp.RegisterNewUser();
		
	}
}