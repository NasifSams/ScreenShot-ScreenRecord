package MonteRecoder;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestCase {
	
	WebDriver driver;
	
	@BeforeMethod
	public void setup() {
		
		System.setProperty("C:\\eclipse\\chrome-win64\\chrome.exe", "C:\\eclipse");
		
		driver= new ChromeDriver();
		driver.get("https://testautomationpractice.blogspot.com/");
		driver.manage().window().maximize();
		
		
	}
	
	@Test
	public void userinfo() throws Exception {
		ScreenRecorderUtil.startRecord("userinfo");
		Thread.sleep(1000);
		   WebElement name=driver.findElement(By.id("name"));
		   WebElement email=driver.findElement(By.id("email"));
		   WebElement ph=driver.findElement(By.id("phone"));
		   WebElement add=driver.findElement(By.id("textarea"));
		   
		   Assert.assertTrue(name.isDisplayed()&& name.isEnabled());
		   name.sendKeys("nasif");
		   
		   Assert.assertTrue(email.isDisplayed()&&email.isEnabled());
		   email.sendKeys("nasif@xyz.com");
		   
		   Assert.assertTrue(ph.isDisplayed()&& ph.isEnabled());
		   ph.sendKeys("0000000");
		   
		   Assert.assertTrue(add.isDisplayed()&& add.isEnabled());
		   add.sendKeys("Dhaka");
		   
		   ScreenRecorderUtil.stopRecord();
		   
	}
	
	@AfterMethod
	public void teardown() {
		driver.close();
	}

}
