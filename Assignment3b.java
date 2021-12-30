package SeleniumBasics;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Assignment3b {

	public static void main(String[] args) throws InterruptedException {
		
		WebDriver driver = new ChromeDriver();
		
		
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


