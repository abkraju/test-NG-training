package sampleProject;

import static org.testng.Assert.*;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Sample {
	WebDriver driver;
	String userName = "vsandeepkalla@gmail.com";
	String password = "zaq1@wsx";

	String searchFor = "wall clocks for home";
	String enterProductText = "Ajanta AQ-2147 Quartz Glass Abstract Blue Office Clock (214 x 44 x 215 mm )";

	SoftAssert softassert = new SoftAssert();

	@Test
	public void LaunchApp() {

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.amazon.in/");
		String expectedText = "Hello, Sign in";
		String actualText = driver.findElement(By.id("nav-link-accountList-nav-line-1")).getText();
		softassert.assertEquals(actualText, expectedText, "Text Missmatch"); 
	}

	@Test
	public void LoginDetails() {

		WebElement login = driver.findElement(By.id("nav-link-accountList-nav-line-1"));
		Actions action = new Actions(driver);
		action.moveToElement(login).perform();
		driver.findElement(By.className("nav-action-inner")).click();
		boolean loginButton = driver.findElement(By.id("ap_email")).isDisplayed();
		softassert.assertTrue(loginButton, "Doesn`t Open Login Page");
		driver.findElement(By.id("ap_email")).sendKeys(userName);
		driver.findElement(By.id("continue")).click();
		boolean passwordButton = driver.findElement(By.id("ap_password")).isDisplayed();
		softassert.assertTrue(passwordButton, "Doesn`t Open Password Page");
		driver.findElement(By.id("ap_password")).sendKeys(password);
		driver.findElement(By.id("signInSubmit")).click();
		boolean checkLogin = driver.findElement(By.id("nav-link-accountList-nav-line-1")).getText()
				.startsWith("Hello,");
		System.out.println(checkLogin);
		softassert.assertTrue(checkLogin, "Login Page Not Opened");
	}

	@Ignore
	@Test
	public void ProductDetails() throws InterruptedException {
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys(searchFor);
		driver.findElement(By.id("nav-search-submit-button")).click();
		String expectedUrl = "https://www.amazon.in/s?k=wall+clocks+for+home&crid=22UGN17ORHE1A&sprefix=wall+clocks+for+ho%2Caps%2C444&ref=nb_sb_noss_2";
		String actualUrl = driver.getCurrentUrl();
		softassert.assertNotEquals(actualUrl, expectedUrl, "Search Window not opend");
		Thread.sleep(10);
		driver.findElement(By.linkText(enterProductText)).click();
		String expectedUrl2 = "https://www.amazon.in/Ajanta-Quartz-Office-Clock-AQ-2147/dp/B00PXJWJJI/ref=sr_1_6?crid=22UGN17ORHE1A&keywords=wall+clocks+for+home&qid=1646421557&sprefix=wall+clocks+for+ho%2Caps%2C444&sr=8-6";
		String actualUrl2 = driver.getCurrentUrl();
		softassert.assertNotEquals(actualUrl2, expectedUrl2, "Selected Product Window not opend");
	}

	@Ignore
	@Test
	public void AddProductToCart() {
		Set<String> tab = driver.getWindowHandles();
		for (String handles : tab) {
			driver.switchTo().window(handles);
		}
		driver.findElement(By.xpath("//input[@title='Add to Shopping Cart']")).click();
		driver.findElement(By.xpath("//input[@name='proceedToRetailCheckout']")).click();
	}

	@Ignore
	@Test
	public void CardDetailsAndPayment() {
		driver.findElement(By.xpath("//a[@class='a-declarative a-button-text ']")).click();
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys("822");
		driver.quit();
		softassert.assertAll();
	}

}
