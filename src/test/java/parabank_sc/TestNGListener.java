package parabank_sc;

import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestNGListener implements ITestListener {

    public void onTestFailure(ITestResult result) {
	// Code to update the report with information about a failed test
    }

    public void onTestSkipped(ITestResult result) {
	// Code to update the report with information about a skipped test
    }

    public void onTestSuccess(ITestResult result) {
	// Code to update the report with information about a successful test
    }

    // other overridden methods like onStart,onFinish etc.
}
