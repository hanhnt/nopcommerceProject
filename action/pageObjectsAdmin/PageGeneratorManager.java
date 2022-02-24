package pageObjectsAdmin;

import org.openqa.selenium.WebDriver;

import pageObjectsAdmin.AdminDashboardPageObject;
import pageObjectsAdmin.AdminLoginPageObject;
import pageObjectsJQuery.HomePageObject;
import pageObjectsUser.UserAddressPageObject;
import pageObjectsUser.UserHomePageObject;
import pageObjectsUser.UserLoginPageObject;
import pageObjectsUser.UserMyProductReviewPageObject;
import pageObjectsUser.UserRegisterPageObject;
import pageObjectsUser.UserRewardPointPageObject;

public class PageGeneratorManager {
	
	public static AdminLoginPageObject getAdminLoginPage(WebDriver driver) {
		return new AdminLoginPageObject(driver);
	}

	public static AdminDashboardPageObject getAdminDashboardPage(WebDriver driver) {
		return new AdminDashboardPageObject(driver);
	}
	
	public static AdminProductDetailPageObject getAdminProductDetailPage(WebDriver driver) {
		return new AdminProductDetailPageObject(driver);
	}
	
	public static AdminProductSearchPageObject getAdminProductSearchPage(WebDriver driver) {
		return new AdminProductSearchPageObject(driver);
	}

}
