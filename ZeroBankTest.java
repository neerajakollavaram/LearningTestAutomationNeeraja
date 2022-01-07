package com.test;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.testng.annotations.BeforeTest;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;

public class ZeroBankTest {

	 WebDriver driver;
	 SoftAssert As = new SoftAssert();

	// Setting up the properties of the Chrome driver
	@BeforeTest
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Administrator\\Desktop\\Softwares\\chromedriver.exe");		
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}
	
	// Login to the ZeroBankAccount
	@BeforeMethod
	public void loginToApp() {
		driver.get("http://zero.webappsecurity.com/");	
		driver.findElement(By.id("signin_button")).click();
		driver.findElement(By.id("user_login")).sendKeys("username");
		driver.findElement(By.id("user_password")).sendKeys("password");
		driver.findElement(By.name("submit")).click();
		driver.findElement(By.id("details-button")).click();
		driver.findElement(By.id("proceed-link")).click();		
		if (driver.findElement(By.id("details-button")).isDisplayed()) {
			driver.findElement(By.id("details-button")).click();
			driver.findElement(By.id("proceed-link")).click();
		}
	}
	
	// Validate the testcase by without providing any field data  for the Purchase Foreign Currency
	// Empty field validation
	@Test(priority = 1, enabled = true, groups= {"RegressionTest"} )
	public void purchaseEmptyFieldValidation() {
		driver.findElement(By.linkText("Pay Bills")).click();
		WebDriverWait ewait = new WebDriverWait(driver, 10);
		ewait.until(ExpectedConditions.titleContains("Pay Bills"));
		driver.findElement(By.linkText("Purchase Foreign Currency")).click();
		WebDriverWait ewait1 = new WebDriverWait(driver, 10);
		ewait1.until(ExpectedConditions.titleContains("Purchase Foreign Currency"));
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		driver.findElement(By.cssSelector("input#purchase_cash[type='submit']")).click();
		Alert jsAlert = driver.switchTo().alert();
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		jsAlert.accept();
	}
	
	// Validate the testcase by providing valid data for the FundTransfer
	@Test(priority = 2, enabled = true, groups= {"SmokeTest"} )
	public void fundTransferPositiveTest() {
		driver.findElement(By.xpath("//a[contains(text(),'Pay Bills')]")).click();
		driver.findElement(By.xpath("//input[@id='tf_amount']")).sendKeys("1200");
		driver.findElement(By.xpath("//input[@id='tf_description']")).sendKeys("This is to transfer amount");
		driver.findElement(By.xpath("//button[@id='btn_submit']")).click();
		String actualTitle = driver
				.findElement(By.xpath("//h2[contains(text(),'Transfer Money & Make Payments - Verify')]")).getText();
		String expectedTitle = "Transfer Money & Make Payments - Verify";
		As.assertEquals(actualTitle, expectedTitle);
	}

	@Test(priority = 3, enabled = true, groups= {"RegressionTest"} )
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

	@Test(priority = 4, enabled = true, groups= {"RegressionTest"} )
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

	@Test(priority = 5, enabled = true, groups= {"RegressionTest"} )
	public void forgotPasswordTest() {

		driver.findElement(By.xpath("//a[contains(text(),'Forgot your password ?')]")).click();
		driver.findElement(By.xpath("//input[@id='user_email']")).sendKeys("abc.co&");
		driver.findElement(By.name("submit")).click();
		String actualText = driver.findElement(By.className("offset3 span6")).getText();
		String expectedText = "Your password will be sent to the following email: abc.co&";
		As.assertEquals(actualText, expectedText);
	}

	@Test(priority = 6, enabled = true, groups= {"RegressionTest"} )
	public void invalidSigninTest() {
		driver.findElement(By.id("signin_button")).click();
		driver.findElement(By.className("btn btn-primary")).click();
		String actualMessage = driver.findElement(By.className("alert alert-error")).getText();
		String expectedMessage = " Login and/or password are wrong.";
		As.assertEquals(actualMessage, expectedMessage);
	}

	@AfterMethod
	public void logOut() {
		driver.findElement(By.className("dropdown-toggle")).click();
		driver.findElement(By.id("logout_link")).click();

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