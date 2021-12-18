package pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import commons.BaseFactoryPage;
import commons.BasePage;
import pageUIsUser.UserHomePageUI;

public class HomePageObjectFactory extends BaseFactoryPage {
	private WebDriver driver;
	
	public HomePageObjectFactory(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//a[@class='ico-login']")
	private WebElement lnLogin;
	
	@FindBy(xpath = "//a[@class='ico-register']")
	private WebElement lnRegister;
	
	@FindBy(xpath = "//h2[text()='Welcome to our store']")
	private WebElement txtWelcome;

	public void clickToRegisterLink() {
		waitForElementVisible(driver,lnRegister);
		clickToElement(driver, lnRegister);;
	}
	
	public void clickToLoginLink() {
		waitForElementVisible(driver,lnLogin);
		clickToElement(driver, lnLogin);
	}

	public String getWelomeText() {
		waitForElementVisible(driver,txtWelcome);
		return getElementText(driver, txtWelcome);
	}

}
