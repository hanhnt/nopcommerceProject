package pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import commons.BaseFactoryPage;
import commons.BasePage;
import pageUIsUser.UserLoginPageUI;

public class LoginPageObjectFactory extends BaseFactoryPage{
	WebDriver driver;
	
	public LoginPageObjectFactory(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath= "//button[@class='button-1 login-button']")
	private WebElement lnLogin;
	
	@FindBy(xpath= "//span[@id='Email-error']")
	private WebElement lbErrorAtEmail;
	
	@FindBy(xpath= "//input[@id='Email']")
	private WebElement txtEmail;
	
	@FindBy(xpath= "//input[@id='Password']")
	private WebElement txtPassword;
	
	@FindBy(xpath= "//span[@id='Email-error']")
	private WebElement lbErrorEmailNotExist;
	
	UserLoginPageUI loginPageUI = new UserLoginPageUI();
	
	public void clickToLoginButton() {
		waitForElementClickable(driver, lnLogin);
		clickToElement(driver, lnLogin);
	}

	public String getErrorMessageAtEmail() {
		waitForElementVisible(driver, lbErrorAtEmail);
		return getElementText(driver, lbErrorAtEmail);
	}

	public void inputToEmailTextbox(String emailValue) {
		waitForElementVisible(driver, txtEmail);
		sendkeyToElement(driver, txtEmail, emailValue);
		
	}

	public void inputToPasswordTextbox(String passwordValue) {
		waitForElementVisible(driver, txtPassword);
		sendkeyToElement(driver, txtPassword, passwordValue);
		
	}

	public String getErrorMessageEmailNotExist() {
		waitForElementVisible(driver, lbErrorEmailNotExist);
		return getElementText(driver, lbErrorEmailNotExist);
	}

}
