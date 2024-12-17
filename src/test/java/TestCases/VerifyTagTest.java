package TestCases;


import org.testng.annotations.Test;

import base.BaseClass;
import pom.pageElements;
import utilities.DataProviders;

public class VerifyTagTest extends BaseClass {
	
	@Test(dataProviderClass = DataProviders.class, dataProvider="data", retryAnalyzer = ReRunAutomationScript2.class )
	public void tagTestSteps(String website, String tagText, String GTMtext)
	{
//	   String website="https://www.gdata.in";
//	     String tagText="GTM-WKLN4BF";
		pageElements pe = new pageElements(getdriver());
		getdriver().get("https://tagassistant.google.com/");
		try {
			pe.steps(website,tagText,GTMtext);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

}
