package pageObjectsUser;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIsUser.UserCustomerInforUI;
import pageUIsUser.UserHomePageUI;

public class UserCustomerInforPageObject extends BasePage {
	private WebDriver driver;
	
	public UserCustomerInforPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public String getMyAccountPageTitle() {
		waitForElementVisible(driver, UserCustomerInforUI.MY_ACCOUNT_PAGE_TITLE);
		return getElementText(driver, UserCustomerInforUI.MY_ACCOUNT_PAGE_TITLE);
	}
}
