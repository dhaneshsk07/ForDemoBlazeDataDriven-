package nnd.TestCases;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.annotations.Test;

import base.ConnectionSetup;
import nnd.Pages.LoginPage;
import nnd.Utilities.ExcelReader;

public class LoginTest extends ConnectionSetup {
	
	
	@Test(description="test01")
	public void ValidUserLogin() throws InvalidFormatException, IOException {
	
		LoginPage lp =new LoginPage(driver);
		lp.loginUser();
		
		
	}

}
