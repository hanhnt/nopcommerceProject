package com.nopcommerce.user;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.GloalConstants;
import commons.PageGeneratorManager;
import pageObjectsUser.UserAddressPageObject;
import pageObjectsUser.UserCustomerInforPageObject;
import pageObjectsUser.UserHomePageObject;
import pageObjectsUser.UserLoginPageObject;
import pageObjectsUser.UserMyProductReviewPageObject;
import pageObjectsUser.UserRegisterPageObject;
import pageObjectsUser.UserRewardPointPageObject;

public class Page_Object_Switch_Page extends BaseTest {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	private UserHomePageObject homePage;
	private UserLoginPageObject loginPage;
	private UserCustomerInforPageObject customerInforPage;
	private UserRegisterPageObject registerPage;
	private UserAddressPageObject addressPage;
	private UserMyProductReviewPageObject myProductReviewPage;
	private UserRewardPointPageObject rewardPointPage;

	private String emailValue = "user" + getRandomNumber() + "@gmail.com";;
	private String passwordValue = "12345678";
	private String firstName = "Automation", lastName = "FC";

	@Parameters({"browser","appUrl"})
	@BeforeClass
	public void beforeClass(String browserName,String appUrl) {
		driver=getBroswerDriver(browserName, appUrl);
		homePage = new UserHomePageObject(driver);
		loginPage = new UserLoginPageObject(driver);

	}
	@Test
	public void User1_Register() {
		registerPage = homePage.openRegisterPage();
		registerPage.inputToFirstNameTextbox(firstName);
		registerPage.inputToLastNameTextbox(lastName);
		registerPage.inputToEmailTextbox(emailValue);
		registerPage.inputToPasswordTextbox(passwordValue);
		registerPage.inputToConfirmPasswordTextbox(passwordValue);
		registerPage.clickToRegisterButton();
		Assert.assertEquals(registerPage.getSuccessMessage(),
				"Your registration completed");
		registerPage.clickToLogoutButton();
	}

	@Test
	public void User2_Login() {
		loginPage = homePage.openLoginPage();
		loginPage.inputToEmailTextbox(emailValue);
		loginPage.inputToPasswordTextbox(passwordValue);
		homePage = loginPage.clickToLoginButton();
		Assert.assertEquals(homePage.getWelomeText(), "Welcome to our store");
		Assert.assertTrue(homePage.isMyAccountLinkDisplay());
	}

	@Test
	public void User3_MyAccount() {
		customerInforPage = homePage.clickToMyAccountLink();
		Assert.assertEquals(customerInforPage.getMyAccountPageTitle(),
				"My account - Customer info");
	}

	@Test
	public void User4_Switch_Page() {
		// Customer infor ->Address
		addressPage = customerInforPage.openAddressPage(driver);

		// Address -> my product review
		myProductReviewPage = addressPage.openMyProductReviewPage(driver);

		// my product review -> Reward point
		rewardPointPage = myProductReviewPage.openRewardPointPage(driver);

		// Reward point-> Address
		addressPage = rewardPointPage.openAddressPage(driver);

	}
	
	@Test
	public void User5_Dynamic_Page () {
		// Customer infor ->Address
		addressPage = (UserAddressPageObject) customerInforPage.openPageAtMyAccountByPage(driver, "Addresses");

		// Address -> my product review
		myProductReviewPage = (UserMyProductReviewPageObject) addressPage.openPageAtMyAccountByPage(driver, "My product reviews");

		// my product review -> Reward point
		rewardPointPage = (UserRewardPointPageObject) myProductReviewPage.openPageAtMyAccountByPage(driver, "Reward points");

		// Reward point-> Address
		addressPage = (UserAddressPageObject) myProductReviewPage.openPageAtMyAccountByPage(driver, "Addresses");

	}
	
	@Test
	public void User6_Dynamic_Page (){
		// Customer infor ->Address
		customerInforPage.openPageAtMyAccountPageByPage(driver, "Addresses");
		addressPage= PageGeneratorManager.getUserAddressPage(driver);

		// Address -> my product review
		addressPage.openPageAtMyAccountPageByPage(driver, "My product reviews");
		myProductReviewPage=PageGeneratorManager.getUserMyProductReviewPage(driver);

		// my product review -> Reward point
		myProductReviewPage.openPageAtMyAccountPageByPage(driver, "Reward points");
		rewardPointPage=PageGeneratorManager.getUserRewardPointPage(driver);

		// Reward point-> Address
		myProductReviewPage.openPageAtMyAccountPageByPage(driver, "Addresses");
		addressPage= PageGeneratorManager.getUserAddressPage(driver);
	}


	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public int getRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(99999);
	}
}
