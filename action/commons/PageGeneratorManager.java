package commons;

import org.openqa.selenium.WebDriver;

import pageObjects.AddressPageObject;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.MyProductReviewPageObject;
import pageObjects.RegisterPageObject;
import pageObjects.RewardPointPageObject;

public class PageGeneratorManager {
	
	public static HomePageObject getHomePage(WebDriver driver) {
		return new HomePageObject(driver);
	}

	public static LoginPageObject getLoginPage(WebDriver driver) {
		return new LoginPageObject(driver);
	}
	
	public static RegisterPageObject getRegisterPage(WebDriver driver) {
		return new RegisterPageObject(driver);
	}
	
	public static AddressPageObject getAddressPage (WebDriver driver) {
		return new AddressPageObject(driver);
	}
	
	public static MyProductReviewPageObject getMyProductReviewPage (WebDriver driver) {
		return new MyProductReviewPageObject(driver);
	}
	
	public static RewardPointPageObject getRewardPointPage (WebDriver driver) {
		return new RewardPointPageObject(driver);
	}
}
