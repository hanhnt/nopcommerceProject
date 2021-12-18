package pageObjectsUser;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIsUser.UserCustomerInforUI;

public class UserAddressPageObject extends BasePage{
	WebDriver driver;
	
	public UserAddressPageObject(WebDriver driver) {
		this.driver=driver;
	}

}
