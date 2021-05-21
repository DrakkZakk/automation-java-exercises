package exercises;

import java.util.StringTokenizer;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Exercise1 {
	
	WebDriver driver;

	@Test
	public void testCase() {
		WebElement element;
		String s1;
		String s2;
		element = driver.findElement(By.cssSelector("li.ac-gn-item.ac-gn-item-menu.ac-gn-mac > a.ac-gn-link.ac-gn-link-mac"));
		click(element);
		String pageText = driver.findElement(By.xpath("//h2[@class='typography-hero-product-headline']")).getText();
		String pageText2 = driver.findElement(By.xpath("//p[contains(text(), 'Dile hola.')]")).getText();
		
		StringTokenizer st = new StringTokenizer(pageText);
		s1 = st.nextToken();
		s2 = st.nextToken();
		pageText = s1 + " " + s2;
		String expectedText = "Nueva iMac";
		String expectedText2 = "Dile hola.";
		
		Assert.assertEquals(pageText, expectedText);
		Assert.assertEquals(pageText2, expectedText2);
		
		element = driver.findElement(By.id("ac-gn-link-search"));
		click(element);
		element = driver.findElement(By.xpath("//div[@class='ac-gn-searchform-wrapper']/child::input[@id='ac-gn-searchform-input']"));
		sendText("iphone SE", element);
		element = driver.findElement(By.linkText("iPhone SE"));
		click(element);
		String pageTitle = driver.getTitle();
		String expectedTitle = "iPhone SE - Apple (MX)";
		Assert.assertEquals(pageTitle, expectedTitle);
		
		boolean why = driver.findElement(By.linkText("Por qué cambiar")).isEnabled();
		boolean specs = driver.findElement(By.linkText("Especificaciones")).isEnabled();
		boolean btn = driver.findElement(By.cssSelector("a.ac-ln-button")).isEnabled();
		Assert.assertTrue(why);
		Assert.assertTrue(specs);
		Assert.assertTrue(btn);
	}

	@BeforeTest
	public WebDriver setUp() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\jose.morales\\Downloads\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.apple.com/mx/");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		return driver;
	}
	
	public void click(WebElement element) {
		element.click();
	}
	
	public void sendText(String text, WebElement element) {
		element.sendKeys(text);
	}
	
	@AfterTest
	public void closeBrowser() {
		driver.close();
	}
	
}
