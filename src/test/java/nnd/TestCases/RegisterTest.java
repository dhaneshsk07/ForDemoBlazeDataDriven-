package nnd.TestCases;

import nnd.Pages.RegisterPage;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import base.ConnectionSetup;


import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



@Listeners(nnd.Listners.MyTestListener.class)
public class RegisterTest extends ConnectionSetup{
	private static final Logger logger = LoggerFactory.getLogger(RegisterTest.class);
	String excelPath = "C:\\Users\\dhane\\eclipse-workspace\\OpenCart26012025\\src\\test\\java\\nnd\\TestData\\TestData.xlsx";
	
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

	@Test(description="RegisterTest_TC01_Valid User Registration ",enabled=true)
	public void validUserRegistration() throws InterruptedException, InvalidFormatException, IOException {
		
		RegisterPage rp=new RegisterPage(driver);
		rp.	registerNewUser(); 
		
	}
	
	// Need to work on this 07022025
	@Test(description="RegisterTest_TC02_Valid User Registration with data provider ",dataProvider="testData",enabled=false)
	public void newUserRegistrationWithDatProvider(String firstName, String lastName, String email, String password) throws InterruptedException, InvalidFormatException, IOException {
		
		RegisterPage rp=new RegisterPage(driver);
		rp.registerNewUserDataProvider(firstName, lastName, email, password);
	 
		
	}
}