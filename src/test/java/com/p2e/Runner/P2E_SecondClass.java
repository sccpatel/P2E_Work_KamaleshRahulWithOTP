package com.p2e.Runner;

import org.testng.annotations.Test;

import com.p2e.Base.BaseTest;
import com.p2e.testScript.LoginTestScript;

public class P2E_SecondClass extends BaseTest {

	@Test
	public void runnerFirst() {
		LoginTestScript videoRun = new LoginTestScript();
		videoRun.verifyNau_001_P2ELoginPage(0, 1, "ExcelData\\P2EFormFillReport1.xlsx");
	}

	@Test
	public void runnerSecond() {
		LoginTestScript videoRun = new LoginTestScript();
		videoRun.verifyNau_001_P2ELoginPage(1, 1, "ExcelData\\P2EFormFillReport2.xlsx");
	}

	@Test
	public void runnerThird() {
		LoginTestScript videoRun = new LoginTestScript();
		videoRun.verifyNau_001_P2ELoginPage(2, 1, "ExcelData\\P2EFormFillReport3.xlsx");
	}

	@Test
	public void runnerFour() {
		LoginTestScript videoRun = new LoginTestScript();
		videoRun.verifyNau_001_P2ELoginPage(3, 1, "ExcelData\\P2EFormFillReport4.xlsx");
	}

	@Test
	public void runnerFive() {
		LoginTestScript videoRun = new LoginTestScript();
		videoRun.verifyNau_001_P2ELoginPage(4, 1, "ExcelData\\P2EFormFillReport5.xlsx");
	}

}
