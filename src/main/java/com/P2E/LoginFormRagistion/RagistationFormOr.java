package com.P2E.LoginFormRagistion;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import com.P2E.utils.WebUtil;

import lombok.Getter;

@Getter
public class RagistationFormOr {

	protected RagistationFormOr(WebUtil util) {
		PageFactory.initElements(util.getDriver(), this);
	}

	@FindBy(xpath = "//input[@name='First Name']")
	private WebElement firstName;

	@FindBy(xpath = "//input[@name='Last Name']")
	private WebElement lastName;

	@FindBy(xpath = "//input[@name='Contact']")
	private WebElement contactName;
	
	@FindBy(xpath = "//button[@aria-label='select Gender']")
	private WebElement gender;
	
	@FindBy(xpath = "(//span[@class='ms-Label'])[1]")
	private WebElement genderFindMale;
	
	@FindBy(xpath = "(//span[@class='ms-Label'])[2]")
	private WebElement genderFindFemale;
	
	@FindBy(xpath = "//span[text()='Confirm']")
	private WebElement Confirm;
	
	
	@FindBy(xpath = "//button[contains(@aria-label,'select Age')]")
	private WebElement Age;
	
	@FindBy(xpath = "(//span[@class='ms-Label'])[4]")
	private WebElement selectAge;
	
	
	@FindBy(xpath = "//button[@aria-label='select Role']")
	private WebElement role;
	
	@FindBy(xpath = "(//span[@class='ms-Label'])[1]")
	private WebElement studentSelect;
	
	
	@FindBy(xpath = "//button[@aria-label='select State']")
	private WebElement state;

	@FindBy(xpath = "//input[@placeholder='Search']")
	private WebElement serchBox;
	
//	@FindBy(xpath = "//span[text()='Madhya Pradesh']")
//	private WebElement stateSelect;

	@FindBys(@FindBy(xpath = "//span[@class='ms-Label']"))
	private List<WebElement> selectAllOtions;	
	
	
	@FindBy(xpath = "//button[@aria-label='select District']")
	private WebElement district;

//	@FindBy(xpath = "//span[text()='Balaghat']")
//	private WebElement districtSelect;
	
	
	@FindBy(xpath = "//button[@aria-label='select Institution Name'] | //button[@aria-label='select Center']")
	private WebElement institution;

//	@FindBy(xpath = "//span[text()='Kaushal Prashikshan and Rojgar Vikas Sansthan, Mandla']")
	@FindBy(xpath = "//span[text()='Kaushal Prashikshan and Rojgar Vikas Sansthan, Balaghat']")
	private WebElement institutNameSelect;
	

	@FindBy(xpath = "//button[@aria-label='select Are you interested to get contacted by P2E partners for job related support?']")
	private WebElement interested;

	@FindBy(xpath = "//span[text()='No']")
	private WebElement interestedSelect;
	
	@FindBy(xpath = "//span[text()='GET STARTED']")
	private WebElement getStarted;
	
	
	@FindBy(xpath = "//span[text()='Course Library']")
	private WebElement selectCourse;
	
	@FindBy(xpath = "//div[contains(@aria-label,'Digital Productivity')]")
	private WebElement digitalPro;
	
	@FindBy(xpath = "//span[text()='Register']/parent::span")
	private WebElement coursRagister;
	
	
	@FindBy(xpath = "(//button[@aria-label='select Gender']//span)[2]")
	private WebElement genderName;
	
	@FindBy(xpath = "(//button[@aria-label='select State']//span)[2]")
	private WebElement stateName;
	
	@FindBy(xpath = "(//button[@aria-label='select District']//span)[2]")
	private WebElement districtName;
	
	@FindBy(xpath = "(//button[@aria-label='select Institution Name'] | //button[@aria-label='select Center'])//span/span")
	private WebElement institutionName;
	
	
	
/// For Screen Short 	
	@FindBy(xpath = "//div[@class='ms-Panel-main main-203']")
	private WebElement screen1;
	
	@FindBy(xpath = "//div[@class='sc-koyXVk jBwCtq']")
	private WebElement screen2;
	
	@FindBy(xpath = "//div[@class='ms-Panel-scrollableContent scrollableContent-165']")
	private WebElement screen3;
	
	@FindBy(xpath = "//div[@id='Profile']")
	private WebElement profileButton;
	
	@FindBy(xpath = "//div[text()='My Certificates']")
	private WebElement certificate;
	
/////// CSC Form

	@FindBy(xpath = "//input[@placeholder='P2E will contact on this number for user validation']")
	private WebElement mobileNo;
	
	@FindBy(xpath = "//button[@aria-label='select By filling this form you give your consent to share your data with UNICEF partners for monitoring purposes']")
	private WebElement interestedSelectCSC;
	
	@FindBy(xpath = "//span[text()='Yes']")
	private WebElement interestedSelectYes;
	
	@FindBy(xpath = "//a[text()='Home']")
	private WebElement errorHomePage;
	
	@FindBy(xpath = "//button[@class='header__signing-button']")
	private WebElement signIn;
	
	@FindBy(xpath = "//button[@id='PhoneAuthentication']")
	private WebElement phoneAuthentication;
	
	@FindBy(xpath = "//body[text()='Bad Request']")
	private WebElement badRequest;
	
	@FindBy(xpath = "//span[text()='Done']")
	private WebElement doneSubmit;
	
	
	@FindBy(xpath = "(//h1[@id='panel-heading'])[2]")
	private WebElement headingName;
	
	@FindBys({@FindBy(xpath = "//div[text()='Required Field']")})
	private List<WebElement> requiedField;
	
	
	
}
