package base;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

public class BaseClass {
	private static ChromeOptions cOpt= new ChromeOptions();
	private static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
    
	@BeforeTest
	@Parameters("browser")
	public void setUp(String browserName)
	{
		if(browserName.equalsIgnoreCase("chrome"))
		{
			cOpt.addArguments("--start-maximized");
			cOpt.addArguments("--incognito");
			cOpt.addArguments("--headless");
			driver.set(new ChromeDriver(cOpt));
		}
	}
	
	public WebDriver getdriver()
	{
		return driver.get();
	}
	
	  @AfterTest
	  public void tearDown() {
	  
	  if(driver!=null)
	  { driver.get().quit(); } }
	
	 //Screenshot functionality
	  public String getscrenshotpath(String Testname, WebDriver driver)
	  {
		  TakesScreenshot ts = (TakesScreenshot)driver;
			Date d = new Date();
		  String datename = d.toString().replace(" ","-").replace(":","-");
		File source =   ts.getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir")+"\\Screenshot\\"+Testname+datename+".png";
		System.out.println("path is "+ path);
		File f = new File(path);
		try {
			FileUtils.copyFile(source, f);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return path;
	  }
	 
}
