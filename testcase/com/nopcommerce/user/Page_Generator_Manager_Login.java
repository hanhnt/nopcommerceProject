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
import commons.GloalConstants;
import commons.PageGeneratorManager;
import pageObjectsUser.UserHomePageObject;
import pageObjectsUser.UserLoginPageObject;
import pageObjectsUser.UserRegisterPageObject;

public class Page_Generator_Manager_Login extends BaseTest{
	WebDriver driver;
	String projectPath= System.getProperty("user.dir");
	private UserHomePageObject homePage;
	private UserLoginPageObject loginPage;
	private UserRegisterPageObject registerPage;
	private String emailValue= "user"+ getRandomNumber()+"@gmail.com";;
	private String passwordValue="12345678";
	private String emailExist="user@gmail.com";
	private String firstName="Automation", lastName="FC" ;
	
	@Parameters({"browser","url"})
	@BeforeClass
	public void beforeClass(String browserName,String appUrl) {
		driver=getBroswerDriver(browserName, appUrl);
		driver.manage().window().maximize();
		driver.get("https://demo.nopcommerce.com/");
		
		homePage = PageGeneratorManager.getUserHomePage(driver);		
		System.out.println("Register_03-Step1: Click register link at home page");
		registerPage=homePage.openRegisterPage();	
		
		System.out.println("Pre-condition-Step2: Input to required field");
		registerPage.inputToFirstNameTextbox(firstName);
		registerPage.inputToLastNameTextbox(lastName);
		registerPage.inputToEmailTextbox(emailExist);
		registerPage.inputToPasswordTextbox(passwordValue);
		registerPage.inputToConfirmPasswordTextbox(passwordValue);
		System.out.println("Pre-condition-Step3: Click into register button");
		registerPage.clickToRegisterButton();
		System.out.println("Pre-condition-Step4: Showing the success message");
		Assert.assertEquals(registerPage.getSuccessMessage(), "Your registration completed");
		System.out.println("Pre-condition-Step5: Click into logout button");
		
		homePage=registerPage.clickToLogoutButton();
	}
	@Test
	public void Login_01_Empty_Data() {
		loginPage=homePage.openLoginPage();
		System.out.println("Login_01-Step1: Click into login button without data");
		loginPage.clickToLoginButton();
		
		System.out.println("Login_01-Step2: Check showing the error message");
		Assert.assertEquals(loginPage.getErrorMessageAtEmail(), "Please enter your email");
		
	}
	@Test
	public void Login_02_Invalid_Email() {
		loginPage=homePage.openLoginPage();
		System.out.println("Login_02-Step1: Input invalid email");
		loginPage.inputToEmailTextbox("hanh");
		loginPage.inputToPasswordTextbox("12345678");
		
		System.out.println("Login_02-Step2: Click into login button");
		loginPage.clickToLoginButton();
		
		System.out.println("Login_02-Step3: Check showing the error message");
		Assert.assertEquals(loginPage.getErrorMessageAtEmail(), "Wrong email");
		
	}
	
	@Test
	public void Login_03_Email_Not_Exist() {
		loginPage=homePage.openLoginPage();
		System.out.println("Login_03-Step1: Input email not exist");
		loginPage.inputToEmailTextbox(emailValue);
		loginPage.inputToPasswordTextbox(passwordValue);
		
		System.out.println("Login_02-Step2: Click into login button");
		loginPage.clickToLoginButton();
		
		System.out.println("Login_02-Step3: Check showing the error message");
		Assert.assertEquals(loginPage.getErrorMessageEmailNotExist(), "Login was unsuccessful. Please correct the errors and try again.\nNo customer account found");		
	}
	
	@Test
	public void Login_04_Email_Exist_Null_Password() {
		
		loginPage=homePage.openLoginPage();
		
		System.out.println("Login_04-Step1: Input email exist and null password");
		loginPage.inputToEmailTextbox(emailExist);
		
		System.out.println("Login_04-Step2: Click into login button");
		loginPage.clickToLoginButton();
		
		System.out.println("Login_04-Step3: Check showing the error message");
		Assert.assertEquals(loginPage.getErrorMessageEmailNotExist(), "Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");
		
	}
	
	@Test
	public void Login_05_Email_Exist_Incorrect_Password() {		
		loginPage=homePage.openLoginPage();
		
		System.out.println("Login_05-Step1: Input email exist and incorrect password");
		loginPage.inputToEmailTextbox(emailExist);
		loginPage.inputToPasswordTextbox("23456789");
		
		System.out.println("Login_05-Step2: Click into login button");
		loginPage.clickToLoginButton();
		
		System.out.println("Login_05-Step3: Check showing the error message");
		Assert.assertEquals(loginPage.getErrorMessageEmailNotExist(), "Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");		
	}
	
	@Test
	public void Login_06_Correct_Email_Password() {
		
		loginPage=homePage.openLoginPage();
		
		System.out.println("Login_06-Step1: Input email exist and correct password");
		loginPage.inputToEmailTextbox(emailExist);
		loginPage.inputToPasswordTextbox(passwordValue);
		
		System.out.println("Login_05-Step2: Click into login button");
		loginPage.clickToLoginButton();
		
		System.out.println("Login_06-Step3: Check showing the homePage successfully");
		Assert.assertEquals(homePage.getWelomeText(), "Welcome to our store");		
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
