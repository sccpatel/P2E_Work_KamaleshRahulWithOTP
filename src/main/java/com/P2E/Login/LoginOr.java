package com.P2E.Login;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import com.P2E.utils.WebUtil;

import lombok.Getter;
@Getter
public class LoginOr {

	protected LoginOr(WebUtil util) {
		PageFactory.initElements(util.getDriver(), this);
	}

	@FindBy(xpath = "//button[@class='header__signing-button']")
	private WebElement signIn;

	@FindBy(xpath = "//button[@id='PhoneAuthentication']")
	private WebElement phoneAuthentication;

	@FindBy(xpath = "//select[@class='PhoneInputCountrySelect']")
	private WebElement selectphoneContry;
	
	@FindBy(xpath = "//input[@placeholder='Enter your mobile number']")
	private WebElement mobileNoEnert;

	@FindBy(xpath = "//input[@placeholder='Enter 6 digit code']")
	private WebElement mobileOtp;
	
//////////////////////// CSC P2E   ///////////////	
	@FindBy(xpath = "//a[text()='Sign up now']")
	private WebElement signUpBT;
	
	@FindBy(xpath = "//input[@placeholder='Email Address']")
	private WebElement emailAdderss;
	
	@FindBy(xpath = "//button[text()='Send verification code']")
	private WebElement sendVerificationCode;
	
	@FindBy(xpath = "//input[@placeholder='Verification Code']")
	private WebElement otpCode;
	
	@FindBy(xpath = "//button[text()='Verify code']")
	private WebElement verifyOtpBT;
	
	@FindBy(xpath = "//input[@placeholder='New Password']")
	private WebElement createNewPassw;
	
	@FindBy(xpath = "//input[@placeholder='Confirm New Password']")
	private WebElement confirmPassw;
	
	@FindBy(xpath = "//button[text()='Create']")
	private WebElement createButton;
	
	@FindBy(xpath = "//input[@placeholder='Password']")
	private WebElement loginPassword;
	
	@FindBy(xpath = "//button[text()='Sign in']")
	private WebElement singhInBT;

	@FindBy(xpath = "//div[text()='Next']")
	private WebElement nextBT;
	
	
	@FindBy(xpath = "//div[text()='Submit']")
	private WebElement submitBT;
	
	
	
	
	@FindBy(xpath = "//p[@id='num']")
	private WebElement mobileNumber;
	
	@FindBys(@FindBy(xpath = "//p[@id='num']"))
	private List<WebElement> fingMobileNo;
	
	@FindBys(@FindBy(xpath = "//div[@class='relative']"))
	private List<WebElement> mobileOtp2;

	@FindBys({@FindBy(xpath = "//button[text()='Cancel']")})
	private List<WebElement> otpCancle;
	
	@FindBy(xpath = "//button[text()='Delete']")
	private WebElement deleteButton;
	
	@FindBy(xpath = "//button[text()='Buy Number']")
	private WebElement buyNumberButton;
	
	@FindBy(xpath = "//div[contains(@class,'nice-select selectize')]")
	private WebElement selectServermain;
	
	@FindBy(xpath = "//li[text()='SERVER #1']")
	private WebElement selectServerFirst;

	@FindBy(xpath = "//li[text()='SERVER  MS']")
	private WebElement selectServerSecond;
	
	@FindBy(xpath = "//li[text()='SERVER X']")
	private WebElement selectServerThird;
	
	@FindBys(@FindBy(xpath = "//li[contains(text(),'SERVER')]"))
	private List<WebElement> selectServer;
	
	@FindBy(xpath = "//span[text()='Select Service']")
	private WebElement selectServerPrice;
	
	@FindBy(xpath = "//li[text()='Select Service']/following-sibling::li")
	private WebElement selectServrValue;
	
	@FindBy(xpath = "//span[contains(text(),'This page is')]")
	private WebElement errorpageNotWork;
	
	@FindBy(xpath = "//span[contains(text(),'Incorrect OTP')]")
	private WebElement wrongOTP;

	
}
