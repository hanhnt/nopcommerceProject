package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.LoginPageUI;

public class LoginPageObject extends BasePage{
	WebDriver driver;
	
	public LoginPageObject(WebDriver driver) {
		this.driver=driver;
	}
	
	LoginPageUI loginPageUI = new LoginPageUI();
	
	public HomePageObject clickToLoginButton() {
		waitForElementVisible(driver, loginPageUI.LOGIN_LINK);
		clickToElement(driver, loginPageUI.LOGIN_LINK);
		return new HomePageObject(driver);
	}

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

	public String getErrorMessageEmailNotExist() {
		waitForElementVisible(driver, loginPageUI.ERROR_MESSAGE_NOT_EXIST_EMAIL);
		return getElementText(driver, loginPageUI.ERROR_MESSAGE_NOT_EXIST_EMAIL);
	}

}
