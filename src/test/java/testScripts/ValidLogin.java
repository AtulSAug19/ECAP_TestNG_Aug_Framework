package testScripts;

import java.io.IOException;

import org.testng.TestNG;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import pageClass.LoginPage;
import reporting.ExtentReport;


public class ValidLogin {

	LoginPage loginPageObj = new LoginPage();
	ExtentReport extentObj = new ExtentReport();
	ExtentReports extent = null;
	ExtentTest test = null;
	String aplicationURL = null;
	String screenshotPath = null;

	@BeforeClass()
	public void launch() throws IOException {
		extent = extentObj.getExtentReportInstance(
				loginPageObj.getConfigValue("testCaseReportPath") + this.getClass().getName()+".html");
		aplicationURL = loginPageObj.getConfigValue("applicationURL");
		screenshotPath = loginPageObj.getConfigValue("screenShotsPath");
		
	}
	
	@BeforeMethod()
	public void startTest()
	{
		test = extentObj.startTest("Valid login credential", extent);
		loginPageObj.launchBrowser();
		test.log(LogStatus.PASS, "Browser is launched");
		loginPageObj.intialiseElements();
	}
	
	@AfterMethod()
	public void endTest() {
		loginPageObj.closeBrowser();
		test.log(LogStatus.PASS, "Browser is closed");
		extentObj.endTest(test, extent);
	}

	@Test
	public void validlogin() throws IOException {
		loginPageObj.launchApplication(aplicationURL);
		loginPageObj.takeScreenShots(screenshotPath+"launchapplication.png");
		test.log(LogStatus.PASS, "Application is launched");
		test.log(LogStatus.PASS,test.addScreenCapture(screenshotPath));
		loginPageObj.clickOnRegistrationLink();
		test.log(LogStatus.PASS, "Registration link is clicked");
		loginPageObj.validLogin();
		test.log(LogStatus.PASS, "User logged in");
		extentObj.endTest(test, extent);
	}

	@AfterClass()
	public void tearDown() throws IOException {
		extentObj.flushReport(extent);
		
	}

}
