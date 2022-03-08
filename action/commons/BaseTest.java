package commons;

import java.io.File;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeTest;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	WebDriver driver;
	
	protected final Log log; //log này khong cho phép gnas lại/ khởi tạo lại. -> final. (dùng protect để nhưng thang nào kế thừa mới gọi ra dc).

	// Constructor
	protected BaseTest() {
		log = LogFactory.getLog(getClass()); // đoạn code này khởi tạo log.
	}

	
	String projectPath= System.getProperty("user.dir");
	public WebDriver getBroswerDriver(String browserName, String appUrl) {
		
		switch (browserName) {
			case "firefox":
				//System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
				break;
			case "chrome":
				//System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
				WebDriverManager.chromedriver().setup();
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
		driver.manage().window().maximize();
		driver.get(appUrl);
		return driver;
	}
	
	private boolean checkTrue(boolean condition) {
		boolean pass = true;
		try {
			if (condition == true) {
				log.info(" -------------------------- PASSED -------------------------- "); // nếudùng log ở đây phải có log4j.xml 
			} else {
				log.info(" -------------------------- FAILED -------------------------- ");
			}
			Assert.assertTrue(condition);
		} catch (Throwable e) {
			pass = false;

			// Add lỗi vào ReportNG
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

	protected boolean verifyTrue(boolean condition) {
		return checkTrue(condition);
	}

	private boolean checkFailed(boolean condition) {
		boolean pass = true;
		try {
			if (condition == false) {
				log.info(" -------------------------- PASSED -------------------------- ");
			} else {
				log.info(" -------------------------- FAILED -------------------------- ");
			}
			Assert.assertFalse(condition);
		} catch (Throwable e) {
			pass = false;
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

	protected boolean verifyFalse(boolean condition) {
		return checkFailed(condition);
	}

	private boolean checkEquals(Object actual, Object expected) {
		boolean pass = true;
		try {
			Assert.assertEquals(actual, expected);
			log.info(" -------------------------- PASSED -------------------------- ");
		} catch (Throwable e) {
			pass = false;
			log.info(" -------------------------- FAILED -------------------------- ");
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

	protected boolean verifyEquals(Object actual, Object expected) {
		return checkEquals(actual, expected);
	}
	public WebDriver getWebDriver() {
		return this.driver;
	}
	

@BeforeTest
	public void deleteAllFilesInReportNGScreenshot() {
		log.info("---------- START delete file in folder ----------");
		deleteAllFileInFolder();
		log.info("---------- END delete file in folder ----------");
	}

	public void deleteAllFileInFolder() {
		try {
			String workingDir = System.getProperty("user.dir");
			String pathFolderDownload = workingDir + "/ReportNGScreenshots";
			File file = new File(pathFolderDownload);
			File[] listOfFiles = file.listFiles();
			for (int i = 0; i < listOfFiles.length; i++) {
				if (listOfFiles[i].isFile()) {
					System.out.println(listOfFiles[i].getName());
					new File(listOfFiles[i].toString()).delete();
				}
			}
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
	}


}
