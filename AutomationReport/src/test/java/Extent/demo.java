package Extent;

import java.io.File;
import java.util.*;
import java.io.IOException;

import java.text.SimpleDateFormat;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class demo {
public WebDriver driver;
	
	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest test;

	
	@BeforeTest
	public void setExtent(){
		htmlReporter=new ExtentHtmlReporter(System.getProperty("user.dir")+"/test-output/myReport.html");
		htmlReporter.config().setDocumentTitle("Automation Report");// Title of the report htmlReporter.config().setReportName("Functional Report");
		htmlReporter.config().setTheme (Theme.DARK);
		extent.attachReporter(htmlReporter);
		
		extent = new ExtentReports();
		extent.setSystemInfo("Host Name", "HP");
		extent.setSystemInfo("OS", "Windows 11");
		extent.setSystemInfo("User Name", "Nasif Automation Labs");
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("Browser", "Chorme");
		
	}
	
	@BeforeMethod
	public void setup() {
		
		System.setProperty("C:\\eclipse\\chrome-win64\\chrome.exe", "C:\\\\eclipse");
		driver= new FirefoxDriver();
		driver.get("https://testautomationpractice.blogspot.com/");
		driver.manage().window().maximize();
	}
	
	public void userinfo() {
		
		driver.findElement(By.id("name")).sendKeys("nasif");
		driver.findElement(By.id("email")).sendKeys("nasif@xyz.com");
		driver.findElement(By.id("phone")).sendKeys("018888811111");
		driver.findElement(By.id("textarea")).sendKeys("dhaka-1200");
		
	}
	
	
	
	
	@AfterTest
	public void endReport(){
		extent.flush();
		
	}
	
	
    @AfterMethod
    public void tearDown(ITestResult result) throws IOException {
		
		if (result.getStatus()== ITestResult.FAILURE) {
			test.log(Status.FAIL,"TEST CASE FAILED IS"+result.getName());
			test.log(Status.FAIL, "TEST CASE FAILED IS"+ result.getName()); // to add name in extent report result.getThrowable()); // to add error/exception in e

	         String screenshotPath =demo.getScreenshot (driver, result.getName());

	         test.addScreenCaptureFromPath(screenshotPath);// adding screen shot
		}
		else if(result.getStatus()==ITestResult.SKIP) {
			test.log(Status.SKIP, "Test Case is skipped"+ result.getName());
			
		}
		else if(result.getStatus()==ITestResult.SUCCESS) {
			test.log(Status.PASS, "Test Case is passed"+ result.getName());
			
		}
}
		
	
	public static String getScreenshot(WebDriver driver, String screenshotName) throws IOException{
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		// after execution, you could see a folder "FailedTestsScreenshots"
		// under src folder
		String destination = System.getProperty("user.dir") + "/FailedTestsScreenshots/" + screenshotName + dateName
				+ ".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		return destination;
	}
	
}
