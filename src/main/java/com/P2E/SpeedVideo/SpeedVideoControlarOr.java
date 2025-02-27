package com.P2E.SpeedVideo;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.P2E.utils.WebUtil;

import lombok.Getter;

@Getter
public class SpeedVideoControlarOr {
	
	protected SpeedVideoControlarOr(WebUtil util) {
		PageFactory.initElements(util.getDriver(), this);
	}
	
	@FindBy(xpath = "//span[text()='Add to Chrome']")
	private WebElement videoextention;
	
	@FindBy(xpath = "//button[text()='Add extension']")
	private WebElement acceptExt;

	@FindBy(tagName =  "video")
	private WebElement speed16x;
	
	@FindBy(tagName =  "//span[text='Pin to toolbar']")
	private WebElement pinBT;

}
