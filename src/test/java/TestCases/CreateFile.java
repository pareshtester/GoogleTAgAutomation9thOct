package TestCases;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.testng.annotations.Test;

public class CreateFile {
	String userHome = System.getProperty("user.dir");
	
	//File folder = new File("D:\\Automation\\GoogleTags\\Reports");
	File file;
	@Test 
	public File newF()
	
	{
		System.out.println(userHome);
		try {
			Date date = new Date();
			 String date1= date.toString().replace(" ", "-").replace(":", "-");
            // Create a File object
             file = new File(userHome+"/Reports/"+date1+".html");
            
            // Check if the file already exists
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
		return file;
	}

}
