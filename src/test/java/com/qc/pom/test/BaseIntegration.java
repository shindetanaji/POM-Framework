package com.qc.pom.test;

import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;

import com.qc.pom.utils.TestUtils;

public class BaseIntegration {

	WebDriver driver;
	TestUtils test = new TestUtils();
	Properties prop;
	
	@BeforeSuite
	public void doSetUp() throws IOException {
		prop = test.readProp();
		if(prop.getProperty("browser").equals("chrome")) {
			System.setProperty(prop.getProperty("chromeKey"), prop.getProperty("chromeVal"));
			driver = new ChromeDriver();
			driver.manage().window().maximize();
		}
		
		driver.get(prop.getProperty("siteUrl"));
	}
	
	@DataProvider
	public Object[][] loginData() throws IOException{
		return test.readExceldata("LoginData");
	}
	
	@DataProvider
	public Object[][] registerData() throws IOException{
		return test.readExceldata("RegisterData");
	}
	@AfterSuite
	public void tearDown() {
		driver.close();
	}
}
