package sampleProject;

import java.time.Duration;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

public class AmazonSampleTest {
	static WebDriver driver = null;
	private String searchProduct;
	private String select;

	@Test(priority = 0)
	void LaunchBrowser() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://www.amazon.in/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	}

	@Test(priority = 1)
	void SearchProduct() {
		searchProduct = ("Iphone");
		//select = "Ajanta AQ-2147 Quartz Glass Abstract Blue Office Clock (214 x 44 x 215 mm )";
	   	WebElement login = driver.findElement(By.id("nav-link-accountList"));
		Actions action = new Actions(driver);
		action.moveToElement(login).perform();
		driver.findElement(By.className("nav-action-inner")).click();
		driver.findElement(By.id("ap_email")).sendKeys("9092005345");
		driver.findElement(By.id("continue")).click();
		driver.findElement(By.id("ap_password")).sendKeys("Raju@1281");
		driver.findElement(By.id("signInSubmit")).click();
	}

	@Test(priority=2)
	void Element() {
	//CharSequence searchProduct = null;
	driver.findElement(By.id("twotabsearchtextbox")).sendKeys(searchProduct);
	driver.findElement(By.id("nav-search-submit-button")).click();
	//String select = null;
	driver.findElement(By.linkText(select)).click();
	}
	
	@Test(priority=3)
	void AddtoCart() {
	Set<String> Tab = driver.getWindowHandles();
	for (String Handles : Tab) {
		driver.switchTo().window(Handles);
	}
	driver.findElement(By.xpath("//input[@title='Add to Shopping Cart']")).click();
	driver.findElement(By.xpath("//input[@name='proceedToRetailCheckout']")).click();	
	}
	
	@Test(priority=4)
	void AddressandPayment() {
	driver.findElement(By.xpath("//a[@class='a-declarative a-button-text ']")).click();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	driver.findElement(By.xpath("//input[@type='password']")).sendKeys("822");
	driver.findElement(By.xpath("(//input[@class='a-button-input a-button-text'])[4]")).click();
	driver.findElement(By.xpath("(//input[@title='Place Your Order and Pay'])[1]")).click();
	}
 		
}	
