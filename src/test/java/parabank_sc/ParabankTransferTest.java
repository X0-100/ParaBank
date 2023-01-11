package parabank_sc;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestNGListener.class)
public class ParabankTransferTest {
    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
	// Set the location of the Chrome driver executable
	System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");

	// Create a new instance of the Chrome driver
	driver = new ChromeDriver();

	// Navigate to the Parabank website
	driver.get("https://parabank.parasoft.com/parabank/index.htm");
    }

    @Test

    public void testTransferFunds() {
	// Log in to Parabank
	driver.findElement(By.name("username")).sendKeys("john");
	driver.findElement(By.name("password")).sendKeys("demo");
	driver.findElement(By.xpath("//input[@value='Log In']")).click();

	// Click on the "Transfer Funds" link
	driver.findElement(By.linkText("Transfer Funds")).click();

	// Fill out the transfer form
	driver.findElement(By.name("fromAccountId")).sendKeys("12212");
	driver.findElement(By.name("toAccountId")).sendKeys("54321");
	driver.findElement(By.name("amount")).sendKeys("100");
	driver.findElement(By.name("desc")).sendKeys("Test transfer");
	driver.findElement(By.xpath("//input[@value='Transfer']")).click();

	// Verify that the transfer was successful
	WebElement message = driver.findElement(By.xpath("//table//tr[2]/td[2]"));
	Assert.assertEquals(message.getText(), "Transfer Successful!");
    }

    @AfterMethod
    public void tearDown() {
	// Close the browser
	driver.quit();
    }
}
