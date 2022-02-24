package pageObjectsAdmin;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import commons.BasePage;
import pageUIsAdmin.AdminProductDetailPageUI;

public class AdminProductDetailPageObject extends BasePage {
	WebDriver driver;
	public AdminProductDetailPageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	public void scrollToUploadButton(String blockName) {
		waitForElementVisible(driver, AdminProductDetailPageUI.BLOCK_NAME, blockName);
		scrollToElementOnDown(driver, AdminProductDetailPageUI.BLOCK_NAME, blockName);		
	}

	public void clickToExpandPanelByBlockName(String blockName) {
		String toolgleIconStatus= getAttribute(driver,AdminProductDetailPageUI.TOOL_BUTTON_BY_BLOCK_NAME,"class", blockName);
		if(toolgleIconStatus.contains("fa-plus")) {
			waitForElementClickable(driver, AdminProductDetailPageUI.TOOL_BUTTON_BY_BLOCK_NAME, blockName);
			clickToElement(driver, AdminProductDetailPageUI.TOOL_BUTTON_BY_BLOCK_NAME, blockName);
		}
	}

//	public void uploadFileByFileName(String blockName, String fileName) {		
//		waitForElementVisible(driver,AdminProductDetailPageUI.UPLOAD_FILE_BUTTON_BY_BLOCK_NAME, blockName);
//		sendkeyToElement(driver,AdminProductDetailPageUI.UPLOAD_FILE_BUTTON_BY_BLOCK_NAME,fileName, blockName);
//	}
	
	
	public boolean uploadFileSuccessfully(String imageFile) {
		imageFile=imageFile.split("\\.")[0];
		waitForElementVisible(driver,AdminProductDetailPageUI.IMAGE_UPDATE_SUCCESSFULLY, imageFile);
		return isElementDisplay(driver, AdminProductDetailPageUI.IMAGE_UPDATE_SUCCESSFULLY, imageFile);

	}
	public void inputIntoAltTextbox(String altText) {
		waitForElementVisible(driver, AdminProductDetailPageUI.ALT_TEXTBOX);
		sendkeyToElement(driver, AdminProductDetailPageUI.ALT_TEXTBOX, altText);

	}
	public void inputToTitleTextbox(String titleText) {
		waitForElementVisible(driver, AdminProductDetailPageUI.TITLE_TEXTBOX);
		sendkeyToElement(driver, AdminProductDetailPageUI.TITLE_TEXTBOX,
				titleText);

	}
//	public void inputToDisplayOrderTextbox(String displayOrderText) {
//		waitForElementVisible(driver, AdminProductDetailPageUI.DISPLAY_ORDER_TEXTBOX);
//		sendkeyToElement(driver, AdminProductDetailPageUI.DISPLAY_ORDER_TEXTBOX,displayOrderText);
//
//	}
	
	public void inputToDisplayOrderTextbox(String blockName) {
		waitForElementVisible(driver, AdminProductDetailPageUI.INCREASE_BUTTON_AT_BLOCK_NAME, blockName);
		clickToElement(driver, AdminProductDetailPageUI.INCREASE_BUTTON_AT_BLOCK_NAME, blockName);

	}
	
	public void clickToAddProductPictureButton() {
		waitForElementClickable(driver, AdminProductDetailPageUI.ADD_PRODUCT_PICTURE_BUTTON);
		clickToElement(driver, AdminProductDetailPageUI.ADD_PRODUCT_PICTURE_BUTTON);

	}
	public boolean isPictureInforDisplay(String productImage, String altValue,
			String titleValue) {
		waitForElementVisible(driver,
				AdminProductDetailPageUI.PICTURE_INFOR_AFTER_EDIT, productImage,
				altValue, titleValue);
		return isElementDisplay(driver,
				AdminProductDetailPageUI.PICTURE_INFOR_AFTER_EDIT, productImage,
				altValue, titleValue);
	}
	public AdminProductSearchPageObject clickToSaveButton() {
		waitForElementVisible(driver, AdminProductDetailPageUI.SAVE_BUTTON);
		clickToElement(driver, AdminProductDetailPageUI.SAVE_BUTTON);
		return new AdminProductSearchPageObject(driver);
	}
//	public void clickToDeletedButtonAndAcceptAlert(String altValue,String kindOfButton) {
//		waitForElementVisible(driver, AdminProductDetailPageUI.DELETE_BUTTON_BY_ALT_VALUE, altValue, kindOfButton);
//		clickToElement(driver, AdminProductDetailPageUI.DELETE_BUTTON_BY_ALT_VALUE, altValue, kindOfButton);
//		acceptAlert(driver);
//	}
	
	public void clickToDeletedButtonAndAcceptAlert() {
		waitForAllElementVisible(driver, AdminProductDetailPageUI.DELETE_BUTTON_LIST);
		List<WebElement> listDeleteButton= getListElement(driver, AdminProductDetailPageUI.DELETE_BUTTON_LIST);
		for (WebElement delete: listDeleteButton) {
			delete.click();
			acceptAlert(driver);
		}				
	}
	
	public boolean getNoDataText(String blockName, String textValue) {
		waitForAllElementVisible(driver,
				AdminProductDetailPageUI.NO_DATA_TEXT_BY_BLOCK_NAME, blockName,
				textValue);
		return isElementDisplay(driver,
				AdminProductDetailPageUI.NO_DATA_TEXT_BY_BLOCK_NAME, blockName,
				textValue);
	}

	public void uploadFileAtBlockName(String blockName, String productImage) {
		uploadFileAtBlockName(driver,blockName ,productImage );
		
	}

}
