package baseLayer;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;
import utility.Constant;

public class TestBase {

	static WebDriver driver = null;
	Properties prop = null;
	

	public String getConfigValue(String key) {
		String value = "";
		try {
			File file = new File("./src/test/resources/config.properties");
			FileInputStream fileInput = new FileInputStream(file);
			prop = new Properties();
			prop.load(fileInput);
			value = prop.getProperty(key);
		} catch (IOException i) {

		}
		return value;
	}

	public static WebDriver getDriver() {
		return driver;
	}

	public void launchBrowser() {
		String browserName = getConfigValue("browser");
		if (browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else if (browserName.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		} else {
			System.out.println("Invalid browser name");
			Assert.assertTrue(false);
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Constant.implicitWaitTimeout, TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();
	}

	public void launchApplication(String url) {
		driver.get(url);
	}

	public void setValue(WebElement element, String text) {
		if (element.isEnabled()) {
			element.sendKeys(text);
		}
	}

	public void clickOnElement(WebElement element) {
		if (element.isDisplayed())
			element.click();
	}

	public WebElement getElement(String locatorType, String locatorValue) {
		String value = locatorType.toUpperCase();
		WebElement element = null;
		switch (value) {
		case "XPATH":
			element = driver.findElement(By.xpath(locatorValue));
			break;
		case "ID":
			element = driver.findElement(By.id(locatorValue));
			break;
		case "NAME":
			element = driver.findElement(By.name(locatorValue));
			break;
		case "CSS":
			element = driver.findElement(By.cssSelector(locatorValue));
			break;
		case "TAGNAME":
			element = driver.findElement(By.tagName(locatorValue));
			break;
		}
		return element;
	}

	public List<WebElement> getAllElementOfSimilarType(String locatorType, String locatorValue) {
		String value = locatorType.toUpperCase();
		List<WebElement> element = null;
		switch (value) {
		case "XPATH":
			element = driver.findElements(By.xpath(locatorValue));
			break;
		case "ID":
			element = driver.findElements(By.id(locatorValue));
			break;
		case "NAME":
			element = driver.findElements(By.name(locatorValue));
			break;
		case "CSS":
			element = driver.findElements(By.cssSelector(locatorValue));
			break;
		case "TAGNAME":
			element = driver.findElements(By.tagName(locatorValue));
			break;
		}
		return element;
	}

	public void switchToAnotherWindow(String windowID) {
		driver.switchTo().window(windowID);
	}

	public void switchToFrame(String switchToType, Object value) {
		switch (switchToType) {
		case "index": {
			driver.switchTo().frame((int) value);
			break;
		}
		case "Name": {
			driver.switchTo().frame((String) value);
			break;
		}
		case "WebElement":
			driver.switchTo().frame((WebElement) value);
			break;
		}
	}

	public String getWindowID() {
		return driver.getWindowHandle();
	}

	public Set<String> getAllWindowID() {
		return driver.getWindowHandles();
	}

	public Alert switchToAlert() {
		return driver.switchTo().alert();
	}

	public void acceptAlertPopUp() {
		switchToAlert().accept();
	}

	public void cancelAlertPopUp() {
		switchToAlert().dismiss();
	}

	public void closeBrowser() {
		driver.close();
	}

	public void closeAllBrowser() {
		driver.quit();
	}

	public void takeScreenShots(String filePath) throws IOException {
		TakesScreenshot screenShot = (TakesScreenshot) driver;
		File srcFile = screenShot.getScreenshotAs(OutputType.FILE);
		File dest = new File(filePath);
		FileUtils.copyFile(srcFile, dest);
	}

}
