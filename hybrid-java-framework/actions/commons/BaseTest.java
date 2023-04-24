package commons;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.Assert;
import org.testng.Reporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	WebDriver driver;
	
	//methods use for log4j
	protected Log log;

	protected BaseTest() {
		log = LogFactory.getLog(getClass());
	}
	
	// use for log/report
	public WebDriver getInstanceDriver() {
		return this.driver;
	}
	
	//get browser methods
	protected WebDriver getBrowserDriver(String browserName, String url) {
		BrowserName browser = BrowserName.valueOf(browserName.toUpperCase());
		switch (browser) {
		case CHROME:
			driver = WebDriverManager.chromedriver().create();
			break;
		case FIREFOX:
			driver = WebDriverManager.firefoxdriver().create();
			break;
		case EDGE:
			driver = WebDriverManager.edgedriver().create();
			break;
		default:
			throw new RuntimeException();
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(GlobalConstants.LONG_TIMEOUT, TimeUnit.SECONDS);
		driver.get(url);
		return driver;
	}
	
	protected  String getEnviromentList(String enviromentName) {
		String envUrl = null;
		EnviromentList enviroment = EnviromentList.valueOf(enviromentName.toUpperCase());
		switch (enviroment) {
		case DEV:
			envUrl = "http://localhost/wp-admin";
			break;
		case STAGING:
			envUrl = "https://www.stagingsisters.ca/";
			break;
		case TESTING:
			envUrl = "https://demo.nopcommerce.com/";
			break;
		case PRE_PROD:
			envUrl = "http://localhost/wp-admin";
			break;
		case PROD:
			envUrl = "http://localhost/wp-admin";
			break;

		default:
			envUrl = null;
			break;
		}
		
		return envUrl;
	}
	

	protected WebDriver getBrowserDriverByEnviroment (String browserName, String envName) {
		BrowserName browser = BrowserName.valueOf(browserName.toUpperCase());
		switch (browser) {
		case FIREFOX:
			driver = WebDriverManager.firefoxdriver().create();
			break;
		case CHROME:
			driver = WebDriverManager.chromedriver().create();
			break;
		case H_FIREFOX:
			System.setProperty("webdriver.gecko.driver", GlobalConstants.PROJECT_PATH + File.separator
					+ "browserDrivers" + File.separator + "geckodriver.exe");
			FirefoxOptions hfOtions = new FirefoxOptions();
			hfOtions.addArguments("--headless");
			hfOtions.addArguments("window-size=1920x1080");
			driver = new FirefoxDriver(hfOtions);
			break;
		case H_CHROME:
			System.setProperty("webdriver.chrome.driver", GlobalConstants.PROJECT_PATH + File.separator
					+ "browserDrivers" + File.separator + "chromedriver_111.exe");
			ChromeOptions hcOptions = new ChromeOptions();
			hcOptions.addArguments("--headless");
			hcOptions.addArguments("window-size=1920x1080");
			driver = new ChromeDriver(hcOptions);
			break;
		case EDGE:
			driver = WebDriverManager.edgedriver().create();
			break;
		case OPERA:
			driver = WebDriverManager.operadriver().create();
			break;
		case COCCOC:
			WebDriverManager.chromedriver().driverVersion("").setup();
			ChromeOptions ccOptions = new ChromeOptions();
			ccOptions.setBinary("C:\\Users\\Admin\\AppData\\Local\\CocCoc\\Browser\\Application\\browser.exe");
			driver = new ChromeDriver(ccOptions);
			break;
		case BRAVO:
			WebDriverManager.chromedriver().driverVersion("").setup();
			ChromeOptions bcOptions = new ChromeOptions();
			bcOptions.setBinary("");
			driver = new ChromeDriver(bcOptions);
			break;

		default:
			throw new RuntimeException();
		}
		
		driver.manage().timeouts().implicitlyWait(GlobalConstants.LONG_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(getEnviromentList(envName));
		return driver;
	}

	protected WebDriver getMultiBrowserDriver(String browserName, String url) {
		BrowserName browser = BrowserName.valueOf(browserName.toUpperCase());
		switch (browser) {
		case FIREFOX:
			driver = WebDriverManager.firefoxdriver().create();
			break;
		case CHROME:
			driver = WebDriverManager.chromedriver().create();
			break;
		case H_FIREFOX:
			System.setProperty("webdriver.gecko.driver", GlobalConstants.PROJECT_PATH + File.separator
					+ "browserDrivers" + File.separator + "geckodriver.exe");
			FirefoxOptions hfOtions = new FirefoxOptions();
			hfOtions.addArguments("--headless");
			hfOtions.addArguments("window-size=1920x1080");
			driver = new FirefoxDriver(hfOtions);
			break;
		case H_CHROME:
			System.setProperty("webdriver.chrome.driver", GlobalConstants.PROJECT_PATH + File.separator
					+ "browserDrivers" + File.separator + "chromedriver_111.exe");
			ChromeOptions hcOptions = new ChromeOptions();
			hcOptions.addArguments("--headless");
			hcOptions.addArguments("window-size=1920x1080");
			driver = new ChromeDriver(hcOptions);
			break;
		case EDGE:
			driver = WebDriverManager.edgedriver().create();
			break;
		case OPERA:
			driver = WebDriverManager.operadriver().create();
			break;
		case COCCOC:
			WebDriverManager.chromedriver().driverVersion("").setup();
			ChromeOptions ccOptions = new ChromeOptions();
			ccOptions.setBinary("C:\\Users\\Admin\\AppData\\Local\\CocCoc\\Browser\\Application\\browser.exe");
			driver = new ChromeDriver(ccOptions);
			break;
		case BRAVO:
			WebDriverManager.chromedriver().driverVersion("").setup();
			ChromeOptions bcOptions = new ChromeOptions();
			bcOptions.setBinary("");
			driver = new ChromeDriver(bcOptions);
			break;

		default:
			throw new RuntimeException();
		}
		driver.manage().timeouts().implicitlyWait(GlobalConstants.LONG_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(url);
		return driver;
	}
	
	
	//Close browser
	protected void closeBrowserDriver() {
		String cmd = null;
		try {
			String driverInstanceName = driver.toString().toLowerCase();
			String osName = GlobalConstants.OS_NAME;

			String browserName = null;
			if (driverInstanceName.contains("chrome")) {
				browserName = "chromedriver";
			} else if (driverInstanceName.contains("firefox")) {
				browserName = "geckodriver";
			} else if (driverInstanceName.contains("edge")) {
				browserName = "msedgedriver";
			} else if (driverInstanceName.contains("opera")) {
				browserName = "operadriver";
			} else {
				browserName = "safaridriver";
			}

			if (osName.contains("window")) {
				cmd = "taskkill /F /FI \"IMAGENAME eq " + browserName + "*\"";
			} else {
				cmd = "pkill " + browserName;
			}

			if (driver != null) {
				driver.manage().deleteAllCookies();
				driver.quit();
			}
		} catch (Exception e) {
			e.getMessage();
		} finally {
			try {
				Process process = Runtime.getRuntime().exec(cmd);
				process.waitFor();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	
	// Get random methods
	protected static int getRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(9999);
	}
	
	protected static int getRandomNumberByLimit(int minimum, int maximum) {
		Random rand = new Random();
		return minimum + rand.nextInt(maximum-minimum);
	}
	
	public static int getRandomNumberByLimit() {
		int uLimit = 999;
		int lLimit = 100;
		Random rand = new Random();
		return lLimit + rand.nextInt(uLimit - lLimit);
	}
	
	protected static long getRandomNumberByDateTime() {
		return Calendar.getInstance().getTimeInMillis() %1000;
	}
	
	// Verify true false methods
	protected boolean checkTrue(boolean condition) {
		boolean pass = true;
		try {
			log.info(" -------------------------- PASSED -------------------------- ");
		} catch (Throwable e) {
			log.info(" -------------------------- FAILED -------------------------- ");
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

	protected boolean checkFailed(boolean condition) {
		boolean pass = true;
		try {
			Assert.assertFalse(condition);
			log.info(" -------------------------- PASSED -------------------------- ");
		} catch (Throwable e) {
			log.info(" -------------------------- FAILED -------------------------- ");
			pass = false;
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

	protected boolean verifyFalse(boolean condition) {
		return checkFailed(condition);
	}

	protected boolean checkEquals(Object actual, Object expected) {
		boolean pass = true;
		try {
			Assert.assertEquals(actual, expected);
			System.out.println(" -------------------------- PASSED -------------------------- ");
		} catch (Throwable e) {
			pass = false;
			System.out.println(" -------------------------- FAILED -------------------------- ");
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

	protected boolean verifyEquals(Object actual, Object expected) {
		return checkEquals(actual, expected);
	}

}
