package pageObjectsJQuery;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIsJQuery.HomePageUI;

public class HomePageObject extends BasePage {
	WebDriver driver;
	public HomePageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void openPageNumber(String pageNumber) {
		waitForElementClickable(driver, HomePageUI.PAGINATION, pageNumber);
		clickToElement(driver, HomePageUI.PAGINATION, pageNumber);

	}

	public boolean isPageNumberDisplay(String pageNumber) {
		waitForElementVisible(driver, HomePageUI.PAGE_NUMBER_DISPLAY, pageNumber);
		return isElementDisplay(driver, HomePageUI.PAGE_NUMBER_DISPLAY, pageNumber);
	}

	public void inputToTextboxUnderHeader(String headerName, String valueInput) {
		waitForElementVisible(driver, HomePageUI.TEXTBOX_UNDER_HEADER, headerName);
		sendkeyToElement(driver,HomePageUI.TEXTBOX_UNDER_HEADER , valueInput, headerName);
		
	}

	public void deletedRowByCountry(String countryName) {
		waitForElementClickable(driver, HomePageUI.DELETED_ICON, countryName);
		clickToElement(driver, HomePageUI.DELETED_ICON, countryName);		
	}
	
	public void pressEnterAfterInput(String headerName, String valueInput) {
		waitForElementVisible(driver, HomePageUI.TEXTBOX_UNDER_HEADER, headerName);
		sendkeyToElement(driver,HomePageUI.TEXTBOX_UNDER_HEADER , valueInput, headerName);
		pressKeyToElement(driver, HomePageUI.TEXTBOX_UNDER_HEADER, Keys.ENTER, headerName);
		
	}

	public boolean isValueShowCorrectlyAfterSearch(String female, String country, String male, String total) {
		waitForElementVisible(driver, HomePageUI.VALUE_UNDER_TEXTBOX_AFTER_SEARCH, female,country, male, total);
		return isElementDisplay(driver, HomePageUI.VALUE_UNDER_TEXTBOX_AFTER_SEARCH, female,country, male, total);
	}

	public void inputDataIntoTextboxByHeader(String headerName, String rowNumber, String value) {
		waitForElementVisible(driver, HomePageUI.FIELDS_UNDER_HEADER_DATA_TABLE, headerName, rowNumber);
		sendkeyToElement(driver,HomePageUI.FIELDS_UNDER_HEADER_DATA_TABLE , value, headerName, rowNumber);
		
	}
	
	public void clickToAddButton() {
		waitForElementClickable(driver, HomePageUI.ADD_BUTTON);
		clickToElement(driver, HomePageUI.ADD_BUTTON);
		
	}

	public void selectDataAtOriginDropdown(String headerName, String rowNumber,String value) {
		waitForElementVisible(driver, HomePageUI.FIELDS_UNDER_HEADER_DATA_TABLE, headerName, rowNumber);
		selectItemInDefaultDropdown(driver,HomePageUI.FIELDS_UNDER_HEADER_DATA_TABLE , value, headerName, rowNumber);
		
	}

	public void clickToCheckboxUnderPoster(String headerName, String rowNumber) {
		waitForElementClickable(driver, HomePageUI.FIELDS_UNDER_HEADER_DATA_TABLE, headerName, rowNumber);
		clickToElement(driver,HomePageUI.FIELDS_UNDER_HEADER_DATA_TABLE , headerName, rowNumber);		
	}

	public void clickToAction(String actionName, String rowNumber) {
		waitForElementVisible(driver, HomePageUI.FIELDS_UNDER_HEADER_DATA_TABLE, actionName, rowNumber);
		clickToElement(driver,HomePageUI.FIELDS_UNDER_HEADER_DATA_TABLE , actionName, rowNumber);
	}
}
