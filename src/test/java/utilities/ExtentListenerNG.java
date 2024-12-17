package utilities;

import java.util.Arrays;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.model.Media;

import base.BaseClass;

public class ExtentListenerNG extends BaseClass implements ITestListener {
    ExtentReports extent = ExtentReportNG.extentSetup();
    ExtentTest test;
	private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
	private Media Throwable;
	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		Object[] parameters = result.getParameters();
		String param = null;
		if(parameters.length>0)
		{
			 param = Arrays.toString(parameters);
			 System.out.println("parameters are "+param);
		}
		test = extent.createTest(param);
		extentTest.set(test);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		extentTest.get().log(Status.PASS, "Success");
	}
	@Override
    public void onTestSkipped(ITestResult result) {
        String skipMessage = "Test Skipped";
        Throwable throwable = result.getThrowable();

        // Add skip reason if available
        if (throwable != null) {
            skipMessage += ": " + throwable.getMessage();
        }

        extentTest.get().log(Status.SKIP, skipMessage);
    }

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		extentTest.get().log(Status.FAIL,  Throwable);
		WebDriver driver = null;
		Object obj = result.getInstance();
		if(obj instanceof BaseClass)
		{
			driver = ((BaseClass)obj).getdriver();
		}
		
		if(driver!=null)
		{
			try {
			extentTest.get().addScreenCaptureFromPath(getscrenshotpath(result.getMethod().getMethodName(), driver), result.getMethod().getMethodName());
			}catch(Exception e)
			{
				e.getStackTrace();
				System.out.println("issue");
			}
		}else {
			System.out.println("Driver instance is null, unable to capture screenshot.");
		}
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		extent.flush();
	}
	
	

}
