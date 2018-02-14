package automation.pageObject;

import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import automation.coreClasses.BasePage;
import automation.coreClasses.EnumPage.LeftOptions;

@SuppressWarnings("rawtypes")
public class AdminCommonUtilityPage extends BasePage
{
	public BasePage resultpage;
	
	public AdminCommonUtilityPage(WebDriver driver) {
		super(driver);
	}
	
	public BasePage gotoLeftNavigation(LeftOptions leftOption) throws IOException
	{
		String leftNavOption = getOptionAsString(leftOption.toString());
		timeIntervel(1);
		click(By.xpath(".//div[@class='side-bar-buttons']//a[normalize-space(.)='"+leftNavOption+"']"));
		timeIntervel();
		return detectPage();
	}
	
	public String verifyPopUpMessage()
	{
		timeIntervel(1);
		waitForParticularElement(By.id("message-modal-popup"), Seconds);
		timeIntervel(1);
		return getWindowPopUpMsgAndAccept(By.id("message-modal-body"),By.xpath(".//button[@id='message-button-ok' and normalize-space(.)='OK']"));
	}

}
