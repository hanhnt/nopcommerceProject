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
	
	public void TC01_Register_Empty_Data() {
		System.out.println("Click register link at home page");
//		waitForElementClickable(driver, "//a[@class='ico-register']");
//		clickToElement(driver, "//a[@class='ico-register']");
		homePage.clickToRegisterLink();
		
		System.out.println("Click register link at home page");
//		waitForElementClickable(driver, "register-button");
//		clickToElement(driver, "register-button");
		registerPage.clickToRegisterButton();
		
		System.out.println("Showing the error message at each required textbox");
		
//		Assert.assertEquals(getElementText(driver, "FirstName-error"), "First name is required.");
//		Assert.assertEquals(getElementText(driver, "LastName-error"), "Last name is required.");
//		Assert.assertEquals(getElementText(driver, "Email-error"), "Email is required.");
//		Assert.assertEquals(getElementText(driver, "Password-error"), "Password is required.");
//		Assert.assertEquals(getElementText(driver, "ConfirmPassword-error"), "Password is required.");
		
		Assert.assertEquals(registerPage.getErrorMessageAtFirstNameTextbox(), "First name is required.");
		Assert.assertEquals(registerPage.getErrorMessageAtLastNameTextbox(), "Last name is required.");
		Assert.assertEquals(registerPage.getErrorMessageAtEmailTextbox(), "Email is required.");
		Assert.assertEquals(registerPage.getErrorMessageAtPasswordTextbox(), "Password is required.");
		Assert.assertEquals(registerPage.getErrorMessageAtConfirmTextbox(), "Password is required.");		
	}
	
	@Test
	public void TC02_Register_Invalid_Email() {
		System.out.println("Click register link at home page");
		homePage.clickToRegisterLink();
		
		System.out.println("Input to required field");
		registerPage.inputToFirstNameTextbox(firstName);
		registerPage.inputToLastNameTextbox(lastName);
		registerPage.inputToEmailTextbox("123@123@123@123");
		registerPage.inputToPasswordTextbox(password);
		registerPage.inputToConfirmPasswordTextbox(password);
		registerPage.clickToRegisterButton();
		Assert.assertEquals(registerPage.getErrorMessageAtEmailTextbox(), "Wrong email");
	}

	@Test
	public void TC03_Register_Success() {
		System.out.println("Click register link at home page");
		homePage.clickToRegisterLink();
		
		System.out.println("Input to required field");
		registerPage.inputToFirstNameTextbox(firstName);
		registerPage.inputToLastNameTextbox(lastName);
		registerPage.inputToEmailTextbox(emailValue);
		registerPage.inputToPasswordTextbox(password);
		registerPage.inputToConfirmPasswordTextbox(password);
		registerPage.clickToRegisterButton();
		Assert.assertEquals(registerPage.getSuccessMessage(), "Your registration completed");
	}
	
	@AfterClass
	public void afterClass() {
	}
	
	public int getRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(99999);
	}

}
