package com.p2e.Base;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.P2E.utils.WebUtil;

public class BaseTest {

	protected WebUtil webUtil = WebUtil.getInstance();;
	private ExtentReports extent;
	private String timeStamp;

	@BeforeSuite
	public void beforeSuite() {
		System.out.println("Extent-Report Initialization");
		extent = new ExtentReports();
		timeStamp = webUtil.currentDateWithTime();
		ExtentSparkReporter spark = new ExtentSparkReporter("ExtentReports\\ExtentReport" + timeStamp + ".html");
		extent.attachReporter(spark);
		webUtil.lodProperties();
	}

	@BeforeMethod
	public void beforeMethod(Method mt) {
		ExtentTest extTest = extent.createTest(mt.getName());
		webUtil.setExtTest(extTest);
		ChromeOptions opt = webUtil.addChromeExtension("ExtensionOfChrome\\chnccghejnflbccphgkncbmllhfljdfa.crx");
		webUtil.launchBrowser(webUtil.getProperties().getProperty("browser"), opt);
		webUtil.openUrl(webUtil.getProperties().getProperty("url"));

	}

	@AfterMethod
	public void afterMethod(ITestResult itest, Method mt) {

//		webUtil.quit();
		extent.flush();
	}

	@AfterSuite
	public void afterSuite() {
		System.out.println("Extent-Report Finalization");
		webUtil.statusAndResult(Status.PASS, "Extent-Report Finalization");
		extent.flush();
		Desktop desk = Desktop.getDesktop();
		try {
			desk.open(new File("ExtentReports\\ExtentReport" + timeStamp + ".html"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
