package com.P2E.utils;

import java.awt.RenderingHints.Key;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.NoSuchSessionException;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.SessionNotCreatedException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.google.common.io.Files;

import lombok.Getter;
import lombok.Setter;
import net.bytebuddy.utility.RandomString;

/*__________ lombok ____________
 * 
 * 1.to download lombok site >>> https://projectlombok.org/download
 * 2.it is use for getting getter and setter method
 * 3.it is open with java file but if not open then open their path with cmd(command prompt)
 * 4.Run as this prompt >> java -jar lombok.jar
 * 5.TDD :-TEST DRIVEN DEVELOPMENT(low level language)
 * 6.BBD :-BEHAVIOR DRIVEN DEVELOPMENT(Gherkin language)-high level language	
 */

/**
 * @author Virendra Kumar Patel
 *
 */
@Getter
@Setter
public class WebUtil {

	/**
	 * Defines a test. You can add logs, snapshots, assign author
	 */
	private ExtentTest extTest;

	/**
	 * driver :- it is the object of WebDriver that initialize at class level or
	 * variable.....
	 */
	private WebDriver driver;

	/**
	 * 
	 * 
	 * _ Created By User(public/private/default) without argument / # Default
	 * Constructors/ \ \_ Created By JVM(public) without argument
	 * 
	 * 1.whenever we create a default constructor then we can create an Object
	 * within the package. 2.whenever we create a public constructor then we can
	 * create an Object anyWhere. 3.whenever we create a private constructor hen we
	 * can create an Object of that class only within the class.
	 * 
	 * *InterViewer Question* Ques:- Can we create an Object outside the class if
	 * our class is public and constructor is private? Ans:- No! because when we
	 * create a constructor as a private then JVM won't create a constructor it can
	 * only possible within the class
	 * 
	 * 
	 */
	private WebUtil() {

	}

	/**
	 * 1.private :- instance variable is private so it can't be accessed from
	 * outside. 2.static :- instance variable is static so object created only once
	 * and reused(shared) through getInstance() method. 1.Multiton class:-all the
	 * class that we create an object two or more times i.e called multiton class.
	 * 2.Singleton class:- singleton is a design pattern(architature). - A singleton
	 * class should have only one (Object)instance, not more than one instance is
	 * created i.e called as singleton.
	 */
	private static WebUtil webUtil;

	/**
	 * getInstance() :- this method is used to create a new Object but firstly it
	 * will check the condition when WebUtil is null then it'll create an instance
	 * And it can be accessed from anywhere a) create private constructor b) create
	 * public static method c) create private static variable of same class type d)
	 * Give condition as if(webUtil==null) { webUtil=new WebUtil(); }
	 * 
	 * @return it returns the instance of WebUtil.
	 */
	public static WebUtil getInstance() {
		if (webUtil == null) {
			webUtil = new WebUtil();
		}
		return webUtil;
	}

	/**
	 * takeSnapshotSingleElement() :- this method is commonally used to take
	 * Screenshot of the individual element.
	 * 
	 * @param webObj               - we pass the reference of WebElement as argument
	 *                             of the element which we want to take snapShot.
	 * 
	 * @param screenShotfolderName :- we also pass the screen shot folder name in
	 *                             which we want to store the snapShot file in the
	 *                             form of png format.
	 */

	public void statusAndResult(Status status, String message) {
		extTest.log(status, message);
	}

	public void printMessage(String message) {
		System.out.println(message);
		statusAndDetails(Status.INFO, message);
	}

	public void takeSnapshotSingleElement(WebElement webObj, String screenShortFileName) {
		String currentTimeStamp = currentDate("dd_MMM_yyyy hh_mm_ss a");
		try {
			File srcFile1 = webObj.getScreenshotAs(OutputType.FILE);
			File destFile1 = new File("C:\\2_TakeSnapshots\\" + screenShortFileName + currentTimeStamp + ".png");
			Files.copy(srcFile1, destFile1);
			statusAndDetails(Status.INFO, "Snapshot is taken of the individual element successfully");
		} catch (Exception e) {
			statusAndDetails(Status.INFO, "Snapshot is not taken of the individual element successfully");
			e.printStackTrace();

		}
	}

	/**
	 * takeSnapshotOfPage():- this method is commonally used to take Screenshot of
	 * the current Page.
	 * 
	 * @param screenShotfolderName:- we also pass the screen shot folder name in
	 *                               which we want to store the snapShot file in the
	 *                               form of png format.
	 */
	public String takeSnapshotOfPage(String fileName) {
		TakesScreenshot takeSnapshot = (TakesScreenshot) driver;
		String currentTimeStamp = currentDate("dd_MMM_yyyy hh_mm_ss a");
		File srcFile = takeSnapshot.getScreenshotAs(OutputType.FILE);
		File destFile = new File("C:\\1_Snapshots\\" + fileName + currentTimeStamp + ".png");
		try {
			Files.copy(srcFile, destFile);
			statusAndDetails(Status.INFO, "Snapshot is taken of the page successfully");
		} catch (Exception e) {
			statusAndDetails(Status.INFO, "Snapshot is not taken of the page successfully");
			e.printStackTrace();
		}
		String snapPath = destFile.getAbsolutePath();

		return snapPath;

//Returns the absolute path of the file.

	}

	/**
	 * currentDateWithTime() :- this method is used to find the current date with
	 * time in the format of dd-MMM-yyyy HH:mm:ss a. For example:- 10-04-2023
	 * 12:23:22 am Or pm
	 * 
	 * @return - it will return the current date with time
	 */
	public String currentDateWithTime() {
		String currentDateAndTime = null;
		try {
			LocalDateTime lclDtTime = LocalDateTime.now();
			DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("dd-MMM-yyyy HH_mm_ss a");
			currentDateAndTime = lclDtTime.format(dateTimeFormat);
//			System.out.println("Current date with time- " + currentDateAndTime);
//			printMessage("Current date with time- " + currentDateAndTime);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return currentDateAndTime;
	}

	/**
	 * currentDate() :- this method is also used to find the current date with time
	 * in the format of dd-MMM-yyyy HH:mm:ss a.
	 * 
	 * For example:- 10-04-2023 12:23:22 am Or pm
	 * 
	 * @param dateFormat - we choose the date formate just like 12 Jan 2023 or any
	 *                   format that you want to perform.
	 *
	 * @return - it will return the current date with time
	 *
	 */
	public String currentDate(String dateFormat) {
		String currentDate = null;
		try {
			DateFormat dtFrmt = new SimpleDateFormat(dateFormat);
			currentDate = dtFrmt.format(new Date());
			statusAndDetails(Status.INFO, "Current date-" + currentDate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return currentDate;
	}

	public String getRandomName(int randLength) {
		RandomString rs = new RandomString(randLength);
		String randName = rs.nextString();
		return randName;
	}

	/**
	 * @param yourString : it prints message in the console whatever you want to
	 *                   print
	 */

	public void statusAndDetails(Status status, String description) {
		extTest.log(status, description);

	}
	//////////// myThread \\\\\\\\\\

	public void thread(long duration) {
		try {
			Thread.sleep(duration);
			statusAndDetails(Status.INFO, "we have given " + duration + " millies seconds for wait to the element");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * addCustomWindow:- this method is commonaly used to open custom window that
	 * you want by ChromeOptions() to use or add arguments with ChromeOptions....
	 * 
	 * @return :- this method returns the object of ChromeOptions class
	 */
	public ChromeOptions addToDisableInfobar() {
		ChromeOptions opt = null;
		try {
//			System.setProperty("webdriver.chrome.driver", "Driver\\chromedriver.exe");
			opt = new ChromeOptions();
			opt.setExperimentalOption("excludeSwitches", new String[] { "enable-automation" });
			statusAndDetails(Status.INFO,
					"chrome is being controlled by automated test software is removed Successfully");
			// the above method remove this ---chrome is being controlled by automated test
			// software
		} catch (InvalidArgumentException e) {
			statusAndResult(Status.INFO, "InvalidArgumentException occurred: " + e.getMessage());
		} catch (NullPointerException e) {
			System.setProperty("webdriver.chrome.driver", "Driver\\chromedriver.exe");
			opt = new ChromeOptions();
			opt.setExperimentalOption("excludeSwitches", new String[] { "enable-automation" });
			statusAndDetails(Status.INFO,
					"chrome is being controlled by automated test software is removed Successfully");
		} catch (WebDriverException e) {
			statusAndResult(Status.INFO, "WebDriverException occurred: " + e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return opt;
	}

	/**
	 * addCustomWindow:- this method is commonaly used to open custom window that
	 * you want by ChromeOptions() to use or add arguments with ChromeOptions....
	 * 
	 * @return :- this method returns the object of ChromeOptions class
	 */
	public ChromeOptions addCustomWindow(int xOffSet, int yOffSet) {
		ChromeOptions opt = null;
		try {
//			System.setProperty("webdriver.chrome.driver", "Driver\\chromedriver.exe");
			opt = new ChromeOptions();
			opt.addArguments("window-size=" + xOffSet + "," + yOffSet + "");
			// to manage the window size whatever you want to see
		} catch (InvalidArgumentException e) {
			statusAndResult(Status.INFO, "InvalidArgumentException occurred: " + e.getMessage());
		} catch (NullPointerException e) {
			System.setProperty("webdriver.chrome.driver", "Driver\\chromedriver.exe");
			opt = new ChromeOptions();
			opt.addArguments("window-size=" + xOffSet + "," + yOffSet + "");
			// to manage the window size whatever you want to see
		} catch (WebDriverException e) {
			statusAndResult(Status.INFO, "WebDriverException occurred: " + e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return opt;
	}

	/**
	 * addMaximizedWindow:- this method is commonaly used to maximize to the window
	 * by ChromeOptions() to use or add arguments with ChromeOptions....
	 * 
	 * @return :- this method returns the object of ChromeOptions class
	 */
	public ChromeOptions addMaximizedWindow() {
		ChromeOptions opt = null;
		try {
//			System.setProperty("webdriver.chrome.driver", "Driver\\chromedriver.exe");
			opt = new ChromeOptions();
			opt.addArguments("--start-maximized");// to maximize the window
			statusAndDetails(Status.INFO, "Window is maximized");
		} catch (InvalidArgumentException e) {
			statusAndResult(Status.INFO, "InvalidArgumentException occurred: " + e.getMessage());
		} catch (NullPointerException e) {
//			System.setProperty("webdriver.chrome.driver", "Driver\\chromedriver.exe");
			opt = new ChromeOptions();
			opt.addArguments("--start-maximized");// to maximize the window
			statusAndDetails(Status.INFO, "Window is maximized");
		} catch (WebDriverException e) {
			statusAndResult(Status.INFO, "WebDriverException occurred: " + e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return opt;
	}

	//////////// addDisableNotification \\\\\\\\\\

	/**
	 * addDisableNotification:- the Chrome browser will not display any notification
	 * requests sent by websites....
	 * 
	 * @return :- this method returns the object of ChromeOptions class
	 */
	public ChromeOptions addDisableNotification() {
		ChromeOptions opt = null;
		try {
//			System.setProperty("webdriver.chrome.driver", "Driver\\chromedriver.exe");
			opt = new ChromeOptions();
			opt.addArguments("--disable-notifications");
			statusAndDetails(Status.INFO, "All notification is disabled");
		} catch (InvalidArgumentException e) {
			statusAndResult(Status.INFO, "InvalidArgumentException occurred: " + e.getMessage());
		} catch (NullPointerException e) {
			System.setProperty("webdriver.chrome.driver", "Driver\\chromedriver.exe");
			opt = new ChromeOptions();
			opt.addArguments("--disable-notifications");//// to disable popup in Chrome
			statusAndDetails(Status.INFO, "All notification is disabled");
		} catch (WebDriverException e) {
			statusAndResult(Status.INFO, "WebDriverException occurred: " + e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return opt;
	}

	//////////// add Chrome Extension \\\\\\\\\\

	/**
	 * add Chrome Extension:- we want to add any type of Chrome Extensions requests
	 * sent by websites....
	 * 
	 * @param : Give the File extension file Path in parameter
	 * @return :- this method returns the object of ChromeOptions class
	 */
	public ChromeOptions addChromeExtension(String crxFilePath) {
		ChromeOptions opt = null;
		try {
//			System.setProperty("webdriver.chrome.driver", "Driver\\chromedriver.exe");
			opt = new ChromeOptions();
			opt.addArguments("--disable-notifications");
			opt.setExperimentalOption("excludeSwitches", new String[] { "enable-automation" });
			opt.setExperimentalOption("useAutomationExtension", false);
			opt.addExtensions(new File(crxFilePath));
			statusAndDetails(Status.INFO, "chrome extensions Add successfully ");
		} catch (InvalidArgumentException e) {
			statusAndResult(Status.INFO, "InvalidArgumentException occurred: " + e.getMessage());
		} catch (NullPointerException e) {
			System.setProperty("webdriver.chrome.driver", "Driver\\chromedriver.exe");
			opt = new ChromeOptions();
			opt.addArguments("--disable-notifications");//// to disable popup in Chrome
			statusAndDetails(Status.INFO, "All notification is disabled");
		} catch (WebDriverException e) {
			statusAndResult(Status.INFO, "WebDriverException occurred: " + e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return opt;
	}

	//////////// addIncognitoMode \\\\\\\\\\

	/**
	 * addIncognitoMode :-this method is commonaly used to Incognito mode, also
	 * known as private browsing or privacy mode In this mode, the browser does not
	 * save browsing history, cookies, site data, or form input
	 * 
	 * @return :- this method returns the object of ChromeOptions class
	 */
	public ChromeOptions addIncognitoMode() {
		ChromeOptions opt = null;
		try {
//			System.setProperty("webdriver.chrome.driver", "Driver\\chromedriver.exe");
			opt = new ChromeOptions();
			opt.addArguments("--incognito");
			statusAndDetails(Status.INFO, "Incognito mode is opened");
		} catch (InvalidArgumentException e) {
			statusAndResult(Status.INFO, "InvalidArgumentException occurred: " + e.getMessage());
		} catch (NullPointerException e) {
//			System.setProperty("webdriver.chrome.driver", "Driver\\chromedriver.exe");
			opt = new ChromeOptions();
			opt.addArguments("--incognito");// it works like incognito mode do not save or change in chrome
			statusAndDetails(Status.INFO, "Incognito mode is opened");
		} catch (WebDriverException e) {
			statusAndResult(Status.INFO, "WebDriverException occurred: " + e.getMessage());
		} catch (Exception e) {
			statusAndDetails(Status.INFO, "Incognito mode is not opened");
			e.printStackTrace();
		}
		return opt;
	}

	//////////// addGestMode \\\\\\\\\\

	/**
	 * addGestMode :- it works like incognito mode do not save or change in chrome.
	 * When a browser is launched in guest mode, it operates in an isolated session,
	 * also known as "private browsing" or "incognito mode" in some browsers. In
	 * this mode, the browser does not save browsing history, cookies, site data, or
	 * form input
	 * 
	 * @return this method returns the object of ChromeOptions class
	 */
	public ChromeOptions addGestMode() {
		ChromeOptions opt = null;
		try {
			System.setProperty("webdriver.chrome.driver", "Driver\\chromedriver.exe");
			opt = new ChromeOptions();
			opt.addArguments("--guest");
			statusAndDetails(Status.INFO, "guest mode is opened");
		} catch (InvalidArgumentException e) {
			statusAndResult(Status.INFO, "InvalidArgumentException occurred: " + e.getMessage());
		} catch (NullPointerException e) {
			System.setProperty("webdriver.chrome.driver", "Driver\\chromedriver.exe");
			opt = new ChromeOptions();
			opt.addArguments("--guest");// it works like incognito mode do not save or change in chrome
			statusAndDetails(Status.INFO, "guest mode is opened");
		} catch (WebDriverException e) {
			statusAndResult(Status.INFO, "WebDriverException occurred: " + e.getMessage());
		} catch (Exception e) {
			statusAndDetails(Status.INFO, "guest mode is not opened");
			e.printStackTrace();
		}
		return opt;
	}

	//////////// launchBrowser \\\\\\\\\\

	/**
	 * launchBrowser():- this method is commonaly used to launch multi-choice
	 * browser just like chrome,firefox,edge and safari browser .
	 * 
	 * @param browserName - choice the browserName in which you want to open your
	 *                    browser.
	 */
	public void launchBrowser(String browserName) {
		try {

			if (browserName.equalsIgnoreCase("chrome")) {
//				System.setProperty("webdriver.chrome.driver", "Driver\\chromedriver.exe");
				driver = new ChromeDriver();
				printMessage("Chrome Browser is launched sucessfully");
			} else if (browserName.equalsIgnoreCase("firefox")) {
//				System.setProperty("webdriver.chrome.driver", "Driver\\geckodriver.exe");
				driver = new FirefoxDriver();
				printMessage("FireFox Browser is launched sucessfully");
			} else if (browserName.equalsIgnoreCase("edge")) {
//				System.setProperty("webdriver.chrome.driver", "Driver\\msedgedriver.exe");
				driver = new EdgeDriver();
				printMessage("Edge Browser is launched sucessfully");
			} else if (browserName.equalsIgnoreCase("safari")) {
				driver = new SafariDriver();
				printMessage("Safari Browser is launched sucessfully");
			} else {
				printMessage("Unsupported browser choice");
				return;
			}

		} catch (InvalidArgumentException e) {
			printMessage("InvalidArgumentException occurred: " + e.getMessage());
			// If the exception is due to an invalid argument being passed
			printMessage("Please check the validity of the arguments passed to ChromeOptions.");
		} catch (SessionNotCreatedException e) {
			// This block will be executed if a SessionNotCreatedException occurs during the
			// process
			// Handle the exception here, you can print an error message or take appropriate
			// action
			printMessage("SessionNotCreatedException occurred: " + e.getMessage());
			// Example: If the exception is due to incompatible Chrome and ChromeDriver
			// versions
			if (e.getMessage().contains("This version of ChromeDriver only supports")) {
				printMessage("Please check the compatibility between Chrome and ChromeDriver versions.");
			}
			// Example: If the exception is due to any other issue with creating a WebDriver
			// session
			// Handle it accordingly...

		} catch (WebDriverException e) {
			// This block will be executed if a WebDriverException occurs during the process
			// Handle the exception here, you can print an error message or take appropriate
			// action
			printMessage("ChromeDriverException occurred: " + e.getMessage());
			// Example: If the exception is due to issues with ChromeDriver
			if (e.getMessage().contains("Unable to discover open pages")) {
				printMessage("Please ensure that ChromeDriver is working correctly.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			printMessage("Browser is not launched sucessfully");
		}
		windowMaximize();
		implicitilyOfSeconds(40);

	}

	//////////// launchBrowser \\\\\\\\\\

	/**
	 * launchBrowser():- this method is commonaly used to launch multi-choice
	 * browser just like chrome,firefox,edge and safari browser .
	 * 
	 * @param browserName - choice the browserName in which you want to open your
	 *                    browser.
	 * @param Pass        the chrome option extension file Name
	 * 
	 */
	public void launchBrowser(String browserName, ChromeOptions ChromeExtension) {
		try {

			if (browserName.equalsIgnoreCase("chrome")) {
				driver = new ChromeDriver(ChromeExtension);
				printMessage("Chrome Browser is launched sucessfully");
			} else if (browserName.equalsIgnoreCase("firefox")) {
				driver = new FirefoxDriver();
				printMessage("FireFox Browser is launched sucessfully");
			} else if (browserName.equalsIgnoreCase("edge")) {
				driver = new EdgeDriver();
				printMessage("Edge Browser is launched sucessfully");
			} else if (browserName.equalsIgnoreCase("safari")) {
				driver = new SafariDriver();
				printMessage("Safari Browser is launched sucessfully");
			} else {
				printMessage("Unsupported browser choice");
				return;
			}

		} catch (InvalidArgumentException e) {
			printMessage("InvalidArgumentException occurred: " + e.getMessage());
			printMessage("Please check the validity of the arguments passed to ChromeOptions.");
		} catch (SessionNotCreatedException e) {
			printMessage("SessionNotCreatedException occurred: " + e.getMessage());
			if (e.getMessage().contains("This version of ChromeDriver only supports")) {
				printMessage("Please check the compatibility between Chrome and ChromeDriver versions.");
			}

		} catch (WebDriverException e) {
			printMessage("ChromeDriverException occurred: " + e.getMessage());
			if (e.getMessage().contains("Unable to discover open pages")) {
				printMessage("Please ensure that ChromeDriver is working correctly.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			printMessage("Browser is not launched sucessfully");
		}
		windowMaximize();
		implicitilyOfSeconds(40);

	}

	//////////// windowMaximize \\\\\\\\\\

	/**
	 * with the help of this method we can maximize our window ....
	 */
	public void windowMaximize() {

		try {
			driver.manage().window().maximize();
			statusAndResult(Status.INFO, "the window is maximized");
//			statusAndDetails(Status.INFO, "the window is maximized");
		} catch (Exception e) {
			e.printStackTrace();
			statusAndResult(Status.INFO, "the window is not maximized");
//			statusAndDetails(Status.INFO, "the window is not maximized");
		}
	}

	//////////// windowMinimize \\\\\\\\\\

	/**
	 * with the help of this method we can minimize our window ....
	 */
	public void windowMinimize() {

		try {
			driver.manage().window().minimize();
			statusAndResult(Status.INFO, "the window is minimized");
		} catch (Exception e) {
			e.printStackTrace();
			statusAndResult(Status.INFO, "the window is not minimized");
		}
	}

	//////////// myIplicitilyWait \\\\\\\\\\

	/**
	 * myIplicitilyWait() -- with the help of this method we can wait as per given
	 * duration to the element until we get the element ,if we don't get the element
	 * as per given duration then we don't wait.. but if we get the element under
	 * given duration then after that we donn't wait... this type of wait is known
	 * as dynamic wait....
	 * 
	 * @param seconds it requaires long type number in the form of argument.
	 */
	public void implicitilyOfSeconds(long seconds) {

		try {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(seconds));
			statusAndResult(Status.INFO, "the maximum timeouts for waiting is " + seconds);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	//////////// openUrl \\\\\\\\\\

	/**
	 * this method is commonaly used to hit the url to the browser
	 * 
	 * @param url :- we can open any url in the form of string
	 * 
	 */
	public void openUrl(String url) {
		try {
			driver.get(url);
			printMessage("the given url :-" + url + " is launched sucessfully");
		} catch (ElementNotInteractableException e) {
			// Use JavascriptExecutor to open the URL
			JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
			String script = "window.location.href='" + url + "';";
			jsExecutor.executeScript(script);
			printMessage("the given url :-" + url + " is launched sucessfully");
		} catch (Exception e) {
			e.printStackTrace();
			printMessage("the given url :-" + url + " is not launched sucessfully");
			throw e;

		}
	}

	//////////// goToBackPage \\\\\\\\\\

	/**
	 * by using of this method we can go to back page from the current page...
	 * 
	 */
	public void goToBackPage() {
		try {
			driver.navigate().back();
			statusAndResult(Status.INFO, "the Page has been backed sucessfully");
		} catch (ElementNotInteractableException e) {
			// Use JavascriptExecutor to execute JavaScript code to go back
			JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
			jsExecutor.executeScript("window.history.back();");
			statusAndResult(Status.INFO, "the Page has been backed sucessfully");
		} catch (WebDriverException e) {

			// WebDriverException:-----WebDriverException can be thrown for various reasons,
			// such as invalid URL,
			// network issues, browser crashes, or any other unexpected
			// errors during the navigation.......
			e.printStackTrace();
			statusAndResult(Status.INFO, "the Page hasn't been backed sucessfully");
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			statusAndResult(Status.INFO, "the Page hasn't been backed sucessfully");
			throw e;
		}
	}

	//////////// goToForwardPage \\\\\\\\\\
	/**
	 * by using of this method we can go to forword page from the current page...
	 * 
	 */

	public void goToForwardPage() {
		try {
			driver.navigate().forward();
			statusAndResult(Status.INFO, "the Page has been forwarded sucessfully");
		} catch (ElementNotInteractableException e) {
			// Use JavascriptExecutor to execute JavaScript code to go forward
			JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
			jsExecutor.executeScript("window.history.forward();");
			statusAndResult(Status.INFO, "the Page has been forwarded sucessfully");
		} catch (WebDriverException e) {

			// WebDriverException:-----WebDriverException can be thrown for various reasons,
			// such as invalid URL,
			// network issues, browser crashes, or any other unexpected
			// errors during the navigation.......
			e.printStackTrace();
			statusAndResult(Status.INFO, "the Page hasn't been forwarded sucessfully");
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			statusAndResult(Status.INFO, "the Page hasn't been forwarded sucessfully");
			throw e;
		}
	}

	//////////// goToRefeshPage \\\\\\\\\\
	/**
	 * by using of this method we can refresh to the current page...
	 * 
	 */

	public void goToRefreshPage() {
		try {
			driver.navigate().refresh();
			statusAndResult(Status.INFO, "the Page has been refreshed sucessfully");
		} catch (ElementNotInteractableException e) {
			// Use JavascriptExecutor to execute JavaScript code to refresh the page
			JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
			jsExecutor.executeScript("location.reload();");
			statusAndResult(Status.INFO, "the Page has been refreshed sucessfully");
		} catch (WebDriverException e) {

			// WebDriverException:-----WebDriverException can be thrown for various reasons,
			// such as invalid URL,
			// network issues, browser crashes, or any other unexpected
			// errors during the navigation.......
			e.printStackTrace();
			statusAndResult(Status.INFO, "the Page hasn't been refreshed sucessfully");
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			statusAndResult(Status.INFO, "the Page hasn't been refreshed sucessfully");
			throw e;
		}
	}

	//////////// goToHitUrl \\\\\\\\\\

	/**
	 * with the help of this method i.e goToHitUrl() we can hit any url in the
	 * browser.
	 * 
	 * @param url by using of this url we can hit any url to the browser
	 */
	public void goToHitUrl(String url) {
		try {
			driver.navigate().to(url);
			printMessage("the given url :- " + url + " is hit successfully");
		} catch (ElementNotInteractableException e) {
			// Use JavascriptExecutor to execute JavaScript code to open the URL
			JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
			jsExecutor.executeScript("window.location.href = '" + url + "';");
			printMessage("the given url :- " + url + " is hit successfully");
		} catch (WebDriverException e) {

			// WebDriverException:-----WebDriverException can be thrown for various reasons,
			// such as invalid URL,
			// network issues, browser crashes, or any other unexpected
			// errors during the navigation.......
			e.printStackTrace();
			printMessage("the given url :- " + url + " is not hit successfully");
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			printMessage("the given url :- " + url + " is not hit successfully");
			throw e;
		}
	}

	//////////// getPresentUrl \\\\\\\\\\

	/**
	 * this method is commonaly used to get current url of the current page.
	 * 
	 * @return this method is return String as url.
	 */
	public String getPresentUrl() {
		String pageUrl = null;
		try {
			pageUrl = driver.getCurrentUrl();
			statusAndResult(Status.INFO, "find the current page url :-" + pageUrl);

		} catch (ElementNotInteractableException e) {
			// Use JavascriptExecutor to execute JavaScript code and get the page URL
			JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
			String script = "return window.location.href;";
			pageUrl = (String) jsExecutor.executeScript(script);
			statusAndResult(Status.INFO, "find the current page url :-" + pageUrl);
			return pageUrl;
		} catch (Exception e) {
			e.printStackTrace();
			statusAndResult(Status.INFO, " Did't find the current page url ");
			throw e;
		}
		return pageUrl;

	}

	//////////// getPageTitle \\\\\\\\\\

	/**
	 * with the help of this method we can easily find the title of the current
	 * page..
	 * 
	 * @return it returns string as a title of the current page
	 */
	public String getPageTitle() {
		String pageTitle = null;
		try {
			pageTitle = driver.getTitle();
			statusAndResult(Status.INFO, "find the title of the page is :-" + pageTitle);

		} catch (ElementNotInteractableException e) {
			// Use JavascriptExecutor to execute JavaScript code and get the page title
			JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
			String script = "return document.title;";
			pageTitle = (String) jsExecutor.executeScript(script);
			statusAndResult(Status.INFO, "find the title of the page is :-" + pageTitle);
			return pageTitle;
		} catch (Exception e) {
			e.printStackTrace();
			statusAndResult(Status.INFO, "Didn't find the title of the page");
		}
		return pageTitle;
	}

	//////////// getPageSourceCode \\\\\\\\\\

	/**
	 * @return it returns the source code of the current page in the form of HTML.
	 */
	public String getPageSourceCode() {
		String sourceCode = null;
		try {
			sourceCode = driver.getPageSource();
			statusAndResult(Status.INFO, "find the source code of the page :-" + sourceCode);
		} catch (ElementNotInteractableException e) {
			// Use JavascriptExecutor to execute JavaScript code and get the page source
			// code
			JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
			String script = "return document.documentElement.outerHTML;";
			sourceCode = (String) jsExecutor.executeScript(script);
			statusAndResult(Status.INFO, "find the source code of the page :-" + sourceCode);
			return sourceCode;
		} catch (Exception e) {
			e.printStackTrace();
			statusAndResult(Status.INFO, "Didn't find the source code of the page");

		}
		return sourceCode;
	}

	//////////// myClose \\\\\\\\\\

	/**
	 * In Selenium WebDriver, the myClose() method is used to close the current
	 * browser window or tab that the WebDriver instance is currently focused on. It
	 * does not quit the entire browser session, meaning if you have opened multiple
	 * windows or tabs using the WebDriver, only the currently active window/tab
	 * will be closed.
	 * 
	 */
	public void myClose() {

		try {
			driver.close();
			statusAndResult(Status.INFO, "the current window or tab is closed successfully");
		} catch (ElementNotInteractableException e) {
			// Use JavascriptExecutor to close the current browser window
			JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
			jsExecutor.executeScript("window.close();");
			statusAndResult(Status.INFO, "the current window or tab is closed successfully");
		} catch (NoSuchWindowException e) {
			// NoSuchWindowException: If the current window or tab is already closed or does
			// not exist.
			e.printStackTrace();
			statusAndResult(Status.INFO, "the current window or tab is not closed successfully");
			throw e;
		} catch (WebDriverException e) {
			// WebDriverException can be caused by a variety of factors, such as invalid
			// driver parameters,
			// network errors, or unexpected JavaScript errors....
			e.printStackTrace();
			statusAndResult(Status.INFO, "the current window or tab is not closed successfully");
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			statusAndResult(Status.INFO, "the current window or tab is not closed successfully");
			throw e;
		}
	}

	//////////// myQuit \\\\\\\\\\

	/**
	 * In Selenium WebDriver, the myQuit() method is used to completely close all
	 * browser windows and end the WebDriver session. It is the recommended method
	 * to use when you want to clean up and close the WebDriver instance along with
	 * all associated browser windows or tabs.
	 * 
	 */
	public void quit() {

		try {
			driver.quit();

			statusAndResult(Status.INFO, "All the  window or tab is closed successfully");

		} catch (ElementNotInteractableException e) {
			// Use JavascriptExecutor to quit the entire WebDriver session
			JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
			jsExecutor.executeScript("window.open('', '_self', ''); window.close();");
			statusAndResult(Status.INFO, "All the  window or tab is closed successfully");
		} catch (NoSuchSessionException e) {

			// The NoSuchSessionException is an exception raised by Selenium WebDriver when
			// it tries to perform an operation on a session that does not exist...
			e.printStackTrace();
			statusAndResult(Status.INFO, "All the window or tab is not closed successfully");
			throw e;
		} catch (WebDriverException e) {
			// WebDriverException can be caused by a variety of factors, such as invalid
			// driver parameters,
			// network errors, or unexpected JavaScript errors....
			e.printStackTrace();
			statusAndResult(Status.INFO, "All the  window or tab is not closed successfully");
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			statusAndResult(Status.INFO, "All the  window or tab is not closed successfully");
			throw e;
		}
	}
	//////////// switchToWindowByTitle \\\\\\\\\\

	/**
	 * in Selenium WebDriver , switchToWindowByTitle() method is commonaly used to
	 * switch the current window to another window with the help of it's title that
	 * we want to switch the window...
	 * 
	 * @param expectPageTitle this expectedPageTitle is in the form of String. it
	 *                        means in which window we want to switch the window
	 *                        from current window that webdriver instance is
	 *                        currently focoused on.
	 */
	public void switchToWindowByTitle(String expectPageTitle) {

		// NoSuchWindowException: This exception occurs when you try to switch to a
		// window
		// using a window handle that does not exist or has been closed.......

		try {
			String originalWindow = driver.getWindowHandle();

			Set<String> setWindowHandles = driver.getWindowHandles();
			boolean isTitleMatched = false;

			for (String handleValue : setWindowHandles) {
				driver.switchTo().window(handleValue);
				String actPageTitle = driver.getTitle();

				if (actPageTitle.equalsIgnoreCase(expectPageTitle)) {
					printMessage(expectPageTitle + "  <<<Expected and Actual>>  " + actPageTitle);
					isTitleMatched = true;
					break;
				}
			}
			// If no match is found, switch back to the original window
			if (!isTitleMatched) {
				driver.switchTo().window(originalWindow);
				printMessage(expectPageTitle + "  <<<Expected and Actual>>  " + driver.getTitle());
				printMessage("No match found for the expected title. Returned to the original window.");

			}
			statusAndResult(Status.INFO, "the Window is switched by title :- " + expectPageTitle + " successfully");
		} catch (StaleElementReferenceException e) {
			Set<String> setWindowHandles = driver.getWindowHandles();
			for (String handleValue : setWindowHandles) {
				driver.switchTo().window(handleValue);
				String actPageTitle = driver.getTitle();
				if (actPageTitle.equalsIgnoreCase(expectPageTitle)) {
					break;
				}
			}
			statusAndResult(Status.INFO, "the Window is  switched by title :- " + expectPageTitle + " successfully");

		} catch (NoSuchWindowException e) {
			e.printStackTrace();
			statusAndResult(Status.INFO, "the Window is not switched by title :- " + expectPageTitle + " successfully");
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			statusAndResult(Status.INFO, "the Window is not switched by title :- " + expectPageTitle + " successfully");
			throw e;

		}

	}

	public ArrayList<String> findOutAllWindowName() {
		ArrayList<String> winName = new ArrayList<>();
		try {
			Set<String> setWindowHandles = driver.getWindowHandles();

			for (String handleValue : setWindowHandles) {
				driver.switchTo().window(handleValue);
				String actPageTitle = driver.getTitle();
				winName.add(actPageTitle);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return winName;
	}

	//////////// switchToWindowByUrl \\\\\\\\\\

	/**
	 * in Selenium WebDriver , switchToWindowByUrl() method is commonaly used to
	 * switch the current window to another window with the help of it's url that we
	 * want to switch the window...
	 * 
	 * @param expectPageUrl this expectPageUrl is in the form of String. it means in
	 *                      which window we want to switch the window from current
	 *                      window that webdriver instance is currently focoused on.
	 */
	public void switchToWindowByUrl(String expectPageUrl) {

		try {
			Set<String> setWindowHandles = driver.getWindowHandles();
			for (String handleValue : setWindowHandles) {
				driver.switchTo().window(handleValue);
				String actPageUrl = driver.getCurrentUrl();
				if (actPageUrl.equalsIgnoreCase(expectPageUrl)) {
					break;
				}
			}
			statusAndResult(Status.INFO, "the Window is switched by title :- " + expectPageUrl + " successfully");
		} catch (StaleElementReferenceException e) {
			Set<String> setWindowHandles = driver.getWindowHandles();
			for (String handleValue : setWindowHandles) {
				driver.switchTo().window(handleValue);
				String actPageUrl = driver.getCurrentUrl();
				if (actPageUrl.equalsIgnoreCase(expectPageUrl)) {
					break;
				}
			}
			statusAndResult(Status.INFO, "the Window is switched by url :- " + expectPageUrl + " successfully");
		} catch (NoSuchWindowException e) {
			e.printStackTrace();
			statusAndResult(Status.INFO, "the Window is not switched by url :- " + expectPageUrl + " successfully");
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			statusAndResult(Status.INFO, "the Window is not switched by url :- " + expectPageUrl + " successfully");
			throw e;
		}

	}

	//////////// switchToWindowByDynamicTitle \\\\\\\\\\

	/**
	 * switchToWindowByDynamicTitle :- this method is also used to switch the wnidow
	 * from current window to another window where the title of the page is changed
	 * again and again to refress the page .this type of tilte is called dynamic
	 * title.
	 * 
	 * @param dynamicTitle we give our expected pageTitle in which we want to switch
	 *                     from the current window or tab that contain some common
	 *                     title to refress the page..
	 * 
	 */
	public void switchToWindowByDynamicTitle(String dynamicTitle) {

		try {
			Set<String> handleValues = driver.getWindowHandles();
			for (String handleValue : handleValues) {
				driver.switchTo().window(handleValue);
				String actPageTitle = driver.getTitle();
				if (actPageTitle.contains(dynamicTitle)) {
					printMessage(dynamicTitle + "  <<<Expected and Actual>>  " + actPageTitle);
					break;
				}
			}
			statusAndResult(Status.INFO, "the Window is switched by title :- " + dynamicTitle + " successfully");
		} catch (StaleElementReferenceException e) {
			Set<String> handleValues = driver.getWindowHandles();
			for (String handleValue : handleValues) {
				driver.switchTo().window(handleValue);
				String actPageTitle = driver.getTitle();
				if (actPageTitle.contains(dynamicTitle)) {
					break;
				}
			}
			statusAndResult(Status.INFO,
					"the Window is switched by dynamic  title :- " + dynamicTitle + " successfully");
		} catch (NoSuchWindowException e) {
			e.printStackTrace();
			statusAndResult(Status.INFO,
					"the Window is not switched bydynamic  title :- " + dynamicTitle + " successfully");
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			statusAndResult(Status.INFO,
					"the Window is not switched by dynamic title :- " + dynamicTitle + " successfully");
			printMessage(dynamicTitle + "  <<<Expected and Actual>>  " + driver.getTitle());
			throw e;
		}
	}

	/// Second Windows My Manual ////
	public void switchToWindowByDynamicTitleMainJob(String dynamicTitle) {

		try {
			Set<String> handleValues = driver.getWindowHandles();
			for (String handleValue : handleValues) {
				driver.switchTo().window(handleValue);
				String actPageTitle = driver.getTitle();
				if (actPageTitle.contains(dynamicTitle)) {
					break;
				} else {
					myClose();
				}
			}
			statusAndResult(Status.INFO, "the Window is switched by title :- " + dynamicTitle + " successfully");
		} catch (StaleElementReferenceException e) {
			Set<String> handleValues = driver.getWindowHandles();
			for (String handleValue : handleValues) {
				driver.switchTo().window(handleValue);
				String actPageTitle = driver.getTitle();
				if (actPageTitle.contains(dynamicTitle)) {
					break;
				}
			}
			statusAndResult(Status.INFO,
					"the Window is switched by dynamic  title :- " + dynamicTitle + " successfully");
		} catch (NoSuchWindowException e) {
			e.printStackTrace();
			statusAndResult(Status.INFO,
					"the Window is not switched bydynamic  title :- " + dynamicTitle + " successfully");
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			statusAndResult(Status.INFO,
					"the Window is not switched by dynamic title :- " + dynamicTitle + " successfully");
			throw e;
		}
	}

	//////////// switchToWindowByDynamicUrl \\\\\\\\\\

	/**
	 * switchToWindowByDynamicUrl :- this method is also used to switch the wnidow
	 * from current window to another window where the Url of the page is changed
	 * again and again to refress the page .this type of url is called dynamic url.
	 * 
	 * @param dynamicUrl we give our expected pageUrl in which we want to switch
	 *                   from the current window or tab that contain some common url
	 *                   to refress the page..
	 * 
	 */
	public void switchToWindowByDynamicUrl(String dynamicUrl) {

		try {
			Set<String> handleValues = driver.getWindowHandles();
			for (String handleValue : handleValues) {
				driver.switchTo().window(handleValue);
				String actPageUrl = driver.getCurrentUrl();
				if (actPageUrl.contains(dynamicUrl)) {
					break;
				}
			}
			statusAndResult(Status.INFO, "the Window is switched by title :- " + dynamicUrl + " successfully");
		} catch (StaleElementReferenceException e) {
			Set<String> handleValues = driver.getWindowHandles();
			for (String handleValue : handleValues) {
				driver.switchTo().window(handleValue);
				String actPageUrl = driver.getCurrentUrl();
				if (actPageUrl.contains(dynamicUrl)) {
					break;
				}
			}
			statusAndResult(Status.INFO, "the Window is switched by title :- " + dynamicUrl + " successfully");
		} catch (NoSuchWindowException e) {
			e.printStackTrace();
			statusAndResult(Status.INFO,
					"the Window is not switched by dynamic url :- " + dynamicUrl + " successfully");
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			statusAndResult(Status.INFO,
					"the Window is not switched by dynamic url :- " + dynamicUrl + " successfully");
			throw e;
		}
	}

	//////////// switchToFrameByWebElement \\\\\\\\\\

	/**
	 * switchToFrameByWebElement() :-this method is used to enter in the frame with
	 * the help of WebElement of the frame from the current browser window or tab
	 * that the WebDriver instance is currently focused on.
	 * 
	 * @param webObj :- it requires the object of WebElement in the form of
	 *               arguments to switching the frame
	 * 
	 */
	public void switchToFrameByWebElement(WebElement webObj) {

		// NoSuchFrameException:--This exception occurs when you attempt to switch to a
		// frame
		// that does not exist or cannot be found.

		String elementName = webObj.getAccessibleName();

		try {
			driver.switchTo().frame(webObj);
			statusAndResult(Status.INFO, "the window is switched into the frame " + elementName + "  suceessfully");
		} catch (NoSuchFrameException e) {
			driver.switchTo().frame(webObj);
			statusAndResult(Status.INFO, "the window is switched into the frame " + elementName + "  suceessfully");
		} catch (StaleElementReferenceException e) {
			driver.switchTo().frame(webObj);
			statusAndResult(Status.INFO, "the window is switched into the frame " + elementName + "  suceessfully");
		} catch (Exception e) {
			e.printStackTrace();
			statusAndResult(Status.INFO, "the window is not switched into the frame " + elementName + "  suceessfully");
			throw e;
		}
	}

	//////////// switchToFrameByIndex \\\\\\\\\\

	/**
	 * switchToFrameByIndex() :-this method is also used to enter in the frame with
	 * the help of index of the frame from the current browser window or tab that
	 * the WebDriver instance is currently focused on.
	 * 
	 * @param frameIndex  In here we give frame's index to enter in frame
	 * 
	 * @param elementName elementName is mostly used to locate in which frame
	 *                    element we are entering and give the proper message in the
	 *                    console
	 * 
	 */
	public void switchToFrameByIndex(String elementName, int frameIndex) {
		try {
			driver.switchTo().frame(frameIndex);
			statusAndResult(Status.INFO, "the window is switched into the frame " + elementName + "  suceessfully");
		} catch (NoSuchFrameException e) {
			driver.switchTo().frame(frameIndex);
			statusAndResult(Status.INFO, "the window is switched into the frame " + elementName + "  suceessfully");
		} catch (StaleElementReferenceException e) {
			driver.switchTo().frame(frameIndex);
			statusAndResult(Status.INFO, "the window is switched into the frame " + elementName + "  suceessfully");
		} catch (Exception e) {
			e.printStackTrace();
			statusAndResult(Status.INFO, "the window is not switched into the frame " + elementName + "  suceessfully");
			throw e;
		}
	}

	//////////// switchToFrameByNameOrId \\\\\\\\\\

	/**
	 * switchToFrameByNameOrId() :-this method is also used to enter in the frame
	 * with the help of name or id (locator) of the frame from the current browser
	 * window or tab that the WebDriver instance is currently focused on.
	 * 
	 * @param NameOrId In here we give frame's name and id as a locator to enter in
	 *                 frame
	 * 
	 */
	public void switchToFrameByNameOrId(String NameOrId) {
		try {
			driver.switchTo().frame(NameOrId);
			statusAndResult(Status.INFO, "the window is switched into the frame by Name or Id  suceessfully");
		} catch (NoSuchFrameException e) {
			driver.switchTo().frame(NameOrId);
			statusAndResult(Status.INFO, "the window is switched into the frame by Name or Id  suceessfully");
		} catch (StaleElementReferenceException e) {
			driver.switchTo().frame(NameOrId);
			statusAndResult(Status.INFO, "the window is switched into the frame by Name or Id  suceessfully");
		} catch (Exception e) {
			e.printStackTrace();
			statusAndResult(Status.INFO, "the window is not switched into the frame by Name or Id  suceessfully");
			throw e;
		}
	}

	//////////// switchToParentFrame \\\\\\\\\\

	public void switchToParentFrame(String elementName) {
		try {
			driver.switchTo().parentFrame();
			statusAndResult(Status.INFO, "the window is switched into the parentFrame suceessfully");
		} catch (StaleElementReferenceException e) {
			driver.switchTo().parentFrame();
			statusAndResult(Status.INFO, "the window is switched into the parentFrame suceessfully");
		} catch (Exception e) {
			e.printStackTrace();
			statusAndResult(Status.INFO, "the window is not switched into the parentFrame suceessfully");
			throw e;
		}
	}

	//////////// switchToMainWindow \\\\\\\\\\

	public void switchToMainWindow(String elementName) {
		try {
			driver.switchTo().defaultContent();
			statusAndResult(Status.INFO, "the window is switched into the  Main Window suceessfully");
		} catch (Exception e) {
			e.printStackTrace();
			statusAndResult(Status.INFO, "the window is not switched into the  Main Window suceessfully");
			throw e;
		}
	}

	//////////// openNewWindowTab \\\\\\\\\\

	public void openNewWindowTab(String elementName) {
		try {
			driver.switchTo().newWindow(WindowType.TAB);
			statusAndResult(Status.INFO, elementName + " is opened or Launch suceessfully");
		} catch (Exception e) {
			e.printStackTrace();
			statusAndResult(Status.INFO, elementName + " is not opened or Launch suceessfully");
			throw e;
		}
	}

	//////////// popUpAccept \\\\\\\\\\
	/**
	 * with the help of this method we can easily accept the alert message.
	 */
	public void popUpAccept() {

		// NoAlertPresentException: This exception occurs when there is no alert present
		// on the web page
		// at the time you attempt to interact with it..............

		try {
			driver.switchTo().alert().accept();
			statusAndResult(Status.INFO, "Pop up is accepted successfully");
		} catch (NoAlertPresentException e) {
			WebDriverWait webWait = new WebDriverWait(driver, Duration.ofSeconds(30));
			statusAndResult(Status.INFO, " Wait for the alert to be present (timeout for 30 seconds)");
			webWait.until(ExpectedConditions.alertIsPresent());
			statusAndResult(Status.INFO, "Switch to the alert and perform operations if needed");
			driver.switchTo().alert().accept();
			statusAndResult(Status.INFO, "Pop up is accepted successfully");
		} catch (Exception e) {
			e.printStackTrace();
			statusAndResult(Status.INFO, "Pop up is not accepted successfully");
			throw e;
		}

	}

	//////////// popUpDismiss \\\\\\\\\\
	/**
	 * with the help of this method we can easily dismiss the alert message.
	 */
	public void popUpDismiss() {
		try {
			driver.switchTo().alert().dismiss();
			statusAndResult(Status.INFO, "Pop up is dismissed successfully");
		} catch (NoAlertPresentException e) {
			WebDriverWait webWait = new WebDriverWait(driver, Duration.ofSeconds(30));
			statusAndResult(Status.INFO, " Wait for the alert to be present (timeout for 30 seconds)");
			webWait.until(ExpectedConditions.alertIsPresent());
			statusAndResult(Status.INFO, "Switch to the alert and perform operations if needed");
			driver.switchTo().alert().dismiss();
			statusAndResult(Status.INFO, "Pop up is dismissed successfully");
		} catch (Exception e) {
			e.printStackTrace();
			statusAndResult(Status.INFO, "Pop up is not dismissed successfully");
			throw e;
		}
	}

	//////////// popUpSendKeys \\\\\\\\\\
	/**
	 * with the help of this method we can easily send value in input box of the
	 * alert to accept or dismiss the alert message.
	 */
	public void popUpSendKeys(String popUpKeyText) {
		try {
			driver.switchTo().alert().sendKeys(popUpKeyText);
			statusAndResult(Status.INFO, "Pop up is  " + popUpKeyText + "  successfully");
		} catch (NoAlertPresentException e) {
			WebDriverWait webWait = new WebDriverWait(driver, Duration.ofSeconds(30));
			statusAndResult(Status.INFO, " Wait for the alert to be present (timeout for 30 seconds)");
			webWait.until(ExpectedConditions.alertIsPresent());
			statusAndResult(Status.INFO, "Switch to the alert and perform operations if needed");
			driver.switchTo().alert().sendKeys(popUpKeyText);
			statusAndResult(Status.INFO, "Pop up is  " + popUpKeyText + "  successfully");
		} catch (Exception e) {
			e.printStackTrace();
			statusAndResult(Status.INFO, "Pop up is  not " + popUpKeyText + "  successfully");
			throw e;
		}

	}

	//////////// popUpGetText \\\\\\\\\\

	/**
	 * with the help of this method we can easily get inner text of alert message.
	 * 
	 * @return
	 */
	public String popUpGetText() {
		String popText = null;
		try {
			popText = driver.switchTo().alert().getText();
			statusAndResult(Status.INFO, "the text of Pop up is found successfully ");
		} catch (NoAlertPresentException e) {
			WebDriverWait webWait = new WebDriverWait(driver, Duration.ofSeconds(30));
			statusAndResult(Status.INFO, " Wait for the alert to be present (timeout for 30 seconds)");
			webWait.until(ExpectedConditions.alertIsPresent());
			statusAndResult(Status.INFO, "Switch to the alert and perform operations if needed");
			driver.switchTo().alert().getText();
			statusAndResult(Status.INFO, "the text of Pop up is found successfully");
		} catch (Exception e) {
			e.printStackTrace();
			statusAndResult(Status.INFO, "the text of Pop up is not found successfully");
			throw e;
		}
		return popText;
	}

	//////////// getTableRowCount \\\\\\\\\\
	/**
	 * this method is used to obtain row count of the table as a int.
	 * 
	 * @param tableXpath with the help table xpath we can find those element that
	 *                   comes under the table as multiple column list name
	 */
	public int getTableRowCount(List<WebElement> listObj) {

		int countRow = -1;
		if (listObj.isEmpty() == false) {
			try {
				countRow = listObj.size();
				statusAndResult(Status.INFO, "total row count of leads table is =" + countRow);
			} catch (Exception e) {
				e.printStackTrace();
				statusAndResult(Status.INFO, "total row count of leads table is not counted");
				throw e;
			}

		} else {
			statusAndResult(Status.INFO, "List is empty there is no any element");
		}
		return countRow;

	}

	//
	//////////// myJsSendKeys \\\\\\\\\\

	/**
	 * this method is commonaly used to send value in input box or input field by
	 * java script. if the element is hidden in UI then finally we can use java
	 * script....
	 * 
	 * @param webObj     :- it requires the object of WebElement as a parameter to
	 *                   find out the element where you want to work.
	 * 
	 * @param inputValue we have to send the input value in the textbox or input
	 *                   field by java script.
	 * 
	 */

	public void myJsSendKeys(WebElement webObj, String inputValue) {

		String elementName = webObj.getAccessibleName();
		try {
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].value='" + inputValue + "'", webObj);
			statusAndResult(Status.INFO,
					inputValue + " value has entered in " + elementName + " in textbox by java script successfully");

		} catch (Exception e) {
			e.printStackTrace();
			statusAndResult(Status.INFO,
					inputValue + " value hasn't entered in " + elementName + " in textbox by java script successfully");
			throw e;
		}

	}

	//////////// mySendKeys \\\\\\\\\\

	/**
	 * this method is commonaly used to send value in input box or input field.
	 * 
	 * @param webObj     :- it requires the object of WebElement as a parameter to
	 *                   find out the element where you want to work.
	 * @param inputValue we have to send the input value in the textbox or input
	 *                   field
	 * 
	 * @throws ElementClickInterceptedException this type exception occures when the
	 *                                          element is hidden so we can use to
	 *                                          overcome this exception hen we can
	 *                                          use java script because
	 *                                          JavasriptExecutorenters any value or
	 *                                          action whether any element hidden or
	 *                                          not hidden in UI.........
	 * 
	 */
	public void sendKeys(WebElement webObj, String inputValue, String elementName)
			throws ElementClickInterceptedException {

//		String elementName=webObj.getAccessibleName();
		try {
			WebDriverWait webWait = new WebDriverWait(driver, Duration.ofSeconds(30));
			webWait.until(ExpectedConditions.visibilityOf(webObj));
		} catch (Exception q) {
			printMessage(elementName + "  is not found successfully");
		}

		if (webObj.isDisplayed() && webObj.isEnabled()) {

			statusAndResult(Status.INFO, "Element is Displayed and enabled ");

			try {

				webObj.clear();
				webObj.sendKeys(inputValue);
				statusAndResult(Status.INFO,
						inputValue + " value is passed in " + elementName + " textbox successfully");

			} catch (ElementNotInteractableException e) {

				/**
				 * ElementNotInteractableException :- this type exception occures when the
				 * element is hidden so we can use to overcome this exception then we can use
				 * java script because JavasriptExecutorenters any value or action whether any
				 * element hidden or not hidden in UI.........
				 */
				JavascriptExecutor jse = (JavascriptExecutor) driver;
				jse.executeScript("arguments[0].value='" + inputValue + "'", webObj);
				statusAndResult(Status.INFO,
						inputValue + " value has entered in " + elementName + " textbox successfully");
			} catch (StaleElementReferenceException e) {
				webObj = driver.findElement(By.xpath("//input[@name='" + inputValue + "']"));
				statusAndResult(Status.INFO, "we have found " + elementName + " successfully");
				webObj.sendKeys(inputValue);
				statusAndResult(Status.INFO,
						inputValue + " value has entered in " + elementName + " textbox successfully");
			} catch (Exception e) {
				e.printStackTrace();
				statusAndResult(Status.INFO,
						inputValue + " value hasn't entered in " + elementName + " textbox successfully");
				throw e;
			}

		} else {
			statusAndResult(Status.INFO, "Element is Displayed and enabled");
		}
	}

	public void sendKeysWithEnter(WebElement webObj, String inputValue, String elementName)
			throws ElementClickInterceptedException {

//		String elementName=webObj.getAccessibleName();

		if (webObj.isDisplayed() && webObj.isEnabled()) {

			statusAndResult(Status.INFO, "Element is Displayed and enabled ");

			try {
				webObj.clear();
				webObj.sendKeys(inputValue, Keys.ENTER);
				statusAndResult(Status.INFO,
						inputValue + " value is passed in " + elementName + " textbox successfully");
				printMessage(inputValue + " value is passed in " + elementName + " textbox successfully");
			} catch (ElementNotInteractableException e) {

				/**
				 * ElementNotInteractableException :- this type exception occures when the
				 * element is hidden so we can use to overcome this exception then we can use
				 * java script because JavasriptExecutorenters any value or action whether any
				 * element hidden or not hidden in UI.........
				 */
				JavascriptExecutor jse = (JavascriptExecutor) driver;
				jse.executeScript("arguments[0].value='" + inputValue + "'", webObj);
				statusAndResult(Status.INFO,
						inputValue + " value has entered in " + elementName + " textbox successfully");
			} catch (StaleElementReferenceException e) {
				webObj = driver.findElement(By.xpath("//input[@name='" + inputValue + "']"));
				statusAndResult(Status.INFO, "we have found " + elementName + " successfully");
				webObj.sendKeys(inputValue);
				statusAndResult(Status.INFO,
						inputValue + " value has entered in " + elementName + " textbox successfully");
			} catch (Exception e) {
				e.printStackTrace();
				statusAndResult(Status.INFO,
						inputValue + " value hasn't entered in " + elementName + " textbox successfully");
				throw e;
			}

		} else {
			statusAndResult(Status.INFO, "Element is Displayed and enabled");
		}
	}

	//////////// getAttributeValue \\\\\\\\\\

	/**
	 * this method is used to find the attribute value with the help of attribute
	 * name.
	 * 
	 * @param webObj        :- it requires the object of WebElement as a parameter
	 *                      to find out the element where you want to get attribute
	 *                      value
	 * 
	 * @param attributeName we have to find the attribute value from attribute name
	 *                      in the form of string formate
	 * 
	 * @return it returns String i.e attrubute value as a string
	 * 
	 * @throws ElementClickInterceptedException this type exception occures when the
	 *                                          element is hidden so we can use to
	 *                                          overcome this exception hen we can
	 *                                          use java script because
	 *                                          JavasriptExecutorenters any value or
	 *                                          action whether any element hidden or
	 *                                          not hidden in UI.........
	 * 
	 */
	public String getAttributeValue(WebElement webObj, String attributeName) throws ElementClickInterceptedException {

		String elementName = webObj.getAccessibleName();
		String attributeValue = null;
		if (webObj.isDisplayed() && webObj.isEnabled()) {
			statusAndResult(Status.INFO, "Element is Displayed and enabled ");
			try {
				attributeValue = webObj.getAttribute(attributeName);
				statusAndResult(Status.INFO, attributeName + " Attribute value of " + elementName + " :-"
						+ attributeValue + " is found successfully ");
			} catch (ElementNotInteractableException e) {
				// Use JavascriptExecutor to execute JavaScript code and get the attribute value
				JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
				// Replace with the actual attribute name you want to retrieve
				String script = "return arguments[0].getAttribute('" + attributeName + "');";
				attributeValue = (String) jsExecutor.executeScript(script, webObj);
				statusAndResult(Status.INFO, attributeName + " Attribute value of " + elementName + " :-"
						+ attributeValue + " is found successfully ");
				return attributeValue;
			} catch (StaleElementReferenceException e) {
				// StaleElementReferenceException :---when the page has been updated or
				// refressed then
				// this type of exception will get....
				// webObj= myFindElement(xpath, elementName);
				attributeValue = webObj.getAttribute(attributeName);
				statusAndResult(Status.INFO, attributeName + " Attribute value of " + elementName + " :-"
						+ attributeValue + " is found successfully ");
			} catch (Exception e) {
				e.printStackTrace();
				statusAndResult(Status.INFO, attributeName + " Attribute value of " + elementName + " :-"
						+ attributeValue + " is not found successfully ");
				throw e;
			}

		} else {
			statusAndResult(Status.INFO, "Element is Displayed and enabled");
		}

		return attributeValue;
	}

	//////////// myClear \\\\\\\\\\
	/**
	 * The myClear() method is commonly used to clear to the textbox or input field
	 * 
	 * @param webObj :- it requires the object of WebElement as a parameter to find
	 *               out the element where you want to clear the data in the input
	 *               box or input field.
	 */
	public void clear(WebElement webObj) {
		String elementName = webObj.getAccessibleName();
		statusAndResult(Status.INFO, "we have found " + elementName + " successfully");

		if (webObj.isDisplayed() && webObj.isEnabled()) {
			statusAndResult(Status.INFO, "Element is Displayed and enabled ");
			try {
				webObj.clear();
				statusAndResult(Status.INFO, elementName + " textbox is cleared successfully");

			} catch (ElementNotInteractableException e) {
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("arguments[0].value=''", webObj);
				statusAndResult(Status.INFO, elementName + " textbox is cleared successfully");
			} catch (StaleElementReferenceException e) {
				// webObj=myFindElement(xpath, elementName);
				statusAndResult(Status.INFO, "we have found " + elementName + " successfully");
				webObj.clear();
				statusAndResult(Status.INFO, elementName + " textbox is cleared successfully");
			} catch (Exception e) {
				e.printStackTrace();
				statusAndResult(Status.INFO, elementName + " textbox is  not cleared successfully");
				throw e;
			}

		} else {

			statusAndResult(Status.INFO, "Element is not Displayed and enabled ");

		}

	}

	//////////// myJSClick \\\\\\\\\\

	/**
	 * The myJSClick() method is commonly used to click any element whether element
	 * is hidden or not. it's doesn't matter because JS perform action with the html
	 * code directly.
	 * 
	 * @param webObj :- it requires the object of WebElement as a parameter to find
	 *               out the element where you want to click the element by java
	 *               script.
	 */
	public void myJsClick(WebElement webObj) {

		String elementName = webObj.getAccessibleName();
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click()", webObj);
			statusAndResult(Status.INFO, elementName + " element is clicked by java script successfully");
		} catch (Exception e) {
			e.printStackTrace();
			statusAndResult(Status.INFO, elementName + " element is clicked by java script successfully");
			throw e;
		}
	}
	//////////// myClick \\\\\\\\\\

	/**
	 * The myClick() method is commonly used to click to the element
	 * 
	 * @param webObj :- it requires the object of WebElement as a parameter to find
	 *               out the element where you want to click as a normal.
	 */
	public void click(WebElement webObj, String elementName) {

//		String elementName=webObj.getAccessibleName();
		try {
			try {
				WebDriverWait webWait = new WebDriverWait(driver, Duration.ofSeconds(20));
				webWait.until(ExpectedConditions.visibilityOf(webObj));
			} catch (Exception q) {
				printMessage(elementName + "  is not found successfully");
			}

			if (webObj.isDisplayed() && webObj.isEnabled()) {

				statusAndResult(Status.INFO, "Element is Displayed and enabled");
				try {
					webObj.click();
					statusAndResult(Status.INFO, elementName + " element is clicked successfully");

				} catch (ElementClickInterceptedException e) {

					JavascriptExecutor js = (JavascriptExecutor) driver;

					js.executeScript("arguments[0].click()", webObj);
					statusAndResult(Status.INFO, elementName + " element is clicked successfully");
				} catch (StaleElementReferenceException e) {

					// webObj=myFindElement( xpath, elementName);

					webObj.click();
					statusAndResult(Status.INFO, elementName + " element is clicked successfully");

				} catch (Exception e) {
					try {
						JavascriptExecutor js = (JavascriptExecutor) driver;
						js.executeScript("arguments[0].click()", webObj);
						statusAndResult(Status.INFO, elementName + " element is clicked successfully");

					} catch (Exception r) {

					}

					e.printStackTrace();
					statusAndResult(Status.INFO, elementName + " element is not clicked successfully");
					throw e;
				}
			} else {
				statusAndResult(Status.INFO, "Element is not Displayed and enabled ");

			}
		} catch (Exception e) {
//			e.printStackTrace();
			statusAndResult(Status.INFO, elementName + " element is not clicked successfully");
		}
	}

//////////////////////////////////////////////////////////////////
	public void click(WebElement webObj, String elementName, int durationOfSecond) {

//		String elementName=webObj.getAccessibleName();
		try {
			try {
				WebDriverWait webWait = new WebDriverWait(driver, Duration.ofSeconds(durationOfSecond));
				webWait.until(ExpectedConditions.visibilityOf(webObj));
			} catch (Exception q) {
				printMessage(elementName + "  is not found successfully ");
			}

			if (webObj.isDisplayed() && webObj.isEnabled()) {

				statusAndResult(Status.INFO, "Element is Displayed and enabled");
				try {

//				webObj.click();
//				statusAndResult(Status.INFO, elementName+" element is clicked successfully");

					JavascriptExecutor js = (JavascriptExecutor) driver;
					js.executeScript("arguments[0].click()", webObj);
					statusAndResult(Status.INFO, elementName + " element is clicked successfully");

				} catch (ElementClickInterceptedException e) {

					JavascriptExecutor js = (JavascriptExecutor) driver;

					js.executeScript("arguments[0].click()", webObj);
					statusAndResult(Status.INFO, elementName + " element is clicked successfully");
				} catch (StaleElementReferenceException e) {

					// webObj=myFindElement( xpath, elementName);

					webObj.click();
					statusAndResult(Status.INFO, elementName + " element is clicked successfully");

				} catch (Exception e) {
					e.printStackTrace();
					statusAndResult(Status.INFO, elementName + " element is not clicked successfully");
					throw e;
				}
			} else {
				statusAndResult(Status.INFO, "Element is not Displayed and enabled ");

			}
		} catch (Exception e) {
			e.printStackTrace();
			statusAndResult(Status.INFO, elementName + " element is not clicked successfully");
		}
	}

	//////////// checkAllCheckBoxes \\\\\\\\\\
	/**
	 * this method is used to check all the check boxes whether they are already
	 * checked or unchecked.
	 * 
	 * @param listObj it requires the object of list of WebElement as a parameter to
	 *                find out the element where you want to check all the check
	 *                boxes.
	 */

	public void verifyAllCheckBoxes(List<WebElement> listObj) {

		if (listObj.isEmpty() == false) {
			try {
				for (int i = 0; i < listObj.size(); i++) {
					WebElement web = listObj.get(i);
					if (web.isSelected() == false) { // isSelected---checkbox or radio button is selected or not
						try {
							web.click();
						} catch (ElementNotInteractableException e) {
							JavascriptExecutor js = (JavascriptExecutor) driver;
							js.executeScript("arguments[0].click()", web);
						}
					}
					statusAndResult(Status.PASS, "All the checkBoxes is checked successfully");
				}

			} catch (Exception e) {
				e.printStackTrace();
				statusAndResult(Status.INFO, "All the checkBoxes is not checked successfully");

			}

			statusAndResult(Status.INFO, "this List is not empty");

		} else {
			statusAndResult(Status.FAIL, "this List is empty");
		}
	}

	//////////// uncheckAllCheckBoxes \\\\\\\\\\

	/**
	 * this method is used to check all the check boxes whether they are already
	 * checked or unchecked.
	 * 
	 * @param listObj it requires the object of list of WebElement as a parameter to
	 *                find out the element where you want to uncheck all the check
	 *                boxes.
	 */

	public void uncheckAllCheckBoxes(List<WebElement> listObj) {

		if (listObj.isEmpty() == false) {
			try {
				for (int i = 0; i < listObj.size(); i++) {
					WebElement web = listObj.get(i);
					if (web.isSelected() == true) { // isSelected---checkbox or radio button is selected or not
						try {
							web.click();
						} catch (ElementNotInteractableException e) {
							JavascriptExecutor js = (JavascriptExecutor) driver;
							js.executeScript("arguments[0].click()", web);
						}
					}
					statusAndResult(Status.PASS, "All the checkBoxes is unchecked successfully");
				}

			} catch (Exception e) {
				e.printStackTrace();
				statusAndResult(Status.INFO, "All the checkBoxes is  checked successfully");

			}

			statusAndResult(Status.INFO, "this List is not empty");

		} else {
			statusAndResult(Status.FAIL, "this List is empty");
		}

	}

	public void QuestionSelectBoxes(List<WebElement> listObj, String answer) {
		try {
			WebDriverWait webWait = new WebDriverWait(driver, Duration.ofSeconds(60));
			webWait.until(ExpectedConditions.visibilityOfAllElements(listObj));
		} catch (Exception q) {
			printMessage(answer + "  is not found successfully");
		}

		if (listObj.isEmpty() == false) {
			try {

				for (int i = 0; i < listObj.size(); i++) {
					WebElement web = listObj.get(i);

					if (web.getText().contains(answer) || web.getText().equalsIgnoreCase(answer)) {
						try {
//							web.click();
							JavascriptExecutor js = (JavascriptExecutor) driver;
							js.executeScript("arguments[0].click()", web);
						} catch (ElementNotInteractableException e) {
							JavascriptExecutor js = (JavascriptExecutor) driver;
							js.executeScript("arguments[0].click()", web);
						}
					}
					statusAndResult(Status.PASS, "Answer Select is  successfully");
				}

			} catch (Exception e) {
				e.printStackTrace();
				statusAndResult(Status.INFO, "All the checkBoxes is  checked successfully");

			}

			statusAndResult(Status.INFO, "this List is not empty");

		} else {
			statusAndResult(Status.FAIL, "this List is empty");
		}

	}

	//////////// myInnerText \\\\\\\\\\
	/**
	 * The myInnerText() method is commonly used to find the inner text of any
	 * element.
	 *
	 * @param webObj :- it requires the object of WebElement as a parameter to find
	 *               out the element where you want to get inner text of the
	 *               element.
	 * 
	 * @return :- it returns the inner text of the element in the form of String
	 * 
	 */
	public String mygetText(WebElement webObj, int startDuration, int defaultDur) {
		String innerText = null;
		try {
			implicitilyOfSeconds(startDuration);
			boolean a = webObj.isDisplayed();
			boolean b = webObj.isEnabled();
			if (a && b) {
				statusAndResult(Status.INFO, "Element is Displayed and enabled");
				try {
					innerText = webObj.getText();
					statusAndResult(Status.INFO, "Inner Text :-" + innerText + " is Found Successfully");
					implicitilyOfSeconds(defaultDur);
				} catch (ElementNotInteractableException e) {
					// Use JavascriptExecutor to execute JavaScript code and get the inner text
					JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
					String script = "return arguments[0].innerText;";
					innerText = (String) jsExecutor.executeScript(script, webObj);
					statusAndResult(Status.INFO, "Inner Text :-" + innerText + " is Found Successfully");
					return innerText;
				} catch (StaleElementReferenceException e) {
					// webObj=myFindElement(xpath, elementName);
					innerText = webObj.getText();
					statusAndResult(Status.INFO, "Inner Text :-" + innerText + " is Found Successfully");
				} catch (Exception e) {
					e.printStackTrace();
					statusAndResult(Status.INFO, "Inner Text :-" + innerText + " is not Found Successfully");
					throw e;
				}
			} else {
				implicitilyOfSeconds(defaultDur);
				statusAndResult(Status.INFO, "Element is Displayed and enabled");
			}
		} catch (Exception e) {
			implicitilyOfSeconds(defaultDur);
		}
		implicitilyOfSeconds(defaultDur);
		return innerText;
	}

	public String getTextByAttributeValue(WebElement webObj, String AttriuteName) {
		String innerText = null;
		try {
			boolean a = webObj.isDisplayed();
			boolean b = webObj.isEnabled();
			if (a && b) {
				statusAndResult(Status.INFO, "Element is Displayed and enabled");
				try {
					innerText = webObj.getAttribute(AttriuteName);
					statusAndResult(Status.INFO, "Inner Text :-" + innerText + " is Found Successfully");
				} catch (ElementNotInteractableException e) {
					// Use JavascriptExecutor to execute JavaScript code and get the inner text
					JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
					String script = "return arguments[0].innerText;";
					innerText = (String) jsExecutor.executeScript(script, webObj);
					statusAndResult(Status.INFO, "Inner Text :-" + innerText + " is Found Successfully");
					return innerText;
				} catch (StaleElementReferenceException e) {
					// webObj=myFindElement(xpath, elementName);
					innerText = webObj.getText();
					statusAndResult(Status.INFO, "Inner Text :-" + innerText + " is Found Successfully");
				} catch (Exception e) {
					e.printStackTrace();
					statusAndResult(Status.INFO, "Inner Text :-" + innerText + " is not Found Successfully");
					throw e;
				}
			} else {
				statusAndResult(Status.INFO, "Element is Displayed and enabled");
			}
		} catch (Exception e) {
		}
		return innerText;
	}

	//////////// getXAndYOffSet \\\\\\\\\\
	/**
	 * The getXAndYOffSet() method is commonly used to find the location of any
	 * element that means x offset and y offset.
	 * 
	 * @param webObj :- it requires the object of WebElement as a parameter to find
	 *               out the element where you want to get offset of x-axis and
	 *               y-axis of the element.
	 * 
	 * @return it returns the object of Point class.
	 * 
	 */
	public Point getXAndYOffSet(WebElement webObj) {

		Point location = null;
		if (webObj.isDisplayed() && webObj.isEnabled()) {
			statusAndResult(Status.INFO, "Element is Displayed and enabled");
			try {
				location = webObj.getLocation();
				statusAndResult(Status.INFO, "the location of the element is found successfully");
			} catch (StaleElementReferenceException e) {
				// webObj=myFindElement(xpath, elementName);
				location = webObj.getLocation();
				statusAndResult(Status.INFO, "the location of the element is found successfully");
			} catch (Exception e) {
				e.printStackTrace();
				statusAndResult(Status.INFO, "the location of the element is not found successfully");
				throw e;
			}
		} else {
			statusAndResult(Status.INFO, "Element is Displayed and enabled");
		}
		return location;
	}

	//////////// getHeightAndWidth \\\\\\\\\\
	/**
	 * The getHeightAndWidth() method is commonly used to find height and weidth of
	 * any element.
	 * 
	 * @param webObj :- it requires the object of WebElement as a parameter to find
	 *               out the element where you want to get hight and width of the
	 *               element.
	 * 
	 * @return it returns the object of Dimension class.
	 * 
	 */

	public Dimension getHeightAndWidth(WebElement webObj) {

		Dimension size = null;
		if (webObj.isDisplayed() && webObj.isEnabled()) {
			statusAndResult(Status.INFO, "Element is Displayed and enabled");
			try {
				size = webObj.getSize();
				statusAndResult(Status.INFO, "the size of the element is found successfully");
			} catch (StaleElementReferenceException e) {
				// webObj=myFindElement(xpath, elementName);
				size = webObj.getSize();
				statusAndResult(Status.INFO, "the size of the element is found successfully");
			} catch (Exception e) {
				e.printStackTrace();
				statusAndResult(Status.INFO, "the size of the element is not found successfully");
				throw e;
			}
		} else {
			statusAndResult(Status.INFO, "Element is Displayed and enabled");
		}
		return size;
	}

	//////////// verifyString \\\\\\\\\\

	/**
	 * verifyInnerText() :- this method is used to verify the innner text of actual
	 * and expected value of the element.
	 * 
	 * @param we           -it requires the object of webElement
	 * @param expectedText we give our expected value in the form of String
	 */
	public void verifyInnerText(WebElement we, String expectedText) {
		String actualText = we.getText();
		if (actualText.equalsIgnoreCase(expectedText)) {
			statusAndDetails(Status.PASS, "Passed!,Actual-" + actualText + " && Expected-" + expectedText);
		} else {
			statusAndDetails(Status.FAIL, "Failed!,Actual-" + actualText + " && Expected-" + expectedText);
		}
		Assert.assertEquals(actualText, expectedText);
	}

	// SoftAssertion
//	SoftAssert sa=new SoftAssert();
//	sa.assertEquals(false, true);
//	sa.assertAll();

//   SoftAssert    HardAssert 
	/// Verify Assert

//   try {
//	   Assert.assertEquals(actualText, expectedText);  // fail
//   }catch(Throwable e) {
//	   
//   }

	/**
	 * verifyAttributeValue() - this method is used to verify the attribute value of
	 * the element.
	 * 
	 * @param attributeName -it requires the attribute Name of webElement
	 * @param we            -it requires the object of webElement
	 * @param expectedText  -we give our expected value in the form of String
	 */
	public void verifyAttributeValue(WebElement we, String attributeName, String expectedAttribute) {
		String actualAttribute = we.getAttribute(attributeName);
		if (actualAttribute.equalsIgnoreCase(expectedAttribute)) {
			webUtil.statusAndResult(Status.PASS,
					"Passed. Actual-" + actualAttribute + " && Expected-" + expectedAttribute);
		} else {
			webUtil.statusAndResult(Status.FAIL,
					"Failed. Actual-" + actualAttribute + " && Expected-" + expectedAttribute);
		}
		Assert.assertEquals(actualAttribute, expectedAttribute);

	}

//////verifyTitle
////verifyUrl
////verifyEnabled
////verifyDisabled
////verifyElementVisible
//verifyDropdownValue

	public void verifyTitle(String expTitle) {
		String actPageTitle = getPageTitle();
		if (actPageTitle.equalsIgnoreCase(expTitle)) {
			webUtil.statusAndResult(Status.PASS, "Passed. Actual-" + actPageTitle + " && Expected-" + expTitle);
		} else {
			webUtil.statusAndResult(Status.FAIL, "Failed. Actual-" + actPageTitle + " && Expected-" + expTitle);
		}
		Assert.assertEquals(actPageTitle, expTitle);

	}

	public void verifyUrl(String expectedUrl) {
		String actualUrl = getPresentUrl();
		if (actualUrl.equalsIgnoreCase(expectedUrl)) {
			webUtil.statusAndResult(Status.PASS, "Passed. Actual-" + actualUrl + " && Expected-" + expectedUrl);
		} else {
			webUtil.statusAndResult(Status.FAIL, "Failed. Actual-" + actualUrl + " && Expected-" + expectedUrl);
		}
		Assert.assertEquals(actualUrl, expectedUrl);

	}

	//////////// verifyInt \\\\\\\\\\

	/**
	 * it is used to verify two integer number that is same or not.
	 * 
	 * @param actInt it refers to the actual number.
	 * 
	 * @param expInt it refers to the expected number.
	 */
	public void verifyInt(int actInt, int expInt) {

		if (actInt == expInt) {
			statusAndResult(Status.PASS,
					"the actual integer :-" + actInt + " and expected integer :-" + expInt + " both are same");
		} else {
			statusAndResult(Status.FAIL,
					"the actual integer :-" + actInt + " and expected integer :-" + expInt + " both are not same");
		}
		Assert.assertEquals(actInt, expInt);
	}

	//////////// verifyString \\\\\\\\\\

	/**
	 * it is used to verify two String that is same or not.
	 * 
	 * @param actualString   it refers to the actual String.
	 * 
	 * @param expectedString it refers to the expected String.
	 */
	public void verifyText(String actualString, String expectedString, String message) {
		if (actualString.equalsIgnoreCase(expectedString)) {
			statusAndResult(Status.PASS,
					"the actual :-" + actualString + " && expected :-" + expectedString + " both are same");
		} else {
			statusAndResult(Status.FAIL,
					"the actual :-" + actualString + " && expected :-" + expectedString + " both are not same");
		}
		Assert.assertEquals(actualString, expectedString, message);
	}

	//////////// checkDisplayed \\\\\\\\\\
	/**
	 * The checkDisplayed() method is commonly used to check the element that is
	 * display or not
	 * 
	 * @param webObj :- it requires the object of WebElement as a parameter to find
	 *               out the element where you want to check the element is
	 *               displayed or not.
	 * 
	 * @return it returns boolean i.e true or false
	 * 
	 */
	public boolean checkDisplayed(WebElement webObj) {

		boolean status = false;
		try {
			status = webObj.isDisplayed();
			statusAndResult(Status.INFO, "Element is displayed");
		} catch (StaleElementReferenceException e) {

			status = webObj.isDisplayed();
			statusAndResult(Status.INFO, "Element is displayed");
		} catch (Exception e) {
			e.printStackTrace();
			statusAndResult(Status.INFO, "Element is not displayed");
			throw e;

		}
		return status;
	}

	public void verifyElementVisibility(WebElement we) {
		boolean actStatus = checkDisplayed(we);
		if (actStatus == true) {
			statusAndResult(Status.PASS, "Passed !,The Element is Displayed");
		} else {
			statusAndResult(Status.FAIL, "Failed !,The Element is Displayed");
		}
		Assert.assertTrue(actStatus, "It is also verified By Assertion");

	}

	//////////// checkEnabled \\\\\\\\\\
	/**
	 * The checkEnabled() method is commonly used to check the element that is
	 * enable or not
	 * 
	 * @param webObj :- it requires the object of WebElement as a parameter to find
	 *               out the element where you want to check the element is enabled
	 *               or not.
	 * 
	 * @return it returns boolean i.e true or false
	 * 
	 */
	public boolean checkEnabled(WebElement webObj) {
		boolean status = false;
		try {
			status = webObj.isEnabled();
			statusAndResult(Status.INFO, "Element is enabled");
		} catch (ElementNotInteractableException e) {
			WebDriverWait webWait = new WebDriverWait(driver, Duration.ofSeconds(30));
			webWait.until(ExpectedConditions.visibilityOf(webObj));
		} catch (StaleElementReferenceException e) {
			status = webObj.isEnabled();
			statusAndResult(Status.INFO, "Element is enabled");
		} catch (Exception e) {
			e.printStackTrace();
			statusAndResult(Status.INFO, "Element is not enabled");
			throw e;

		}
		return status;

	}

	public void verifyEnabled(WebElement we) {
		boolean actStatus = checkEnabled(we);
		if (actStatus == true) {
			statusAndResult(Status.PASS, "Passed !,The Element is Enabled");
		} else {
			statusAndResult(Status.FAIL, "Failed !,The Element is Enabled");
		}
		Assert.assertTrue(actStatus, "It is also verified By Assertion");

	}

	//////////// isSelected \\\\\\\\\\

	/**
	 * The isSelected() method is commonly used with radio buttons, checkboxes, and
	 * options in a select dropdown.
	 * 
	 * @param webObj :- it requires the object of WebElement as a parameter to find
	 *               out the element where you want to check the element is selected
	 *               or not in the drop down.
	 * @return it returns boolean i.e true or false
	 * 
	 */
	public boolean checkSelected(WebElement webObj) {

		// The isSelected() method is commonly used with radio buttons, checkboxes,
		// and options in a select dropdown.

		boolean status = false;
		if (webObj.isDisplayed() && webObj.isEnabled()) {
			statusAndResult(Status.INFO, "Element is displayed and enabked");
			try {
				status = webObj.isSelected();
				statusAndResult(Status.INFO,
						"textBox or radio button or option in select dropDown is selected successfilly");
			} catch (ElementNotInteractableException e) {
				// Use JavascriptExecutor to execute JavaScript code and check if the element is
				// selected
				JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
//				status = (boolean) jsExecutor.executeScript("return arguments[0].selected;", webObj);
				statusAndResult(Status.INFO,
						"textBox or radio button or option in select dropDown is selected successfilly");
				return status;
			} catch (StaleElementReferenceException e) {
				status = webObj.isSelected();
				statusAndResult(Status.INFO,
						"textBox or radio button or option in select dropDown is selected successfilly");

			} catch (Exception e) {
				e.printStackTrace();
				statusAndResult(Status.INFO,
						"textBox or radio button or option in select dropDown is not selected successfilly");

			}

		} else {
			statusAndResult(Status.INFO, "Element is  not displayed and enabked");

		}
		return status;
	}

	public void verifySelectedDropOrRadioBT(WebElement we) {
		boolean actStatus = checkSelected(we);
		if (actStatus == true) {
			statusAndResult(Status.PASS, "Passed !,The Element is Selected");
		} else {
			statusAndResult(Status.FAIL, "Failed !,The Element is Selected");
		}
		Assert.assertTrue(actStatus, "It is also verified By Assertion");

	}
	//////////// selectByValueAttribute \\\\\\\\\\

	/**
	 * with the help of this method we can select the element by it's value
	 * attributes from drop down.
	 * 
	 * @param webObj :- it requires the object of WebElement as a parameter to find
	 *               out the element where you want to select the element in the
	 *               drop down.
	 * 
	 * @param value  it is used to select by value attribute
	 */
	public void selectByValueAttribute(WebElement webObj, String value) {

		Select slct = new Select(webObj);

		if (webObj.isDisplayed() && webObj.isEnabled()) {

			statusAndResult(Status.INFO, "Element is Displayed and enabled");
			try {
				slct.selectByValue(value);
				statusAndResult(Status.INFO, "you have selected the element in dropDown by it's value :--" + value);
			} catch (ElementNotInteractableException eo) {
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("arguments[0].value='" + value + "'", webObj);
				statusAndResult(Status.INFO, "you have selected the element in dropDown by it's value :--" + value);
			} catch (StaleElementReferenceException e) {
				slct.selectByValue(value);
				statusAndResult(Status.INFO, "you have selected the element in dropDown by it's value :--" + value);
			} catch (NullPointerException e) {
				slct.selectByValue(value);
				statusAndResult(Status.INFO, "you have selected the element in dropDown by it's value :--" + value);
			} catch (Exception e) {
				e.printStackTrace();
				statusAndResult(Status.INFO, "you haven't selected the element in dropDown by it's value :--" + value);
				throw e;

			}
		} else {
			statusAndResult(Status.INFO, "Element is not Displayed and enabled ");
		}
	}

	//////////// selectByIndexNumber \\\\\\\\\\
	/**
	 * it is used to select that element with the help of it's index number from
	 * drop down the index number is starts 0 to number-1
	 * 
	 * @param webObj      :- it requires the object of WebElement as a parameter to
	 *                    find out the element where you want to select the element
	 *                    in the drop down by using the indexing of the element.
	 * 
	 * @param indexNumber it refers to the index number of drop down
	 */
	public void selectByIndexNumber(WebElement webObj, int indexNumber) {

		Select slct = new Select(webObj);

		if (webObj.isDisplayed() && webObj.isEnabled()) {

			statusAndResult(Status.INFO, "Element is Displayed and enabled");
			try {
				slct.selectByIndex(indexNumber);
				statusAndResult(Status.INFO,
						"you have selected the element in dropDown by it's index number :--" + indexNumber);
			} catch (ElementNotInteractableException eo) {
				String script = "arguments[0].selectedIndex = " + indexNumber + "; "
						+ "arguments[0].dispatchEvent(new Event('change'));";
				((JavascriptExecutor) driver).executeScript(script, webObj);
				statusAndResult(Status.INFO,
						"you have selected the element in dropDown by it's index number :--" + indexNumber);

			} catch (StaleElementReferenceException e) {
				slct.selectByIndex(indexNumber);
				statusAndResult(Status.INFO,
						"you have selected the element in dropDown by it's index number :--" + indexNumber);

			} catch (NullPointerException e) {
				slct.selectByIndex(indexNumber);
				statusAndResult(Status.INFO,
						"you have selected the element in dropDown by it's index number :--" + indexNumber);
			} catch (Exception e) {

				e.printStackTrace();
				statusAndResult(Status.INFO,
						"you haven't selected the element in dropDown by it's index number :--" + indexNumber);
				throw e;

			}
		} else {
			statusAndResult(Status.INFO, "Element is not Displayed and enabled ");
		}
	}

	//////////// selectByVisibleString \\\\\\\\\\

	/**
	 * it is used to select that element with the help of inner text or that we are
	 * seeing the element in UI.
	 * 
	 * @param webObj        :- it requires the object of WebElement as a parameter
	 *                      to find out the element where you want to select the
	 *                      element in the drop down by using text of the element.
	 * 
	 * @param visibleString it refers to String as a text
	 */
	public void selectByVisibleString(WebElement webObj, String visibleString) {
		if (webObj.isDisplayed() || webObj.isEnabled()) {

			Select slct = new Select(webObj);
			statusAndResult(Status.INFO, "Element is Displayed and enabled");
			try {
				slct.selectByVisibleText(visibleString);
				statusAndResult(Status.INFO,
						"you have selected the element in dropDown by it's visible String :--" + visibleString);
			} catch (StaleElementReferenceException e) {
				slct.selectByVisibleText(visibleString);
				statusAndResult(Status.INFO,
						"you have selected the element in dropDown by it's visible String :--" + visibleString);
			} catch (Exception e) {
				e.printStackTrace();
				statusAndResult(Status.INFO,
						"you haven't selected the element in dropDown by it's visible String :--" + visibleString);
				throw e;
			}

		} else {
			statusAndResult(Status.INFO, "Element is not displayed and Enabled");

		}
	}

	/**
	 * this method is used to Fetch the element that is by defult selected in the
	 * dropDown with the help of getFirstSelectedElement method.
	 * 
	 * @param webObj :- it requires the object of WebElement as a parameter to find
	 *               out the element where you want to select the element in the
	 *               drop down as a first.
	 * 
	 */
	//////////// getFirstSelectedElement \\\\\\\\\\

	public String getFirstSelectedElement(WebElement webObj) {

		String elementName = webObj.getAccessibleName();

		Select slt = new Select(webObj);
		String firstSelected = null;
		try {
			firstSelected = slt.getFirstSelectedOption().getText();
			statusAndResult(Status.INFO, "Fetch the first selected element :- " + firstSelected + " of " + elementName
					+ " dropDown  successfully");
		} catch (StaleElementReferenceException e) {
			firstSelected = slt.getFirstSelectedOption().getText();
			statusAndResult(Status.INFO, "Fetch the first selected element :- " + firstSelected + " of " + elementName
					+ " dropDown  successfully");
		} catch (ElementNotInteractableException e) {
			JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
			String script = "arguments[0].selectedIndex = 0;";
			firstSelected = (String) jsExecutor.executeScript(script, webObj);
			statusAndResult(Status.INFO, "Fetch the first selected element :- " + firstSelected + " of " + elementName
					+ " dropDown  successfully");
			return firstSelected;
		} catch (Exception e) {
			e.printStackTrace();
			statusAndResult(Status.INFO, "Didn't Fetch the first selected element :- " + firstSelected + " of "
					+ elementName + " dropDown successfully");
		}
		return firstSelected;
	}

	//////////// getAllElements \\\\\\\\\\
	/**
	 * this method is used to Fetch all the webElements from the dropDown with the
	 * help of getAllElements method.
	 * 
	 * @param webObj :- it requires the object of list of WebElement as a parameter
	 *               to find out the element where you want to get all the elements
	 *               in the drop down.
	 * 
	 */
	public List<WebElement> getAllElements(WebElement webObj) {

		Select slctObj = new Select(webObj);
		List<WebElement> lstElements = null;
		try {
			lstElements = slctObj.getOptions();
			statusAndResult(Status.INFO, "Fetched all the elements from the dropDown successfully ");
		} catch (StaleElementReferenceException e) {
			lstElements = slctObj.getOptions();
			statusAndResult(Status.INFO, "Fetched all the elements from the dropDown successfully ");
		} catch (Exception e) {
			e.printStackTrace();
			statusAndResult(Status.INFO, "Didn't Fetch all the elements from the dropDown successfully ");
		}
		return lstElements;
	}

	//////////// selectedByTextContains \\\\\\\\\\
	/**
	 * it is used to select the element with the help of contains text in drop down.
	 * 
	 * @param webObj     :- it requires the object of WebElement as a parameter to
	 *                   find out the element where you want to select the element
	 *                   in the drop down by using contains text of the element.
	 * 
	 * @param selectText it refers to the string as a text that shows which text we
	 *                   have to selected in drop down.....
	 */

	public void selectedByTextContains(WebElement webObj, String selectText) {

		int indexNumber = 0;
		Select slct = new Select(webObj);
		try {
			List<WebElement> webListOptions = slct.getOptions();
			statusAndResult(Status.INFO, "All the WebElement of dropDown is fetched");
			for (int i = 0; i < webListOptions.size(); i++) {
				String optionText = webListOptions.get(i).getText();
				boolean status = optionText.contains(selectText);
				if (status == true) {
					indexNumber = i;
					break;
				}
			}
			slct.selectByIndex(indexNumber);
			statusAndResult(Status.INFO, "select text " + selectText + "  is selected from dropDown successfully");
		} catch (StaleElementReferenceException e) {
			List<WebElement> webListOptions = slct.getOptions();
			statusAndResult(Status.INFO, "All the WebElement of dropDown is fetched");
			for (int i = 0; i < webListOptions.size(); i++) {
				String optionText = webListOptions.get(i).getText();

				boolean status = optionText.contains(selectText);
				if (status == true) {
					indexNumber = i;
					break;
				}
				slct.selectByIndex(indexNumber);
				statusAndResult(Status.INFO, "select text " + selectText + "  is selected from dropDown successfully");
			}
		} catch (Exception e) {
			e.printStackTrace();
			statusAndResult(Status.INFO, "select text " + selectText + "  is not selected from dropDown successfully");

		}
	}

	//////////// getOptionsCount \\\\\\\\\\

	/**
	 * it is used to get the total number of element in drop down.
	 * 
	 * @param webObj :- it requires the object of WebElement as a parameter to find
	 *               out the element where you want to get the numbers of the
	 *               elements in the drop down.
	 * 
	 * @return it returns int i.e count of option in dropDown
	 */
	public int getOptionsCount(WebElement webObj) {

		Select slt = new Select(webObj);
		int optionCount = 0;
		try {
			optionCount = slt.getOptions().size();
			statusAndResult(Status.INFO, "Fetch the total element of the dropDown is " + optionCount);
		} catch (StaleElementReferenceException e) {
			optionCount = slt.getOptions().size();
			statusAndResult(Status.INFO, "Fetch the total element of the dropDown is " + optionCount);
		} catch (Exception e) {
			e.printStackTrace();
			statusAndResult(Status.INFO, "Fetch the total element of the dropDown is " + optionCount);
		}
		return optionCount;
	}

	//////////// mouseClick \\\\\\\\\\

	/**
	 * this method is used to click action on the webElement with the help of
	 * mouseClick method using Actions class.
	 * 
	 * @param webObj :- it requires the object of WebElement as a parameter to find
	 *               out the element where you want to click the element with the
	 *               help of Actions's click method.
	 * 
	 */
	public void mouseClick(WebElement webObj) {
		try {
			if (webObj.isDisplayed() && webObj.isEnabled()) {

				statusAndResult(Status.INFO, "Element is Displayed and enabled");
				Actions act = new Actions(driver);
				try {
					act.click(webObj).build().perform();
					statusAndResult(Status.INFO, "Click action is done successfully by Actions method");
					printMessage("Click Mouse is done successfully by Actions method");
				} catch (StaleElementReferenceException e) {
					act.click(webObj).build().perform();
					statusAndResult(Status.INFO, "Click action is done successfully by Actions method");
				} catch (ElementNotInteractableException e) {
					JavascriptExecutor jse = (JavascriptExecutor) driver;
					jse.executeScript("arguments[0].click()", webObj);
					statusAndResult(Status.INFO, "Click action is done successfully by Actions method");
				} catch (Exception e) {
					e.printStackTrace();
					statusAndResult(Status.INFO, "Click Action is not done successfully by Actions method");
					throw e;
				}
			} else {
				statusAndResult(Status.INFO, "Element is not displayed and enabled");

			}
		} catch (Exception e) {

		}
	}

	//////////// mouseRightClick \\\\\\\\\\

	/**
	 * this method is used to right click action on the webElement with the help of
	 * mouseRightClick method using Actions class.
	 * 
	 * @param webObj :- it requires the object of WebElement as a parameter to find
	 *               out the element where you want to right click of the element
	 *               with the help of Actions's contextClick method.
	 */
	public void mouseRightClick(WebElement webObj) {

		Actions act = new Actions(driver);
		if (webObj.isDisplayed() && webObj.isEnabled()) {
			statusAndResult(Status.INFO, "Element is Displayed and enabled");
			try {
				act.contextClick(webObj).build().perform();
				statusAndResult(Status.INFO, "Right click is performed successfully by Actions method");
			} catch (ElementNotInteractableException e) {
				// Use JavascriptExecutor to execute the right-click event
				JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
				String script = "var event = new MouseEvent('contextmenu', { 'bubbles': true, "
						+ "'cancelable': true });" + "arguments[0].dispatchEvent(event);";
				jsExecutor.executeScript(script, webObj);
				statusAndResult(Status.INFO, "Right click is  performed successfully by Actions method");

			} catch (StaleElementReferenceException e) {
				act.contextClick(webObj).build().perform();
				statusAndResult(Status.INFO, "Right click is performed successfully by Actions method");
			} catch (Exception e) {
				e.printStackTrace();
				statusAndResult(Status.INFO, "Right click is not performed successfully by Actions method");
				throw e;
			}

		} else {
			statusAndResult(Status.INFO, "Element is not displayed and Enabled");

		}
	}

	//////////// mouseDoubleClick \\\\\\\\\\
	/**
	 * this method is used to perform double click action on the webElement with the
	 * help of mouseDoubleClick method using Actions class.
	 * 
	 * @param webObj :- it requires the object of WebElement as a parameter to find
	 *               out the element where you want to double click of the element
	 *               with the help of Actions's doubleClick method.
	 * 
	 */
	public void mouseDoubleClick(WebElement webObj) {

		Actions act = new Actions(driver);
		if (webObj.isDisplayed() && webObj.isEnabled()) {
			statusAndResult(Status.INFO, "Element is Displayed and enabled");
			try {
				act.doubleClick(webObj).build().perform();
				statusAndResult(Status.INFO, "Double click is performed successfully by Actions method");
			} catch (ElementNotInteractableException e) {
				JavascriptExecutor executor = (JavascriptExecutor) driver;
				executor.executeScript(
						"arguments[0].dispatchEvent(new MouseEvent('dblclick'," + " { bubbles: true }));", webObj);
				statusAndResult(Status.INFO, "Double click is performed successfully by Actions method");
			} catch (StaleElementReferenceException e) {
				act.doubleClick(webObj).build().perform();
				statusAndResult(Status.INFO, "Double click is performed successfully by Actions method");
			} catch (Exception e) {
				e.printStackTrace();
				statusAndResult(Status.INFO, "Double click is not performed successfully by Actions method");
				throw e;
			}
		} else {
			statusAndResult(Status.INFO, "Element is not displayed and Enabled");

		}
	}
	//////////// mouseSendKeys \\\\\\\\\\

	/**
	 * this method is used to send any input value in textbox or any input field
	 * with the help of mouseSendKeys method using Actions class.
	 * 
	 * @param webObj     :- it requires the object of WebElement as a parameter to
	 *                   find out the element where you want to send the value in
	 *                   input box or input field with the help of Actions's
	 *                   sendKeys method.
	 * 
	 * @param inputValue here we have to send input value
	 * 
	 */
	public void mouseSendKeys(WebElement webObj, String inputValue) {
		Actions act = new Actions(driver);
		if (webObj.isDisplayed() && webObj.isEnabled()) {
			statusAndResult(Status.INFO, "Element is Displayed and enabled");
			try {
				act.sendKeys(webObj, inputValue).build().perform();
				statusAndResult(Status.INFO, "SendKeys is performed successfully by Actions method");
			} catch (ElementNotInteractableException e) {
				JavascriptExecutor executor = (JavascriptExecutor) driver;
				executor.executeScript("arguments[0].value='" + inputValue + "'", webObj);
				statusAndResult(Status.INFO, "SendKeys is performed successfully by Actions method");
			} catch (StaleElementReferenceException e) {
				act.sendKeys(webObj, inputValue).build().perform();
				statusAndResult(Status.INFO, "SendKeys is performed successfully by Actions method");
			} catch (Exception e) {
				e.printStackTrace();
				statusAndResult(Status.INFO, "SendKeys is not performed successfully by Actions method");
				throw e;
			}
		} else {
			statusAndResult(Status.INFO, "Element is not displayed and Enabled");

		}
	}

	public void useEnterKeys(String inputValue) {
		Actions act = new Actions(driver);
		try {
			act.sendKeys(Keys.TAB).build().perform();
			act.sendKeys(Keys.ENTER).build().perform();
			printMessage(inputValue + "  Accepted Successfully  ");
		} catch (Exception e) {
			e.printStackTrace();
			statusAndResult(Status.INFO, "SendKeys is not performed successfully by Actions method");
		}
	}

	//////////// scrollingToElement \\\\\\\\\

	/**
	 * this method is used to scroll to any particular element.
	 * 
	 * @param webObj :- it requires the object of WebElement as a parameter to find
	 *               out the element where you want to scroll the cursor at the
	 *               element with the help of Actions's scrollToElement method.
	 */
	public void scrollingToElement(WebElement webObj) {

		Actions act = new Actions(driver);
		if (webObj.isDisplayed() && webObj.isEnabled()) {
			statusAndResult(Status.INFO, "Element is Displayed and enabled");
			try {
				act.scrollToElement(webObj).build().perform();
				statusAndResult(Status.INFO, "scrolling to the element is performed successfully by Actions method");
			} catch (ElementNotInteractableException e) {
				JavascriptExecutor executor = (JavascriptExecutor) driver;
				executor.executeScript("arguments[0].scrollIntoView(true);", webObj);
				statusAndResult(Status.INFO, "scrolling to the element is performed successfully by Actions method");
			} catch (StaleElementReferenceException e) {
				act.scrollToElement(webObj).build().perform();
				statusAndResult(Status.INFO, "scrolling to the element is performed successfully by Actions method");
			} catch (Exception e) {
				e.printStackTrace();
				statusAndResult(Status.INFO,
						"scrolling to the element is not performed successfully by Actions method");
				throw e;
			}
		} else {
			statusAndResult(Status.INFO, "Element is not displayed and Enabled");

		}
	}

	//////////// mouseDragDrop \\\\\\\\\

	/**
	 * 
	 * mouseDragDrop(WebElement dragWeb,WebElement dropWeb) :- this method is
	 * especially designed to drag and drop operation from one webElement to another
	 * webElement
	 * 
	 * @param dragWeb :-it requires the object of WebElement as a parameter to find
	 *                out the element where you want to drag at the element with the
	 *                help of Actions's dragAndDrop method.
	 * 
	 * @param dropWeb :-it requires the object of WebElement as a parameter to find
	 *                out the element where you want to drop at the element with the
	 *                help of Actions's dragAndDrop method.
	 */
	public void mouseDragDrop(WebElement dragWeb, WebElement dropWeb) {

		String dragElementName = dropWeb.getAccessibleName();
		String dropElementName = dropWeb.getAccessibleName();
		Actions ac = new Actions(driver);

		if (dragWeb.isDisplayed() && dropWeb.isEnabled() && dropWeb.isDisplayed() && dropWeb.isEnabled()) {
			statusAndResult(Status.INFO,
					"Both element " + dragElementName + " and " + dropElementName + "is disable and enable");
			try {
				ac.dragAndDrop(dragWeb, dropWeb).build().perform();
				statusAndResult(Status.INFO, "Drag operation " + dragElementName + " is  droped to the "
						+ dropElementName + " successfully");
			} catch (StaleElementReferenceException e) {
				ac.dragAndDrop(dragWeb, dropWeb).build().perform();
				statusAndResult(Status.INFO, "Drag operation " + dragElementName + " is  droped to the "
						+ dropElementName + " successfully");
			} catch (ElementNotInteractableException e) {
				String xto = Integer.toString(dropWeb.getLocation().x);
				String yto = Integer.toString(dropWeb.getLocation().y);
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("function simulate(f,c,d,e){var b,a=null;"
						+ "for(b in eventMatchers)if(eventMatchers[b].test(c)){a=b;break}if(!a)"
						+ "return!1;document.createEvent?(b=document.createEvent(a),a==\"HTMLEvents\""
						+ "?b.initEvent(c,!0,!0):b.initMouseEvent(c,!0,!0,document.defaultView,0,d,e,"
						+ "d,e,!1,!1,!1,!1,0,null),f.dispatchEvent(b)):(a=document.createEventObject(),"
						+ "a.detail=0,a.screenX=d,a.screenY=e,a.clientX=d,a.clientY=e,a.ctrlKey=!1,a."
						+ "altKey=!1,a.shiftKey=!1,a.metaKey=!1,a.button=1,f.fireEvent(\"on\"+c,a));"
						+ "return!0} var eventMatchers={HTMLEvents:/^(?:load|unload|abort|error|select|"
						+ "change|submit|reset|focus|blur|resize|scroll)$/,MouseEvents:/^(?:click|dblclick"
						+ "|mouse(?:down|up|over|move|out))$/}; "
						+ "simulate(arguments[0],\"mousedown\",0,0); simulate(arguments[0],\"mousemove\""
						+ ",arguments[1],arguments[2]); simulate(arguments[0],\"mouseup\",arguments[1],arguments[2]); ",
						dragWeb, xto, yto);
				statusAndResult(Status.INFO, "Drag operation " + dragElementName + " is not droped to the "
						+ dropElementName + " successfully");
			} catch (Exception e) {
				e.printStackTrace();
				statusAndResult(Status.INFO, "Drag operation " + dragElementName + " is not droped to the "
						+ dropElementName + " successfully");
				throw e;
			}

		} else {
			statusAndResult(Status.INFO,
					"Both element " + dragElementName + " and " + dropElementName + "is not disable and enable");
		}
	}

	//////////// mouseClickAndHold \\\\\\\\\

	/**
	 * this method is used to click and hold of that element with the help of mouse
	 * it means that pressing the left button on an element without releasing
	 * it.This method is often used in combination with other Actions methods to
	 * perform complex interactions like drag-and-drop.
	 * 
	 * @param xpath       it is used to find the element with the help of
	 *                    myFindElement method.
	 * 
	 * @param elementName it is used in which element we are working and print a
	 *                    proper message to include this.
	 */

	/**
	 * mouseClickAndHold(WebElement webObj) :- this method is used to click and hold
	 * of that element with the help of mouse it means that pressing the left button
	 * on an element without releasing it.This method is often used in combination
	 * with other Actions methods to perform complex interactions like
	 * drag-and-drop.
	 * 
	 * @param webObj :-it requires the object of WebElement as a parameter to find
	 *               out the element where you want to click and hold to the element
	 *               with the help of Actions's clickAndHold method.
	 */
	public void mouseClickAndHold(WebElement webObj) {
		Actions ac = new Actions(driver);
		if (webObj.isDisplayed() && webObj.isEnabled()) {
			statusAndResult(Status.PASS, "Element is Displayed and enabled");
			try {
				ac.clickAndHold(webObj).build().perform();
				statusAndResult(Status.INFO, "Element is clicked and hold on successfully");

			} catch (StaleElementReferenceException e) {
				ac.clickAndHold(webObj).build().perform();
				statusAndResult(Status.INFO, "Element is clicked and hold on successfully");
			} catch (ElementNotInteractableException e) {
				// Use JavascriptExecutor to execute JavaScript code for the click and hold
				// action
				JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
				String script = "function simulateClickAndHold(element) { "
						+ "   var event = new MouseEvent('mousedown', { " + "       bubbles: true, "
						+ "       cancelable: true, " + "       view: window, " + "       buttons: 1 " + "   }); "
						+ "   element.dispatchEvent(event); " + "} " + "simulateClickAndHold(arguments[0]);";
				jsExecutor.executeScript(script, webObj);
				statusAndResult(Status.INFO, "Element is clicked and hold on successfully");
			} catch (Exception e) {
				e.printStackTrace();
				statusAndResult(Status.INFO, "Element is not clicked and hold on successfully");
			}

		} else {
			statusAndResult(Status.INFO, "Element is not displayed and Enabled");

		}

	}
	//////////// mouseReleaseElement \\\\\\\\\

	/**
	 * it is used to release mouse button after performing mouse related action. the
	 * release() method should be called after performing mouse action just like
	 * clickAndHold,click and moveToElement.
	 * 
	 * @param webObj :-it requires the object of WebElement as a parameter to find
	 *               out the element where you want to release to the element with
	 *               the help of Actions's release method.
	 */
	public void mouseReleaseElement(WebElement webObj) {
		Actions ac = new Actions(driver);

		if (webObj.isDisplayed() && webObj.isEnabled()) {
			statusAndResult(Status.INFO, "Element is Displayed and enabled");
			try {
				ac.release(webObj).build().perform();
				statusAndResult(Status.INFO, "Element is released successfully");

			} catch (StaleElementReferenceException e) {
				ac.release(webObj).build().perform();
				statusAndResult(Status.INFO, "Element is released successfully");
			} catch (ElementNotInteractableException e) {
				// Use JavascriptExecutor to execute JavaScript code for the click and hold
				// action
				JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
				String releaseScript = "function simulateMouseRelease() { "
						+ "   var event = new MouseEvent('mouseup', { " + "       bubbles: true, "
						+ "       cancelable: true, " + "       view: window, " + "       buttons: 0 " + "   }); "
						+ "   document.dispatchEvent(event); " + "} " + "simulateMouseRelease();";
				jsExecutor.executeScript(releaseScript);
				statusAndResult(Status.INFO, "Element is released successfully");
			} catch (Exception e) {
				e.printStackTrace();
				statusAndResult(Status.INFO, "Element is not released successfully");
			}

		} else {
			statusAndResult(Status.INFO, "Element is not displayed and Enabled");

		}

	}
	//////////// mouseMoveToElement \\\\\\\\\

	/**
	 * with the help of mouseMoveToElement method we can mouse hover of any element
	 * it means that we can move to the mouse in any given element
	 * 
	 * @param webObj :-it requires the object of WebElement as a parameter to find
	 *               out the element where you want to move to the element with the
	 *               help of Actions's moveToElement method.
	 */
	public void mouseMoveToElement(WebElement webObj) {
		Actions ac = new Actions(driver);
		if (webObj.isDisplayed() && webObj.isEnabled()) {
			statusAndResult(Status.INFO, "Element is Displayed and enabled");
			try {
				ac.moveToElement(webObj).build().perform();
				statusAndResult(Status.INFO, "Move to Element is performed successfully");

			} catch (StaleElementReferenceException e) {
				ac.release(webObj).build().perform();
				statusAndResult(Status.INFO, "Move to Element is performed successfully");
			} catch (ElementNotInteractableException e) {
				// Use JavascriptExecutor to execute JavaScript code for moving to the element
				JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
				String script = "var element = arguments[0];" + "var rect = element.getBoundingClientRect();"
						+ "var centerX = rect.left + rect.width / 2;" + "var centerY = rect.top + rect.height / 2;"
						+ "var mouseMoveEvent = new MouseEvent('mousemove', {" + "  bubbles: true,"
						+ "  cancelable: true," + "  view: window," + "  clientX: centerX," + "  clientY: centerY"
						+ "});" + "element.dispatchEvent(mouseMoveEvent);";
				jsExecutor.executeScript(script, webObj);

				statusAndResult(Status.INFO, "Element is released successfully");
			} catch (Exception e) {
				e.printStackTrace();
				statusAndResult(Status.INFO, "Move to Element is not performed successfully");
			}

		} else {
			statusAndResult(Status.INFO, "Element is not displayed and Enabled");

		}

	}

	private Properties properties;

	public Properties lodProperties() {
		properties = new Properties();

		try (InputStream input = new FileInputStream("src\\test\\resources\\configproperty.properties")) {
			// Load the properties file
			properties.load(input);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return properties;
	}

	/**
	 * Adjusts the zoom level of the webpage.
	 * 
	 * @param driver    The WebDriver instance.
	 * @param zoomLevel The zoom level (e.g., 1.0 for 100%, 0.8 for 80%, 1.2 for
	 *                  120%).
	 */

	public void zoomInAndOutPage(double zoomLevel) {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("document.body.style.zoom='" + zoomLevel + "'");
	}

	public int randonNumber(int range) {

		Random random = new Random();
		int randomIntRange = random.nextInt(range);
		System.out.println(" Random Number Start " + randomIntRange);
		return randomIntRange;
	}

	public ArrayList<String> readDataFromExcelSheet(int rowNumber, String excelFilePath) {
		ArrayList<String> list = new ArrayList<String>();

		try {
			XSSFWorkbook excelWBook = null;
			XSSFSheet excelWSheet;
			FileInputStream inputStrem = null;

			File excelFile = new File(excelFilePath);

			try {
				inputStrem = new FileInputStream(excelFile);
				excelWBook = new XSSFWorkbook(inputStrem);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

			excelWSheet = excelWBook.getSheetAt(0);
//			int lastRowNo = excelWSheet.getLastRowNum() + 1;
			int lasrCellNo = excelWSheet.getRow(0).getLastCellNum();
//			System.out.println(lastRowNo + "\t" + lasrCellNo);

			for (int currentCell = 0; currentCell < lasrCellNo; currentCell++) {
				String name = excelWSheet.getRow(rowNumber).getCell(currentCell).toString();
				list.add(name);
				System.out.print(name + "\t");
			}
			System.out.println();
			System.out.println("Serial No\t" + (rowNumber)+" \t and Row No  =  "+(rowNumber+1));
			excelWBook.close();
		} catch (Exception e) {
			list.add("Virendra");
			list.add("Male");
		}

		return list;
	}

	public int writeDataInToExcelSheet(ArrayList<String> addAllList, String elementName, String filePathName) {
		int lastRowNo = 0;
		try {
			XSSFWorkbook excelWBook = null;
			XSSFSheet excelWSheet;
			FileInputStream inputStrem = null;
			FileOutputStream outPutStrem = null;
			File excelFile = new File(filePathName);

			try {
				inputStrem = new FileInputStream(excelFile);
				excelWBook = new XSSFWorkbook(inputStrem);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

			excelWSheet = excelWBook.getSheet("AutomationReport");

///Write Data			
			Row newRow = excelWSheet.createRow(excelWSheet.getLastRowNum() + 1);
			for (int i = 0; i < addAllList.size(); i++) {
				Cell newCell1 = newRow.createCell(i); // First cell to Last Cell in the new row
				newCell1.setCellValue(addAllList.get(i));
			}

//// Save Excel Data in Excel File
			outPutStrem = new FileOutputStream(excelFile);
			excelWBook.write(outPutStrem);

///Row and Cell Count			
			lastRowNo = excelWSheet.getLastRowNum() + 1;
			int lasrCellNo = excelWSheet.getRow(0).getLastCellNum();
			excelWBook.close();
			System.out.println("Total Row " + lastRowNo + "\t Total Cell " + lasrCellNo + "\t" + elementName
					+ " is write in Excel SuccessFully");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(elementName + " is not write in Excel SuccessFully");
		}
		return lastRowNo;
	}

	public void writeDateInToExcelSheet1(String state, String district, String centerName, String filePathName,
			String excelSheetName) {
		int lastRowNo = 0;
		String dateInCell = currentDateWithTime();
		try {
			XSSFWorkbook excelWBook = null;
			XSSFSheet excelWSheet;
			FileInputStream inputStrem = null;
			FileOutputStream outPutStrem = null;
			File excelFile = new File(filePathName);

			try {
				inputStrem = new FileInputStream(excelFile);
				excelWBook = new XSSFWorkbook(inputStrem);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

			excelWSheet = excelWBook.getSheet(excelSheetName);

///Write Data			
			Row newRow = excelWSheet.createRow(excelWSheet.getLastRowNum() + 1);
			Cell newCell1 = newRow.createCell(0); // First cell in the new row
			newCell1.setCellValue(state);

			Cell newCell2 = newRow.createCell(1); // Second cell in the new row
			newCell2.setCellValue(district);

			Cell newCell3 = newRow.createCell(2); // Third cell in the new row
			newCell3.setCellValue(centerName);

			Cell newCell4 = newRow.createCell(3); // Fourth cell in the new row
			newCell4.setCellValue(dateInCell);

//// Save Excel Data in Excel File
			outPutStrem = new FileOutputStream(excelFile);
			excelWBook.write(outPutStrem);

///Row and Cell Count			
			lastRowNo = excelWSheet.getLastRowNum() + 1;
			int lasrCellNo = excelWSheet.getRow(0).getLastCellNum();
			excelWBook.close();
//			System.out.println("Total Row " + lastRowNo + "\t Total Cell " + lasrCellNo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int writeDateInToExcelSingleRow(String writeValue, int count, String SheetName, String filePath) {
		int lastRowNo = 0;
		try {
			XSSFWorkbook excelWBook = null;
			XSSFSheet excelWSheet;
			FileInputStream inputStrem = null;
			FileOutputStream outPutStrem = null;

			File excelFile = new File(filePath);

			try {
				inputStrem = new FileInputStream(excelFile);
				excelWBook = new XSSFWorkbook(inputStrem);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

			excelWSheet = excelWBook.getSheet(SheetName);

///Write Data			
			excelWSheet.getRow(count).getCell(5).setCellValue(writeValue);
			;

//// Save Excel Data in Excel File
			outPutStrem = new FileOutputStream(excelFile);
			excelWBook.write(outPutStrem);

///Row and Cell Count			
			lastRowNo = excelWSheet.getLastRowNum() + 1;
			int lasrCellNo = excelWSheet.getRow(0).getLastCellNum();
			excelWBook.close();
			System.out.println("Total Row " + lastRowNo + "\t Total Cell " + lasrCellNo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lastRowNo;
	}

	public int countRowInExcel() {

		int lastRowNo = 0;
		try {
			XSSFWorkbook excelWBook = null;
			XSSFSheet excelWSheet;
			FileInputStream inputStrem = null;

			File excelFile = new File("ExcelData\\CenterNameAndAutomation.xlsx");

			try {
				inputStrem = new FileInputStream(excelFile);
				excelWBook = new XSSFWorkbook(inputStrem);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

			excelWSheet = excelWBook.getSheet("AutomationReport");

///Row and Cell Count			
			lastRowNo = excelWSheet.getLastRowNum() + 1;
			int lasrCellNo = excelWSheet.getRow(0).getLastCellNum();
			excelWBook.close();
			System.out.println("Total Row " + lastRowNo + "\t Total Cell " + lasrCellNo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lastRowNo;

	}

	public int readAndWriteDataInToExcel(String elementName, String filePathName) {
		int lastRowNo = 0;
		try {
			XSSFWorkbook excelWBook = null;
			XSSFSheet excelWSheet;
			FileInputStream inputStrem = null;
			FileOutputStream outPutStrem = null;
			File excelFile = new File(filePathName);

			try {
				inputStrem = new FileInputStream(excelFile);
				excelWBook = new XSSFWorkbook(inputStrem);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

			excelWSheet = excelWBook.getSheet("Center");
			int lastRow = excelWSheet.getLastRowNum();
///Write Data			
			Row newRow = excelWSheet.createRow(excelWSheet.getLastRowNum() + 1);
			Cell newCell1 = newRow.createCell(0); // First cell to Last Cell in the new row
			newCell1.setCellValue(lastRow + 1);

//// Save Excel Data in Excel File
			outPutStrem = new FileOutputStream(excelFile);
			excelWBook.write(outPutStrem);

///Row and Cell Count			
			lastRowNo = excelWSheet.getLastRowNum() + 1;
			int lasrCellNo = excelWSheet.getRow(0).getLastCellNum();
			excelWBook.close();
			System.out.println("Total Row " + lastRowNo + "\t Total Cell " + lasrCellNo + "\t" + elementName
					+ " is write in Excel SuccessFully");
		} catch (

		Exception e) {
			e.printStackTrace();
			System.out.println(elementName + " is not write in Excel SuccessFully");
		}
		return lastRowNo;
	}

}
