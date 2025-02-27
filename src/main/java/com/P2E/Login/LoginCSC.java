package com.P2E.Login;

import java.util.Scanner;

import com.P2E.LoginFlashSide.FlashLogin;
import com.P2E.utils.WebUtil;

public class LoginCSC extends LoginOr {
	private WebUtil util;

	public LoginCSC(WebUtil util) {
		super(util);
		this.util = util;
	}

	public void emailRagistation(String email, String Password) {
		util.click(getSignIn(), "Login Button", 20);
		util.thread(2000);
		util.click(getSignUpBT(), "Email SignUP", 20);
		util.sendKeys(getEmailAdderss(), email, "Enter Email ID");
		util.click(getSendVerificationCode(), "Otp code Button");
//		String otp = scannerName("Enter Otp and I am Waithing. ");
//		util.sendKeys(getOtpCode(), otp, "Enter Otp Code");
//		util.thread(1000);
//		util.click(getVerifyOtpBT(), "Verify OTP Code");
		util.thread(1000);
		util.sendKeys(getCreateNewPassw(), Password, "Create New Password");
		util.sendKeys(getConfirmPassw(), Password, "Confirm New Password");
//		util.click(getCreateButton(), "Password Create ");
	}

	public void login(String email, String password) {
		util.click(getSignIn(), "Login Button", 20);
		util.thread(2000);
		util.sendKeys(getEmailAdderss(), email, "Enter Email");
		util.sendKeys(getLoginPassword(), password, "Enter Password");
		util.click(getSinghInBT(), "Login Sign Button");

	}

	public String scannerName(String printValue) {
		String otpV = null;
		try {
			System.out.println(printValue);
			Scanner scanner = new Scanner(System.in);

			otpV = scanner.nextLine();
			scanner.close();
		} catch (Exception e) {
			System.out.println("Value incurrect found! plz ReEnter");
			otpV = scannerName(printValue);
		}
		return otpV;
	}

}
