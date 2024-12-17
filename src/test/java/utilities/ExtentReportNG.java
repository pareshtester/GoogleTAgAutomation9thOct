package utilities;

import java.io.File;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import base.BaseClass;

public class ExtentReportNG extends BaseClass {
	
	private static ExtentReports extent;
	private static ExtentSparkReporter spark;
	
	public static ExtentReports extentSetup()
	{
		if(extent==null)
		{
			CreateFile f = new CreateFile();
			File returnFile =f.filrSetup();
//		File f = new File(System.getProperty("user.dir")+"//Reports//report.html");
		spark = new ExtentSparkReporter(returnFile);
		spark.config().setTheme(Theme.DARK);
		spark.config().setReportName("Google tag Automation");
		spark.config().setDocumentTitle("Automation Report");
		extent = new ExtentReports();
		try {
			extent.attachReporter(spark);
			
		}catch(Exception e)
		{
			e.getStackTrace();
		}
		
		}
		return extent;
	}

}
