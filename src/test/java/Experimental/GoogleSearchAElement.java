package Experimental;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import java.time.Duration;

public class GoogleSearchAElement {
    public static void main(String[] args) throws InterruptedException {
        //System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.google.com");

        // Create WebDriverWait instance
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // Locate the search box and enter text
        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys("Selenium WebDriver");
        
        // Wait for the suggestions to appear
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//ul[@role='listbox']/li")));

        // Retrieve the list of suggestions
        List<WebElement> suggestions = driver.findElements(By.xpath("//ul[@role='listbox']/li"));
        
        // Click on the second suggestion
        if (suggestions.size() >= 2) {
            suggestions.get(1).click();
        } else {
            System.out.println("Less than two suggestions available.");
        }

        // Ensure the search box is interactable and bring it into view (if necessary)
        searchBox = wait.until(ExpectedConditions.elementToBeClickable(By.name("q")));

        // Scroll into view if necessary
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", searchBox);

        // Press ENTER to search instead of clicking the button
        searchBox.sendKeys(Keys.ENTER);

        driver.quit(); 
        
    }
}
