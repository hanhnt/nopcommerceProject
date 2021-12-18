package pageObjectsUser;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIsUser.UserCustomerInforUI;

public class UserMyProductReviewPageObject extends BasePage{
	WebDriver driver;
	
	public UserMyProductReviewPageObject(WebDriver driver) {
		this.driver=driver;
	}
}
