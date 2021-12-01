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

public class Page_Object extends BasePage {

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
	
	@Test
	public void Register01_Empty_Data() {
		System.out.println("Step1: Click register link at home page");
		homePage.clickToRegisterLink();
		
		System.out.println("Step2: Click register link at home page");
		registerPage.clickToRegisterButton();
		
		System.out.println("Step3: Showing the error message at each required textbox");		
		Assert.assertEquals(registerPage.getErrorMessageAtFirstNameTextbox(), "First name is required.");
		Assert.assertEquals(registerPage.getErrorMessageAtLastNameTextbox(), "Last name is required.");
		Assert.assertEquals(registerPage.getErrorMessageAtEmailTextbox(), "Email is required.");
		Assert.assertEquals(registerPage.getErrorMessageAtPasswordTextbox(), "Password is required.");
		Assert.assertEquals(registerPage.getErrorMessageAtConfirmTextbox(), "Password is required.");		
	}
	
	@Test
	public void Register02_Invalid_Email() {
		System.out.println("Step1: Click register link at home page");
		homePage.clickToRegisterLink();
		
		System.out.println("Step2: Input to required field");
		registerPage.inputToFirstNameTextbox(firstName);
		registerPage.inputToLastNameTextbox(lastName);
		registerPage.inputToEmailTextbox("123@123@123@123");
		registerPage.inputToPasswordTextbox(password);
		registerPage.inputToConfirmPasswordTextbox(password);
		System.out.println("Step3: Click into register button");
		registerPage.clickToRegisterButton();
		System.out.println("Step4: Showing the error message at email");
		Assert.assertEquals(registerPage.getErrorMessageAtEmailTextbox(), "Wrong email");
	}

	@Test
	public void Register03_Register_Success() {
		System.out.println("Step1: Click register link at home page");
		homePage.clickToRegisterLink();
		
		System.out.println("Step2: Input to required field");
		registerPage.inputToFirstNameTextbox(firstName);
		registerPage.inputToLastNameTextbox(lastName);
		registerPage.inputToEmailTextbox(emailValue);
		registerPage.inputToPasswordTextbox(password);
		registerPage.inputToConfirmPasswordTextbox(password);
		System.out.println("Step3: Click into register button");
		registerPage.clickToRegisterButton();
		System.out.println("Step4: Showing the success message");
		Assert.assertEquals(registerPage.getSuccessMessage(), "Your registration completed");
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
