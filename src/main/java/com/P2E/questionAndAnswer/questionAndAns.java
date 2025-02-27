package com.P2E.questionAndAnswer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebElement;

import com.P2E.utils.WebUtil;

public class questionAndAns extends questionAndAnsOr{
	private WebUtil util;

	public questionAndAns(WebUtil util) {
		super(util);
		this.util = util;
	}
	

	

	public  void handleChatboxQuestions() {

		Map<String, String> qaMap = new HashMap<>();
		qaMap.put("MS Word application can be used for", "All of the Above");
		qaMap.put("In MS Word, Ctrl+S is used for …..", "Save");
		qaMap.put("Which of the following is not a font style?", "Superscript");
		qaMap.put("Ctrl + P in MS Word is used for ______.", "Print a document");
		qaMap.put("In MS Word if we want a heading at the center of the page we will use", "Center");
		qaMap.put("Which of the below are the types of font in MS Word", "All of the Options");
		qaMap.put("In order to insert a picture on a word document we will use", "Home Tab");
		qaMap.put("In order to provide background color to a sentence we can use", "Background Shading");
		qaMap.put("How do we launch MS Excel on a computer?", "Start>All Programs>MS Office> Excel");
		qaMap.put("In MS Excel, Ctrl+S is used for …..", "Save");
		qaMap.put("What type of charts can be created in MS Excel?", "All of the above");
		qaMap.put("In an MS Excel sheet, to find a value in cell, which option in the list below will you use?",
				"Find");
		qaMap.put("A formula in Excel starts with _________.", "=");
		qaMap.put("The data in Excel are arranged in _____________.", "Rows and Columns");
		qaMap.put("How do we launch MS PowerPoint on a computer?", "Start>All Programs>MS Office> PowerPoint");
		qaMap.put("PowerPoint is used to create a", "Slideshow");
		qaMap.put("From where can you access the Save command ?", "None of the options");
		qaMap.put("In the title bar of MS Word we generally see the", "File name");
		qaMap.put("Extension of a saved MS Word file will be", ".docx");
		qaMap.put("Autosum function in MS Excel calculates _______ of a given range of cells.", "Sum");
		qaMap.put("Header & Footer Option is found in which Tab?", "Insert");
		qaMap.put("MS Excel can be used to automate.", "All of the above");
		qaMap.put("In order to find an average of a given cell which function will be used.", "Average");
		qaMap.put("MS Excel sheet resembles with a regular", "Maths Copy");
		qaMap.put("Formulae that is used in a cell to calculate a value is visible in", "Formula bar");
		qaMap.put("In a PowerPoint slideshow we can insert", "All of the above");
		qaMap.put("The extension of a PowerPoint file is", ".pptx");
		qaMap.put("Which of the following statements is not true?",
				"You can type text directly into a PowerPoint slide but typing in text box is more convenient");
		qaMap.put("Which menu provides you options like Animation Scheme, custom Animation, Slide Transition?",
				"Slide Show Menu");
		qaMap.put("What happens if you select first and second slide and then click on New Slide button on toolbar?",
				"A new slide is inserted as second slide in presentation");
		qaMap.put("Which of the following cannot be done using a computer?", "Cook a tasty meal");
		qaMap.put("To keep several files in one place you create a ______________.", "Folder");
		qaMap.put("Choose the benefit of making electronic payments of bills?", "Saves time and travel cost");
		qaMap.put("Which of the following can be used to check emails?", "All of the above");
		qaMap.put("Which will be a best device to make a utility bill payment online.", "Smartphone");
		qaMap.put("An application for making a video call through a smartphone is ___________", "Whatsapp");

		String question = getQuesWeb().getText();
//		String question = "find Question ";
		for (Map.Entry<String, String> entry : qaMap.entrySet()) {
			
			String que = entry.getKey();
			String answer = entry.getValue();			
			
			if (question.contains(que)) {
//			util.printMessage("Question & Answer  "+que+"   "+answer);	
				
			List<WebElement> qu =  getQuestions();
			util.QuestionSelectBoxes(qu, answer);
			}
		}
	}

}
