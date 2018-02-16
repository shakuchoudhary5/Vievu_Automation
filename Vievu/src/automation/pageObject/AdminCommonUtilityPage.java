package automation.pageObject;

import java.io.IOException;
import java.util.Calendar;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import automation.coreClasses.BasePage;
import automation.coreClasses.EnumPage.LeftOptions;

@SuppressWarnings("rawtypes")
public class AdminCommonUtilityPage extends BasePage
{
	public BasePage resultpage;
	private static final By dateFrom=By.xpath(".//*[@id='uploadDatePickerFrom']//i");
	private static final By dateTo=By.xpath(".//*[@id='uploadDatePickerTo']//i");
	private static final By switchMonth=By.xpath(".//div[@class='datepicker-days']//th[@class='picker-switch']");
	private static final By switchYear=By.xpath(".//div[@class='datepicker-months']//th[@class='picker-switch']");
	private static final By moveNext=By.xpath(".//span[contains(@class,'chevron-right')]");
	private static final By moveBack=By.xpath(".//span[contains(@class,'chevron-left')]");
	
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
	
	public void datePickerFrom(String month,int year,int date)
	{
		click(dateFrom);
		timeIntervel();
		waitForParticularElement(By.xpath(".//*[@id='uploadDatePickerFrom']/div"),Seconds);
		timeIntervel();
		
		selectYear(year);
		selectMonth(month);
		selectDate(date);
		
		click(dateFrom);
	}
	
	public void datePickerTo(String month,int year,int date)
	{
		click(dateTo);
		waitForParticularElement(By.xpath(".//*[@id='uploadDatePickerTo']/div"),Seconds);
		selectYear(year);
		selectMonth(month);
		selectDate(date);
		
		click(dateTo);
	}
	
	private void selectYear(int year)
	{
		click(switchMonth);
		click(switchYear);
		
		int currentYear= Calendar.getInstance().get(Calendar.YEAR);
		int yearDiff= currentYear - year;
		
		while(!isDisplayed(By.xpath(".//span[normalize-space(.)='"+year+"']")))
		{
			if(yearDiff>0)
			{
				click(moveNext);
			}
			else
			{
				click(moveBack);
			}
		}
		click(By.xpath("//span[normalize-space(.)='"+year+"']"));
	}
	
	private void selectMonth(String month)
	{
		click(By.xpath(".//div[@class='datepicker-months']//span[normalize-space(.)='"+month+"']"));
	}
	
	private void selectDate(int date)
	{
		click(By.xpath(".//div[@class='datepicker-days']//td[normalize-space(.)='"+date+"']"));
	}

}
