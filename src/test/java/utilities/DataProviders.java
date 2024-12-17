package utilities;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.annotations.DataProvider;

import TestCases.Data;

public class DataProviders {

	@DataProvider(name="data")
	public Object[][] getData()
	{
		Data eld = new Data();
		Object[][] data = null;
		try {
			data = eld.readData();
		} catch (EncryptedDocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}
}
