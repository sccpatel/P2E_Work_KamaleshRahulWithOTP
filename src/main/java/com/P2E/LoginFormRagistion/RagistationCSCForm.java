package com.P2E.LoginFormRagistion;

import com.P2E.utils.WebUtil;

public class RagistationCSCForm extends RagistationFormOr {

	private WebUtil util;

	public RagistationCSCForm(WebUtil util) {
		super(util);
		this.util = util;
	}


/////////////////////////// Parameter Option /////////////////////////////////////////////////		
String state = "Jharkhand";
String dist = "Koderma";
String center = "317771540010-Surendra Kumar Mehta";

/////////////////////////////////////////////////////////////////////////////////			

	
	
	public void ragistationForm(String name, String gender, String mobileNumber, int count) {
		waitTillRagistation();
//Name 
		String lastName = "";

		String[] listName = name.split(" ");
		int size = listName.length;
		String firstName = listName[0];
		if (size == 1) {
			lastName = ".";

		} else {

			for (int i = 1; i < listName.length; i++) {
				if(i == listName.length-1) {
					lastName = lastName + listName[i];
				}else {
				lastName = lastName + listName[i] + " ";}
			}
		}
		util.sendKeys(getFirstName(), firstName, "First name");
		util.sendKeys(getLastName(), lastName, "Last Name");

//Select Age 25
		util.click(getAge(), "Age");
		util.click(getSelectAge(), "Select Age");
		util.click(getConfirm(), "Confirm Age ");

//Select Gender Type		
		util.click(getGender(), "Gender");
		if (gender.equalsIgnoreCase("Male")) {
			util.click(getGenderFindMale(), "Male");
		} else {
			util.click(getGenderFindFemale(), "Female");
		}
		util.click(getConfirm(), "Gender Confirm");

//Select State 	
		util.click(getState(), "State", 30);
		util.thread(500);
		util.sendKeys(getSerchBox(), state, "Search State");
		util.thread(500);
		util.QuestionSelectBoxes(getSelectAllOtions(), state);
		util.click(getConfirm(), "Confirm State");

//Select District 	
		util.click(getDistrict(), "District", 30);
		util.thread(500);
		util.sendKeys(getSerchBox(), dist, "Search District");
		util.thread(500);
		util.QuestionSelectBoxes(getSelectAllOtions(), dist);
		util.click(getConfirm(), "Confirm District");

//Select Center Name Select  		
		util.click(getInstitution(), "Intitution", 30);
		util.thread(500);
		util.sendKeys(getSerchBox(), center, "Search Intitution");
		util.thread(500);
		util.QuestionSelectBoxes(getSelectAllOtions(), center);
		util.click(getConfirm(), "Confirm Intitution");

//insert Mobile Number		
		util.sendKeys(getMobileNo(), mobileNumber, "Mobile No");
		util.thread(500);
		util.click(getInterestedSelectCSC(), " Interested Opion");
		util.thread(500);
		util.click(getInterestedSelectYes(), "Interested Selection Yes");
		util.click(getConfirm(), "Confirm Intitution");

//// Screen Short
		util.zoomInAndOutPage(.65);
		util.thread(400);
		util.takeSnapshotOfPage(count + " P2E");
		util.takeSnapshotSingleElement(getScreen3(), count + " short");
		util.thread(300);
		util.zoomInAndOutPage(1.0);
////Submit Ragistation Form 		
		util.click(getGetStarted(), "Ragistation Submit");

		util.thread(1000);
		util.click(getSelectCourse(), "Select Course");
		util.click(getDigitalPro(), "Select Digital Productivity");
		util.thread(600);
//		util.click(getCoursRagister(), "Course Ragistor");

	}

	public void waitTillRagistation() {
		for (int i = 0; true; i++) {
			util.thread(500);
			try {
				util.implicitilyOfSeconds(2);
				if (getFirstName().isDisplayed()||getFirstName().isEnabled()) {
					util.implicitilyOfSeconds(40);
					break;
				} 
			} catch (Exception e) {
//				e.printStackTrace();
				util.printMessage("Waiting For Ragistation ");
			}
		}

	}


}
