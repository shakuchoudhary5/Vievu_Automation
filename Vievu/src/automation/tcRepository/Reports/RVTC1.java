package automation.tcRepository.Reports;

import org.testng.Assert;
import org.testng.annotations.Test;
import automation.Reporter;
import automation.coreClasses.BasePreCondition;
import automation.coreClasses.ConstantPage;
import automation.coreClasses.EnumPage.BannerOptions;
import automation.pageObject.AdminCommonUtilityPage;
import automation.pageObject.Reports;

public class RVTC1 extends BasePreCondition
{
	@Test(priority=1)
	void Scenario01() throws Exception
	{
		testCaseDevelopedBy("Shakuntala Choudhary","Verify Functionality of Download Report.");
		scenarioHeading(1, "Verify Download Reports");
		testcaseID("RVTC1");
		
		Reporter.log("Login Into System");
		doLogin(username,password);
		
		Reporter.log("Click on Report from Banner Option");
		resultpage=resultpage.detectPage();
		AdminCommonUtilityPage adminCommonUtilityPage=(AdminCommonUtilityPage) resultpage;
		resultpage=adminCommonUtilityPage.gotoTopNavigation(BannerOptions.Reports);
		
		Reports reports=(Reports) resultpage; 
		
		Reporter.log("Click on Uncategorized Videos (csv) option for Download Reports");
		reports.clickOnUncategorizedVideos();
		
		Reporter.log("verify Downloaded Reports Document");
		
		
		expectedResult();
		if(reports.verifyDownloadDocument(ConstantPage.UNCATEGORIZEDVIDEOS,10))
		{
			pass("Document Downloaded Successfully");
		}
		else
		{
			fail("Document not Download");
			Assert.assertTrue(false);
		}
	}
}
