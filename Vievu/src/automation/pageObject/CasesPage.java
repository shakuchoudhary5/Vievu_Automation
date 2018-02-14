package automation.pageObject;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CasesPage extends AdminCommonUtilityPage
{

	public CasesPage(WebDriver driver) {
		super(driver);
	}
	
	public void addCommentInCase(String summary) throws IOException
	{
		timeIntervel();
		waitForParticularElement(By.xpath(".//*[@id='editCaseDetailsModal' and @aria-hidden='false']"),Seconds);
		timeIntervel(1);
		sendKeys(By.xpath(".//input[@name='Summary']"), summary);
		timeIntervel();
		click(By.xpath(".//*[@id='editCaseDetailsModal']//button[normalize-space(.)='Save']"));
	}
	
	public boolean verifySummaryOnSearch(String summary)
	{
		waitForParticularElement(By.xpath(".//*[contains(@class,'details-container')]"), Seconds);
		return isDisplayed(By.xpath(".//*[contains(@class,'details-container')]//h5[normalize-space(.)='"+summary+"']"));
	}

}
