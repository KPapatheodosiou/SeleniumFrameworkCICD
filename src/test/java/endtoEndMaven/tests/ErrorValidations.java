package endtoEndMaven.tests;

import java.io.IOException;
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
import org.testng.annotations.Test;

import endtoEndMaven.testsComponents.BaseTest;
import io.github.bonigarcia.wdm.WebDriverManager;
import pageObjects.CartPage;
import pageObjects.CheckOutPage;
import pageObjects.LandingPage;
import pageObjects.ProductCatalog;

public class ErrorValidations extends BaseTest{

	@Test
	public void LoginErrorValidation() throws IOException, InterruptedException
	{
		// TODO Auto-generated method stub
		String productName = "ZARA COAT 3";
		String country = "Greece";
		
		//LandingPage landingPage = launchApplication(); Before Test
		
		//login
		landingPage.loginApplication("papatheodosiou@test.com", "Paok1926asd");
		
			////div[@aria-label='Incorrect email or password.']
			//div[aria-label='Incorrect email or password.']
			
		//String text=landingPage.getErrorMessage();
		System.out.println(landingPage.getErrorMessage());
		Assert.assertEquals("*Password is required", landingPage.getErrorMessage());

		
	}
	
	
	@Test //(dependsOnMethods= {"submitOrder"})
	public void cardcheck() throws InterruptedException {
		
		
		
		landingPage.loginApplication("papatheodosiou@test.com", "Paok1926");
		//find all products 
		ProductCatalog ProductCataloge = new ProductCatalog(driver);
		List<WebElement>products = ProductCataloge.getProductList();
		//Get product names VerifyProductDisplay

		ProductCataloge.addProductToCart("ZARA COAT 3");
		ProductCataloge.clickCard();
		//cartpage
		CartPage cartpage = new CartPage(driver);
		Boolean match = cartpage.VerifyProductDisplay("ZARA COAT 3");
		Assert.assertTrue(match);
		
		
	}

}
