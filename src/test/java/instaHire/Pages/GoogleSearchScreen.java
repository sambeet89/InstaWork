package instaHire.Pages;

import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

public class GoogleSearchScreen {

	WebDriver driver;

	public GoogleSearchScreen(WebDriver dvr) {
		this.driver = dvr;
		PageFactory.initElements(driver, this);
	}

	@FindBy(name = "q")
	WebElement searchTextBox;

	@FindBy(tagName = "cite")
	List<WebElement> resultUrls;

	@FindBy(xpath = "//div[@id='foot']//a[@class='fl']")
	List<WebElement> paginationItems;

	@FindBy(xpath = "//div[@id='foot']//span[text()='Next']")
	WebElement footerPaginationNext;

	public void enterSearchItem(String seachText) {
		try {

			searchTextBox.sendKeys(seachText);
			Reporter.log("Entered seach item :" + seachText);
		} catch (Exception e) {
			Reporter.log("Unable to search.");
		}
	}

	public void pressEnterKeyOnSearch() {
		searchTextBox.sendKeys(Keys.ENTER);
	}

	public boolean getSearchItemPosition(String exp) {
		int pageCount = 1;
		int TotalresultScanned = 0;
		int onPageCount = 0;
		Boolean res = false;
		boolean continueScan = true;
		try {

			while (footerPaginationNext.isDisplayed() && pageCount < 9 && continueScan == true) {
				onPageCount = 0;

				for (WebElement webElement : resultUrls) {
					TotalresultScanned++;
					onPageCount++;
					if (webElement.getText().equals(exp)) {
						res = true;
						continueScan = false;
						// pageCount++;
						break;
					}

				}
				// System.out.println(resultUrls.size());
				if(continueScan) {
				footerPaginationNext.click();
				pageCount++;
				}
			}
		} catch (NoSuchElementException e) {
			pageCount++;
			Reporter.log("unable to find the next button or scanned all pages");
		}

		if (res == true)
			Reporter.log("expected url found  on seach result.  page number : " + pageCount + ". Item number: "
					+ onPageCount);
		else {
			Reporter.log("Unable to find the expected url on seach result.number of pages scanned : " + pageCount
					+ ". number of search result scanned: " + TotalresultScanned);
		}
		return res;

	}

}
