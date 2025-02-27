package com.P2E.SpeedVideo;

import com.P2E.utils.WebUtil;
import java.awt.*;
import java.awt.event.KeyEvent;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.io.File;


public class SpeedVideoControlar extends SpeedVideoControlarOr {
	private WebUtil util;

	public SpeedVideoControlar(WebUtil util) {
		super(util);
		this.util = util;
	}

	public void speedVideoCon() {
		try {
			System.out.println("Hello");
			util.openUrl("chrome-extension://your-extension-id/popup.html");
			JavascriptExecutor js = (JavascriptExecutor) util.getDriver();
            js.executeScript("arguments[0].playbackRate = 16.0;", getSpeed16x());

            // Print confirmation
            System.out.println("Video playback speed set to 1600%");
			
            util.quit();
			util.openUrl("chrome://extensions/?id=chnccghejnflbccphgkncbmllhfljdfa");
//			util.clickExt();
			
			System.out.println(" ");
			
		} catch (Exception e) {
e.printStackTrace();

		System.out.println("Hello");
		}

	}
}
