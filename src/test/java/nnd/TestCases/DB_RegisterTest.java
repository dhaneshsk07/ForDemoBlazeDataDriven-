package nnd.TestCases;

import nnd.Pages.DB_RegisterPage;
import nnd.Utilities.DB_ConfigReader;
import nnd.Utilities.DB_ExcelUtil;

import java.io.FileInputStream;
import java.io.IOException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import base.DB_ConnectionSetup;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



@Listeners(nnd.Listners.DB_MyTestListener.class)
public class DB_RegisterTest extends DB_ConnectionSetup{
	
	//Logger implementation 
	private static final Logger logger = LoggerFactory.getLogger(DB_RegisterTest.class);
	DB_ConfigReader config = new DB_ConfigReader();
   //String excelPath = config.getProperty("CONFIG_EXCEL_PATH"); //config.properties file
	
	String excelPath = "C:\\Users\\dhane\\eclipse-workspace\\DemoBlazeDataDriven14022025\\src\\test\\java\\nnd\\TestData\\TestData.xlsx";
	
	//TILL 07-02-2025 DATA PROVIDER CONCEPT NOT USED
	
	@DataProvider(name = "testData") //this for RegisterTest_TC02 
    public Object[][] readExcelData() throws IOException { 
		FileInputStream fs = new FileInputStream(excelPath);
		Workbook workbook = new XSSFWorkbook(fs);
		Sheet sheet = workbook.getSheetAt(0);
        int rows = sheet.getPhysicalNumberOfRows();
        int cols = sheet.getRow(0).getPhysicalNumberOfCells();

        Object[][] data = new Object[rows-1][cols];  // exclude header row 
        for (int i = 1; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                data[i-1][j] = sheet.getRow(i).getCell(j).toString();
            }
        }
        fs.close();
        return data;
    }
	
	//invalid data
	@DataProvider(name = "invalidUserData")
	public Object[][] getinvalidData() throws IOException {
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

	//hard coded here 
	@Test(description="valid user sign Up ",enabled=true)
	public void validUserSignUp() throws InterruptedException {
		
		DB_RegisterPage reg=new DB_RegisterPage(driver);
		reg.registerlblClick();
		
		String username="Dhan Spec 011";
		String password="dhan#03011";
		
		reg.enterUsername(username); 
		reg.enterPassword(password);
		
		reg.clickSignUp();   
		
		
	}
	
	
	@Test(description="invalid user sign Up ",dataProvider="invalidUserData",enabled=true)
	public void invalidUserSignUp(String username,String password) throws InterruptedException {
		
		DB_RegisterPage reg=new DB_RegisterPage(driver);
		reg.registerlblClick();
		reg.enterUsername(username); 
		reg.enterPassword(password);
		reg.verifyInvalidSignUp();
		
	}
}