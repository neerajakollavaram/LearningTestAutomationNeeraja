package com.test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class LoginTest {
	

	 WebDriver driver;
	 SoftAssert As = new SoftAssert();
	 WebDriverWait ewait;
	// Setting up the properties of the Chrome driver
	@BeforeTest
	public void setUp() {
		
		
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Administrator\\Desktop\\Softwares\\chromedriver.exe");		
		driver = new ChromeDriver();
		driver.manage().window().maximize();		
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get("http://zero.webappsecurity.com/");
	}
	

	@Test(priority = 3, groups= {"RegressionTest"} )
	public void invalidUserNameTest() {

		driver.findElement(By.id("signin_button")).click();
		driver.findElement(By.id("user_login")).sendKeys("user@123");
		driver.findElement(By.id("user_password")).sendKeys("password");
		driver.findElement(By.name("submit")).click();
		String actualText = driver.findElement(By.xpath("//div[contains(text(),'Login and/or password are wrong.')]"))
				.getText();
		String expectedText = "Login and/or password are wrong.";
		As.assertEquals(actualText, expectedText);

	}

	@Test(priority = 4, groups= {"RegressionTest"} )
	public void invalidPasswordTest() {

		driver.findElement(By.id("signin_button")).click();
		driver.findElement(By.id("user_login")).sendKeys("username");
		driver.findElement(By.id("user_password")).sendKeys("pass#253");
		driver.findElement(By.name("submit")).click();
		String actualText = driver.findElement(By.xpath("//div[contains(text(),'Login and/or password are wrong.')]"))
				.getText();
		String expectedText = "Login and/or password are wrong.";
		As.assertEquals(actualText, expectedText);
	}

	@Test(priority = 5, groups= {"RegressionTest"} )
	public void forgotPasswordTest() {
		driver.findElement(By.id("signin_button")).click();																													
		driver.findElement(By.xpath("//a[contains(text(),'Forgot your password ?')]")).click();
		driver.findElement(By.xpath("//input[@id='user_email']")).sendKeys("abc.co&");
		driver.findElement(By.name("submit")).click();
		String actualText = driver.findElement(By.xpath("//body/div[1]/div[2]/div[1]/div[1]/div[1]")).getText();
		String expectedText = "Your password will be sent to the following email: abc.co&";
		As.assertEquals(actualText, expectedText);
	}

	@Test(priority = 6, groups= {"RegressionTest"} )
	public void invalidSigninTest() {
		driver.findElement(By.id("signin_button")).click();
		driver.findElement(By.name("submit")).click();
		String actualMessage = driver.findElement(By.xpath("//div[contains(text(),'Login and/or password are wrong.')]")).getText();
		String expectedMessage = " Login and/or password are wrong.";
		As.assertEquals(actualMessage, expectedMessage);
	}
	
	@AfterMethod
	public void logOut() {
		driver.findElement(By.xpath("//a[contains(text(),'Zero Bank')]")).click();
		//driver.findElement(By.xpath(".//body/div[1]/div[1]/div[1]/div[1]/div[1]/ul[1]/li[3]/a[1]")).click();
		//driver.findElement(By.xpath("//a[contains(@id,'logout')]")).click();
	}

	// cleanup
	@AfterTest
	public void cleanUp() {

		// close browser
		driver.close();

		// kill/quit driver
		driver.quit();

	}
																																																			
}																																																								
