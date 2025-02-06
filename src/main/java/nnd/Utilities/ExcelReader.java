package nnd.Utilities;

import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {

	String excelPath = "C:\\Users\\dhane\\eclipse-workspace\\OpenCart26012025\\src\\test\\java\\nnd\\TestData\\TestData.xlsx";
	String username;
	String password;
	String firstName,LastName,email,passwordReg;

	public String[] readExcelDataForLogin() throws InvalidFormatException, IOException {

		// File file=new File(excelPath);
		FileInputStream fs = new FileInputStream(excelPath);
		Workbook workbook = new XSSFWorkbook(fs);

		// 1️⃣ Get the first sheet
		Sheet sheet = workbook.getSheetAt(0); // Get sheet by index (0-based)

		// ✅ Get row 1 (assuming row 0 is the header)
		Row row = sheet.getRow(1);

		// ✅ Get username & password 
		username = row.getCell(0).getStringCellValue();
		password = row.getCell(1).getStringCellValue();

		System.out.println("Username : " + username);
		System.out.println("Password : " + password);

		workbook.close();
		return new String[] { username, password };
	}
	
	public String[] readExcelDataFoRegistration() throws InvalidFormatException, IOException {

		// File file=new File(excelPath);
		FileInputStream fs = new FileInputStream(excelPath);
		Workbook workbook = new XSSFWorkbook(fs);

		// 1️⃣ Get the first sheet
		Sheet sheet = workbook.getSheetAt(1); // Get sheet by index (0-based) - "sheet1 "

		  int rowCount = sheet.getPhysicalNumberOfRows();
		
		// ✅ Get row 1 (assuming row 0 is the header)
		Row row = sheet.getRow(1);
		
		// ✅ Get username & password 
		firstName = row.getCell(0).getStringCellValue();
		LastName = row.getCell(1).getStringCellValue();
		email = row.getCell(2).getStringCellValue();
		passwordReg = row.getCell(3).getStringCellValue();
		
		System.out.println("Username : " + username);
		System.out.println("Password : " + password); 

		workbook.close();
		return new String[] { firstName, LastName,email, passwordReg};
	}
	
	

}
