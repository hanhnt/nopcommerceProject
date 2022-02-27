package pageObjectsUser;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIsUser.UserLoginPageUI;
import pageUIsUser.UserLoginPageUIVerify;

public class UserLoginPageObjectVerify extends BasePage{
	WebDriver driver;
	
	public UserLoginPageObjectVerify(WebDriver driver) {
		this.driver=driver;
	}
	
	UserLoginPageUIVerify loginPageUI = new UserLoginPageUIVerify();

	public String getErrorMessageAtEmail() {
		waitForElementVisible(driver, loginPageUI.ERROR_MESSAGE_AT_EMAIL);
		return getElementText(driver, loginPageUI.ERROR_MESSAGE_AT_EMAIL);
	}

	public void inputToEmailTextbox(String emailValue) {
		waitForElementVisible(driver, loginPageUI.EMAIL_TEXTBOX);
		sendkeyToElement(driver, loginPageUI.EMAIL_TEXTBOX, emailValue);
		
	}

	public void inputToPasswordTextbox(String passwordValue) {
		waitForElementVisible(driver, loginPageUI.PASSWORD_TEXTBOX);
		sendkeyToElement(driver, loginPageUI.PASSWORD_TEXTBOX, passwordValue);
		
	}
	
	public UserHomePageObject clickToLoginButton() {
		waitForElementVisible(driver, loginPageUI.LOGIN_LINK);
		clickToElement(driver, loginPageUI.LOGIN_LINK);
		return new UserHomePageObject(driver);
	}
	
	public UserHomePageObject loginAsUser(String email, String password) {
		inputToEmailTextbox(email);
		inputToPasswordTextbox(password);
		return clickToLoginButton();
	}

	public String getErrorMessageEmailNotExist() {
		waitForElementVisible(driver, loginPageUI.ERROR_MESSAGE_NOT_EXIST_EMAIL);
		return getElementText(driver, loginPageUI.ERROR_MESSAGE_NOT_EXIST_EMAIL);
	}

	public boolean isErrorMessageEmailDisplay() {
		waitForElementVisible(driver, loginPageUI.ERROR_MESSAGE_AT_EMAIL);
		return isElementDisplay(driver, loginPageUI.ERROR_MESSAGE_AT_EMAIL);
	}

}
