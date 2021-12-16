package com.nopcommerce.user;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.AddressPageObject;
import pageObjects.CustomerInforPageObject;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.MyProductReviewPageObject;
import pageObjects.RegisterPageObject;
import pageObjects.RewardPointPageObject;

public class Page_Object_Switch_Page extends BaseTest{
	WebDriver driver;
	String projectPath= System.getProperty("user.dir");
	private HomePageObject homePage;
	private LoginPageObject loginPage;
	private CustomerInforPageObject customerInforPage;
	private RegisterPageObject registerPage;
	private AddressPageObject addressPage;
	private MyProductReviewPageObject myProductReviewPage;
	private RewardPointPageObject rewardPointPage;
	
	private String emailValue= "user"+ getRandomNumber()+"@gmail.com";;
	private String passwordValue="12345678";
	private String emailExist="user@gmail.com";
	private String firstName="Automation", lastName="FC" ;
	
	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver=getBroswerDriver(browserName);
		driver.manage().window().maximize();
		driver.get("https://demo.nopcommerce.com/");
		homePage= new HomePageObject(driver);
		loginPage= new LoginPageObject(driver);		
			
	}
	@Test
	public void User1_Register() {
		registerPage=homePage.clickToRegisterLink();
		registerPage.inputToFirstNameTextbox(firstName);
		registerPage.inputToLastNameTextbox(lastName);
		registerPage.inputToEmailTextbox(emailValue);
		registerPage.inputToPasswordTextbox(passwordValue);
		registerPage.inputToConfirmPasswordTextbox(passwordValue);
		registerPage.clickToRegisterButton();
		Assert.assertEquals(registerPage.getSuccessMessage(), "Your registration completed");
		registerPage.clickToLogoutButton();		
	}
	
	
	@Test
	public void User2_Login() {		
		loginPage=homePage.clickToLoginLink();
		loginPage.inputToEmailTextbox(emailValue);
		loginPage.inputToPasswordTextbox(passwordValue);
		homePage=loginPage.clickToLoginButton();
		Assert.assertEquals(homePage.getWelomeText(), "Welcome to our store");
		Assert.assertTrue(homePage.isMyAccountLinkDisplay());
	}
	

	@Test
	public void User3_MyAccount() {		
		customerInforPage=homePage.clickToMyAccountLink();
		Assert.assertEquals(customerInforPage.getMyAccountPageTitle(), "My account - Customer info");
	}
	
	@Test
	public void User4_Switch_Page() {
		//Customer infor ->Address
		addressPage=customerInforPage.openAddressPage(driver);
		
		//Address -> my product review
		myProductReviewPage= addressPage.openMyProductReviewPage(driver);
		
		// my product review -> Reward point
		rewardPointPage=myProductReviewPage.openRewardPointPage(driver);
		
		//Reward point-> Address
		addressPage=rewardPointPage.openAddressPage(driver);
		
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public int getRandomNumber() {
		Random rand= new Random();
		return rand.nextInt(99999);
	}
}
