package automation.pageObject;

import java.io.File;
import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import automation.Constant;
import automation.coreClasses.EnumPage.VideosSubOptions;

public class HomePage extends AdminCommonUtilityPage
{ 
	public HomePage(WebDriver driver) {
		super(driver);
	}

	public void clickOnLeftPanelSubOptions(VideosSubOptions option)
	{
		String subOption=getOptionAsString(option.toString());
		click(By.xpath(".//*[@id='downloadMenu']//a[normalize-space(.)='"+subOption+"']"));
		timeIntervel();
	}
	
	public void gotoUploadFileFromComputer(String fileName) throws IOException
	{
		String pATH= new File(Constant.filepath).getCanonicalPath().trim();
		timeIntervel(1);
		Runtime.getRuntime().exec(pATH+"/videos/"+fileName);
		timeIntervel();
		waitForParticularElement(By.xpath(".//*[@id='saveButton' and @disabled='']"), Seconds);
		timeIntervel();
		waitUntilElementDisplays(By.xpath(".//*[@id='saveButton' and @disabled='']"));
		timeIntervel(1);
	}
	
	public void addComment(String comment) throws IOException
	{
		sendKeys(By.xpath(".//*[@id='editVideoDetailsModal']//textarea"), comment);
		timeIntervel();
	}
	
	public void clickOnSave()
	{
		timeIntervel(1);
		click(By.id("saveButton"));
	}

	public boolean verifyVideoUploadComment(String Value)
	{
		scrollToBottom();
		return isDisplayed(By.xpath(".//*[@id='detailsTab']//div[normalize-space(.)='"+Value+"']"));
	}
	
	public void SearchTemplates(String value) throws Exception
	{
		timeIntervel();
		sendKeys(By.xpath(".//*[contains(@id,'filter-template')]//input"), value);
		timeIntervel(1);
		click(By.xpath(".//*[contains(@id,'filter-template')]//i[@class='fa fa-plus']"));
		timeIntervel();
		waitForParticularElement(By.xpath(".//i[@class='fa fa-minus']"), Seconds);
	}
	
	public void clickOnSearch() throws IOException
	{
		timeIntervel();
		click(By.xpath(".//*[@id='videosContainer']//a[normalize-space(.)='Search']"));
		timeIntervel();
		waitUntilElementDisplays(By.xpath(".//*[@id='loading-overlay' and @style='']"));
		timeIntervel(1);
	}
	
	public boolean verifyValuesOfResultVideos(String video,String comment) throws IOException
	{
		int videos=getNumberOfListOfElements(By.xpath(".//*[@id='videosContainer']//div[@class='panel-body']//a"));
		for(int i=0;i<=videos;i++)
		{
			int j=i+1;
			if(!(isDisplayed(By.xpath(".//*[@id='videosContainer']//div[@class='panel-body']//a["+j+"]//div[normalize-space(.)='00:00:00']"))))
			{
				click(By.xpath(".//*[@id='videosContainer']//div[@class='panel-body']//a["+j+"]"));
				timeIntervel();
				waitUntilElementDisplays(By.xpath(".//*[@id='loading-overlay' and @style='']"));
				timeIntervel(1);
				
				if(isDisplayed(By.xpath(".//*[@id='detailsTab']//div[text()='"+video+"']")) &&
						isDisplayed(By.xpath(".//*[@id='detailsTab']//div[text()='"+comment+"']")))
				{
					return true;
				}
			}
		}
		return false;
	}
	
	public void clickOnShareIcon()
	{
		timeIntervel();
		click(By.xpath(".//*[contains(@class,'file-info')]//i[@class='fa fa-share']"));
	}
	
	public boolean verifyShareOptions()
	{
		timeIntervel(1);
		waitForParticularElement(By.xpath(".//*[@id='sharedLinkModal' and @style='display: block;']"),Seconds);
		timeIntervel(1);
		click(By.xpath(".//*[@id='sharedLinkModal']//i[@class='fa fa-plus']"));
	
		return isDisplayedAllXPath(By.xpath(".//ul[@class='dropdown-menu']//a[text()='Share with User']"),
			By.xpath(".//ul[@class='dropdown-menu']//a[text()='Share with Email']"),
			By.xpath(".//ul[@class='dropdown-menu']//a[text()='Share by Link']"));
	}
	
	public void clickOnVideo() throws IOException
	{
		click(By.xpath(".//div[contains(@class,'list-group')]//a[1]"));
		timeIntervel();
		waitUntilElementDisplays(By.xpath(".//*[@id='loading-overlay' and @style='']"));
		timeIntervel();
	}
}