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
import pageObjectsAdmin.AdminDashboardPageObject;
import pageObjectsAdmin.AdminLoginPageObject;
import pageObjectsUser.UserAddressPageObject;
import pageObjectsUser.UserCustomerInforPageObject;
import pageObjectsUser.UserHomePageObject;
import pageObjectsUser.UserLoginPageObject;
import pageObjectsUser.UserMyProductReviewPageObject;
import pageObjectsUser.UserRegisterPageObject;
import pageObjectsUser.UserRewardPointPageObject;

public class Page_Object_Switch_Roles extends BaseTest{
	WebDriver driver;
	String projectPath= System.getProperty("user.dir");
	private UserHomePageObject userHomePage;
	private UserLoginPageObject userLoginPage;
	private UserCustomerInforPageObject userCustomerInforPage;
	private UserRegisterPageObject registerPage;
	private UserAddressPageObject addressPage;
	private UserMyProductReviewPageObject myProductReviewPage;
	private UserRewardPointPageObject rewardPointPage;
	private AdminLoginPageObject adminLoginPage;
	private AdminDashboardPageObject adminDashboardPage;
	
	private String userEmailValue= "user"+ getRandomNumber()+"@gmail.com";
	private String adminEmailValue= "admin@yourstore.com";
	private String userPasswordValue="12345678";
	private String adminPasswordValue="admin";
	private String firstName="Automation", lastName="FC" ;
	
	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver=getBroswerDriver(browserName);
		userHomePage= new UserHomePageObject(driver);
		userLoginPage= new UserLoginPageObject(driver);		
			
	}
	
	@Test
	public void Role_01_01_Register_User() {
		registerPage=userHomePage.openRegisterPage();
		registerPage.registerAsUser(firstName, lastName, userEmailValue, userPasswordValue);
		Assert.assertEquals(registerPage.getSuccessMessage(), "Your registration completed");
		registerPage.clickToLogoutButton();		
	}
	
	@Test
	public void Role_01_02_Login_User() {
		userLoginPage=userHomePage.openLoginPage();
		userHomePage=userLoginPage.loginAsUser(userEmailValue, userPasswordValue);
		Assert.assertTrue(userHomePage.isMyAccountLinkDisplay());
		userCustomerInforPage=userHomePage.clickToMyAccountLink();
		userHomePage=userCustomerInforPage.clickToLogoutLinkAtUserPage(driver);
		
	}
	

	@Test
	public void Role_01_03_User_To_Admin() {
		userLoginPage=userHomePage.openLoginPage();
		userHomePage=userLoginPage.loginAsUser(userEmailValue, userPasswordValue);
		Assert.assertTrue(userHomePage.isMyAccountLinkDisplay());
		userCustomerInforPage=userHomePage.clickToMyAccountLink();
		userHomePage=userCustomerInforPage.clickToLogoutLinkAtUserPage(driver);
		
		userHomePage.openPageUlr(driver, GloalConstants.ADMIN_LINK);
		adminLoginPage=PageGeneratorManager.getAdminLoginPage(driver);
		adminDashboardPage=adminLoginPage.loginAsAdmin(adminEmailValue, adminPasswordValue);
		Assert.assertEquals(adminDashboardPage.getDashboardText(), "Dashboard");
		adminLoginPage= adminDashboardPage.clickToLogoutLinkAtAdminPage(driver);		
	}
		
	@Test
	public void Role_01_04_Admin_To_User() {		
		//adminLoginPage.openPageUlr(driver, GloalConstants.ADMIN_LINK);
		adminLoginPage=PageGeneratorManager.getAdminLoginPage(driver);
		adminDashboardPage=adminLoginPage.loginAsAdmin(adminEmailValue, adminPasswordValue);
		Assert.assertEquals(adminDashboardPage.getDashboardText(), "Dashboard");
		sleepInSecond(10);
		adminLoginPage= adminDashboardPage.clickToLogoutLinkAtAdminPage(driver);
		
		adminLoginPage.openPageUlr(driver, GloalConstants.USER_LINK);		
		userLoginPage=userHomePage.openLoginPage();
		userHomePage=userLoginPage.loginAsUser(userEmailValue, userPasswordValue);
		Assert.assertTrue(userHomePage.isMyAccountLinkDisplay());
		userCustomerInforPage=userHomePage.clickToMyAccountLink();
		userHomePage=userCustomerInforPage.clickToLogoutLinkAtUserPage(driver);
		
	}
	
//	public void Role3_Admin_To_User() {
//		
//	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public int getRandomNumber() {
		Random rand= new Random();
		return rand.nextInt(99999);
	}
	
	public void sleepInSecond(long time) {
		try {
			Thread.sleep(time * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
