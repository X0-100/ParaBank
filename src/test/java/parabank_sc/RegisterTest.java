package parabank_sc;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import parabank_utils.ReadData;
import parabank_utils.WriteData;

@Listeners(TestNGListener.class)
public class RegisterTest {
    private static WebDriver driver;
    private static WebDriverWait wait;

    @BeforeSuite
    public void beforeMethod() {
	WebDriverManager.chromedriver().setup();

    }

    @BeforeClass
    public void instantiate_driver() throws Exception {
	driver = new ChromeDriver();
	driver.manage().window().maximize();
	wait = new WebDriverWait(driver, Duration.ofSeconds(12));
	String[] headers = { "firstname", "lastname", "street", "city", "state", "zip", "phone", "ssn", "username",
		"password", "repeatedpassword" };
	WriteData.writeData(headers);
    }

    @Test(priority = 1, enabled = true, dataProvider = "testData")
    public void register_testsuccess(

	    String firstname, String lastname, String street, String city, String state, String zipco, String phone,
	    String ssn, String username, String passcode, String repeatpasscode

    ) throws Exception {
	driver.get("https://parabank.parasoft.com/parabank/index.htm");
	wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Register"))).click();

	driver.findElement(By.id("customer.firstName")).sendKeys(firstname);
	driver.findElement(By.id("customer.lastName")).sendKeys(lastname);
	driver.findElement(By.id("customer.address.street")).sendKeys(street);
	driver.findElement(By.id("customer.address.city")).sendKeys(city);
	driver.findElement(By.id("customer.address.state")).sendKeys(state);
	driver.findElement(By.id("customer.address.zipCode")).sendKeys(zipco);
	driver.findElement(By.id("customer.phoneNumber")).sendKeys(phone);
	driver.findElement(By.id("customer.ssn")).sendKeys(ssn);
	driver.findElement(By.id("customer.username")).sendKeys(username);
	driver.findElement(By.id("customer.password")).sendKeys(passcode);
	driver.findElement(By.id("repeatedPassword")).sendKeys(repeatpasscode);
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@value='Register']")));

	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@value='Register']"))).click();

	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("*//a[contains(text(),\"Log Out\")]")));

	Assert.assertEquals(driver.getCurrentUrl(), "https://parabank.parasoft.com/parabank/register.htm");

	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("*//a[contains(text(),\"Log Out\")]")))
		.click();

    }

    @DataProvider(name = "testData")
    public static Object[][] testData() {
	ReadData.getWorkbook("src/test/java/parabank_testData/parabank_user_registration.xlsx");
	int rows = ReadData.getRowCount(0);
	int cells = ReadData.getCellCount(0);
	String[][] data = new String[rows - 1][cells];
	for (int i = 0; i < rows - 1; i++) {
	    for (int j = 0; j < cells; j++) {
		data[i][j] = ReadData.getData(0, i + 1, j);
	    }
	}
	return data;
    }

    @Test(priority = 2, dataProvider = "testData")
    public void register_testfailure(String firstname, String lastname, String street, String city, String state,
	    String zipco, String phone, String ssn, String username, String passcode, String repeatpasscode) {
	driver.findElement(By.id("customer.firstName")).sendKeys(firstname);
	WebElement clearfirstname = driver.findElement(By.id("customer.firstName"));
	clearfirstname.clear();
	driver.findElement(By.id("customer.lastName")).sendKeys(lastname);
	WebElement clearsecondname = driver.findElement(By.id("customer.lastName"));
	clearsecondname.clear();
	driver.findElement(By.id("customer.address.street")).sendKeys(street);
	driver.findElement(By.id("customer.address.city")).sendKeys(city);
	driver.findElement(By.id("customer.address.state")).sendKeys(state);
	driver.findElement(By.id("customer.address.zipCode")).sendKeys(zipco);
	driver.findElement(By.id("customer.phoneNumber")).sendKeys(phone);
	driver.findElement(By.id("customer.ssn")).sendKeys(ssn);
	driver.findElement(By.id("customer.username")).sendKeys(username);
	driver.findElement(By.id("customer.password")).sendKeys(passcode);
	driver.findElement(By.id("repeatedPassword")).sendKeys("ADIFFERENTONE");
	driver.findElement(By.xpath("//input[@value='Register']")).click();

	Assert.assertNotEquals(driver.getCurrentUrl(), "https://parabank.parasoft.com/parabank/register.htm");
    }

    @AfterMethod
    public void afterMethod() {
	driver.quit();
    }
}
