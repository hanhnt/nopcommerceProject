package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.CustomerInforUI;

public class MyProductReviewPageObject extends BasePage{
	WebDriver driver;
	
	public MyProductReviewPageObject(WebDriver driver) {
		this.driver=driver;
	}

}
