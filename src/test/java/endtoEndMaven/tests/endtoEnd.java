package endtoEndMaven.tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;
import pageObjects.LandingPage;

public class endtoEnd {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String productName = "QWERTY";
		//WebDriver
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		//open link
		driver.get("https://rahulshettyacademy.com/client");
		
		//create object for Landing Page
		LandingPage landingPage = new LandingPage(driver);
		
		//login
		driver.findElement(By.id("userEmail")).sendKeys("papatheodosiou@test.com");
		driver.findElement(By.id("userPassword")).sendKeys("Paok1926");
		driver.findElement(By.id("login")).click();
		
		//find all products 
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("mb-3")));
		List<WebElement> products = driver.findElements(By.className("mb-3")); //CLASSNAME no dot  CSS SLECTOR with dot
		//div[class*='col-lg-4'] b
	
		WebElement prod = products.stream().filter(product->
		product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null); //?? CLASSNAME CSS SLECTOR
//		
		System.out.println(prod);  
		prod.findElement(By.cssSelector("div[class='card-body'] button:last-of-type")).click(); 
		//driver.findElement(By.xpath("//div[@class='container']//div[1]//div[1]//div[1]//button[2]")).click(); //previus dont work idk??
		
		
		//checked explicitywait for loeading
		//WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		//ng-animating
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		
		driver.findElement(By.cssSelector(".btn.btn-custom[routerlink='/dashboard/cart']")).click();	
		
		//check products in cart
		List<WebElement> cartProducts=  driver.findElements(By.cssSelector("div[class='cartSection'] h3"));
		
		System.out.println( driver.findElement(By.cssSelector("div[class='cartSection'] h3")).getText());
		
		Boolean match = cartProducts.stream().anyMatch(s->s.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(match);
		
		
		driver.findElement(By.cssSelector("div[class*='subtotal'] button")).click();
		
		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "india").build().perform();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		
		driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
		driver.findElement(By.cssSelector(".action__submit")).click();
		
		String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
		System.out.println(confirmMessage);
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		
		driver.quit();
	}

}
