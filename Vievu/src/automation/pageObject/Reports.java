package automation.pageObject;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import helper.DownloadVerifier;

public class Reports extends AdminCommonUtilityPage 
{

	DownloadVerifier download=new DownloadVerifier();
	
	public Reports(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	public void clickOnUncategorizedVideos() throws IOException
	{
		download.moveAllFiles();
		click(By.xpath(".//div[@class='side-bar-buttons']//a[normalize-space(.)='Uncategorized Videos (csv)']"));
		timeIntervel();
	}
	
	public boolean verifyDownloadDocument(String fileName,int time) throws IOException
	{
		timeIntervel(5);
		return download.verifyDownloadedFile(fileName,time);
	}
}
