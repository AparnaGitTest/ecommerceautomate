package homePage;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class searchFunctionality {
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
	

	public void acceptAnyData()
	{
		SoftAssert softassert=new SoftAssert() ;
		WebElement search=driver.findElement(By.id("twotabsearchtextbox"));
		
		String addAlphabets="ABCD";
		String addNumbers="1234";
		String addSpecialCharacters="@3%2#!*";
		
		search.sendKeys(addAlphabets);
		softassert.assertEquals(addAlphabets, search.getAttribute("value")+"1");
		System.out.println(search.getAttribute("value"));
		search.clear();
		
		search.sendKeys(addNumbers);
		softassert.assertEquals(addNumbers, search.getAttribute("value"));
		System.out.println(search.getAttribute("value"));
		search.clear();
		
		
		search.sendKeys(addSpecialCharacters);
		softassert.assertEquals(addSpecialCharacters, search.getAttribute("value"));
		System.out.println(search.getAttribute("value"));
		search.clear();
		
		softassert.assertAll();
		
		
	}
	
	//@Test(priority=1)
	public void validateWhetherSearchIsWorking() 
	{
		WebElement search=driver.findElement(By.id("twotabsearchtextbox"));
		search.sendKeys("Java");
		search.sendKeys(Keys.ENTER);
		Boolean check=false;
		System.out.println(driver.getTitle());
		if(driver.getTitle().contains("Java"))
		{
			check=true;
		}
		Assert.assertTrue(check);
	}
	
	@Test(priority=2)
	public void verfiySearchWithResultsTEST()
	{
		WebElement search=driver.findElement(By.id("twotabsearchtextbox"));
		search.sendKeys("samsung");
		search.sendKeys(Keys.ENTER);
		List<WebElement> listsearch=driver.findElements(By.xpath("//div[@class='sg-col-inner']//h2//span"));
		for(WebElement i:listsearch)
		{
			if(i.getText().contains("Samsung"))
			{
				System.out.println(i.getText()+"-------SAMSUNG-----------");
			}
			else
			{
				System.out.println(i.getText()+"-------XXXXXX--------------");
			}
		}
		
	}
	
	//div[@class='sg-col-inner']//h2//span
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}


}
