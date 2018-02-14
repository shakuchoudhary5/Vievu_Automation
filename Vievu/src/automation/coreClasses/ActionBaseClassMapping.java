package automation.coreClasses;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import automation.ApplicationProperties;
import automation.pageObject.CasesPage;
import automation.pageObject.LoginPage;
import automation.pageObject.Reports;

public class ActionBaseClassMapping
{
	static Map<String, Class<?>> pagesClassMappingMap = new LinkedHashMap<String, Class<?>>();
	
	static Map<String, Class<?>> pagesClassMappingMapWithOutApplicationBaseURL= new LinkedHashMap<String,Class<?>>();
	
	@SuppressWarnings("unchecked")
	public static <T> BasePage<T> getStartsWithBasePageDetect(WebDriver selenium,String pageActionName,Logger logger) throws IOException 
	{	
		try
		{
			Class<?> className= findStartWithDetechPage(pagesClassMappingMap, pageActionName, logger, true);
			if(className !=null)
			{
					return (BasePage<T>) PageFactory.initElements(selenium, className);
			}	
			else
			{
				className= findStartWithDetechPage(pagesClassMappingMapWithOutApplicationBaseURL, pageActionName, logger, false);
				if(className !=null)
				{
					return (BasePage<T>) PageFactory.initElements(selenium, className);
				}
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return null;
	}
	
	public static Class<?> findStartWithDetechPage(Map<String,Class<?>> pagesClassMappingMap,String pageActionName, Logger logger,boolean checkWithApplicationBaseURL) throws IOException
	{
		String applicationBaseURL = "https://"+ApplicationProperties.getInstance().getProperty("test.domain").trim()+"/";
		for(Entry<String,Class<?>> e:pagesClassMappingMap.entrySet())
		{
			if(checkWithApplicationBaseURL && pageActionName.contains(applicationBaseURL+e.getKey()))
			{
				logger.info("Action name:" + pageActionName + " , Class name:" + e.getValue().getName());
				return e.getValue();
			}
			else if(!checkWithApplicationBaseURL && pageActionName.contains(applicationBaseURL+e.getKey()))
			{
				logger.info("Action name:" + pageActionName + " , Class name:" + e.getValue().getName());
				return e.getValue();	
			}
		}
		return null;	
	}
	
	static
	{
		pagesClassMappingMap.put("Account/Login",LoginPage.class);
		pagesClassMappingMap.put("Cases",CasesPage.class);
		pagesClassMappingMap.put("Reports",Reports.class);
	}
}
