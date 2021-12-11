package commons;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	WebDriver driver;
	String projectPath= System.getProperty("user.dir");
	public WebDriver getBroswerDriver(String browserName) {
		switch (browserName) {
			case "firefox":
				//System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
				break;
			case "chrome":
				//System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
				WebDriverManager.firefoxdriver().setup();
				driver = new ChromeDriver();
				break;
			case "safari":
				driver = new SafariDriver();
				break;
			case "h_chrome":
				System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
				ChromeOptions options= new ChromeOptions();
				options.addArguments("--headless");
				options.addArguments("window-size=1920x1080");
				driver=new ChromeDriver(options);
				
			case "h_firefox":
				System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
				FirefoxOptions options1= new FirefoxOptions();
				options1.addArguments("--headless");
				options1.addArguments("window-size=1920x1080");
				driver=new FirefoxDriver(options1);
				
				
				default:
				new RuntimeException("Please select browser");
			break;
		}
		return driver;
	}
}
