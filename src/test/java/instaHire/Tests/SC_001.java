package instaHire.Tests;

import org.testng.annotations.Test;

import instaHire.Pages.GoogleSearchScreen;
import utils.Driver;
import utils.ExcelUtil;

import org.testng.annotations.BeforeMethod;

import static org.testng.Assert.assertTrue;

import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.AfterTest;

public class SC_001 {
	WebDriver driver;
	ExcelUtil util;

	@BeforeMethod
	public void beforeMethod() {
		util = new ExcelUtil();

		driver.get("https://www.google.com/");

	}

	@Test
	public void VerifySearchResultScreen_TC001() throws InterruptedException {

		Reporter.log("Application started and page is on google search.");

		HashMap<String, String> seachData = util.getRowdata("./Data/GoogleSearch.xlsx", "google", 1);
		GoogleSearchScreen search = new GoogleSearchScreen(driver);
		search.enterSearchItem(seachData.get("SearchItem"));
		search.pressEnterKeyOnSearch();
		assertTrue(search.getSearchItemPosition(seachData.get("ExpectedUrl")),
				"Expected url not found on google search result");

	}

	@AfterMethod
	public void afterMethod() {

	}

	@Parameters("BrowserName")
	@BeforeTest
	public void beforeTest(@Optional("WinChrome") String BrowserName) {
		// Driver initialization and launch browser
		driver = new Driver().getDriver(BrowserName);
	}

	@AfterTest
	public void afterTest() {
		driver.quit();
	}

}
