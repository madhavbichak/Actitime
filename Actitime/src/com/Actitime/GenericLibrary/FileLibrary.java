package com.Actitime.GenericLibrary;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * This is a generic filelibrary class which contains all the generic methods of data driven testing
 * @author Nade Ali M
 *
 */
public class FileLibrary {
/**
 * This method is a non-static method used to read the data from property file
 * @throws IOException 
 */
	public String readDataFromPropertyFile(String key) throws IOException {
		FileInputStream fis=new FileInputStream("./Testdata/NewCommondata.property");
		Properties p=new Properties();
		p.load(fis);
	String value = p.getProperty(key);
	return value;
		
	}
	
	/**
	 * This method is a non static method used to read data from excel sheet
	 * @throws IOException 
	 * @throws EncryptedDocumentException 
	 */
	public String readDataFromExcel(String sheetname,int row,int cell) throws EncryptedDocumentException, IOException {
		FileInputStream fis1=new FileInputStream("./Testdata/BankData.xlsx");
	Workbook wb = WorkbookFactory.create(fis1);
	String value = wb.getSheet(sheetname).getRow(row).getCell(cell).getStringCellValue();
	return value;
	
	}

}
