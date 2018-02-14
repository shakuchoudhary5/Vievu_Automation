package automation.coreClasses;

import java.io.IOException;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import automation.coreClasses.EnumPage.BannerOptions;
import automation.ApplicationProperties;
import automation.BaseFrameworkPage;
import automation.pageObject.HomePage;

public class BasePage<T> extends BaseFrameworkPage
{
	public Logger logger = Logger.getLogger(this.getClass());
	public String testUrl;
	
	public BasePage(WebDriver driver) {
		super(driver);
	}
	
	public BasePage<T> detectPage() throws IOException{	
		String url = selenium.getCurrentUrl();
		testUrl= ApplicationProperties.getInstance().getProperty("test.url").trim();
		logger.info("==================== Start Detect Page===========================");
		long time = System.currentTimeMillis();
		logger.info("application Base URL  : " + testUrl);
		logger.info("Detect Page Url : " + url);
		BasePage<T> basePagePubObject = ActionBaseClassMapping.getStartsWithBasePageDetect(selenium, url, logger);
		if(basePagePubObject != null)
			return basePagePubObject;
		basePagePubObject = getExtraMappingForBasePageDetect(url);
		logger.info("Time taken For Detect Page ====>"+(System.currentTimeMillis() - time)+" miliseconds");
		logger.info("==================== End Detect Page===========================");
		return basePagePubObject;
	}
	
	@SuppressWarnings("unchecked")
	public BasePage<T> getExtraMappingForBasePageDetect(String url) throws IOException
	{
		String homeURL="https://"+ApplicationProperties.getInstance().getProperty("test.domain").trim()+"/";
		
		if (url.equals(homeURL) || (url.contains("editModal")))
		{
			logger.info("Home page detected");
			return PageFactory.initElements(selenium, HomePage.class);
		}
		logger.info("No any Page Found");
		return null;
	}

	
	public String getOptionAsString(String adminOption)
	{
		String option = adminOption;
		if (adminOption.contains("$") || adminOption.contains("_") || adminOption.contains("__"))
		{
			option = adminOption.replace("$", "&");
			option = option.replace("_", " ");
			option = option.replace("__", "#");
		}
		return option;
	}
	
	public BasePage<T> gotoTopNavigation(BannerOptions bannerOption) throws IOException
	{
		String navOption = getOptionAsString(bannerOption.toString());
		timeIntervel(1);
		click(By.xpath(".//ul[@id='mainMenu']//a[normalize-space(.)='"+navOption+"']"));
		timeIntervel();
		return detectPage();
	}
	
	public BasePage<T> gotoBackOnBrowser() throws IOException{
		timeIntervel(2);
		selenium.navigate().back();
		timeIntervel(3);
		return detectPage();
	}

	public BasePage<T> gotoBrowserForward() throws IOException{
		timeIntervel(2);
		selenium.navigate().forward();
		timeIntervel(3);
		return detectPage();
	}
	
	public String getCurrentURL()
	{
		timeIntervel(1);
		return selenium.getCurrentUrl();
	}
}
