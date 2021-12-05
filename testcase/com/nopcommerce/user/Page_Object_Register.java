package com.nopcommerce.user;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import commons.BasePage;
import pageObjects.HomePageObject;
import pageObjects.RegisterPageObject;

public class Page_Object_Register extends BasePage {

	WebDriver driver;
	String projectPath= System.getProperty("user.dir");
	String emailValue="user@gmail.com";
	private HomePageObject homePage;
	private RegisterPageObject registerPage;
	private String firstName, lastName, email, password;

	@BeforeClass
	public void beforeClass() {		
		System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
		driver = new ChromeDriver();
		
		homePage= new HomePageObject(driver);
		registerPage= new RegisterPageObject(driver);
		driver.manage().window().maximize();
		driver.get("https://demo.nopcommerce.com/");
		firstName="Automation";
		lastName="FC";
		password= "123678";
		email="user" + getRandomNumber()+ "@gmail.com";
	}
	
	//@Test
	public void Register_01_Empty_Data() {
		System.out.println("Register_01-Step1: Click register link at home page");
		homePage.clickToRegisterLink();
		
		System.out.println("Register_01-Step2: Click register link at home page");
		registerPage.clickToRegisterButton();
		
		System.out.println("Register_01-Step3: Showing the error message at each required textbox");		
		Assert.assertEquals(registerPage.getErrorMessageAtFirstNameTextbox(), "First name is required.");
		Assert.assertEquals(registerPage.getErrorMessageAtLastNameTextbox(), "Last name is required.");
		Assert.assertEquals(registerPage.getErrorMessageAtEmailTextbox(), "Email is required.");
		Assert.assertEquals(registerPage.getErrorMessageAtPasswordTextbox(), "Password is required.");
		Assert.assertEquals(registerPage.getErrorMessageAtConfirmTextbox(), "Password is required.");		
	}
	
	//@Test
	public void Register_02_Invalid_Email() {
		System.out.println("Register_02-Step1: Click register link at home page");
		homePage.clickToRegisterLink();
		
		System.out.println("Register_02-Step2: Input to required field");
		registerPage.inputToFirstNameTextbox(firstName);
		registerPage.inputToLastNameTextbox(lastName);
		registerPage.inputToEmailTextbox("123@123@123@123");
		registerPage.inputToPasswordTextbox(password);
		registerPage.inputToConfirmPasswordTextbox(password);
		System.out.println("Register_02-Step3: Click into register button");
		registerPage.clickToRegisterButton();
		System.out.println("Register_02-Step4: Showing the error message at email");
		Assert.assertEquals(registerPage.getErrorMessageAtEmailTextbox(), "Wrong email");
	}

	//@Test
	public void Register_03_Register_Success() {
		System.out.println("Register_03-Step1: Click register link at home page");
		homePage.clickToRegisterLink();
		
		System.out.println("Register_03-Step2: Input to required field");
		registerPage.inputToFirstNameTextbox(firstName);
		registerPage.inputToLastNameTextbox(lastName);
		registerPage.inputToEmailTextbox(emailValue);
		registerPage.inputToPasswordTextbox(password);
		registerPage.inputToConfirmPasswordTextbox(password);
		System.out.println("Register_03-Step3: Click into register button");
		registerPage.clickToRegisterButton();
		System.out.println("Register_03-Step4: Showing the success message");
		Assert.assertEquals(registerPage.getSuccessMessage(), "Your registration completed");
		System.out.println("Register_03-Step5: Click into logout button");
		registerPage.clickToLogoutButton();
	}
	
	//@Test
	public void Register_04_Exist_Email() {
		System.out.println("Register_04-Step1: Click register link at home page");
		homePage.clickToRegisterLink();
		
		System.out.println("Register_04-Step2: Input to required field");
		registerPage.inputToFirstNameTextbox(firstName);
		registerPage.inputToLastNameTextbox(lastName);
		registerPage.inputToEmailTextbox(emailValue);
		registerPage.inputToPasswordTextbox(password);
		registerPage.inputToConfirmPasswordTextbox(password);
		System.out.println("Register_04-Step3: Click into register button");
		registerPage.clickToRegisterButton();
		System.out.println("Register_04-Step4: Showing the error message");
		Assert.assertEquals(registerPage.getErrorExistEmail(), "The specified email already exists");
	}
	
	@Test
	public void Register_05_Password_Less_Than_Six_Characters() {
		System.out.println("Register_05-Step1: Click register link at home page");
		homePage.clickToRegisterLink();
		
		System.out.println("Register_05-Step2: Input to required field");
		registerPage.inputToFirstNameTextbox(firstName);
		registerPage.inputToLastNameTextbox(lastName);
		registerPage.inputToEmailTextbox(emailValue);
		registerPage.inputToPasswordTextbox("12345");
		registerPage.inputToConfirmPasswordTextbox("12345");
		System.out.println("Register_05-Step3: Click into register button");
		registerPage.clickToRegisterButton();
		System.out.println("Register_05-Step4: Showing the error message at password");
		Assert.assertEquals(registerPage.getErrorMessageAtPasswordTextbox(), "Password must meet the following rules:\nmust have at least 6 characters");
	}
	
	@Test
	public void Register_06_Password_Not_Match_With_ConfirmPassword() {
		System.out.println("Register_06-Step1: Click register link at home page");
		homePage.clickToRegisterLink();
		
		System.out.println("Register_06-Step2: Input to required field");
		registerPage.inputToFirstNameTextbox(firstName);
		registerPage.inputToLastNameTextbox(lastName);
		registerPage.inputToEmailTextbox(emailValue);
		registerPage.inputToPasswordTextbox("12345678");
		registerPage.inputToConfirmPasswordTextbox("123456789");
		System.out.println("Register_06-Step3: Click into register button");
		registerPage.clickToRegisterButton();
		System.out.println("Register_06-Step4: Showing the error message at password");
		Assert.assertEquals(registerPage.getErrorMessageAtConfirmTextbox(), "The password and confirmation password do not match.");
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
