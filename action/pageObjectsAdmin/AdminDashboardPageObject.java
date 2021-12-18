package pageObjectsAdmin;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIsAdmin.AdminDashboardPageUI;

public class AdminDashboardPageObject extends BasePage{
	WebDriver driver;
	public AdminDashboardPageObject(WebDriver driver) {
		this.driver=driver;
	}
	public String getDashboardText() {
		waitForElementVisible(driver, AdminDashboardPageUI.DASHBOARD_TEXT);
		return getElementText(driver, AdminDashboardPageUI.DASHBOARD_TEXT);
	}
}
