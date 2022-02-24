package pageObjectsAdmin;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIsAdmin.AdminProductSearchPageUI;

public class AdminProductSearchPageObject extends BasePage{
	WebDriver driver;
	
	public AdminProductSearchPageObject(WebDriver driver) {
		this.driver= driver;
	}

	public void enterToProductNameTextbox(String searchValue) {
		waitForElementVisible(driver, AdminProductSearchPageUI.SEARCH_TEXTBOX);
		sendkeyToElement(driver, AdminProductSearchPageUI.SEARCH_TEXTBOX, searchValue);
		
	}

	public boolean showingTheMessageUpdatesuccessfully(String contentMessage) {
		waitForElementVisible(driver, AdminProductSearchPageUI.SUCCESS_MESSAGE,contentMessage);
		return isElementDisplay(driver, AdminProductSearchPageUI.SUCCESS_MESSAGE, contentMessage);
	}

	public void clickToSearchButton() {
		waitForElementClickable(driver, AdminProductSearchPageUI.SEARCH_BUTTON);
		clickToElement(driver, AdminProductSearchPageUI.SEARCH_BUTTON);
		
	}

	public boolean isPictureUpdatedDisplay(String productName, String imageName) {
		imageName=imageName.replace(" ", "-").toLowerCase();
		waitForElementVisible(driver, AdminProductSearchPageUI.SEARCH_RESULT,productName, imageName);
		return isElementDisplay(driver, AdminProductSearchPageUI.SEARCH_RESULT,productName, imageName);
	}

	public AdminProductDetailPageObject clickToEditButton(String productName) {
		waitForElementClickable(driver, AdminProductSearchPageUI.EDIT_BUTTON_BY_PRODUCT_NAME, productName);
		clickToElement(driver, AdminProductSearchPageUI.EDIT_BUTTON_BY_PRODUCT_NAME, productName);
		return new AdminProductDetailPageObject(driver);
	}

	public void clickToExpandSearchArea() {
		if(getAttribute(driver, AdminProductSearchPageUI.SEARCH_AREA, "class").contains("fa-angle-down")) {
			clickToElement(driver, AdminProductSearchPageUI.SEARCH_AREA);
		}
		
	}

}
