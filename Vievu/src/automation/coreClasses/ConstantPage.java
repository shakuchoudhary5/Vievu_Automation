package automation.coreClasses;

import java.io.IOException;
import automation.ApplicationProperties;

public class ConstantPage
{
	public static String getBaseProject()
	{
		try
		{
			return ApplicationProperties.getInstance().getProperty("test.user").trim();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	public static final String ADMIN=getBaseProject();
	public static final String EXISTVIDEONAME="Video11.avi";
	public static final String EXITVIDEOCOMMENT="Automation Testing for VV";
	public static final String VIDEOCOMMENT="Smoke Test";
	public static final String UNCATEGORIZEDVIDEOS="Uncategorized Videos.csv";
}
