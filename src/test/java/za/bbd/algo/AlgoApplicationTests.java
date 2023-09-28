package za.bbd.algo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;


@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
class AlgoApplicationTests {

	@Test
	void contextLoads() throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("http://localhost:8080/");
		Thread.sleep(2000);
		WebElement upload = driver.findElement(By.id("upload"));
		Thread.sleep(2000);
		upload.sendKeys("C:\\Users\\paueg001\\Documents\\algostuff\\test.csv");
		Thread.sleep(2000);
		WebElement card = driver.findElement(By.className("card"));
		card.click();
		Thread.sleep(2000);
		driver.quit();
	}

}
