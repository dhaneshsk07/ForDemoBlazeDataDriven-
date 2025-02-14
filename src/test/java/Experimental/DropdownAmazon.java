package Experimental;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import base.DB_ConnectionSetup;

public class DropdownAmazon extends DB_ConnectionSetup {

	@Test
	public void dd() {

		try {
			// Open Amazon
			driver.get("https://www.amazon.in/");
			driver.manage().window().maximize();

			// Locate the dropdown (Category Selection)
			WebElement categoryDropdown = driver.findElement(By.id("searchDropdownBox"));

			// Create Select class object
			Select selectCategory = new Select(categoryDropdown);

			// Select "Books" category using different methods
			selectCategory.selectByVisibleText("Books"); // Select by text
			// selectCategory.selectByValue("search-alias=stripbooks"); // Select by value
			// selectCategory.selectByIndex(5); // Select by index (5th option)

			// Print the selected category
			System.out.println("Selected Category: " + selectCategory.getFirstSelectedOption().getText());

		} finally {

		}
	}
}
