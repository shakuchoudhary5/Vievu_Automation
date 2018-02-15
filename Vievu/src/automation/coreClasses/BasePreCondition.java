package automation.coreClasses;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.openqa.selenium.By;
import automation.BaseTestScript;
import automation.Reporter;
import automation.coreClasses.EnumPage.BannerOptions;
import automation.coreClasses.EnumPage.LeftOptions;
import automation.pageObject.AdminCommonUtilityPage;
import automation.pageObject.LoginPage;

@SuppressWarnings("rawtypes")
public class BasePreCondition extends BaseTestScript
{
	public BasePage resultpage;
	protected String timeOfActionsystm;
	private static String dateFormate = "dd MMM YYYY HH:mm";
	protected String logOutTimesystm;
	protected String[] systemlogoutTime = new String[2];
	protected String[] systemloginTime = new String[2];
	
	private static final By logoutButton = By.xpath(".//*[@id='logoutForm']/div[contains(@class,'user-menu pull-right open')]//a[text()='Log out']");
	private static final By avtarButton=By.xpath(".//*[@id='logoutForm']//button");
	
	public void doLogin() throws Exception
	{
		doLogin(username, password);
	}

	@SuppressWarnings("unchecked")
	public <T extends BasePage> T doLogin(String username, String password) throws Exception
	{
		resultpage = getLoginPage();
		Reporter.log(line);
		Reporter.log("NOTE : Login With :" + username);
		Reporter.log(line);
		LoginPage loginPage= (LoginPage) resultpage;
		loginPage.loginAs(username, password);
		loginPage.clickOnLoginBtn();
		Reporter.log("Entered Password Successfully....");
		timeOfActionsystm = BaseTestScript.dateAndSystemTime(dateFormate);
		systemloginTime = getAuditTime();
		setLocation();
		return (T) resultpage.detectPage();
	}
	
	@SuppressWarnings("unchecked")
	public <T extends BasePage> T gotoExpectedPage(String pageUrl) throws IOException
	{
		resultpage = new BasePage(selenium);
		logger.info("PAGE URL ==>" + pageUrl);
		selenium.get(pageUrl);
		timeout(1);
		return (T) resultpage.detectPage();
	}

	@SuppressWarnings("unchecked")
	private <T extends BasePage> T getLoginPage() throws IOException
	{
		logout();
		return (T) resultpage;
	}

	public void logout() throws IOException 
	{
		selenium.navigate().refresh();
		resultpage = new BasePage(selenium);
		resultpage = resultpage.detectPage();
		if (resultpage == null)
		{
			afterTest();
		}
		else if (resultpage !=null && !(resultpage instanceof LoginPage))
		{
			resultpage.timeIntervel();
			if(resultpage.isDisplayed(By.xpath(".//*[@id='logoutForm']/div[contains(@class,'user-menu pull-right open')]")))
			{
				resultpage.click(logoutButton);
			}
			else
			{
				resultpage.click(avtarButton);
				resultpage.click(logoutButton);
			}
			logOutTimesystm = BaseTestScript.dateAndSystemTime(dateFormate);
			systemlogoutTime = getAuditTime();
			resultpage = resultpage.detectPage();
		}
		logger.info("Logout Successfully...");
	}
	
	public static String[] getAuditTime()
	{
		String[] values = new String[2];
		SimpleDateFormat dateFormat = new SimpleDateFormat(dateFormate);
		Date date = new Date();
		values[0] = dateFormat.format(date);

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MINUTE, 1);
		date = cal.getTime();
		values[1] = dateFormat.format(date);
		return values;
	}

	public BasePage gotoLeftPanelOption(BannerOptions bannerOption,LeftOptions adminOption) throws IOException
	{
		try
		{
			resultpage=resultpage.detectPage();
			AdminCommonUtilityPage adminCommonUtilityPage=(AdminCommonUtilityPage) resultpage;
			adminCommonUtilityPage.gotoTopNavigation(bannerOption);
			resultpage=adminCommonUtilityPage.gotoLeftNavigation(adminOption);
		}
		catch (ClassCastException e) 
		{
			logger.info("Page Not Casting");
			e.printStackTrace();
		}
		return resultpage;
	}
	
	
}
