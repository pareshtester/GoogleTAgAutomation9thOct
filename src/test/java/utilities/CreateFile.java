package utilities;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class CreateFile {
	private static File file;
	private  String filePath;
	
	public File filrSetup()
	{String extentpath = System.getProperty("user.dir")+"/ExtentReports";
	File folder = new File(extentpath);
	String screenshotpath = System.getProperty("user.dir")+"/Screenshot";
	File ssfolder = new File(screenshotpath);
	DeleteFiles df = new DeleteFiles();
	df.deletefiles(folder);
	df.deletefiles(ssfolder);
		filePath=System.getProperty("user.dir");
		Date d = new Date();
		String filenme= d.toString().replace(" ","-").replace(":","-");
		file = new File(filePath+"/ExtentReports/"+filenme+".html");
//		filePath = System.getProperty("user.dir")+"//ExtentReports//report.html";
//		file = new File(filePath);
		
		try {
			if(file.createNewFile())
			{
				System.out.println("File created successfully");
			}else {
				System.out.println("File already exists");
			}
		} catch (IOException e) {
			System.out.println("some error occured");
			e.printStackTrace();
		}
		return file;
	}

}
