package com.jquery.datatable;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.BaseTestDataTable;
import commons.PageGeneratorManager;
import pageObjectsJQuery.HomePageObject;

public class DataTableJQuery extends BaseTestDataTable {
	WebDriver driver;
	HomePageObject homePage;

	@Parameters({"browser", "url"})
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		driver = getBroswerDriver(browserName, appUrl);
		driver.manage().window().maximize();
		driver.get(
				"https://www.jqueryscript.net/demo/CRUD-Data-Grid-Plugin-jQuery-Quickgrid/");
		homePage = PageGeneratorManager.getHomePageJQuery(driver);
	}
	// @Test
	public void TC01_Paging() {
		homePage.openPageNumber("15");
		Assert.assertTrue(homePage.isPageNumberDisplay("15"));

		homePage.openPageNumber("2");
		Assert.assertTrue(homePage.isPageNumberDisplay("2"));

		homePage.openPageNumber("5");
		Assert.assertTrue(homePage.isPageNumberDisplay("5"));

	}

	// @Test
	public void TC02_inputToHeader() {
		homePage.inputToTextboxUnderHeader("Females", "43053100");
		homePage.inputToTextboxUnderHeader("Country",
				"Less developed regions, excluding China");
		homePage.inputToTextboxUnderHeader("Males", "45669830");
		homePage.inputToTextboxUnderHeader("Total", "88723120");
	}

	//@Test
	public void TC03_deletedRow() {
		homePage.refreshCurrentPage(driver);
		homePage.deletedRowByCountry("Afghanistan");
		homePage.deletedRowByCountry("Less developed regions");
		homePage.deletedRowByCountry("ASIA");
	}

	@Test
	public void TC02_verifyDataAfterInput() {
		homePage.pressEnterAfterInput("Country", "Afghanistan");
		homePage.sleepInSecond(5);
		Assert.assertTrue(homePage.isValueShowCorrectlyAfterSearch("384187","Afghanistan","407124","791312"));
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
