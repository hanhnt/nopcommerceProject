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

public class DataTable extends BaseTestDataTable {
	WebDriver driver;
	HomePageObject homePage;

	@Parameters({"browser", "url"})
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		driver = getBroswerDriver(browserName, appUrl);
		homePage = PageGeneratorManager.getHomePageJQuery(driver);
	}
	@Test
	public void TC01_InputDataIntoTextboxUnderHeader() {
		homePage.clickToAction("Insert","1");
		homePage.clickToAction("Insert","2");
		homePage.clickToAction("Insert","3");
		homePage.inputDataIntoTextboxByHeader("Album", "3", "test1");
		homePage.inputDataIntoTextboxByHeader("Artist", "3", "test2");
		homePage.inputDataIntoTextboxByHeader("Year", "3", "1990");
		homePage.selectDataAtOriginDropdown("Origin", "3", "Japan");
		homePage.clickToCheckboxUnderPoster("Poster","3");
		homePage.inputDataIntoTextboxByHeader("Price","3", "1000");
		
		homePage.clickToAction("Delete","2");
		homePage.sleepInSecond(4);
		homePage.clickToAction("MoveUp","3");
		homePage.clickToAction("MoveDown","3");
		homePage.sleepInSecond(4);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
