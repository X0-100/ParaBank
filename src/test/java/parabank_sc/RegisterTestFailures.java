package parabank_sc;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestNGListener.class)
public class RegisterTestFailures {

    WebDriver driver;

    @BeforeMethod
    public void beforeMethod() {
	driver = new ChromeDriver();
	driver.get("https://parabank.parasoft.com/parabank/index.htm");
    }

    @Test
    public void register_testfailure() {
	driver.findElement(By.linkText("Register")).click();
	driver.findElement(By.id("customer.firstName")).sendKeys("");
	driver.findElement(By.id("customer.lastName")).sendKeys("");
	driver.findElement(By.id("customer.address.street")).sendKeys("");
	driver.findElement(By.id("customer.address.city")).sendKeys("");
	driver.findElement(By.id("customer.address.state")).sendKeys("");
	driver.findElement(By.id("customer.address.zipCode")).sendKeys("");
	driver.findElement(By.id("customer.phoneNumber")).sendKeys("");
	driver.findElement(By.id("customer.ssn")).sendKeys("");
	driver.findElement(By.id("customer.username")).sendKeys("");
	driver.findElement(By.id("customer.password")).sendKeys("");
	driver.findElement(By.id("repeatedPassword")).sendKeys("");
	driver.findElement(By.xpath("//input[@value='Register']")).click();
	String expectedTitle = "ParaBank | Account Created!";
	String actualTitle = driver.getTitle();
	Assert.assertNotEquals(actualTitle, expectedTitle);
    }
}
