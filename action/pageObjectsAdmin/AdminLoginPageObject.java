package pageObjectsAdmin;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.server.handler.ClearElement;

import commons.BasePage;
import pageUIsAdmin.AdminLoginPageUI;

public class AdminLoginPageObject extends BasePage{
	WebDriver driver;
	
	public AdminLoginPageObject(WebDriver driver) {
		this.driver=driver;
	}
	
	public void inputToEmailTextbox(String email) {
		waitForElementVisible(driver, AdminLoginPageUI.ADMIN_EMAIL_TEXTBOX);
		sendkeyToElement(driver, AdminLoginPageUI.ADMIN_EMAIL_TEXTBOX, email);
	}
	
	public void inputToPasswordTextbox(String password) {
		waitForElementVisible(driver, AdminLoginPageUI.ADMIN_PASSWORD_TEXTBOX);
		sendkeyToElement(driver, AdminLoginPageUI.ADMIN_PASSWORD_TEXTBOX, password);
	}
	
	public void clickToLoginButton() {
		waitForElementClickable(driver, AdminLoginPageUI.ADMIN_LOGIN_BUTTON);
		clickToElement(driver, AdminLoginPageUI.ADMIN_LOGIN_BUTTON);
	}
	
	public AdminDashboardPageObject loginAsAdmin(String email, String password) {
		inputToEmailTextbox(email);
		inputToPasswordTextbox(password);
		clickToLoginButton();
		return new PageGeneratorManager().getAdminDashboardPage(driver);
	}
}
