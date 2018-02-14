package automation.tcRepository.Cases;

import org.testng.annotations.Test;

import automation.Reporter;
import automation.coreClasses.Assert;
import automation.coreClasses.BasePreCondition;
import automation.coreClasses.EnumPage.BannerOptions;
import automation.coreClasses.EnumPage.LeftOptions;
import automation.pageObject.CasesPage;

public class CVTC1 extends BasePreCondition 
{
	@Test(priority=1)
	void Scenario01() throws Exception
	{
			testCaseDevelopedBy("Shakuntala Choudhary", "Verify Add Cases Functionality");
			testcaseID("VTC3");
			
			scenarioHeading(1, "Add One Case");
			
			Reporter.log("1. Login into System.");
			doLogin(username,password);
			
			Reporter.log("2. Click on Cases From Banner.");
			Reporter.log("3. Click on Add Case");
			resultpage=gotoLeftPanelOption(BannerOptions.Cases,LeftOptions.Add_Case);
			resultpage=resultpage.detectPage();
			CasesPage casePage=(CasesPage) resultpage;
			
			Reporter.log("4. Add Summary in Case");
			casePage.addCommentInCase("VTC3");
			
			Reporter.log("5. Verify Pop Up Message");
			
			expectedResult();
			if(!casePage.verifyPopUpMessage().equals("Case details have been saved successfully"))
			{
				fail("'Case details have been saved successfully' Pop Up Message is Not Proper");
				Assert.assertTrue(false);
			}
			
			pass("'Case details have been saved successfully' Pop Up Message is Proper");
			
			Reporter.log("6. Verify Summary On Added Case");
			
			expectedResult();
			if(!casePage.verifySummaryOnSearch("VTC3"))
			{
				fail("Summary are not Display on Added Case");
				Assert.assertTrue(false);
			}
			
			pass("Summary is Display on Added Case");
	}
}
