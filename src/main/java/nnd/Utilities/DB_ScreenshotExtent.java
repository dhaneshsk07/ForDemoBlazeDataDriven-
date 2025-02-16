package nnd.Utilities;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;


import org.testng.ITestResult;
import org.testng.asserts.SoftAssert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class DB_ScreenshotExtent {
    static WebDriver driver;

    public DB_ScreenshotExtent(WebDriver driver) {
        this.driver = driver;
    }

    // Return screenshot file path
    public String takeScreenshot(ITestResult result) throws IOException, InterruptedException {
    	
    
    	
    	/*
        // Capture screenshot
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);

        // Set screenshot name with timestamp
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String screenshotName = "Screenshot_" + result.getName() + "_" + timestamp + ".png";
        String screenshotPath = "C:\\Users\\dhane\\eclipse-workspace\\DemoBlazeDataDriven14022025\\Failed Screenshots\\" + screenshotName;

        // Save the screenshot
        File destination = new File(screenshotPath);
        FileHandler.copy(source, destination);
        makeWritable(destination);
        
        System.out.println("Screenshot saved at: " + screenshotPath);
        return screenshotPath; // Return path to listener
        
        */
        
        
        
        //------------FOR FULL SCREEN SCREEN SHOT WINDOW CAPTURE
    	String screenshotPath = null;
        try {
        	
        	 // Handle Alert if present
            try {
                Alert alert = driver.switchTo().alert();
                System.out.println("Alert displayed: " + alert.getText());
                Thread.sleep(1000); // Allow brief delay for alert rendering
            } catch (Exception e) {
                System.out.println("No alert present.");
            }
        	
            
            // Bring WebDriver window to front (useful if alert is native)
            ((JavascriptExecutor) driver).executeScript("window.focus();");
            
            Thread.sleep(1000); // Additional delay before capturing
            
            // Capture Full-Screen
            BufferedImage fullScreenImage = new Robot().createScreenCapture(
                    new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
            
        

            // Set screenshot name with timestamp
            String timestampFS = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String screenshotNameFS = "FullScreen_" + result.getName() + "_" + timestampFS + ".png";
            screenshotPath = "C:\\Users\\dhane\\eclipse-workspace\\DemoBlazeDataDriven14022025\\Failed Screenshots\\" + screenshotNameFS;

            // Save the screenshot
            File destinationFS = new File(screenshotPath);
            ImageIO.write(fullScreenImage, "png", destinationFS);
            
            System.out.println("Full-Screen Screenshot saved at: " + screenshotPath);

        } catch (AWTException | IOException e) {
            System.out.println("Failed to capture full-screen screenshot: " + e.getMessage());
        }
        return screenshotPath;
    }

    public void makeWritable(File destination) {
        if (destination.exists()) {
            destination.setWritable(true);
        } else {
            System.out.println("File or directory not found.");
        }
        
       
        
    }
}
