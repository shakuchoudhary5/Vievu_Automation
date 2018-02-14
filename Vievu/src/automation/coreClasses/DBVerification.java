package automation.coreClasses;

import org.apache.log4j.Logger;
import helper.SQLHelper;

public class DBVerification extends SQLHelper
{
	private static Logger logger = Logger.getLogger(DBVerification.class.getName());
	
	public String getToken(String query) throws Exception
	{
		String passcode = "";
		try
		{
			Object value = getResult(query);

			if (value != null)
			{
				passcode = value.toString();
			}

			return passcode;
		}
		catch (Exception e)
		{
			logger.info("GetPassCode query execute FAIL.", e);
		}
		return null;
	}
}
