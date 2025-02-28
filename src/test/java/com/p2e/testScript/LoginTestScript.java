package com.p2e.testScript;

import java.util.ArrayList;

import org.testng.annotations.Test;

import com.P2E.Login.Login;
import com.P2E.Login.LoginCSC;
import com.P2E.LoginFlashSide.FlashLogin;
import com.P2E.LoginFormRagistion.RagistationCSCForm;
import com.P2E.LoginFormRagistion.RagistationForm;
import com.P2E.SpeedVideo.SpeedVideoControlar;
import com.P2E.videoAndQuestiom.VideoAndQuestion;
import com.p2e.Base.BaseTest;

public class LoginTestScript extends BaseTest {

	@Test
	public void verifyNau_001_P2ELoginPage(int runOtpID , String excelFilePath) {
		FlashLogin otp = new FlashLogin(webUtil);
		otp.openNewTabAnsLaunchOTP(runOtpID);

		int firstcount = 1;
		while (true) {
			
			try {
				Login lgn = new Login(webUtil);
				for (int i = 0; true; i++) {
					lgn.login();
					String wrOtp =lgn.findWringOtp();
					if(wrOtp==null) {
						break;
					}else {
						continue;
					}
				}

			} catch (Exception e) {
				System.out.println("Login Option is Not Complite Successfully");
				webUtil.switchToWindowByTitle("Passport to Earning (P2E) Skills");
				webUtil.switchToWindowByTitle("Login");
				webUtil.switchToWindowByTitle("My courses");
				System.out.println("Login Option Fail ");
			}
			try {
				System.out.println("I am Waiting For Ragistation ");
				RagistationForm ragPage = new RagistationForm(webUtil);
				ragPage.ragistationForm( excelFilePath);

				VideoAndQuestion qu = new VideoAndQuestion(webUtil);
				qu.QuestionAnswer(firstcount);
				System.out.println(firstcount + "  Form  Fully Ragistation and Video is complit Successfuly");
				firstcount = firstcount + 1;
			} catch (Exception e) {
				try {
					System.out.println("Form Already Submited and Edit form  is editing wait  Successfuly");
//					webUtil.thread(1000);
					VideoAndQuestion qu = new VideoAndQuestion(webUtil);
					qu.signOutAndTackSnapShortPage( excelFilePath);
					System.out.println(" Video is waiting  Successfuly");
					qu.QuestionAnswer(firstcount);
				} catch (Exception r) {
					System.out.println("Ragistation and Video is not complit Successfuly");
				}
			}
//			rowCount = rowCount+1;
		}

	}

//////////////////////Flash OTP Balanace Report/////////////////////////////////////////////////////
	@Test
	public void verifyNau_002_P2GetBalanace() {
		webUtil.openUrl(webUtil.getProperties().getProperty("otpUrl"));
		FlashLogin otp = new FlashLogin(webUtil);
		otp.otpReport(26);

	}

///////////////////// CSC Work P2E  ////////////////////////////////////////////////////////	
	@Test
	public void verifyNau_003_CSC_P2ELoginPage() {
		webUtil.thread(400);
		webUtil.openUrl(webUtil.getProperties().getProperty("cscUrl"));
		String name = null;
		String gender = null;
		String email = null;
		String mobileNo = null;
		String courseResult = null;
		String state = null;
		String dist = null;
		String center = null;

		while (true) {
			int count = webUtil.randonNumber(181);
			ArrayList<String> nalist = webUtil.readDataFromExcelSheet(count,
					"ExcelData\\CSC_StateDistAndCenterName.xlsx");
			name = nalist.get(1);
			email = nalist.get(2);
			mobileNo = nalist.get(3);
			gender = nalist.get(4);
			courseResult = nalist.get(5);
			state = nalist.get(6);
			dist = nalist.get(7);
			center = nalist.get(8);
			if (courseResult.equalsIgnoreCase("yes")) {
				continue;
			}
			email = "fawimem457@fanicle.com";

			try {
				LoginCSC lgn = new LoginCSC(webUtil);
				lgn.emailRagistation(email, "Kam@1234");
//				lgn.login("tedijih245@fanicle.com", "Kam@1234");
				System.out.println("Login Option Successfuly complite ");

			} catch (Exception e) {
				System.out.println("Login Option Fail ");
			}
			try {
				System.out.println("I am Waiting For Ragistation ");
				RagistationCSCForm ragPage = new RagistationCSCForm(webUtil);
				ragPage.ragistationForm(name, gender, mobileNo, count);
				VideoAndQuestion qu = new VideoAndQuestion(webUtil);
				qu.QuestionAnswer(2);
				System.out.println("Fully Ragistation and Video is complit Successfuly");
			} catch (Exception e) {
				System.out.println("Ragistation and Video is complit Successfuly");
			}

		}

	}

}
