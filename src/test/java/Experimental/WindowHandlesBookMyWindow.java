package Experimental;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import base.ConnectionSetup;

import java.util.Set;

public class WindowHandlesBookMyWindow extends ConnectionSetup{
	
	@Test
    public void windowhandle() {
     

        try {
        	 // BookMyShow വെബ്സൈറ്റ് തുറക്കുന്നു
            driver.get("https://in.bookmyshow.com/");

            // Parent Window Handle
            String parentWindow = driver.getWindowHandle();
            System.out.println("Parent Window Handle: " + parentWindow);

            // "Movies" എന്ന ബട്ടൺ ക്ലിക്ക് ചെയ്യുക (പുതിയ വിൻഡോ തുറക്കും)
            WebElement movieLink = driver.findElement(By.xpath("//a[contains(text(),'Movies')]"));
            movieLink.click();

            // എല്ലാ വിൻഡോ Handles നേടുക
            Set<String> allWindows = driver.getWindowHandles();

            // Parent Window വിടാതെ, New Window Handle ചെയ്തു മാറ്റുക
            for (String window : allWindows) {
                if (!window.equals(parentWindow)) {
                    driver.switchTo().window(window);
                    System.out.println("Switched to new window: " + driver.getTitle());
                    
                    // Ticket Details എടുക്കുന്നു
                    WebElement ticketDetails = driver.findElement(By.id("ticket-info"));
                    System.out.println("Ticket Details: " + ticketDetails.getText());

                    // പുതിയ വിൻഡോ ക്ലോസ് ചെയ്യുന്നു
                    driver.close();
                }
            }  

            // Parent Window ലേക്ക് തിരികെ പോകുന്നു
            driver.switchTo().window(parentWindow);
            System.out.println("Switched back to Main Page: " + driver.getTitle());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            
        }
    }
}
