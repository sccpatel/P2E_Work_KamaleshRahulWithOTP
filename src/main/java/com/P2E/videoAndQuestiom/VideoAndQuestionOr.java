package com.P2E.videoAndQuestiom;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import com.P2E.utils.WebUtil;

import lombok.Getter;
@Getter
public class VideoAndQuestionOr {

	protected VideoAndQuestionOr(WebUtil util) {
		PageFactory.initElements(util.getDriver(), this);
	}
	
	@FindBy(xpath = "//a[text()='Start Test']")
	private WebElement startTest;
	
	@FindBys({@FindBy(xpath = "//div[@class='sc-cYnRcR ccRCwb']")})
	private List<WebElement> listOfSection;
	
	@FindBys({@FindBy(xpath = " //span[contains(@id,'ChoiceGroupLabel')]/following-sibling::div")})
	private List<WebElement> question1;
	
	@FindBy(xpath = "//button[@name='Next']")
	private WebElement nextButton;
	
	@FindBy(xpath = "//button[@name='Previous']")
	private WebElement PreviousButton;

	@FindBy(xpath = "//button[@name='Submit']")
	private WebElement submit;
	
	@FindBy(xpath = "//button[@name='Back to the course']")
	private WebElement BackButton;
	
	@FindBy(xpath = "(//a[text()='Start Test'] | //button[@name='Submit'] | //h2[text()='Certificate'] | //button[@name='Next'])[1]")
	private WebElement allText;
	
	@FindBys({@FindBy(xpath = "//div[@class='sc-evbZsv cMNfti']")})
	private List<WebElement> allcurrect;
	
	@FindBys({@FindBy(xpath = "//div[@class='ms-TooltipHost root-93']")})
	private List<WebElement> allSection;
	
	@FindBys({@FindBy(xpath = "//i[@data-icon-name='Accept']")})
	private List<WebElement> allaccept;
	
	@FindBy(xpath = "//span[text()='Start Graded Assessment']")
	private WebElement assessment;
	
	@FindBy(xpath = "//button[text()='play_arrow']")
	private WebElement videoButton;
	
	@FindBy(xpath = "//div[@id='Profile']")
	private WebElement profileButton;
	
	@FindBy(xpath = "//div[text()='Sign out']")
	private WebElement signOutButton;
	
	@FindBy(xpath = "//span[@aria-label='Certificate']")
	private WebElement certificate;
	
	@FindBy(xpath = "//div[text()='My profile']")
	private WebElement editProfile;
	
	@FindBy(xpath = "//span[text()='Done']")
	private WebElement doneSubmit;
	
	@FindBy(xpath = "//button[text()='more_vert']")
	private WebElement selectVideoQuality;

	@FindBy(xpath = "//i[text()='settings']")
	private WebElement selectVideoQualitySetting;

	
	@FindBy(xpath = "//span[text()='136p']")
	private WebElement videoQuality;

	
}
