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
	public AdminProductSearchPageObject openSubMenuByName(String menu, String subMenu) {
		waitForElementClickable(driver,AdminDashboardPageUI.MENU_BY_NAME, menu);
		clickToElement(driver, AdminDashboardPageUI.MENU_BY_NAME,menu);
		
		waitForElementClickable(driver,AdminDashboardPageUI.SUBMENU_BY_NAME, subMenu);
		clickToElement(driver, AdminDashboardPageUI.SUBMENU_BY_NAME,subMenu);
		return new AdminProductSearchPageObject(driver);
	}
	
}
