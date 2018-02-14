package automation.pageObject;

import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import automation.ApplicationProperties;
import automation.Reporter;
import automation.coreClasses.Assert;

public class LoginPage extends AdminCommonUtilityPage
{
	private static final By userEmail = By.xpath(".//*[@id='Login' and @placeholder='Username']");
	private static final By userPassword =By.xpath(".//*[@id='Password' and @placeholder='Password']");
	private static final By submitBtn=By.xpath(".//*[@id='preloginform']//input[@value='Submit']");
	private static final By loginBtn=By.xpath(".//*[@id='loginform']//input[@value='Log In']");
	
	public LoginPage(WebDriver driver) {
		super(driver);
	}

	public void loginAs(String username, String password) throws IOException
	{
		final String preLoginPage="https://"+ApplicationProperties.getInstance().getProperty("test.domain").trim()+"/Account/PreLogin?ReturnUrl=%2F";

		logger.info(" =========== In loginAs Method =========== ");
		
		sendKeys(userEmail,username);
		click(submitBtn);
		if(getCurrentURL().equals(preLoginPage))
		{
			Reporter.log("User Redirect to Pre Login Page");
			sendKeys(userPassword, password);
		}
		else
		{
			Reporter.log("User Not Redirect to Pre Login Page");
			Assert.assertTrue(false);
		}
	}

	public void clickOnLoginBtn() 
	{
		click(loginBtn);
	}
}
