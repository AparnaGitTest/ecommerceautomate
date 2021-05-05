package homePage;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;



import io.github.bonigarcia.wdm.WebDriverManager;

public class homePageTest {
	
	//ap@am11
	public static WebDriver driver;
	
	@BeforeMethod
	public void setUp()
	{
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		driver.get("https://www.amazon.in/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	public static void clickOnSignIn()
	{
		WebElement signInText=driver.findElement(By.xpath("//*[contains(text(),'Hello, Sign in')]"));
		new Actions(driver).moveToElement(signInText).build().perform();
		
		//click on sign in button
		driver.findElement(By.xpath("(//span[@class='nav-action-inner'])[1]")).click();
	}
	
	public static void login()
	{
		clickOnSignIn();
		driver.findElement(By.id("ap_email")).sendKeys("aparna_acharya1989@yahoo.in");
		driver.findElement(By.id("continue")).click();
		driver.findElement(By.id("ap_password")).sendKeys("ap@am11");
		driver.findElement(By.id("signInSubmit")).click();
		
	}
	
	
	
	//@Test(priority=1)
	public void verifyWhetherOnHomePageAfterLoginTest()
	{
		login();
		String title=driver.getTitle();
		System.out.println(title);
		Assert.assertEquals(title, "Your Amazon.in");
	}
	
	//@Test(priority=2)
	public void verifyUserNameOnHomePageTest()
	{
		login();
		String userName=driver.findElement(By.xpath("//div[@id='nav-tools']//a//div/span")).getText();
		System.out.println(userName);
		Assert.assertEquals(userName, "Hello, Aparna");
	}
	
	@Test(priority=1)
	public void searchFunctionalityIsDisplayed()
	{
		Boolean check=driver.findElement(By.id("twotabsearchtextbox")).isDisplayed();
		Assert.assertTrue(check);
	}
	@Test(priority=2)
	public void getcarduiHeaderList()
	{
		List<WebElement> list=driver.findElements(By.xpath("//div[@class='a-cardui-header']"));
		for(WebElement i:list)
		{
			System.out.println(i.getText());
		}
		Assert.assertEquals(list.size(), 20);
	}
	
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}

}
