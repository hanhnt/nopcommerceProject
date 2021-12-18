package pageObjectsUser;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIsUser.UserHomePageUI;

public class UserHomePageObject extends BasePage {
	private WebDriver driver;
	
	public UserHomePageObject(WebDriver driver) {
		this.driver = driver;
	}

	public UserRegisterPageObject openRegisterPage() {
		waitForElementVisible(driver, UserHomePageUI.USER_REGISTER_LINK);
		clickToElement(driver, UserHomePageUI.USER_REGISTER_LINK);
		return new UserRegisterPageObject(driver);
	}
	
	public UserLoginPageObject openLoginPage() {
		waitForElementVisible(driver, UserHomePageUI.USER_LOGIN_LINK);
		clickToElement(driver, UserHomePageUI.USER_LOGIN_LINK);
		return new UserLoginPageObject(driver);
	}

	public String getWelomeText() {
		waitForElementVisible(driver, UserHomePageUI.USER_WELCOME_TEXT);
		return getElementText(driver, UserHomePageUI.USER_WELCOME_TEXT);
	}

	public boolean isMyAccountLinkDisplay() {
		waitForElementVisible(driver, UserHomePageUI.USER_MY_ACCOUNT_LINK);
		return isElementDisplay(driver, UserHomePageUI.USER_MY_ACCOUNT_LINK);
	}

	public UserCustomerInforPageObject clickToMyAccountLink() {
		waitForElementVisible(driver, UserHomePageUI.USER_MY_ACCOUNT_LINK);
		clickToElement(driver, UserHomePageUI.USER_MY_ACCOUNT_LINK);
		return new UserCustomerInforPageObject(driver);
	}

}
