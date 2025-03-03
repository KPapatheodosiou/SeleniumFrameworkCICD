package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import abstravtComponent.AbstractComponent;

public class CartPage extends AbstractComponent{

	WebDriver driver;	
	
	

	
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="div[class='cartSection'] h3")
	private List<WebElement> cartProducts;
	
	@FindBy(css="div[class*='subtotal'] button")
	WebElement checkoutEle;
	
	
	
	
	public Boolean VerifyProductDisplay(String productName) {
		
		Boolean match = cartProducts.stream().anyMatch(s->s.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	public void clickCheckout() {
		
		checkoutEle.click();
	}
	   
	



}

//List<WebElement> cartProducts=  driver.findElements(By.cssSelector("div[class='cartSection'] h3")); **
//System.out.println( driver.findElement(By.cssSelector("div[class='cartSection'] h3")).getText());
//Boolean match = cartProducts.stream().anyMatch(s->s.getText().equalsIgnoreCase(productName));**
//Assert.assertTrue(match);
//driver.findElement(By.cssSelector("div[class*='subtotal'] button")).click();**
