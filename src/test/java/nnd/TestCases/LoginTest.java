package nnd.TestCases;

import java.io.IOException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import base.ConnectionSetup;
import nnd.Pages.LoginPage;

@Listeners(nnd.Listners.MyTestListener.class)
public class LoginTest extends ConnectionSetup {
	
	
	@Test(description="LoginTest_TC01 Valid User Login")
	public void ValidUserLogin() throws InvalidFormatException, IOException {
		
		LoginPage lp =new LoginPage(driver);
		lp.loginUser();
		
		 
	}
	

}
