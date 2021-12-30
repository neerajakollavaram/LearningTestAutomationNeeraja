package SeleniumBasics;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Assignment3 {

	public static void main(String[] args) throws InterruptedException {
		

		System.setProperty("webdriver.chrome.driver", "C:\\SeleniumBrowserDriverLocation\\chromedriver.exe");

		// open browser
		WebDriver driver = new ChromeDriver();
		
		driver.get("http://zero.webappsecurity.com/");
		driver.manage().window().maximize();
		
		driver.findElement(By.id("signin_button")).click();
		driver.findElement(By.xpath("//input[@id='user_login']")).sendKeys("username");
		driver.findElement(By.cssSelector("#user_password")).sendKeys("password");
		driver.findElement(By.name("submit")).click();
		
		if(driver.findElement(By.id("details-button")).isDisplayed()){
		
		driver.findElement(By.id("details-button")).click();
		driver.findElement(By.id("proceed-link")).click();
		
		driver.findElement(By.linkText("Pay Bills")).click();
		Thread.sleep(1000);
		driver.findElement(By.linkText("Purchase Foreign Currency")).click();
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("input#purchase_cash[type='submit']")).click();
		
		Thread.sleep(1000);
       Alert jsAlert = driver.switchTo().alert();
       Thread.sleep(1000);
       jsAlert.accept();
       
        driver.close();
        driver.quit();
		}
			
		
//########## Naukri ###############


		driver.get("https://www.naukri.com/");
		driver.manage().window().maximize();
		System.out.println("Browser Launched");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//button[text()='GOT IT']")).click();
		Thread.sleep(7000);
		System.out.println("Handled cookies pop-up");
		String text1 = driver.findElement(By.xpath("//p[@class='caption']")).getText();
		System.out.println("Header text: "+ text1);
		String text2 = driver.findElement(By.xpath("//p[@class='desc']")).getText();
		System.out.println("Sub text: "+ text2);

	driver.findElement(By.xpath("//span[contains(text(), 'Later')]")).click();
		Thread.sleep(2000);

		System.out.println("Clicked Later button in the location pop-up successfully");

		driver.close();
		driver.quit();

		System.out.println("Run Successful");





		}


driver.get("https://www.naukri.com/");
driver.manage().window().maximize();

System.out.println("Browser Launched");
Thread.sleep(2000);

driver.findElement(By.xpath("//button[text()='GOT IT']")).click();

System.out.println("Handled cookies pop-up");

String windowHandle = driver.getWindowHandle();
System.out.println(windowHandle);

driver.findElement(By.xpath("//img[contains(@src,'/cognizant-hs-tp-21sep2018.gif')]")).click();

Set<String> windowHandles = driver.getWindowHandles();
List<String> windowHandleList = new ArrayList<>(windowHandles);

int size = windowHandleList.size();
System.out.println(size);
driver.switchTo().window(windowHandleList.get(1));
System.out.println(driver.getTitle());
Thread.sleep(2000);
driver.switchTo().defaultContent();
driver.close();
driver.quit();




}
		
		
		
		
		
		}
	
		
		
		
		

	


