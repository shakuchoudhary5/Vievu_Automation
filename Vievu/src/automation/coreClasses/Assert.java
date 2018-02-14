package automation.coreClasses;

import java.io.IOException;

public class Assert
{
	public static void assertTrue(boolean expectedResult) throws IOException
	{
		if (!expectedResult)
		{
			org.testng.Assert.assertTrue(expectedResult);
		}
	}

	public static void assertFalse(boolean expectedResult) throws IOException
	{
		if (expectedResult)
		{
			org.testng.Assert.assertFalse(expectedResult);
		}
	}
}
