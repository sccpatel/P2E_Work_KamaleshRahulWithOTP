package com.P2E.LoginFlashSide;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import com.P2E.utils.WebUtil;

import lombok.Getter;
@Getter
public class FlashLoginOr {
	protected FlashLoginOr(WebUtil util) {
		PageFactory.initElements(util.getDriver(), this);
	}
	
	@FindBy(xpath = "//input[@placeholder='Enter Email']")
	private WebElement userId;
	
	@FindBy(xpath = "//input[@placeholder='Enter Password']")
	private WebElement userPassword;

	@FindBys({@FindBy(xpath = "//span[@class='block text-base text-[#515365] dark:text-white-light']")})
	private List<WebElement> rechargeAndPurNo;
	
	@FindBy(xpath = "//button[text()='SIGN IN']")
	private WebElement signInBT;
	
	@FindBy(xpath = "//div[@Class='relative text-xl whitespace-nowrap']")
	private WebElement avlabeleBalance;
	
	@FindBy(xpath = "(//span[@class='block text-base text-[#515365] dark:text-white-light'])[3]")
	private WebElement recharTot;
//	
	@FindBy(xpath = "(//span[@class='block text-base text-[#515365] dark:text-white-light'])[4]")
	private WebElement totlOTP;
	
	@FindBy(xpath = "//a[@class='relative group']//img")
	private WebElement profile;
	
	@FindBy(xpath = "//a[@href='logout']")
	private WebElement signOut;
	
	@FindBy(xpath = "//span[text()='Buy Numbers']")
	private WebElement buyNumber;
	
	@FindBy(xpath = "//span[text()='Numbers History']")
	private WebElement noHistory;
	
	@FindBy(xpath = "//div[@class='dataTable-info']")
	private WebElement historyReport;
	
//	@FindBys(@FindBy(xpath = "//tbody/tr"))
//	private List<WebElement> historyReport;
	
}
