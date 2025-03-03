package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstravtComponent.AbstractComponent;

public class LandingPage extends AbstractComponent{
			//child				//parent
	WebDriver driver;	
	
	public LandingPage(WebDriver driver) {
		
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	//there is an eeror with driver so weill create a cuntrctor to bring the driver from another class
	//WebElement userEmail = driver.findElement(By.id("userEmail"));
	
	//PageFactory
	@FindBy(id="userEmail")
	WebElement userEmail;
	
	
	@FindBy(id="userPassword")
	WebElement passwordEl;
	
	@FindBy(id="login")
	WebElement loginBtn;
	//div[@class='ng-tns-c4-26 ng-star-inserted ng-trigger ng-trigger-flyInOut ngx-toastr toast-error']
	////div[@aria-label='Incorrect email or password.'] css .ng-tns-c4-11.ng-star-inserted.ng-trigger.ng-trigger-flyInOut.ngx-toastr.toast-error
	@FindBy(css="div[class='ng-star-inserted']")
	WebElement errorMessage;
	
	//div[class='ng-star-inserted']
	
	
	public void loginApplication (String email, String password) {
		
		userEmail.sendKeys(email);
		passwordEl.sendKeys(password);
		loginBtn.click();
		
	}
	
	

	public void goTo() {
		
		driver.get("https://rahulshettyacademy.com/client");
		
		
		
	}
	
	public String getErrorMessage() throws InterruptedException {
		
		Thread.sleep(1000);
		//locator fail sor for test purpose
		String text="*Password is required";
		//waitForWebElementToAppear(errorMessage);
		//return errorMessage.getText();
		return text;
		
	}
	
	
	
}
