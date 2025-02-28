package com.P2E.LoginFormRagistion;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;

import com.P2E.utils.WebUtil;

public class RagistationForm extends RagistationFormOr {

	private WebUtil util;

	public RagistationForm(WebUtil util) {
		super(util);
		this.util = util;
	}

/////////////////////////// Parameter Option /////////////////////////////////////////////////		
	String state = "Jharkhand";
	String dist = "Deoghar";
	String institute = "Anand Foundation, Deoghar";
//	String link = null;
/////////////////////////////////////////////////////////////////////////////////				

	public void ragistationForm(String excelFilePath) {
		waitTillRagistation();
		int ro =util.readAndWriteDataInToExcel("Excel Row Write  ", "ExcelData\\CSC_StateDistAndCenterName.xlsx");
		rowCount = ro-1;
		util.thread(800);
		
		ArrayList<String> nalist = util.readDataFromExcelSheet(rowCount, "ExcelData\\RahulP2E_NameAndState.xlsx");
		String name = nalist.get(0);
		String gender = nalist.get(1);
		state = nalist.get(2);
		dist = nalist.get(3);
		institute = nalist.get(4);
//		link = nalist.get(5);
//		findWindowsWaiting("My courses");

//Name 

		String lastName = "";

		String[] listName = name.split(" ");
		int size = listName.length;
		String firstName = listName[0];
		if (size == 1) {
			lastName = ".";

		} else {

			for (int i = 1; i < listName.length; i++) {
				lastName = lastName + listName[i] + " ";
			}
		}
		int refresh = 0;
		for (int i = 0; i < 76; i++) {
			if (refresh == 15) {
				util.goToRefreshPage();
				refresh = 0;
			}
			refresh = refresh + 1;
			util.implicitilyOfSeconds(5);
			List<WebElement> reqfield = getRequiedField();
			int reqsize = reqfield.size();
			util.implicitilyOfSeconds(30);
			if (reqsize == 9) {
				util.sendKeys(getFirstName(), firstName, "First name");
			} else if (reqsize == 8) {
				util.sendKeys(getLastName(), lastName, "Last Name");

//Select Gender Type		
			} else if (reqsize == 7) {
				util.click(getGender(), "Gender");
				if (gender.equalsIgnoreCase("Male")) {
					util.click(getGenderFindMale(), "Male");
				} else {
					util.click(getGenderFindFemale(), "Female");
				}
				util.click(getConfirm(), "Gender Confirm");

//Select Age 25
			} else if (reqsize == 6) {
				util.click(getAge(), "Age", 30);
				util.click(getSelectAge(), "Select Age", 30);
				util.click(getConfirm(), "Confirm Age ", 30);

//Select Role
			} else if (reqsize == 5) {
				util.click(getRole(), "Role", 30);
				util.click(getStudentSelect(), "Select Role Student", 30);
				util.click(getConfirm(), "Confirm Student Role", 30);

//Select State 	
			} else if (reqsize == 4) {
				util.thread(500);
				util.click(getState(), "State", 30);
				util.thread(500);
				util.sendKeys(getSerchBox(), state, "Search State");
				util.thread(1000);
				util.QuestionSelectBoxes(getSelectAllOtions(), state);
				util.click(getConfirm(), "Confirm State");

//Select District 
			} else if (reqsize == 3) {
				util.thread(500);
				util.click(getDistrict(), "District", 30);
				util.thread(500);
				util.sendKeys(getSerchBox(), dist, "Search District");
				util.thread(1000);
				util.QuestionSelectBoxes(getSelectAllOtions(), dist);
				util.click(getConfirm(), "Confirm District", 30);

//Select Intitution Name Select  
			} else if (reqsize == 2) {
				util.thread(500);
				util.click(getInstitution(), "Intitution", 30);
				util.thread(500);
				util.sendKeys(getSerchBox(), institute, "Search Intitution");
				util.thread(1000);
				util.QuestionSelectBoxes(getSelectAllOtions(), institute);
				util.click(getConfirm(), "Confirm Intitution");

//Select Interested Option	
			} else if (reqsize == 1) {
				util.click(getInterested(), "Interested");
				util.thread(500);
				util.click(getInterestedSelect(), "Interested Selection No");
				util.click(getConfirm(), "Confirm Intitution");
			} else if (reqsize == 0) {
				break;
			}
		}
// Screen Short
		util.zoomInAndOutPage(.65);
		util.thread(500);
		util.takeSnapshotOfPage(dist + "  " + rowCount + " ");
		util.takeSnapshotSingleElement(getScreen3(), dist + "  " + rowCount + " ");
		util.thread(500);
		util.zoomInAndOutPage(1.0);

//// All Data Store in Aray List 		
		ArrayList<String> fullName = formAllDeatailReceveid(rowCount);

////Submit Ragistation Form 
		util.click(getGetStarted(), "Ragistation Submit", 30);

		util.thread(1000);
		util.click(getSelectCourse(), "Select Course", 30);
		util.click(getDigitalPro(), "Select Digital Productivity", 30);
		util.click(getCoursRagister(), "Course Ragistor", 30);

		util.writeDataInToExcelSheet(fullName, "Form Fill ", excelFilePath);

	}

	public ArrayList<String> formAllDeatailReceveid(int rowCount) {
		ArrayList<String> formaData = new ArrayList();
		formaData.add(rowCount + "");
		formaData.add(util.currentDate("dd-MMM-yyyy"));
		formaData.add(util.getTextByAttributeValue(getFirstName(), "value"));
		formaData.add(util.getTextByAttributeValue(getLastName(), "value"));

		String phoneNumber = util.getTextByAttributeValue(getContactName(), "Value");
		String mobileNo = phoneNumber.replaceFirst("^\\+91\\s*", "");
		formaData.add(mobileNo);

		formaData.add(util.mygetText(getGenderName(), 20, 30));
		formaData.add(util.mygetText(getStateName(), 20, 30));
		formaData.add(util.mygetText(getDistrictName(), 20, 30));
		formaData.add(util.mygetText(getInstitutionName(), 20, 30));
//		formaData.add(link);
		formaData.add(util.currentDateWithTime());
		return formaData;
	}

	public void waitTillRagistation() {
		try {
			waitTillRag();
		} catch (Exception e) {
			waitTillRag();
		}
	}

	public void waitTillRag() {
		int cn = 0;
		util.switchToWindowByTitle("My courses");
		for (int i = 0; true; i++) {
			util.thread(500);
			cn = cn + 1;
			if (cn >= 10) {
				util.switchToWindowByTitle("My courses");
				util.printMessage(i + "  Waiting For Ragistation " + util.currentDateWithTime());
				cn = 0;
			}
			try {
				util.implicitilyOfSeconds(2);
				if (getFirstName().isDisplayed() || getFirstName().isEnabled()) {
					util.implicitilyOfSeconds(40);
					break;
				}
			} catch (Exception e) {
				try {
					if (getErrorHomePage().isDisplayed() || getErrorHomePage().isEnabled()) {
						util.click(getErrorHomePage(), "Error Page", 5);
						util.thread(700);
						util.click(getSignIn(), "Login Button", 20);
						util.click(getPhoneAuthentication(), "PhoneAuthentication");
					}
				} catch (Exception p) {

				}
			}
			try {
				util.implicitilyOfSeconds(2);
				if (getBadRequest().isDisplayed() || getBadRequest().isEnabled()) {
					util.implicitilyOfSeconds(30);
					util.goToRefreshPage();
				}
			} catch (Exception y) {

			}

			try {
				util.implicitilyOfSeconds(2);
				if (getProfileButton().isDisplayed() || getProfileButton().isEnabled()) {
					util.implicitilyOfSeconds(30);
					String certi = util.mygetText(getCertificate(), 5, 30);
					if (certi.contains("My Certificates")) {
						int a = 1 / 0;
						System.out.println(a);
					}
				}
			} catch (ArithmeticException q) {
				int a = 1 / 0;
				System.out.println(a);
			} catch (Exception e) {

			}
		}
	}

	public void selectStateDistAndCenter(int selectState) {
		if (selectState == 1) {
			state = "Jharkhand";
			dist = "Bokaro";
			institute = "Anand Foundation, Bokaro";

		} else if (selectState == 2) {
			state = "Jharkhand";
			dist = "Gumla";
			institute = "Anand Foundation, Gumla";
		} else if (selectState == 3) {
			state = "Jharkhand";
			dist = "Pakur";
			institute = "Anand Foundation, Pakur";

		} else if (selectState == 4) {
			state = "Jharkhand";
			dist = "Jamtara";
			institute = "Anand Foundation, Jamtara";

		} else if (selectState == 5) {
			state = "Jharkhand";
			dist = "Deoghar";
			institute = "Anand Foundation, Deoghar";
		}
	}

	public void selectStateDistAndCenter() {
		try {
			int number = util.countRowInExcel();
			int rowIndex = (number - 1) / 50 + 1;

			ArrayList<String> nalist = util.readDataFromExcelSheet(rowIndex, "ExcelData\\CenterNameAndAutomation.xlsx");
			state = nalist.get(0);
			dist = nalist.get(1);
			institute = nalist.get(2);
			System.out.println(rowIndex);
		} catch (Exception e) {
/////////////////////////// Parameter Option /////////////////////////////////////////////////		
			state = "Madhya Pradesh";
			dist = "Balaghat";
			institute = "Kaushal Prashikshan and Rojgar Vikas Sansthan, Balaghat";

/////////////////////////////////////////////////////////////////////////////////					
		}
	}
int rowCount = 1;
	public void selectStatDistAndCenter( String excelFilePath) {
		int ro =util.readAndWriteDataInToExcel("Excel Row Write  ", "ExcelData\\CSC_StateDistAndCenterName.xlsx");
		rowCount = ro-1;
		util.thread(800);
		ArrayList<String> nalist = util.readDataFromExcelSheet(rowCount, "ExcelData\\RahulP2E_NameAndState.xlsx");
		String name = nalist.get(0);
		String gender = nalist.get(1);
		state = nalist.get(2);
		dist = nalist.get(3);
		institute = nalist.get(4);
// Select State
		util.thread(500);
		util.click(getState(), "State", 30);
		util.thread(2000);
		util.sendKeys(getSerchBox(), state, "Search State");
		util.thread(2000);
		util.QuestionSelectBoxes(getSelectAllOtions(), state);
		util.click(getConfirm(), "Confirm State");

		// Select District
		util.thread(500);
		util.click(getDistrict(), "District", 30);
		util.thread(2000);
		util.sendKeys(getSerchBox(), dist, "Search District");
		util.thread(2000);
		util.QuestionSelectBoxes(getSelectAllOtions(), dist);
		util.click(getConfirm(), "Confirm District", 30);

		// Select Intitution Name Select
		util.thread(500);
		util.click(getInstitution(), "Intitution", 30);
		util.thread(2000);
		util.sendKeys(getSerchBox(), institute, "Search Intitution");
		util.thread(2000);
		util.QuestionSelectBoxes(getSelectAllOtions(), institute);
		util.click(getConfirm(), "Confirm Intitution");

		// Select Interested Option
		util.click(getInterested(), "Interested");
		util.thread(500);
		util.click(getInterestedSelect(), "Interested Selection No");
		util.click(getConfirm(), "Confirm Intitution");

		// Screen Short
		util.zoomInAndOutPage(.65);
		util.thread(500);
		util.takeSnapshotOfPage(dist + " " + 54 + " ");
		util.takeSnapshotSingleElement(getScreen2(), dist + " " + 54 + " ");
		util.thread(500);
		util.zoomInAndOutPage(1.0);
		util.thread(500);

		ArrayList<String> fullName = formAllDeatailReceveid(rowCount);
		fullName.add("Resubmit Form");
		util.click(getDoneSubmit(), "Re Submit Form Successfully");

		util.thread(500);
		util.click(getSelectCourse(), "Select Course", 30);
		util.click(getDigitalPro(), "Select Digital Productivity", 30);
//	util.thread(600);
//		util.click(getCoursRagister(), "Course Ragistor", 7);

		util.writeDataInToExcelSheet(fullName, "Form Fill ", excelFilePath);

	}

	public void ragist() {

		List<WebElement> ref = getRequiedField();
		int siz = ref.size();

	}

	public void findWindowsWaiting(String pageTitle) {
		int num = 1;
		for (int i = 0; i < num;) {
			ArrayList<String> namewin = util.findOutAllWindowName();
			for (int j = 0; j < namewin.size(); j++) {
				if (pageTitle.contains(namewin.get(j))) {
					num = 0;
					break;
				}
			}
			util.thread(5000);
		}
		util.switchToWindowByTitle(pageTitle);

	}

}
