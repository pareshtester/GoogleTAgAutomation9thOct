package TestCases;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;


public class Data {
	
	@Test
	public Object[][] readData() throws EncryptedDocumentException, IOException
	{

	  FileInputStream excelFile = new FileInputStream(new File(System.getProperty("user.dir")+"/Tags.xlsx") );
	Workbook wb = new XSSFWorkbook(excelFile);
	Sheet sheet = wb.getSheetAt(0);
	 int rowNo = sheet.getPhysicalNumberOfRows();
	 int colNo = sheet.getRow(0).getLastCellNum();
	 Object[][]  obj = new Object[rowNo-1][colNo];	
	 int i=0;
	 DataFormatter dfm = new DataFormatter();
	 for(Row row: sheet)
	 {
		 if(row.getRowNum()==0)
		 {
			continue; 
		 }
		 
		 int j=0;
		 for(Cell cell: row) 
		 {
			 obj[i][j] = dfm.formatCellValue(cell);
			j++;
		 }
		 i++;
	 }
      return obj;
}
}
