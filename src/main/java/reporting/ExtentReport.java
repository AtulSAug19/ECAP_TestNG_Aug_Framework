package reporting;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class ExtentReport {

	ExtentReports report = null;
	ExtentTest test = null;

	public ExtentReports getExtentReportInstance(String filePath) {
		report = new ExtentReports(filePath);
		return report;
	}

	public ExtentTest startTest(String testName, ExtentReports report) {
		return test = report.startTest(testName);
	}

	public void endTest(ExtentTest testName, ExtentReports report) {
		report.endTest(testName);
	}

	public void flushReport(ExtentReports report) {
		report.flush();
	}

}
