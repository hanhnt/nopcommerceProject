package com.nopcommerce.user;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjectsUser.UserHomePageObject;
import pageObjectsUser.UserLoginPageObjectVerify;
import pageObjectsUser.UserRegisterPageObject;

public class Page_Object_Login_Log_Report extends BaseTest{
	WebDriver driver;
	String projectPath= System.getProperty("user.dir");
	private UserHomePageObject homePage;
	private UserLoginPageObjectVerify loginPage;
	private UserRegisterPageObject registerPage;
	private String emailValue= "user"+ getRandomNumber()+"@gmail.com";;
	private String passwordValue="12345678";
	private String emailExist="user@gmail.com";
	private String firstName="Automation", lastName="FC" ;
	
	@Parameters({"browser", "url"})
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		log.info("Pre-condition Step1: Open browser '" + browserName +"' and navigate to '"+ appUrl+"' " );
		driver=getBroswerDriver(browserName, appUrl);
		
		log.info("Pre-condition Step2: Open homePage");
		homePage= new UserHomePageObject(driver);
		loginPage= new UserLoginPageObjectVerify(driver);
		
		log.info("Pre-condition Step3: Click register link at home pagee");
		homePage.openRegisterPage();		
	}
	@Test
	public void Login_01_Empty_Data() {
		log.info("Login_01_Empty_Data-Step1: Open homePage Login");
		homePage.openLoginPage();
		
		log.info("Login_01_Empty_Data-Step2: Don't input anything and click into Login button");
		loginPage.clickToLoginButton();
		
		log.info("Login_01_Empty_Data-Step3: Showing the error message at email textbox");
		verifyFalse(loginPage.isErrorMessageEmailDisplay());
		
	}
	@Test
	public void Login_02_Invalid_Email() {
		log.info("Login_02_Invalid_Email-Step1: Open homePage Login");
		homePage.openLoginPage();
		
		log.info("Login_02_Invalid_Email-Step2: Input invalid email and valid password");
		loginPage.inputToEmailTextbox("hanh");
		loginPage.inputToPasswordTextbox("12345678");
		
		log.info("Login_02_Invalid_Email-Step3: Click to login button");
		loginPage.clickToLoginButton();
		
		log.info("Login_02_Invalid_Email-Step4: Check showing the error message");
		verifyEquals(loginPage.getErrorMessageAtEmail(), "Wrong email");
		
	}
	
	@Test
	public void Login_03_Email_Not_Exist() {
		log.info("Login_03_Email_Not_Exist-Step1: Open homePage Login");
		homePage.openLoginPage();
		
		log.info("Login_03_Email_Not_Exist-Step2: Input email not exist " + emailValue);
		loginPage.inputToEmailTextbox(emailValue);
		log.info("Login_03_Email_Not_Exist-Step3: Input password " + passwordValue);
		loginPage.inputToPasswordTextbox(passwordValue);
		
		log.info("Login_03_Email_Not_Exist-Step3: Click to login button");
		loginPage.clickToLoginButton();
		
		System.out.println("Login_03_Email_Not_Exist-Step4: Check showing the error message");
		verifyEquals(loginPage.getErrorMessageEmailNotExist(), "Login was unsuccessful. Please correct the errors and try again.\nNo customer account found");		
	}
	
	
	@AfterClass
	public void afterClass() {
		log.info("Post conditon: Close browser");
		driver.quit();
	}
	
	public int getRandomNumber() {
		Random rand= new Random();
		return rand.nextInt(99999);
	}
}
