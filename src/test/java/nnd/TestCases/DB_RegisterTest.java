package nnd.TestCases;

import nnd.Pages.DB_RegisterPage;
import nnd.Utilities.DB_ConfigReader;
import nnd.Utilities.DB_ExcelUtil;
import java.io.IOException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import base.DB_ConnectionSetup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Listeners(nnd.Listners.DB_MyTestListener.class)
public class DB_RegisterTest extends DB_ConnectionSetup {
	
	// Logger implementation
	private static final Logger logger = LoggerFactory.getLogger(DB_RegisterTest.class);

	String excelPath = "C:\\Users\\dhane\\eclipse-workspace\\DemoBlazeDataDriven14022025\\src\\test\\java\\nnd\\TestData\\TestData.xlsx";


	// invalid data
	@DataProvider(name = "invalidUserData")
	public Object[][] getinvalidData() throws IOException {

		DB_ConfigReader cr = new DB_ConfigReader(); // class
		String excelpath = cr.getProperty("CONFIG_EXCEL_PATH"); // same as below path -PATHCODE001

		DB_ExcelUtil.loadExcel(excelpath, "Sheet1"); 		// class
		
		int rowCount = DB_ExcelUtil.getRowCount() - 1; 		// adding -1 to skip header

		Object[][] data = new Object[rowCount][2]; 			// Assuming username & password columns
		for (int i = 1; i <= rowCount; i++) { 				// staring form i=1 skipping header
			data[i - 1][0] = DB_ExcelUtil.getData(i, 0); 	// Username
			data[i - 1][1] = DB_ExcelUtil.getData(i, 1); 	// Password

		}
		return data;
	}

	//TEST CASES
	
	@Test(description = "RegisterTest_TC01 valid user sign Up ", enabled = true)
	public void validUserSignUp() throws InterruptedException {

		
		String username = "Dhan Spec 020518022025";       
		// hard coded here
		
		String password = "dhan#1234"; // hard coded here   
		
		logger.info("Started Test of valid user sign-up with username: {}", username);
	
		DB_RegisterPage reg = new DB_RegisterPage(driver);
		reg.registerlblClick();
		reg.enterUsername(username);
		reg.enterPassword(password);
		reg.clickSignUp();
		
		logger.info("Completed valid user sign-up with username: {}", username); 

	}

	@Test(description = "RegisterTest_TC02 invalid user sign Up ", dataProvider = "invalidUserData", enabled = true)
	public void invalidUserSignUp(String username, String password) throws InterruptedException {

		logger.info("Started Test of valid user sign-up with username: {}", username);
		
		DB_RegisterPage reg = new DB_RegisterPage(driver);
		reg.registerlblClick();
		reg.enterUsername(username);
		reg.enterPassword(password);
		reg.verifyInvalidSignUp();
		
		logger.info("Completed Test of valid user sign-up with username: {}", username);

	}
}