package homePage;

import java.util.Iterator;
import java.util.Set;
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

import io.github.bonigarcia.wdm.WebDriverManager;

public class shoppingCart {
	
	
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
		
		
		@Test
		public void validate15ItemLimitInCart()
		{
			WebElement search=driver.findElement(By.id("twotabsearchtextbox"));
			search.sendKeys("laptop");
			search.sendKeys(Keys.ENTER);
			
			WebElement laptop=driver.findElement(By.xpath("(//div[@class='sg-col-inner']//h2//span)[2]"));
			laptop.click();
			
			Set<String> it=driver.getWindowHandles();
			
			Iterator<String> itr= it.iterator();
			String firstwindow=itr.next();
			String secondwindow=itr.next();
			driver.switchTo().window(secondwindow);
			
			WebElement cartButton=driver.findElement(By.id("add-to-cart-button"));
			cartButton.click();
			
			WebElement cartItem=driver.findElement(By.xpath("//div[@id='nav-cart-count-container']/span"));
			
			Assert.assertEquals(Integer.parseInt(cartItem.getText()), 1);
			
			cartItem.click();
		
		}
		
		@AfterMethod
		public void tearDown()
		{
			driver.quit();
		}

}

