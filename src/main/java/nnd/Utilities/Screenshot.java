package nnd.Utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestResult;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;


public class Screenshot  {
	static WebDriver driver;
	
	public Screenshot(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver=driver;
	}

	public void takeScreenshot(ITestResult result) throws IOException {
	 // Capture screenshot
    TakesScreenshot ts = (TakesScreenshot) driver; 
    File source = ts.getScreenshotAs(OutputType.FILE);
    
    // Set screenshot name with timestamp
    String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
    String screenshotName = "Screenshot_" + result.getName() + "_" + timestamp + ".png";
    File destination = new File("C:\\Users\\dhane\\eclipse-workspace\\OpenCart26012025\\Failed Screenshots\\" + screenshotName);
    
    // Save the screenshot
    FileHandler.copy(source, destination); 
    
    FileHandler.makeWritable(destination);




}
	
	 public void makeWritable(File destination) {
	        if (destination.exists()) {
	            destination.setWritable(true);  // Writable permission set ചെയ്യുന്നു
	        } else {
	            System.out.println("ഫയൽ അല്ലെങ്കിൽ ഡയറക്ടറി ഇല്ല.");
	        }
	    }

}


