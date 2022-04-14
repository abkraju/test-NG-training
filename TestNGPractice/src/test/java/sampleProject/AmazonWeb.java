package sampleProject;

import java.time.Duration;

import org.apache.xmlbeans.impl.xb.xsdschema.ListDocument.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AmazonWeb {
	static WebDriver driver = null;
	private static String searchProduct;
	private String select;

	public void BrowserLaunch() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://www.amazon.in/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	}

	public void Login() {
		WebElement login = driver.findElement(By.id("nav-link-accountList"));
		Actions action = new Actions(driver);
		action.moveToElement(login).perform();
		driver.findElement(By.className("nav-action-inner")).click();
		driver.findElement(By.id("ap_email")).sendKeys("9092005345");
		driver.findElement(By.id("continue")).click();
		driver.findElement(By.id("ap_password")).sendKeys("Raju@1281");
		driver.findElement(By.id("signInSubmit")).click();
	}

	public void SearchBar() {
		WebElement searchbar = driver.findElement(By.id("twotabsearchtextbox"));
		searchbar.click();
		searchbar.sendKeys("Iphone");
		driver.findElement(By.id("nav-search-submit-button")).click();
	}
	public void SearchItem() {
		List<WebElement> searchitem=driver.findElements(By.xpath("//span[@class='a-size-medium-plus a-color-base a-text-normal']"));
		for(WebElement listofElements :searchitem) {
		System.out.println(listofElements.getText());
		}
	}

	public static void main(String[] args) throws InterruptedException {
		AmazonWeb Page = new AmazonWeb();
		Page.BrowserLaunch();
		Page.Login();
		Page.SearchBar();
		Page.SearchItem();
        driver.quit();
	}
}
