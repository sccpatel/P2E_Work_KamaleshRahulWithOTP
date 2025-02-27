package com.P2E.questionAndAnswer;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import com.P2E.utils.WebUtil;

import lombok.Getter;
@Getter
public class questionAndAnsOr {
	protected questionAndAnsOr(WebUtil util) {
		PageFactory.initElements(util.getDriver(), this);
	}
	
	
	@FindBy(xpath = "//div[@id='question-container']//span")
	private WebElement quesWeb;

	@FindBys({@FindBy(xpath = " //span[contains(@id,'ChoiceGroupLabel')]/following-sibling::div")})
	private List<WebElement> questions;
	
	
}
