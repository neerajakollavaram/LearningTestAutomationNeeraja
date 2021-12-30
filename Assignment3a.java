package SeleniumBasics;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Assignment3a {

	public static void main(String[] args) throws InterruptedException {
		
		
		WebDriver driver = new ChromeDriver();

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
	
	



		
	}

