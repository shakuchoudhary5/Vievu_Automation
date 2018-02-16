package automation.tcRepository.VideosAndFiles;

import org.testng.annotations.Test;
import automation.Reporter;
import automation.coreClasses.Assert;
import automation.coreClasses.BasePreCondition;
import automation.coreClasses.ConstantPage;
import automation.coreClasses.EnumPage.BannerOptions;
import automation.coreClasses.EnumPage.VideosSubOptions;
import automation.coreClasses.EnumPage.LeftOptions;
import automation.pageObject.HomePage;

public class VVTC1 extends BasePreCondition
{
	private HomePage homePage;
	
	@Test(priority=1)
	void Scenario01() throws Exception
	{
			testCaseDevelopedBy("Shakuntala Choudhary", "Add Video From Computer into System and Verify");
			testcaseID("VVTC1");
			scenarioHeading(1, "Add Video and Verify Comment");
			
			Reporter.log("1. Login Into System.");
			doLogin(username,password);
			
			Reporter.log("2. Click on Videos & Files From Banner.");
			Reporter.log("3. click on Add Button");
			resultpage=gotoLeftPanelOption(BannerOptions.Videos_$_Files,LeftOptions.Add);
			homePage=(HomePage) resultpage;
			setLocation();
			
			Reporter.log("4. Click on From Computer Option.");
			homePage.clickOnLeftPanelSubOptions(VideosSubOptions.From_Computer);
			
			Reporter.log("5. Upload Video");
			homePage.gotoUploadFileFromComputer("VideoFile.exe");
			
			Reporter.log("6. Add Comment");
			homePage.addComment(ConstantPage.VIDEOCOMMENT);
			homePage.clickOnSave();
			
			Reporter.log("7. Vreify PopUp Message");
			
			expectedResult();
			if(!homePage.verifyPopUpMessage().contains("File details have been saved successfully"))
			{
				fail("'File details have been saved successfully' validation Message Not Display Proper");
				Assert.assertTrue(false);
			}
			
			pass("'File details have been saved successfully' validation Message Display");
			
			Reporter.log("8. Vreify Comment Message");
			
			expectedResult();
			if(!homePage.verifyVideoUploadComment(ConstantPage.VIDEOCOMMENT))
			{
				fail("Video Upload Commnet Not Display");
				Assert.assertTrue(false);
			}
			
			pass("Video Upload Commnet Display");
	}
	
	@Test(priority=2)
	void Scenario02() throws Exception
	{
		scenarioHeading(2, "Verify Shared Options Of Video Using Uploaded Date");
		
		Reporter.log("1. Login Into System.");
		Reporter.log("2. Click on Videos & Files From Banner.");
		getLocation();
		resultpage=resultpage.detectPage();
		
		Reporter.log("3. Click on Upload Date");
		homePage.gotoLeftNavigation(LeftOptions.Upload_Date);
		
		Reporter.log("4. Select From Date");
		homePage.datePickerFrom("Feb",2018,15);
		
		Reporter.log("4. Select To Date");
		homePage.datePickerTo("Feb",2018,16);
		
		Reporter.log("5. Click On Search Button");
		homePage.clickOnSearch();
		
		Reporter.log("6. Click on Any one Video");
		homePage.clickOnVideo();
	
		Reporter.log("7. Click on Share Icon of Videos");
		homePage.clickOnShareIcon();
	
		Reporter.log("8. Verify Share Options");
		
		expectedResult();
		if(homePage.verifyShareOptions())
		{
			pass("Share All Options Are Display Properly");
		}
		else
		{
			fail("Share All Options Are Not Display Properly");
			Assert.assertTrue(false);
		}
	}
	
//	@Test(priority=2)
//	void Scenario02() throws Exception
//	{
//		scenarioHeading(2, "Verify Shared Options Of Video");
//		
//		Reporter.log("1. Login Into System.");
//		Reporter.log("2. Click on Videos & Files From Banner.");
//		getLocation();
//		resultpage=resultpage.detectPage();
//		
//		Reporter.log("3. Click on Comment Button");
//		homePage.gotoLeftNavigation(LeftOptions.Comment);
//		
//		Reporter.log("4. Search Video Using Given Comment");
//		homePage.SearchTemplates(ConstantPage.EXITVIDEOCOMMENT);
//		
//		Reporter.log("5. Click On Search Button");
//		homePage.clickOnSearch();
//		
//		Reporter.log("6. Verify Video Name and Comment Values on Uploaded Video");
//		
//		expectedResult();
//		if(homePage.verifyValuesOfResultVideos(ConstantPage.EXISTVIDEONAME,ConstantPage.EXITVIDEOCOMMENT))
//		{
//			pass("Verify Video Name and Comment Values are Dispaly on Uploaded Video");
//			
//			Reporter.log(" ");
//			Reporter.log("7. Click on Share Icon of Videos");
//			homePage.clickOnShareIcon();
//			
//			Reporter.log("8. Verify Share Options");
//			
//			expectedResult();
//			if(homePage.verifyShareOptions())
//			{
//				
//				pass("Share All Options Are Display Properly");
//			}
//			else
//			{
//				fail("Share All Options Are Not Display Properly");
//				Assert.assertTrue(false);
//			}
//		}
//		else
//		{
//			fail("Verify Video Name and Comment Values are not Dispaly on Uploaded Video");
//			Assert.assertTrue(false);
//		}
//	}
}
