package automation.tcRepository.Login;

import org.testng.annotations.Test;
import automation.coreClasses.Assert;
import automation.coreClasses.BasePreCondition;
import automation.Reporter;
import automation.pageObject.HomePage;

public class LVTC1 extends BasePreCondition 
{
	@Test(priority=1)
	void scenario01() throws Exception
	{
		testCaseDevelopedBy("Shakuntala Choudhary", "Login into System with valid User.");
		testcaseID("VTC1");
		
		scenarioHeading(1,"Login With System Admin");
		
		Reporter.log("1. Enter UserName.");
		Reporter.log("2. Click on Submit Button.");
		Reporter.log("3. Enter Password.");
		Reporter.log("4. Click On Login Button.");
		
		doLogin(username,password);
		
		Reporter.log("5. Home Page Should be Detect");
		resultpage=resultpage.detectPage();
		
		expectedResult();
		if(!(resultpage instanceof HomePage))
		{
			fail("Home Page Not Detected");
			Assert.assertTrue(false);
		}
		
		pass("Home Page Detected");
	}
}
