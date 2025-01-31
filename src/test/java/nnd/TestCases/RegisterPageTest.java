package nnd.TestCases;

import nnd.Pages.RegisterPage;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import base.ConnectionSetup;

@Listeners(nnd.Listners.MyTestListener.class)
public class RegisterPageTest extends ConnectionSetup{
	 

	@Test
	public void test1() throws InterruptedException {
		
		RegisterPage rp=new RegisterPage(driver);
		rp.RegisterNewUser();
		
	}
}