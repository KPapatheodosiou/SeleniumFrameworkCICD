package abstravtComponent;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageObjects.OrderPage;



public class AbstractComponent {

	WebDriver driver;

	// send from child to parent with super keyword
	public AbstractComponent(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(css = ".btn.btn-custom[routerlink='/dashboard/cart']")
	WebElement clickCard;
	
	@FindBy(css = "button[routerlink='/dashboard/myorders']")
	WebElement clickOrders;
	

	//btn btn-custom
	public void clickCard() {
		clickCard.click();
	}
	
	//somethign dow but idk help
	public OrderPage goToOrderPage() {
		clickOrders.click();
		OrderPage orderPage = new OrderPage(driver);
		return orderPage;
	
	}
	
	

	public void waitForElementToAppear(By findBY) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBY));

	}
	public void waitForWebElementToAppear(WebElement findBY) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
		wait.until(ExpectedConditions.visibilityOf(findBY));

	}
	


	public void waitForElementToDisappear(WebElement ele) throws InterruptedException {

		Thread.sleep(2000);
		// WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
		// wait.until(ExpectedConditions.invisibilityOf(ele));

	}

}
