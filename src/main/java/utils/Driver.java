package utils;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestContext;
import org.testng.ITestResult;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

public class Driver {

	ExcelUtil util = new ExcelUtil();

	// Returns the driver after initialization
	/**
	 * @return  this return Driver after creation 
	 */
	public WebDriver getDriver(String browserName) {
		WebDriver driver = null;
		String driverBasePath= "./Drivers/";
		String browser= browserName;
		  
        // switch statement with int data type 
        switch (browser) { 
        case "WinIE": 
        	System.setProperty("webdriver.ie.driver", driverBasePath+"IEDriverServer.exe");
        	
        	DesiredCapabilities ieCaps = DesiredCapabilities.internetExplorer();
        	ieCaps.setCapability(InternetExplorerDriver.INITIAL_BROWSER_URL, "http://www.google.com/");
        	driver = new InternetExplorerDriver(ieCaps);
            break; 
        case "WinChrome": 
        	 System.setProperty("webdriver.chrome.driver", driverBasePath+"chromedriver.exe");
        	   driver = new ChromeDriver();
        	   driver.manage().window().maximize();
            break; 
        case "WinFF": 
        	System.setProperty("webdriver.gecko.driver",driverBasePath+"geckodriver.exe");
    		 driver = new FirefoxDriver();  
    		 driver.manage().window().maximize();
            break; 
        case "MacChorme": 
           
            break; 
       
        default: 
          
            break; 
        } 
		
		driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
		return driver;
		
		
		
	}

}
