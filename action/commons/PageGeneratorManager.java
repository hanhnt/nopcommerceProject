package commons;

import org.openqa.selenium.WebDriver;

import pageObjectsAdmin.AdminDashboardPageObject;
import pageObjectsAdmin.AdminLoginPageObject;
import pageObjectsUser.UserAddressPageObject;
import pageObjectsUser.UserHomePageObject;
import pageObjectsUser.UserLoginPageObject;
import pageObjectsUser.UserMyProductReviewPageObject;
import pageObjectsUser.UserRegisterPageObject;
import pageObjectsUser.UserRewardPointPageObject;

public class PageGeneratorManager {
	
	public static UserHomePageObject getUserHomePage(WebDriver driver) {
		return new UserHomePageObject(driver);
	}

	public static UserLoginPageObject getUserLoginPage(WebDriver driver) {
		return new UserLoginPageObject(driver);
	}
	
	public static UserRegisterPageObject getUserRegisterPage(WebDriver driver) {
		return new UserRegisterPageObject(driver);
	}
	
	public static UserAddressPageObject getUserAddressPage (WebDriver driver) {
		return new UserAddressPageObject(driver);
	}
	
	public static UserMyProductReviewPageObject getUserMyProductReviewPage (WebDriver driver) {
		return new UserMyProductReviewPageObject(driver);
	}
	
	public static UserRewardPointPageObject getUserRewardPointPage (WebDriver driver) {
		return new UserRewardPointPageObject(driver);
	}
	
	public static AdminLoginPageObject getAdminLoginPage(WebDriver driver) {
		return new AdminLoginPageObject(driver);
	}
	
	public static AdminDashboardPageObject getHomePageAdmin(WebDriver driver) {
		return new AdminDashboardPageObject(driver);
	}

	public static AdminDashboardPageObject getAdminDashboardPage(WebDriver driver) {
		return new AdminDashboardPageObject(driver);
	}
}
