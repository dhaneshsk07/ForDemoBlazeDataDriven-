package nnd.TestCases;


import java.io.IOException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import base.DB_ConnectionSetup;
import nnd.Pages.DB_HomePage;
import nnd.Pages.DB_LoginPage;
import nnd.Utilities.DB_ExcelUtil;

@Listeners(nnd.Listners.DB_MyTestListener.class)
public class DB_HomeTest extends DB_ConnectionSetup {

	private static final Logger logger = LoggerFactory.getLogger(DB_LoginPage.class);

	
	@Test( dependsOnMethods = {"nnd.TestCases.DB_LoginTest.validUserLogin"},description = "HomeTest_TC01", enabled = true)
	
	public void verifyPurchaseItem()
			throws InvalidFormatException, IOException, InterruptedException {
		DB_HomePage hp=new DB_HomePage(driver);
		hp.selectCatogory();
		hp.selectItem();
		hp.addToCart();
		hp.verifyElementAddToCart(); 
		 

	}

	

}
