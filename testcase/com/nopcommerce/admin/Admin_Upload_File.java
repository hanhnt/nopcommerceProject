package com.nopcommerce.admin;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjectsAdmin.AdminDashboardPageObject;
import pageObjectsAdmin.AdminLoginPageObject;
import pageObjectsAdmin.AdminProductDetailPageObject;
import pageObjectsAdmin.AdminProductSearchPageObject;
import pageObjectsAdmin.PageGeneratorManager;

public class Admin_Upload_File extends BaseTest{
	WebDriver driver;
	String productImage="images.jpeg";
	private AdminLoginPageObject adminLoginPage;
	private AdminDashboardPageObject adminDashboardPage;
	private AdminProductSearchPageObject adminProductSearchPage;
	private AdminProductDetailPageObject adminProductDetailPage;
	
	@Parameters({"browser", "url"})
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		driver=getBroswerDriver(browserName, appUrl);
		adminLoginPage=PageGeneratorManager.getAdminLoginPage(driver);
		adminDashboardPage=adminLoginPage.loginAsAdmin("admin@yourstore.com", "admin");
		adminProductSearchPage = adminDashboardPage.openSubMenuByName("Catalog","Products");
		adminProductSearchPage.clickToExpandSearchArea();
		adminProductSearchPage.enterToProductNameTextbox("Adobe Photoshop CS4");
		adminProductSearchPage.clickToSearchButton();
		adminProductDetailPage=adminProductSearchPage.clickToEditButton("Adobe Photoshop CS4");
	}
	
	@Test
	public void Admin_Upload_File() {
		adminProductDetailPage.clickToExpandPanelByBlockName("pictures");
		adminProductDetailPage.scrollToUploadButton("product-attributes");
		adminProductDetailPage.uploadFileAtBlockName("pictures",productImage);
		Assert.assertTrue(adminProductDetailPage.uploadFileSuccessfully("images"));
		adminProductDetailPage.inputIntoAltTextbox("Alt text");
		adminProductDetailPage.inputToTitleTextbox("Title text");
		adminProductDetailPage.inputToDisplayOrderTextbox("pictures");
		adminProductDetailPage.clickToAddProductPictureButton();
		
		//Verify upload success at deatail page
		Assert.assertTrue(adminProductDetailPage.isPictureInforDisplay("adobe-photoshop-cs4","Alt text","Title text"));
		adminProductSearchPage=adminProductDetailPage.clickToSaveButton();
		Assert.assertTrue(adminProductSearchPage.showingTheMessageUpdatesuccessfully("The product has been updated successfully."));
		
		//check update image success
		adminProductSearchPage.enterToProductNameTextbox("Adobe Photoshop CS4");
		adminProductSearchPage.clickToSearchButton();
		Assert.assertTrue(adminProductSearchPage.isPictureUpdatedDisplay("Adobe Photoshop CS4","Adobe Photoshop CS4"));
		
		//delete image
		adminProductDetailPage=adminProductSearchPage.clickToEditButton("Adobe Photoshop CS4");
		//adminProductDetailPage.clickToDeletedButtonAndAcceptAlert("Alt text","Delete");
		adminProductDetailPage.clickToDeletedButtonAndAcceptAlert();
		Assert.assertTrue(adminProductDetailPage.getNoDataText("productpictures","No data available in table"));
		adminProductSearchPage=adminProductDetailPage.clickToSaveButton();
		
		//come to Search Page and search again to verify default image
		adminProductSearchPage.enterToProductNameTextbox("Adobe Photoshop CS4");
		Assert.assertTrue(adminProductSearchPage.isPictureUpdatedDisplay("Adobe Photoshop CS4","default image"));		
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
