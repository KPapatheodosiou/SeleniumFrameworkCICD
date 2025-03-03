package endtoEndMaven.tests;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import endtoEndMave.data.Datareader;
import endtoEndMaven.testsComponents.BaseTest;
import io.github.bonigarcia.wdm.WebDriverManager;
import pageObjects.CartPage;
import pageObjects.CheckOutPage;
import pageObjects.LandingPage;
import pageObjects.OrderPage;
import pageObjects.ProductCatalog;

public class EndtoEndPageObject extends BaseTest{
	
	String productName = "IPHONE 13 PRO";
	@Test (dataProvider="getData", groups = "Purchase") 
	public void submitOrder(HashMap<String,String> input) throws IOException, InterruptedException
	{
		// TODO Auto-generated method stub
		
		String country = "Greece";
		
		//LandingPage landingPage = launchApplication(); Before Test
		
		//login
		landingPage.loginApplication(input.get("email"), input.get("password"));
		//find all products 
		ProductCatalog ProductCataloge = new ProductCatalog(driver);
		List<WebElement>products = ProductCataloge.getProductList();
		//Get product names
		ProductCataloge.addProductToCart(input.get("product"));
		ProductCataloge.clickCard();
		//cartpage
		CartPage cartpage = new CartPage(driver);
		Boolean match = cartpage.VerifyProductDisplay(input.get("product"));
		Assert.assertTrue(match);
		cartpage.clickCheckout();
		//chekoutpage
		CheckOutPage checkoutpage= new CheckOutPage(driver);
		checkoutpage.selectCountry(country);
		checkoutpage.submitOrder();
		String confirmMessage = checkoutpage.confirmMsg();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		
	}
	//System.getProperty("user.dir") FILEPATH of the project
	public String getScrennshot(String testCaseName) throws IOException {
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir")+"//reports//"+testCaseName+".png");
		FileUtils.copyFile(source, file);
		
		return (System.getProperty("user.dir")+"//reports//"+testCaseName+".png");
	}
	
	//Extend Reports --
	
	
	@Test (dependsOnMethods= {"submitOrder"}) // first run the sumbit order and after this test
	public void OrderHistoryTest() {
		landingPage.loginApplication("papatheodosiou@test.com", "Paok1926");
		////tbody //tr //td[2]
		OrderPage orderPage = new OrderPage(driver);
		orderPage.goToOrderPage();
		//orderPage.VerifyProductDisplay(productName);
		Assert.assertTrue(orderPage.VerifyProductDisplay(productName));
		
		
	}
	
	@DataProvider
	public Object[][] getData() throws IOException {
		
		HashMap<String,String> map = new HashMap<String,String>();
		map.put("email", "papatheodosiou@test.com");
		map.put("password", "Paok1926");
		map.put("product","IPHONE 13 PRO" );
		
		HashMap<String,String> map1 = new HashMap<String,String>();
		map1.put("email", "papatheodosiou1@test.com");
		map1.put("password", "Paok1926");
		map1.put("product","ZARA COAT 3" );		
		return new Object[][]	 {{map},{map1}};
		
//		//one way to get data from jason is to create an object and call the method
//		//or I can write the method from DataReader.java in the base Test which is the parent
//		
//		//JSON COULDNNT WORK
//		Datareader datareader = new Datareader();
//		List<HashMap<String,String>> data= datareader.getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\endtoEndMave\\data\\PurchaseOrder.json");
//		return new Object[][]	 {{data.get(0)},{data.get(1)}};
		
	}
	
//	@DataProvider
//	public Object[][] getData() {
//		
//		return new Object[][]	 {{"papatheodosiou@test.com","Paok1926","ZARA COAT 3"},{"papatheodosiou1@test.com","Paok1926","ADIDAS ORIGINAL"}};
//	}
//

	
}
