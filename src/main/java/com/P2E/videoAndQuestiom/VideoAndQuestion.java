package com.P2E.videoAndQuestiom;

import java.util.List;

import org.openqa.selenium.WebElement;

import com.P2E.LoginFormRagistion.RagistationForm;
import com.P2E.questionAndAnswer.questionAndAns;
import com.P2E.utils.WebUtil;

public class VideoAndQuestion extends VideoAndQuestionOr {

	private WebUtil util;

	public VideoAndQuestion(WebUtil util) {
		super(util);
		this.util = util;
	}

	int firstcount = 0;

	public void QuestionAnswer(int num) {
		util.thread(600);
		firstcount = num;
///// First Video Paly Waiting and click Next		
		videoPaly();

		for (int i = 0; i < 40; i++) {

			List<WebElement> allOkValue = getAllaccept();
			int size = allOkValue.size();
// Section 1		/////////////////////////////////////////////
//			List<WebElement> sec = getListOfSection();
//			util.click(sec.get(size), " ");
			if (size == 1) {
				waitTillnextAccept(size);
				util.click(getNextButton(), "Next Button");
			} else

			if (size == 2) {
				util.click(getStartTest(), "Start Test");
				answerAllQuestionAndNext(4, "Question 1");
				answerAllQuestionAndSubmit(2, "Questin 2");
			} else
////  Section  2  ///////////////////////////////////

			if (size == 3) {
				waitTillnextAccept(size);
				util.click(getNextButton(), "Next Button");
			} else

			if (size == 4) {
				waitTillnextAccept(size);
				util.click(getNextButton(), "Next Button");

			} else

			if (size == 5) {
				waitTillnextAccept(size);
				util.click(getNextButton(), "Next Button");
			} else

			if (size == 6) {
				util.click(getStartTest(), "Start Test");
				answerAllQuestionAndNext(4, "Question 1");
				answerAllQuestionAndSubmit(1, "Questin 2");
			} else

////  Section  3  ///////////////////////////////////

			if (size == 7) {
				waitTillnextAccept(size);
				util.click(getNextButton(), "Next Button");
			} else

			if (size == 8) {
				waitTillnextAccept(size);
				util.click(getNextButton(), "Next Button");
			} else

			if (size == 9) {
				util.click(getStartTest(), "Start Test");
				answerAllQuestionAndNext(2, "Question 1");
				answerAllQuestionAndSubmit(4, "Questin 2");
			} else

////  Section  4  ///////////////////////////////////

			if (size == 10) {
				waitTillnextAccept(size);
				util.click(getNextButton(), "Next Button");
			} else

			if (size == 11) {
				waitTillnextAccept(size);
				util.click(getNextButton(), "Next Button");
			} else

			if (size == 12) {
				util.click(getStartTest(), "Start Test");
				answerAllQuestionAndNext(2, "Question 1");
				answerAllQuestionAndSubmit(2, "Questin 2");
			} else

////  Section  5  ///////////////////////////////////

			if (size == 13) {
				waitTillnextAccept(size);
				util.click(getNextButton(), "Next Button");
			} else

			if (size == 14) {
				waitTillnextAccept(size);
				util.click(getNextButton(), "Next Button");
			} else

			if (size == 15) {
				waitTillnextAccept(size);
				util.click(getNextButton(), "Next Button");
			} else

			if (size == 16) {
				util.click(getStartTest(), "Start Test");
				answerAllQuestionAndNext(1, "Question 1");
				answerAllQuestionAndSubmit(4, "Questin 2");
			} else

////  Section  6  ///////////////////////////////////

			if (size == 17) {
				waitTillnextAccept(size);
				util.click(getNextButton(), "Next Button");
			} else

			if (size == 18) {
				waitTillnextAccept(size);
				util.click(getNextButton(), "Next Button");
			} else

			if (size == 19) {
				waitTillnextAccept(size);
				util.click(getNextButton(), "Next Button");
			} else

			if (size == 20) {
				util.click(getStartTest(), "Start Test");
				answerAllQuestionAndNext(4, "Question 1");
				answerAllQuestionAndSubmit(3, "Questin 2");
			} else

////  Section  7  ///////////////////////////////////

			if (size == 21) {
				waitTillnextAccept(size);
				util.click(getNextButton(), "Next Button");
			} else

			if (size == 22) {
				waitTillnextAccept(size);
				util.click(getNextButton(), "Next Button");
			} else

			if (size == 23) {
				util.click(getStartTest(), "Start Test");
				answerAllQuestionAndNext(3, "Question 1");
				answerAllQuestionAndSubmit(2, "Questin 2");
			} else

////  Section  8  ///////////////////////////////////

			if (size == 24) {
				waitTillnextAccept(size);
				util.click(getNextButton(), "Next Button");
			} else

			if (size == 25) {
				waitTillnextAccept(size);
				util.click(getNextButton(), "Next Button");
			} else

			if (size == 26) {
				waitTillnextAccept(size);
				util.click(getNextButton(), "Next Button");
			} else

			if (size == 27) {
				util.click(getStartTest(), "Start Test");
				answerAllQuestionAndNext(1, "Question 1");
				answerAllQuestionAndSubmit(3, "Questin 2");
			} else

////  Section  Final  ///////////////////////////////////

			if (size == 28) {
				waitTillnextAccept(size);
				util.click(getNextButton(), "Next Button");
			} else

			if (size == 29) {

				util.click(getAssessment(), "Assessment");
/// Test Paper /////						
				answerAllQuestionAndNext(4, "Question 1");
				answerAllQuestionAndNext(1, "Question 2");
				answerAllQuestionAndNext(1, "Question 3");
				answerAllQuestionAndNext(1, "Question 4");
				answerAllQuestionAndNext(2, "Question 5");
				answerAllQuestionAndNext(2, "Question 6");
				answerAllQuestionAndNext(3, "Question 7");
				answerAllQuestionAndNext(2, "Question 8");
				answerAllQuestionAndNext(4, "Question 9");
				answerAllQuestionAndNext(4, "Question 10");
				answerAllQuestionAndNext(3, "Question 11");
				answerAllQuestionAndNext(4, "Question 12");
				answerAllQuestionAndNext(1, "Question 13");
				answerAllQuestionAndNext(1, "Question 14");
				answerAllQuestionAndNext(4, "Question 15");
				answerAllQuestionAndNext(4, "Question 16");
				answerAllQuestionAndNext(4, "Question 17");
				answerAllQuestionAndNext(3, "Question 18");
				answerAllQuestionAndNext(1, "Question 19");

				answerAllQuestionAndSubmit(14, "Questin 20");

				util.thread(5000);

				util.click(getCertificate(), "Next Button");
				util.thread(1000);
				util.click(getPreviousButton(), "Previous Button");
				util.click(getCertificate(), "Next Button");
				
			} else

			if (size == 30) {
				util.thread(7000);
				util.click(getProfileButton(), "Click Profile", 5);
				util.click(getSignOutButton(), "Task Complit", 5);
				break;

			} else {
				util.printMessage("Print ");
				waitTillnextAccept(size);
				util.click(getNextButton(), "Next Button");

			}

		}

	}

	public void signOutAndTackSnapShortPage(int rowCount,String excelFilePath) {
		try {
			util.thread(600);
			util.click(getProfileButton(), "Click My Profile", 30);
			util.thread(600);
			util.click(getEditProfile(), "Edit Profile ", 30);
			RagistationForm rg = new RagistationForm(util);
			rg.selectStatDistAndCenter(rowCount ,excelFilePath);
			

//			util.click(getDoneSubmit(), "Re Submit Form Successfully");
//			util.click(getProfileButton(), "Click Profile", 5);
//			util.click(getSignOutButton(), "Task Complit", 5);

		} catch (Exception e) {
			util.click(getProfileButton(), "Click Profile", 5);
			util.click(getSignOutButton(), "Task Complit", 5);
		}
	}

	public void waitTillnextAccept(int size) {
		int sz = 0;
		try {
			util.implicitilyOfSeconds(2);
			int match = size + 1;
			sz = size;
			util.printMessage((match) + "  Accept waiting  and  " + sz + "  Next Accept" + "  is  Successfully");
			for (int i = 0; true; i++) {
				waitNum = waitNum + 1;
				if (size == 3 || size == 7 || size == 10 || size == 13 || size == 17 || size == 21 || size == 24) {
					refreshPageAndVideoPlay(waitNum, 50);
				} else {
					refreshPageAndVideoPlay(waitNum, 150);
				}
				util.thread(200);
				sz = getAllaccept().size();
				util.thread(200);
				if (sz >= match) {

					util.implicitilyOfSeconds(40);
					waitNum = 0;
					break;
				}
			}
			
		} catch (Exception e) {
			util.printMessage("Not Accept  " + sz + "Unsuccessfully");
		}
	}

	int count = 0;

	public void answerAllQuestionAndSubmit(int questionNo, String elementNmae) {

		try {
			int nu = questionNo;
			List<WebElement> qu = getQuestion1();
			int size = qu.size();
			questionAndAns quest = new questionAndAns(util);
			if (14 == nu) {
				util.thread(200);
				quest.handleChatboxQuestions();
				;
				util.thread(200);
				util.click(getSubmit(), "Submit");
				util.thread(200);
				util.click(getBackButton(), "Back Button", 10);
			} else {
				util.thread(200);
				quest.handleChatboxQuestions();
				util.thread(200);
				util.click(getSubmit(), "Submit");
				util.thread(200);
				util.click(getBackButton(), "Back Button", 10);
			}
		} catch (Exception e) {
			util.printMessage("Question Selection Fail  >> Method  answerAllQuestionAndSubmit");

			answerAllQuestionAndSubmit(questionNo, elementNmae);
		}
	}

	public void answerAllQuestionAndNext(int questionNo, String elementNmae) {
		questionAndAns quest = new questionAndAns(util);

		try {
			quest.handleChatboxQuestions();
			util.thread(200);
			util.click(getNextButton(), "Next Button");
		} catch (Exception e) {
			util.printMessage("Question Selection Fail   >>>> Method answerAllQuestionAndNext");
		}

	}

	public void videoPaly() {

		util.thread(300);
		try {
			util.thread(5000);
			util.printMessage("Wait 5000 Start Successfully");
			util.mouseClick(getVideoButton());
			util.printMessage("Wait Click Video Button Successfully ");
			util.implicitilyOfSeconds(2);

			for (int a = 0; true; a++) {
				refreshPageAndVideoPlay(waitNum, 40);
				waitNum = waitNum + 1;
//				util.thread(200);
				if (getAllaccept().size() >= 1) {

					util.printMessage("First Video Waiting  Successfully");
					util.click(getNextButton(), "Next Button");
					util.implicitilyOfSeconds(40);
					waitNum = 0;
					break;
				}
			}
		} catch (Exception e) {
			util.implicitilyOfSeconds(40);
		}

	}

	int waitNum = 0;

	public void refreshPageAndVideoPlay(int wait, int loopCondition) {
		try {
			if (firstcount == 1) {
				waitNum = 0;
			}
			if (wait >= loopCondition) {
				util.goToRefreshPage();
				util.thread(4000);
				util.mouseClick(getVideoButton());
				waitNum = 0;
				util.thread(2000);
				util.printMessage("Page Refrash and video Play Succefully ");
			}
		} catch (Exception e) {

		}
	}

}
