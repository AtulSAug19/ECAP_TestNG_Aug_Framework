package testScripts;

import java.io.IOException;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import pageClass.LoginPage;
import reporting.ExtentReport;

public class InvalidLogin {
	
	LoginPage loginPageObj = new LoginPage();
	ExtentReport extentObj = new ExtentReport();
	ExtentReports extent = null;
	ExtentTest test = null;
	int i=0;

	@BeforeClass()
	public void launch() throws IOException {
		extent = extentObj.getExtentReportInstance(
				loginPageObj.getConfigValue("testCaseReportPath") +  this.getClass().getName()+".html");
		
	}
	
	@BeforeMethod()
	public void startTest()
	{
		test = extentObj.startTest("Invalid login credential", extent);
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
	public void invalidlogin() throws IOException {
		test = extentObj.startTest("Invalid login credential", extent);
		loginPageObj.launchApplication(loginPageObj.getConfigValue("applicationURL"));
		test.log(LogStatus.PASS, "Application is launched");
		loginPageObj.clickOnRegistrationLink();
		test.log(LogStatus.PASS, "Registration link is clicked");
		loginPageObj.invalidLogin();
		test.log(LogStatus.PASS, "User logged in");
		extentObj.endTest(test, extent);
	}

	@AfterClass()
	public void tearDown() throws IOException {
		extentObj.flushReport(extent);
		
	}

}
