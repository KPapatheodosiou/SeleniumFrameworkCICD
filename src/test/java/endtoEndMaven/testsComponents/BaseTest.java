package endtoEndMaven.testsComponents;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.github.bonigarcia.wdm.WebDriverManager;
import pageObjects.LandingPage;

public class BaseTest {
	
public WebDriver driver; //global variable
public LandingPage landingPage;

public WebDriver initializeDriver() throws IOException {
	
	//properties class
	
	Properties prop = new Properties();							//user.dir easier for all systems
	FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\resources\\GlobalData.properties"); //C:\\Users\\Administrator\\Desktop\\Eclipse\\Eclipse Tests\\EndtoEndMaven
	prop.load(fis);
	
	String browserName = prop.getProperty("browser");
	
	
	if(browserName.equalsIgnoreCase("chrome")) {
	WebDriverManager.chromedriver().setup();
	driver = new ChromeDriver();
	
	
	
	
	}
	else if(browserName.equalsIgnoreCase("fireforx")) {					
		//firefox
		
	}else if(browserName.equalsIgnoreCase("edge")) {
		//edge
		
		
	}
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	driver.manage().window().maximize();
	return driver;
	
}

@BeforeMethod
public LandingPage launchApplication() throws IOException {
	
	driver = initializeDriver();
	//create object for Landing Page
	landingPage = new LandingPage(driver);
	// open link
	landingPage.goTo();

	return landingPage;
	
}
	
@AfterMethod
public void tearDown() {
	driver.quit();
}

}
