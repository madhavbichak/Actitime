package com.Actitime.Testscripts;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.grid.ActualMain;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.Actitime.GenericLibrary.BaseClass;
import com.Actitime.GenericLibrary.FileLibrary;
import com.Actitime.pom.Homepage;
import com.Actitime.pom.TaskPage;

public class TaskTest extends BaseClass{
	
	@Test
	public void task1() throws EncryptedDocumentException, IOException {
		Homepage hp=new Homepage(driver);
		hp.getTasklnk().click();
		
		TaskPage tp=new TaskPage(driver);
		tp.getNewbtn().click();
		tp.getNewcustbtn().click();
		
		FileLibrary f=new FileLibrary();
		String customername = f.readDataFromExcel("Sheet1", 1, 1);
		String customerdesp = f.readDataFromExcel("Sheet1", 4, 2);
		tp.getCustname().sendKeys(customername);
		tp.getCustdesp().sendKeys(customerdesp);
		tp.getCreatebtn().click();
		String expectedresult = customername;
		String Actualresult = driver.findElement(By.xpath("(//div[.='"+customername+"'])[2]")).getText();
		SoftAssert s=new SoftAssert();
		s.assertEquals(expectedresult, Actualresult);
		s.assertAll();
		
		
	}

}
