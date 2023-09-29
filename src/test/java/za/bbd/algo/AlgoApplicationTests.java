package za.bbd.algo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.tomcat.util.bcel.Const;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import za.bbd.Constants;
import za.bbd.algo.controller.DataController;
import za.bbd.algo.model.DataRequest;
import za.bbd.algo.model.DataResponse;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;


@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
class AlgoApplicationTests {

	
	void doTest(Integer sleepDuration, String filePath, WebDriver driver) throws InterruptedException{
		Thread.sleep(sleepDuration);
		WebElement upload = driver.findElement(By.id("upload"));
		Thread.sleep(sleepDuration);
		upload.sendKeys(filePath);
		Thread.sleep(sleepDuration);
		List<WebElement> cards = driver.findElements(By.className("card"));

		for (WebElement cardElement : cards) {
			cardElement.click();
			Thread.sleep(sleepDuration);
		}
	}

	@Test
	void contextLoads() throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("http://localhost:8080/");
		doTest(2000, "C:\\Users\\hewit\\BBD\\algostuff\\test.csv", driver);
		driver.quit();
	}

	@Test
	void large() throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("http://localhost:8080/");
		doTest(2000, "C:\\Users\\hewit\\BBD\\algostuff\\test-large.csv", driver);
		driver.quit();
	}

	/*@Test
	void stress() throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();  
		driver.get("http://localhost:8080/");
		for (int i = 0; i < 1000; i++) {
			doTest(0, "C:\\Users\\hewit\\BBD\\algostuff\\test-large.csv", driver);
		}
		driver.quit();
	}*/
	
	@Autowired
	private DataController controller;

	@Test
	public void Loads() throws Exception {
		assertNotEquals(controller, null);
	}

	@Test
	public void Works() throws Exception {

		ArrayList<DataResponse> responses = new ArrayList<>();
		String[] sorts = { Constants.BUBBLE_SORT, Constants.INSERTION_SORT, Constants.LINEAR_SORT, Constants.QUICK_SORT };
		File myObj = new File("C:\\Users\\hewit\\BBD\\algostuff\\test-large.csv");
		Scanner myReader = new Scanner(myObj);
		String data = myReader.nextLine();
		ArrayList<Integer> intList = new ArrayList<>();
		for (String dataPoint : data.split(",")){
			intList.add(Integer.parseInt(dataPoint));
		}
		myReader.close();
		for (String sort : sorts) {
			DataRequest dr = new DataRequest(
				sort,
				intList
			);

			responses.add(controller.sortData(dr).getBody());
		}

		for (DataResponse dataResponse : responses) {
			assertEquals(dataResponse.getData(), responses.get(0).getData());
		}
	}

	@Test
	public void Stress() throws Exception {
		ArrayList<DataResponse> responses = new ArrayList<>();
		String[] sorts = {Constants.INSERTION_SORT, Constants.LINEAR_SORT, Constants.QUICK_SORT };
		File myObj = new File("C:\\Users\\hewit\\BBD\\algostuff\\test.csv");
		Scanner myReader = new Scanner(myObj);
		String data = myReader.nextLine();
		ArrayList<Integer> intList = new ArrayList<>();
		responses.clear();
		for (String dataPoint : data.split(",")){
			intList.add(Integer.parseInt(dataPoint));
		}
		myReader.close();

		for (int i = 0; i < 100; i ++) {
			for (String sort : sorts) {
				DataRequest dr = new DataRequest(
					sort,
					intList
				);
	
				responses.add(controller.sortData(dr).getBody());
			}
	
			for (DataResponse dataResponse : responses) {
				assertEquals(dataResponse.getData(), responses.get(0).getData());
			}
		}	
	}
}
