package pom;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class pageElements {
	WebDriver driver;
	public pageElements(WebDriver driver)
	{
	  this.driver = driver;
	  PageFactory.initElements(driver, this);
	}
	
	public void steps(String webSite,String tagtext,String GTMText) throws InterruptedException
	{
		System.out.println("excel data check"+"tagtext"+tagtext+"gtmtext"+ GTMText);
		driver.findElement(By.xpath("//button[text()[normalize-space()='Add domain']]")).click();
		//button[text()[normalize-space()='Add domain']]
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofNanos(10));
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//input[@id='domain-start-url']"))));
		driver.findElement(By.xpath("//input[@id='domain-start-url']")).sendKeys(webSite);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("domain-start-button"))));
		Thread.sleep(10000);
		driver.findElement(By.id("domain-start-button")).click();
		
		/*
		 * String parentwindow = driver.getWindowHandle();
		 * System.out.println("Parent"+parentwindow);
		 */
		Thread.sleep(20000);
        Set<String> windowhandles =  driver.getWindowHandles();
        System.out.println(windowhandles);
        Iterator<String> iterator =windowhandles.iterator();
        String parentwindow=iterator.next();
        String childwindow  =iterator.next();
        System.out.println("child"+childwindow);
        driver.switchTo().window(childwindow);
        System.out.println("current"+driver.getWindowHandle());
        Thread.sleep(10000);	
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.className("__TAG_ASSISTANT_BADGE"))));
        String frametext=driver.switchTo().frame(driver.findElement(By.className("__TAG_ASSISTANT_BADGE"))).getTitle();
        System.out.println("frametext is"+frametext);
        
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button[contains(text(),' Finish ')]"))));
        driver.findElement(By.xpath("//button[contains(text(),' Finish ')]")).click();
        driver.switchTo().window(parentwindow);
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("/html/body/div/div[2]/debugger/div[2]/div[1]/span[2]"))));
        Thread.sleep(10000);	
        String x =	driver.findElement(By.xpath("/html/body/div/div[2]/debugger/div[2]/div[1]/span[2]")).getText();
    	System.out.println("text is"+x);
         driver.switchTo().window(parentwindow);
           // driver.switchTo().parentFrame();
    		driver.findElement(By.xpath("//button[contains(text(),' Continue ')]")).click();
    		
    		List<WebElement> we = driver.findElements(By.cssSelector(".container-picker__chip"));
    		System.out.println("number is"+we.size());
    		String tagtextarray[] = new String[we.size()];
    		for(int i=1; i<=we.size(); i++)
    		{
    			String elementtagtext=driver.findElement(By.xpath("/html/body/div[1]/div[2]/debugger/container-picker-ng/div/div[2]/container-chip-ng["+i+"]")).getText();
    			System.out.println("tag text is"+elementtagtext);
    			
    			tagtextarray[i-1] = elementtagtext;
    			
    		}
    		System.out.println(tagtextarray.length);
    		
    		String newtext="";
    		int i =0;
    		while( i < tagtextarray.length){
    			if(tagtextarray[i].equalsIgnoreCase(tagtext) || tagtextarray[i].equalsIgnoreCase(GTMText) )
    			{
    					System.out.println("pass");
    					//assertTrue(true);
    					//System.out.println();
    					newtext= tagtextarray[i];
    					System.out.println("here check"+newtext);
    					break;
    				}  else {
    					i++;
    				}
    			
    		}
    		
    		if(newtext.equalsIgnoreCase(tagtext) || newtext.equalsIgnoreCase(GTMText))
    		{
    			System.out.println("Title is valid");
    			assertTrue(true);
    		}else {
    			org.testng.Assert.fail();
    		}
    		
	}

}
