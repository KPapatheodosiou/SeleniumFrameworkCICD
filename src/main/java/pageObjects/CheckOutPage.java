package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import abstravtComponent.AbstractComponent;

public class CheckOutPage extends  AbstractComponent{

	
	WebDriver driver;	

	public CheckOutPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy (css="[placeholder='Select Country']")
	WebElement country;
	
	@FindBy(css=".action__submit")
	WebElement submit;
	
	@FindBy (xpath="(//button[contains(@class,'ta-item')])[1]")
	WebElement selectcountry;
	
	@FindBy (css=".hero-primary")
	WebElement confirmMessage;

	By results = By.cssSelector(".ta-results");
	
	public void selectCountry(String countryName) {
		
		Actions a = new Actions(driver);
		a.sendKeys(country, countryName).build().perform();
		
		waitForElementToAppear(results);
	
		selectcountry.click(); 
		
	}
	
	public void submitOrder() {
		
		submit.click();
	}
	
	
	public String confirmMsg() {
		String message = confirmMessage.getText();
		System.out.println(message);
		return message;
		
		
	}
	
	
}
//Actions a = new Actions(driver);
//a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "india").build().perform(); *** 
//
////wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results"))); 
// 
//driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click(); **
//driver.findElement(By.cssSelector(".action__submit")).click(); ***
//
//String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
//System.out.println(confirmMessage);
//Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));