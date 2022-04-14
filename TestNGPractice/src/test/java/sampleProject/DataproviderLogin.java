package sampleProject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DataproviderLogin {


String [] [] loginData = {

{"Admin" , "password123"},
{"Admin" , "admin123"},
{"admin" , "xyz123"},
{"Password" , "xyz123"}


};

@DataProvider (name = "loginData")
public String[][] dataProvider() {



return loginData;
}

@Test(dataProvider= "loginData" )
public void login(String  userName , String Password) {

WebDriverManager.chromedriver().setup();

WebDriver driver = new ChromeDriver();
driver.get("https://opensource-demo.orangehrmlive.com/");
driver.manage().window().maximize();

WebElement userName1 = driver.findElement(By.xpath("//input[@name='txtUsername']"));
userName1.sendKeys(userName);


WebElement password = driver.findElement(By.xpath("//input[@name='txtPassword']"));
password.sendKeys(Password);

WebElement login = driver.findElement(By.xpath("//input[@name='Submit']"));
login.click();
}

}