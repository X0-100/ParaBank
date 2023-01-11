package parabank_sc;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestNGListener.class)
public class AccountOpening {

    WebDriver driver;
    private int initialMainAccountBalance = 1000;

    @BeforeMethod
    public void beforeMethod() {
	driver = new ChromeDriver();
	driver.get("https://parabank.parasoft.com/parabank/index.htm");
    }

    @Test
    public void testOpenSavingsAccount() {
	// Login
	driver.get("https://parabank.parasoft.com/parabank/index.htm");
	driver.findElement(By.name("username")).sendKeys("username");
	driver.findElement(By.name("password")).sendKeys("password");
	driver.findElement(By.xpath("//input[@value='Log In']")).click();

	// navigate to the open new account page
	driver.findElement(By.xpath("//a[text()='Open New Account']")).click();
	driver.findElement(By.xpath("//select[@name='fromAccountId']//option[text()='Checking']")).click();
	driver.findElement(By.xpath("//select[@name='toAccountId']//option[text()='Savings']")).click();
	driver.findElement(By.xpath("//input[@value='Transfer']")).click();

	// Retrieve main account balance
	String mainAccountBalance = driver.findElement(By.xpath("//td[text()='Total Balance']/following-sibling::td"))
		.getText();
	String mainAccountBalanceWithOutDollar = mainAccountBalance.replace("$", "");
	double mainAccountBalanceInDouble = Double.parseDouble(mainAccountBalanceWithOutDollar);
	// Retrieve new savings account balance
	String newSavingsAccountBalance = driver.findElement(By.xpath("//td[text()='Savings']/following-sibling::td"))
		.getText();
	String newSavingsAccountBalanceWithOutDollar = newSavingsAccountBalance.replace("$", "");
	double newSavingsAccountBalanceInDouble = Double.parseDouble(newSavingsAccountBalanceWithOutDollar);

	// Assert that the main account balance is $100 less than the initial balance
	assertEquals(mainAccountBalanceInDouble, initialMainAccountBalance - 100,
		"Main account balance is not correct");
    }

}
