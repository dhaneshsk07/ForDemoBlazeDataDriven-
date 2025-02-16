package nnd.TestCases;

import java.io.IOException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import base.DB_ConnectionSetup;
import nnd.Pages.DB_LoginPage;
import nnd.Utilities.DB_ExcelUtil;

@Listeners(nnd.Listners.DB_MyTestListener.class)
public class DB_LoginTest extends DB_ConnectionSetup {

	private static final Logger logger = LoggerFactory.getLogger(DB_LoginPage.class);
	
	
	

	@DataProvider(name = "validloginData")
	public Object[][] getData() throws IOException {
		DB_ExcelUtil.loadExcel(
				"C:\\Users\\dhane\\eclipse-workspace\\DemoBlazeDataDriven14022025\\src\\test\\java\\nnd\\TestData\\TestData.xlsx",
				"Sheet1");
		int rowCount = DB_ExcelUtil.getRowCount() - 1; // adding -1 to skip header

		Object[][] data = new Object[rowCount][2]; // Assuming username & password columns
		for (int i = 1; i <= rowCount; i++) { // staring form i=1 skipping header
			data[i - 1][0] = DB_ExcelUtil.getData(i, 0); // Username
			data[i - 1][1] = DB_ExcelUtil.getData(i, 1); // Password

		} 
		return data;
	}

	@DataProvider(name = "invalidloginData")
	public Object[][] getinvalidData() throws IOException {
		DB_ExcelUtil.loadExcel(
				"C:\\Users\\dhane\\eclipse-workspace\\DemoBlazeDataDriven14022025\\src\\test\\java\\nnd\\TestData\\TestData.xlsx",
				"Sheet3");
		int rowCount = DB_ExcelUtil.getRowCount() - 1; // adding -1 to skip header

		Object[][] data = new Object[rowCount][2]; // Assuming username & password columns
		for (int i = 1; i <= rowCount; i++) { // staring form i=1 skipping header
			data[i - 1][0] = DB_ExcelUtil.getData(i, 0); // Username
			data[i - 1][1] = DB_ExcelUtil.getData(i, 1); // Password

		} 
		return data;
	}

	@Test(groups = {
			"Positive TestCases" }, dataProvider = "validloginData", description = "LoginTest_TC01 Valid User Login", enabled = true)
	public void validUserLogin(String username, String password)
			throws InvalidFormatException, IOException, InterruptedException {
	
		
		 
		DB_LoginPage lp = new DB_LoginPage(driver);
		
		lp.loginlblClick();
		lp.enterUsername(username);
		lp.enterPassword(password);
		System.out.println("username of " + username);
		System.out.println("password of " + password);
		lp.clickLogin();
		lp.verifyLogin(username); // verify by asseert by welcome label

	}

	@Test(groups = {
			"Negative TestCases" }, dataProvider = "invalidloginData", description = "LoginTest_TC01 inValid User Login", enabled = true)
	public void inValidUserLogin(String username, String password)
			throws InvalidFormatException, IOException, InterruptedException {

		DB_LoginPage lp = new DB_LoginPage(driver);
		lp.loginlblClick();
		lp.enterUsername(username);
		lp.enterPassword(password);
		lp.clickLogin();
		lp.loginAlertHandle(); 

	}

}
