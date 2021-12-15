package commons;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseFactoryPage {
	public void openPageUlr(WebDriver driver, String pageUrl) {
		driver.get(pageUrl);
	}
	
	public String getPageTitle(WebDriver driver) {
		return driver.getTitle();
	}
	
	public String getPageUrl(WebDriver driver) {
		 return driver.getCurrentUrl();
	}
	
	public String getPageSourceCode(WebDriver driver) {
		return driver.getPageSource();
	}
	
	public void backToPage(WebDriver driver) {
		driver.navigate().back();
	}
	
	public void forwardToPage(WebDriver driver) {
		driver.navigate().forward();
	}
	
	public void refreshCurrentPage(WebDriver driver) {
		driver.navigate().refresh();
	}
	
	public Alert waitForAlertPresent(WebDriver driver) {
		WebDriverWait explicitWait = new WebDriverWait(driver, 30);
		return explicitWait.until(ExpectedConditions.alertIsPresent());
		
	}
	
	public void acceptAlert(WebDriver driver) {
		waitForAlertPresent(driver).accept();
		//driver.switchTo().alert().accept();
	}
	
	public void cancelAlert(WebDriver driver) {
		waitForAlertPresent(driver).dismiss();
	}
	
	public String getTextAlert(WebDriver driver) {
		return waitForAlertPresent(driver).getText();
	}
	
	public void sendkeyToAlert(WebDriver driver, String textValue) {
		waitForAlertPresent(driver).sendKeys(textValue);
	}
	
	public void switchToWindowByID(WebDriver driver, String windowID) {
		Set<String> allWindows=driver.getWindowHandles();
		for (String windows: allWindows) {
			if(!windows.equals(windowID)) {
				driver.switchTo().window(windows);
			} 
		}
	}
	
	public void closeAllWindowWithoutParentWindow(WebDriver driver, String parentWindow) {
		Set<String> allWindow=driver.getWindowHandles();
		for (String window: allWindow) {
			if(!window.equals(parentWindow)) {
				driver.switchTo().window(window);
				driver.close();
			}
		}
		driver.switchTo().window(parentWindow);
	}
	
	public void switchWindowByTitle(WebDriver driver, String expectedPageTitle) {
		Set<String> allWindows=driver.getWindowHandles();
		for (String window: allWindows) {
			driver.switchTo().window(window);
			
			String actualTitle=driver.getTitle();
			if(actualTitle.equals(expectedPageTitle)) {break;}			
		}
	}
	
	public By getByXpath(String xpathLocator) {
		return By.xpath(xpathLocator);
	}
	
//	public WebElement getWebElement(WebDriver driver, WebElement element) {
//		return element;
//	}
	
	public List<WebElement> getListElement(WebDriver driver, List<WebElement> elements) {
		return elements;
	}
	
	public void clickToElement(WebDriver driver, WebElement element) {
		element.click();
	}
	
	public void sendkeyToElement(WebDriver driver, WebElement element, String textValue) {
		element.clear();
		element.sendKeys(textValue);
	}
	
	public String getElementText(WebDriver driver, WebElement element) {
		return element.getText();
	}
	
	public void selectItemInDefaultDropdown(WebDriver driver, WebElement element, String textItem) {
		Select select = new Select(element);
		select.selectByValue(textItem);
	}
	
	public String getItemInDefaultDropdown(WebDriver driver, WebElement element, String textItem) {
		Select select = new Select(element);
		return select.getFirstSelectedOption().getText();
	}
	
	public boolean isDropdownMultiple(WebDriver driver, WebElement element) {
		Select select = new Select(element);
		return select.isMultiple();
	}
	
	public void selectItemInDropDownList(WebDriver driver, WebElement parentXpath, String childXpath, String expectedText) {
		WebDriverWait explicitWait= new WebDriverWait(driver, 30);
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		explicitWait.until(ExpectedConditions.elementToBeClickable(parentXpath));
		parentXpath.click();		
		List<WebElement> allElements = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByXpath(childXpath)));
		for (WebElement element : allElements) {
			if (element.getText().trim().equals(expectedText)) {
				if (element.isDisplayed()) {
					element.click();
				} else {
					jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
					element.click();
				}
				break;
			}

		}
	}
	
	public String getAttribute(WebDriver driver, WebElement element, String attributeName) {
		return element.getAttribute(attributeName);
	}
	
	public String getCssValue(WebDriver driver, WebElement element, String propertyName) {
		return element.getCssValue(propertyName);
	}
	
	public String convertRgbaToHex(String rgbaValue) {
		return Color.fromString(rgbaValue).asHex();
	}
	
	public int getElementSize(WebDriver driver, List<WebElement> element) {
		return getListElement(driver, element).size();
	}
	
	public void checkToDefaultCheckbox(WebDriver driver, WebElement element) {
		if (!element.isSelected()) {
			element.click();
		}
	}
	
	public void UnCheckToDefaultCheckbox(WebDriver driver, WebElement element) {
		if (element.isSelected()) {
			element.click();
		}
	}
	
	public boolean isElementDisplay(WebDriver driver, WebElement element) {
		return element.isDisplayed();
	}
	
	public boolean isElementEnable(WebDriver driver, WebElement element) {
		return element.isEnabled();
	}
	
	public boolean isElementSelect(WebDriver driver, WebElement element) {
		return element.isSelected();
	}
	
	public void switchFrameIfram(WebDriver driver, WebElement element) {
		driver.switchTo().frame(element);
	}
	
	public void switchToDefaultContent(WebDriver driver) {
		driver.switchTo().defaultContent();
	}
	
	public void hoverMouseToElement(WebDriver driver, WebElement element) {
		Actions action= new Actions(driver);
		action.moveToElement(element).perform();
	}
	
	public void rightClickToElement(WebDriver driver, WebElement element) {
		Actions action = new Actions(driver);
		action.contextClick(element).perform();
	}

	public void scrollToBottomPage(WebDriver driver) {
		JavascriptExecutor jsExecutor= (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void hightlightElement(WebDriver driver, WebElement element) {
		JavascriptExecutor jsExecutor= (JavascriptExecutor) driver;
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element,
				"border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
	}

	public void clickToElementByJS(WebDriver driver, WebElement element) {
		JavascriptExecutor jsExecutor= (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();", element);
	}

	public void scrollToElementOnTop(WebDriver driver, WebElement element) {
		JavascriptExecutor jsExecutor= (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	public void scrollToElementOnDown(WebDriver driver, WebElement element) {
		JavascriptExecutor jsExecutor= (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(false);", element);
	}
	
	public void removeAttributeInDOM(WebDriver driver, WebElement element, String attributeRemove) {
		JavascriptExecutor jsExecutor= (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", element);
	}

	public String getElementValidationMessage(WebDriver driver, WebElement element) {
		JavascriptExecutor jsExecutor= (JavascriptExecutor) driver;
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;",element);
	}

	public boolean isImageLoaded(WebDriver driver, WebElement element) {
		JavascriptExecutor jsExecutor= (JavascriptExecutor) driver;
		boolean status = (boolean) jsExecutor.executeScript(
				"return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0",
				element);
		if (status) {
			return true;
		}
		return false;
	}
	
	public boolean isJQueryLoadedSuccess1(WebDriver driver) {
		WebDriverWait explicitWait;
		explicitWait = new WebDriverWait(driver, longTimeout);
		JavascriptExecutor jsExecutor;
		jsExecutor = (JavascriptExecutor) driver;
		ExpectedCondition<Boolean> JQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return (Boolean) jsExecutor.executeScript("return（window.jQuery!= null）&&（jQuery.active == 0);");
			}
		};
		return explicitWait.until(JQueryLoad);
	}
	
	public void waitForElementVisible(WebDriver driver, WebElement element) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public void waitForAllElementVisible(WebDriver driver, List<WebElement> elements) {
		WebDriverWait explicitWait= new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfAllElements(elements));
	}
	
	public void waitForElementInvisible(WebDriver driver, WebElement element) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOf(element));
	}
	
	public void waitForAllElementIvisible(WebDriver driver, List<WebElement> elements) {
		WebDriverWait explicitWait= new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(elements));
	}
	
	public void waitForElementClickable(WebDriver driver, WebElement element) {
		WebDriverWait explicitWait= new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	public void sleepInSecond(long time) {
		try {
			Thread.sleep(time * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private long longTimeout=30;
}
