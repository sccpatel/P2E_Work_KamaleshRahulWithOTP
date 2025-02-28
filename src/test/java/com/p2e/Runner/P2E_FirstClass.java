package com.p2e.Runner;

import org.testng.annotations.Test;

import com.p2e.Base.BaseTest;
import com.p2e.testScript.LoginTestScript;

public class P2E_FirstClass extends BaseTest {
int rowCount =1000;

@Test
	public void runnerFirst() {
		LoginTestScript videoRun = new LoginTestScript();
		videoRun.verifyNau_001_P2ELoginPage(11 , "ExcelData\\P2EFormFillReport1.xlsx");
	}

	@Test
	public void runnerSecond() {
		LoginTestScript videoRun = new LoginTestScript();
		
		videoRun.verifyNau_001_P2ELoginPage(12 , "ExcelData\\P2EFormFillReport2.xlsx");
	}

	@Test
	public void runnerThird() {
		LoginTestScript videoRun = new LoginTestScript();
		videoRun.verifyNau_001_P2ELoginPage(13,  "ExcelData\\P2EFormFillReport3.xlsx");
	}

	@Test
	public void runnerFour() {
		LoginTestScript videoRun = new LoginTestScript();
		videoRun.verifyNau_001_P2ELoginPage(14, "ExcelData\\P2EFormFillReport4.xlsx");
	}

	@Test
	public void runnerFive() {
		LoginTestScript videoRun = new LoginTestScript();
		videoRun.verifyNau_001_P2ELoginPage(15,  "ExcelData\\P2EFormFillReport5.xlsx");
	}


	@Test
	public void runnerSix() {
		LoginTestScript videoRun = new LoginTestScript();
		videoRun.verifyNau_001_P2ELoginPage(0,   "ExcelData\\CenterNameAndAutomation.xlsx");
	}

////////////////////////////  Balance Report //////////////////////////////
	@Test
	public void otpBalanace() {
		LoginTestScript videoRun = new LoginTestScript();
		videoRun.verifyNau_002_P2GetBalanace();
	}

}
