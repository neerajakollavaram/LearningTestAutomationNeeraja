package SeleniumBasics;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Assignment2 {

	public static void main(String[] args) {
		
		System.setProperty("webdriver.chrome.driver", "C:\\SeleniumBrowserDriverLocation\\chromedriver.exe");

		// open browser
		WebDriver driver = new ChromeDriver();
		
		driver.get("http://zero.webappsecurity.com/");
		driver.findElement(By.id("signin_button")).click();
		driver.findElement(By.xpath("//input[@id='user_login']")).sendKeys("username");
		driver.findElement(By.cssSelector("#user_password")).sendKeys("password");
		driver.findElement(By.name("submit")).click();
		
		if(driver.findElement(By.id("details-button")).isDisplayed()){
		
		driver.findElement(By.id("details-button")).click();
		driver.findElement(By.id("proceed-link")).click();
		
		driver.findElement(By.linkText("Pay Bills")).click();
		driver.findElement(By.partialLinkText("Add New Payee")).click();
		
		driver.findElement(By.xpath("//input[@id='np_new_payee_name']")).sendKeys("Neeraja");
		driver.findElement(By.xpath("//textarea[@id='np_new_payee_address']")).sendKeys("Tirumala Hills Colony");
		driver.findElement(By.cssSelector("input#np_new_payee_account[id='np_new_payee_account']")).sendKeys("1254879");
		driver.findElement(By.cssSelector("#np_new_payee_details[id='np_new_payee_details']")).sendKeys("Employee");
		driver.findElement(By.cssSelector("input#add_new_payee")).click();
		driver.findElement(By.id("settingsBox")).click();
		driver.findElement(By.id("logout_link")).click();
		//close browser
        driver.close();

        // kill/quit driver
        driver.quit();
        
		
		
		}
		
		
		
		
		
		
		
		

	}

}
