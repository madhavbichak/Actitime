package com.Actitime.GenericLibrary;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.Actitime.pom.Homepage;
import com.Actitime.pom.LoginPage;

public class BaseClass {
	FileLibrary f=new FileLibrary();
	
	public static WebDriver driver;
	
	@BeforeSuite
	public void databaseconnection() {
		Reporter.log("database is connected",true);
	}
	
	@AfterSuite
	public void databaseDisconnection() {
		Reporter.log("database is disconnected",true);
	}
	
	@BeforeClass
	public void launchBrowser() throws IOException {
		ChromeOptions option=new ChromeOptions();
		option.addArguments("--remote-allow-origins=*");
	 driver=new ChromeDriver(option);
	 driver.manage().window().maximize();
	 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	 
	String link = f.readDataFromPropertyFile("url");
	 driver.get(link);
	 Reporter.log("browser launched successfully",true);
	}
	
	@AfterClass
	public void closeBrowser() {
		driver.close();
		Reporter.log("browser closed successfully",true);
	}
	
	@BeforeMethod
	public void login() throws IOException {
	String un = f.readDataFromPropertyFile("username");
	String pw = f.readDataFromPropertyFile("password");
	LoginPage lp=new LoginPage(driver);
	lp.getUntbx().sendKeys(un);
	lp.getPwtbx().sendKeys(pw);
	lp.getLgbtn().click();
		Reporter.log("login successfully",true);
		
	}
	@AfterMethod
	public void logout() throws InterruptedException {
		Thread.sleep(3000);
		Homepage hp=new Homepage(driver);
		hp.getLgoutlnk().click();
		Reporter.log("logged out successfully",true);
	}

}
