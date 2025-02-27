package com.P2E.LoginFlashSide;

import com.P2E.utils.WebUtil;

public class FlashLogin extends FlashLoginOr {
	private WebUtil util;

	public FlashLogin(WebUtil util) {
		super(util);
		this.util = util;
	}

	double avbalance = 0;
	double tBalance = 0;
	int tOPT = 0;
	int hOtp = 0;

	public void otpReport(int totalEmaill) {

		for (int i = 1; i < totalEmaill; i++) {
			try {
				if (i == 0) {
					enterUserIDAndPassword("BOYSCC@gmail.com", "BOY1020");
				} else {
					enterUserIDAndPassword("BOYSCC" + i + "@gmail.com", "BOY1020");
				}
			} catch (Exception e) {
				util.printMessage("No ID Runn");
			}
		}
		util.printMessage("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
		util.printMessage("Finial OTP History");
		util.printMessage("  Avlable Balance = " + avbalance);
		util.printMessage("  Total Recharge = " + tBalance);
		util.printMessage("  Total Used OTP = " + tOPT);
		util.printMessage("  History Of OTP = " + hOtp);
		util.printMessage("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
		util.writeDateInToExcelSheet1("Avlable Balance = " + avbalance, "Total History Of OTP = " + hOtp,
				"Total Used OTP = " + tOPT, "ExcelData\\CenterNameAndAutomation.xlsx", "BalanceOTP");
	}

	public void enterUserIDAndPassword(String userName, String Password) {
		util.sendKeys(getUserId(), userName, "Enter User Name ");
		util.sendKeys(getUserPassword(), Password, "Enter User Password ");
		util.click(getSignInBT(), "Sign In Button ");
		util.thread(500);
		String avBl = util.mygetText(getAvlabeleBalance(), 20, 40);
		String totalR = util.mygetText(getRecharTot(), 20, 40);
		String totalOtp = util.mygetText(getTotlOTP(), 20, 40);

		util.click(getNoHistory(), "Number History ");
//		int sizeNumber=   getHistoryReport().size();
//		String his=sizeNumber+"";
		String his = util.mygetText(getHistoryReport(), 5, 20);
		if(his==null) {
			his = "Showing 1 to 20 of 0 entries";
		}
		String[] value = his.split(" ");
		
		util.writeDateInToExcelSheet1(avBl, his, totalOtp, "ExcelData\\CenterNameAndAutomation.xlsx", "BalanceOTP");

		util.printMessage(userName + "\t Balance = " + avBl + " \t Recharge = " + totalR + "\t Used OTP = " + totalOtp + "\t "+his);

		String avBal = avBl.substring(1);
		avbalance = avbalance + Double.parseDouble(avBal);

		String tRech = totalR.substring(1);
		tBalance = tBalance + Double.parseDouble(tRech);

		tOPT = tOPT + Integer.parseInt(totalOtp);
		
//		hOtp = hOtp+sizeNumber;
		hOtp = hOtp + Integer.parseInt(value[5]);

		util.click(getProfile(), "Profile Open");
		util.click(getSignOut(), "Total OTP ");

	}

	public void openNewTabAnsLaunchOTP(int num) {
		util.openNewWindowTab("New Window Tab");
		util.openUrl(util.getProperties().getProperty("otpUrl"));
		if (num == 0) {
			util.sendKeys(getUserId(), "BOYSCC@gmail.com", "Enter User Name ");
		} else {
			util.sendKeys(getUserId(), "BOYSCC" + num + "@gmail.com", "Enter User Name ");
		}
		util.sendKeys(getUserPassword(), "BOY1020", "Enter User Password ");
		util.click(getSignInBT(), "Sign In Button ");

		util.click(getBuyNumber(), "Purchases Number");
	}

}
