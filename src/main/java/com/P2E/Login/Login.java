package com.P2E.Login;

import java.util.List;

import org.openqa.selenium.WebElement;

import com.P2E.utils.WebUtil;

public class Login extends LoginOr {
	private WebUtil util;

	public Login(WebUtil util) {
		super(util);
		this.util = util;
	}

	public void login() {
		util.switchToWindowByTitle("Passport to Earning (P2E) Skills");
		util.thread(4000);
		util.click(getSignIn(), "Login Button", 20);
		util.thread(2000);
		util.click(getPhoneAuthentication(), "PhoneAuthentication");
		util.thread(4000);
		String mobileNo = refrashAndSignmobileOtp();
		String otp = flashOtp(mobileNo);

		for (int i = 0; true; i++) {
			if (otp == null) {
				util.printMessage("1.5 Minitus Over and OTP Not Find");
				cancleAndDeleteButton(10);
				util.thread(1000);
				mobileNo = refrashAndSignmobileOtp();
				otp = flashOtp(mobileNo);
			} else {
				break;
			}
		}

		enterOTP(otp);
		util.thread(500);
		util.printMessage("Login Option Successfuly complite ");

	}

	public String refrashAndSignmobileOtp() {
		String num = flashRecevMobileNumber();
		util.switchToWindowByTitle("Login");
		util.goToRefreshPage();
		util.selectByVisibleString(getSelectphoneContry(), "India (+91)");
		util.sendKeysWithEnter(getMobileNoEnert(), num, "Enter Mobile Number");
		return num;
	}

	public void enterOTP(String otp) {
		util.thread(500);
		util.switchToWindowByTitle("Login");
		util.thread(500);
		util.printMessage("Find OTP  " + otp + "  Successfully ");
		util.sendKeysWithEnter(getMobileOtp(), otp, "Enter Mobile OTP");
		
	}

////////////////////////////////////////////////////////////		
	int countMoNum = 0;

	public String flashRecevMobileNumber() {

		util.switchToWindowByDynamicTitle("RK ONLINE");
		String result = null;
		cancleAndDeleteButton(4);
		util.printMessage("Waiting for Mobile Number Finding");
		util.thread(1000);

		for (int i = 0; true; i++) {
			try {
				
				
				if (countMoNum == 0) {
//					countMoNum = 0;
					util.printMessage("Waiting for Mobile Number Finding  " + util.currentDateWithTime());
					util.switchToWindowByTitle("Login");
					util.thread(500);
					util.goToRefreshPage();
					util.thread(500);
					util.switchToWindowByDynamicTitle("RK ONLINE");
				}

				if (countMoNum == 0) {
					util.click(getSelectServermain(), "Select Sever");
					util.thread(500);
					List<WebElement> serverName = getSelectServer();
					int size = serverName.size();
					countMoNum = 5 * size;
					util.click(serverName.get(0), "First Server Select ");
					util.thread(1000);
					util.click(getSelectServerPrice(), "Open Server Price");
					util.click(getSelectServrValue(), "Select Server Amount");
				} else if (countMoNum == 5) {
					util.click(getSelectServermain(), "Select Sever");
					util.thread(500);
					util.click(getSelectServer().get(1), "Second Server Select ");
					util.thread(1000);
					util.click(getSelectServerPrice(), "Open Server Price");
					util.click(getSelectServrValue(), "Select Server Amount");
				} else if (countMoNum == 10) {
					util.click(getSelectServermain(), "Select Sever");
					util.thread(500);
					util.click(getSelectServer().get(2), "Third Server Select ");
					util.thread(1000);
					util.click(getSelectServerPrice(), "Open Server Price");
					util.click(getSelectServrValue(), "Select Server Amount");
				} else if (countMoNum == 15) {
					util.click(getSelectServermain(), "Select Sever");
					util.thread(500);
					util.click(getSelectServer().get(3), "Four Server Select ");
					util.thread(1000);
					util.click(getSelectServerPrice(), "Open Server Price");
					util.click(getSelectServrValue(), "Select Server Amount");
				}

				util.click(getBuyNumberButton(), "New Number Buy ");
				util.thread(700);
				String strNumber = util.mygetText(getMobileNumber(), 5, 40);
				result = strNumber.substring(3);
				String res = result.substring(4);
				int intValue = Integer.parseInt(res);

				if (2 < intValue) {
					util.printMessage(result);
					break;
				}
			} catch (Exception e) {

			}
			countMoNum = countMoNum - 1;
		}
		return result;

	}

	public String flashOtp(String mobileNumber) {
		util.switchToWindowByDynamicTitle("RK ONLINE");
		String result = null;
		int count = 0;
		for (int i = 0; i <= 69; i++) {
			try {
				count = count + 1;
				util.thread(500);
				List<WebElement> otp = getMobileOtp2();
				int size = otp.size();
				int intValue = 0;
				String firstSix = "";

				for (int j = 0; j < size; j++) {
					String strNumber = util.mygetText(getFingMobileNo().get(j), 3, 40);
					String mobileNo = strNumber.substring(3);

					if (mobileNumber.equals(mobileNo)) {
						String strOtp = util.mygetText(otp.get(j), 3, 40);
						firstSix = strOtp.substring(0, 6);
						intValue = Integer.parseInt(firstSix);
						if (2 <= intValue) {
							result = firstSix;
							cancleAndDeleteButton(15);
							util.printMessage(result);
							countMoNum = 0;
							break;
						}
					}
				}
				if (2 <= intValue) {
					result = firstSix;
					cancleAndDeleteButton(15);
					util.printMessage(result);
					countMoNum = 0;
					break;
				}
			} catch (Exception e) {

			}

		}
		return result;

	}

	public void cancleAndDeleteButton(int waitOfSecond) {
		try {
			util.implicitilyOfSeconds(waitOfSecond);
			List<WebElement> canBt = getOtpCancle();
			int size = canBt.size();
			for (int i = 0; i < size; i++) {
				util.click(getOtpCancle().get(i), "Otp Button Cancle", 1);
				util.thread(500);
				util.click(getDeleteButton(), "Delete Button ", 1);
				util.thread(500);
				util.implicitilyOfSeconds(30);
			}
		} catch (Exception e) {
			util.implicitilyOfSeconds(30);
		}
	}

	public String findWringOtp() {
		String wringOt =null;
		util.switchToWindowByTitle("Login");
		util.thread(1000);
		try {
			 wringOt = util.mygetText(getWrongOTP(), 4, 30);
			if(wringOt.contains("Incorrect OTP, Please try again")) {
				util.openUrl(util.getProperties().getProperty("url"));
				util.thread(1000);
			}
		}catch(Exception e) {
			
		}
		return wringOt;
	}
	
	
}
