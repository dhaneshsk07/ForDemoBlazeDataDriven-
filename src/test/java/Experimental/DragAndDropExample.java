package Experimental;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import base.ConnectionSetup;

public class DragAndDropExample extends ConnectionSetup{
	
	@Test
    public void draganddrop() {

        try {
            // Step 1: Open a drag-and-drop demo website
            driver.get("https://jqueryui.com/droppable/");
            driver.manage().window().maximize();

            // Step 2: Switch to the iframe containing the draggable element
            driver.switchTo().frame(0); // iframe index (0 is the first iframe)

            // Step 3: Locate the source (draggable) and target (droppable) elements
            WebElement sourceElement = driver.findElement(By.id("draggable"));
            WebElement targetElement = driver.findElement(By.id("droppable"));

            // Step 4: Perform drag-and-drop action using Actions class
            Actions actions = new Actions(driver);
            actions.dragAndDrop(sourceElement, targetElement).perform();

            System.out.println("Drag and Drop Successful!");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            
        }
    }
}
