package reporting;

import org.testng.ITestListener;
import org.testng.ITestResult;

public class SpecListener implements ITestListener {

	@Override
	public void onTestStart(ITestResult result) {
		System.out.println("Test Start: " + result.getName());
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println("Test is Passed :" + result.getTestName());
	}

	@Override
	public void onTestFailure(ITestResult result) {
		System.out.println("Test failed: " + result.getTestName());
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		System.out.println("Test skipped: " + result.getTestName());
	}

}
