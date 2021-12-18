package com.nopcommerce.user;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjectsUser.UserHomePageObject;
import pageObjectsUser.UserLoginPageObject;
import pageObjectsUser.UserRegisterPageObject;

public class Page_Object_Login_Multiple_Browser extends BaseTest {
	private WebDriver driver;
	
	private UserHomePageObject homePage;
	private UserLoginPageObject loginPage;
	private UserRegisterPageObject registerPage;
	private String emailValue= "user"+ getRandomNumber()+"@yopmail.com";;
	private String passwordValue="12345678";
	private String emailExist="user"+ getRandomNumber()+"@gmail.com";
	private String firstName="Automation", lastName="FC" ;
	
	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver=getBroswerDriver(browserName);
		driver.get("https://demo.nopcommerce.com/");
		driver.manage().window().maximize();
		homePage= new UserHomePageObject(driver);
		loginPage= new UserLoginPageObject(driver);		
		
		homePage.openRegisterPage();	
		
		System.out.println("Pre-condition-Step1: Click to register link");
		homePage.openRegisterPage();
		registerPage = new UserRegisterPageObject(driver);
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
		registerPage.clickToLogoutButton();
	}
	@Test
	public void Login_01_Empty_Data() {
		homePage.openLoginPage();
		System.out.println("Login_01-Step1: Click into login button without data");
		loginPage.clickToLoginButton();
		
		System.out.println("Login_01-Step2: Check showing the error message");
		Assert.assertEquals(loginPage.getErrorMessageAtEmail(), "Please enter your email");
		
	}
	@Test
	public void Login_02_Invalid_Email() {
		homePage.openLoginPage();
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
		homePage.openLoginPage();
		
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
		
		
		homePage.openLoginPage();
		
		System.out.println("Login_04-Step1: Input email exist and null password");
		loginPage.inputToEmailTextbox(emailExist);
		
		System.out.println("Login_04-Step2: Click into login button");
		loginPage.clickToLoginButton();
		
		System.out.println("Login_04-Step3: Check showing the error message");
		Assert.assertEquals(loginPage.getErrorMessageEmailNotExist(), "Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");		
	}
	
	@Test
	public void Login_05_Email_Exist_Incorrect_Password() {		
		homePage.openLoginPage();
		
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
		
		homePage.openLoginPage();
		
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
