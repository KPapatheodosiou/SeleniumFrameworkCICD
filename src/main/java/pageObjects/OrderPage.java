package pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstravtComponent.AbstractComponent;

public class OrderPage extends AbstractComponent {

	WebDriver driver;	

	
	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//tbody //tr //td[2]")
	List<WebElement> productsNames;
	
	
	public Boolean VerifyProductDisplay(String productName) {
		
		Boolean match = productsNames.stream().anyMatch(s->s.getText().equalsIgnoreCase(productName));
		return match;
	}
	
}

