package nnd.TestCases;

import java.io.IOException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import base.DB_ConnectionSetup;
import nnd.Pages.LoginPage;
import nnd.Utilities.ExcelUtil;

@Listeners(nnd.Listners.MyTestListener.class)
public class LoginTest extends DB_ConnectionSetup {
	
	@DataProvider(name = "validloginData")
    public Object[][] getData() throws IOException {
        ExcelUtil.loadExcel("C:\\Users\\dhane\\eclipse-workspace\\DemoBlazeDataDriven14022025\\src\\test\\java\\nnd\\TestData\\TestData.xlsx","Sheet1");
        int rowCount = ExcelUtil.getRowCount();
        
        Object[][] data = new Object[rowCount][2]; // Assuming username & password columns
        for (int i = 0; i < rowCount; i++) {
            data[i][0] = ExcelUtil.getData(i, 0); // Username
            data[i][1] = ExcelUtil.getData(i, 1); // Password
        }
        return data;
	}
	
	@DataProvider(name = "invalidloginData")
    public Object[][] getinvalidData() throws IOException {
        ExcelUtil.loadExcel("C:\\Users\\dhane\\eclipse-workspace\\DemoBlazeDataDriven14022025\\src\\test\\java\\nnd\\TestData\\TestData.xlsx","Sheet2");
        int rowCount = ExcelUtil.getRowCount();
        
        Object[][] data = new Object[rowCount][2]; // Assuming username & password columns
        for (int i = 0; i < rowCount; i++) {
            data[i][0] = ExcelUtil.getData(i, 0); // Username
            data[i][1] = ExcelUtil.getData(i, 1); // Password
        }
        return data;
	}
	
	
	@Test(dataProvider = "validloginData",description="LoginTest_TC01 Valid User Login")
	public void validUserLogin(String username,String password) throws InvalidFormatException, IOException, InterruptedException {
		
		LoginPage lp =new LoginPage(driver);
		lp.loginlblClick();
		lp.enterUsername(username);
		lp.enterPassword(password);
		lp.clickLogin(); 
		 
	}
	
	
	@Test(dataProvider = "invalidloginData",description="LoginTest_TC01 Valid User Login")
	public void inValidUserLogin(String username,String password) throws InvalidFormatException, IOException, InterruptedException {
		
		LoginPage lp =new LoginPage(driver);
		lp.loginlblClick();
		lp.enterUsername(username);
		lp.enterPassword(password);
		lp.clickLogin(); 
		 
	}
	

}
