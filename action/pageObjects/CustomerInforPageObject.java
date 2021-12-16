package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.HomePageUI;
import pageUIs.CustomerInforUI;

public class CustomerInforPageObject extends BasePage {
	private WebDriver driver;
	
	public CustomerInforPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public String getMyAccountPageTitle() {
		waitForElementVisible(driver, CustomerInforUI.MY_ACCOUNT_PAGE_TITLE);
		return getElementText(driver, CustomerInforUI.MY_ACCOUNT_PAGE_TITLE);
	}
	
	

}
