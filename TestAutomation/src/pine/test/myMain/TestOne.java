package pine.test.myMain;
/* 
 * Author : Aman Shukla
 */

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import pine.test.pageObjects.*;

import pine.test.reports.ReporterClass;
import pine.test.utility.Constant;

import pine.test.utility.ReusableMethods;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.support.ui.Select;

class TestOne {

	private WebDriver driver;
	private PageObjects po;
	private StorePageObjects so;
	private TerminalCreationObjects tco;
	private Miscellaneous_Object misc_Objects;
	private File_ManagementObject fileMgmt;
	private TIDcreation Tidcreate;

	ReusableMethods methods = new ReusableMethods();
	ReporterClass report = new ReporterClass();

	public String merchantName;
	public String storeName;
	public String terminalIDforIngenico;
	public String terminalIDforPax;
	public String terminalIdforVerifone;

	@BeforeTest
	void setUp() {

		try {

			System.setProperty("webdriver.chrome.driver", Constant.Chrome_Driver);
			ChromeOptions opt = new ChromeOptions();
			opt.addArguments("--disable-notifications");
			opt.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			opt.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
			driver = new ChromeDriver(opt);
			driver.manage().window().maximize();
			driver.get(Constant.URL);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

			po = new PageObjects(driver);
			so = new StorePageObjects(driver);
			tco = new TerminalCreationObjects(driver);
			misc_Objects = new Miscellaneous_Object(driver);
			fileMgmt = new File_ManagementObject(driver);
			Tidcreate = new TIDcreation(driver);

		} catch (Exception e) {

			System.out.println("Exception occurred (SetUp): " + e.getMessage().toString());

		}
	}

	@AfterTest
	void tearDown() {
		try {

			// driver.quit();
			report.writeToFile();

		} catch (Exception e) {
			System.out.println("Exception occurred: " + e.getMessage().toString());

		}
	}

	@Test(priority = 1, dataProviderClass = DataProviderWithExcel.class, dataProvider = "Authentication")
	void LoginPageTest(String user, String password) {
		try {

			report.startTest("Login Page Test");
			report.startChildTest("Login Page Test");
			report.Log(LogStatus.INFO, "Logging In");

			boolean result = methods.login(user, password, driver);
			String imagePath = methods.takeScreenshot(driver, "LoginPageTest");
			if (result == true) {

				System.out.println("Logged in successfully.");
				report.Log(LogStatus.INFO, "Logged in successfully. Current Page: " + driver.getCurrentUrl());
				report.Log(LogStatus.PASS, "Login Test Case Passed");
				report.LogScreenshot(LogStatus.INFO, imagePath);

			} else {
				report.Log(LogStatus.FAIL, "Login Test Case Failed");
				report.LogScreenshot(LogStatus.INFO, imagePath);

			}

		} catch (Exception e) {
			report.Log(LogStatus.FATAL, "Some Exception Occurred - Login Page");
			System.out.println("Exception occurred (LoginPageTest): " + e.getMessage().toString());
			e.printStackTrace();
		} finally {
			report.endTest();
			// driver.close();
		}
	}

	@Test(priority = 2, dataProviderClass = DataProviderWithExcel.class, dataProvider = "Authentication")
	void TestMerchantCreation(String userName, String password, String mName, String addrLine1, String postCode) {
		report.startTest("Merchant Creation Test");
		try {

			String title = driver.getTitle();

			if (title.contains("Privacy Error")) {
				System.out.println("Privacy Error Encountered. Change browser settings.");
			} else {
				report.startChildTest("Merchant Creation Test");
				int retryCount = 1;
				do {
					driver.get(Constant.URL);
					methods.login(userName, password, driver);
					System.out.println("Login Attempt for TestCase CreateMerchant: " + retryCount);
					retryCount++;
				} while (driver.getCurrentUrl().contains("LoginPage") && retryCount <= 3);

				String[] resultMerchant = methods.CreateMerchant(mName, addrLine1, postCode, driver);
				System.out.println(resultMerchant.length);
				String[] merchantDetails = resultMerchant[0].split(":");

				String imagePath = resultMerchant[1];

				String result = merchantDetails[1];
				merchantName = merchantDetails[0];

				if (result.toLowerCase().contains("created")) {
					report.Log(LogStatus.PASS, "Merchant Creation Successful");
					report.Log(LogStatus.INFO, "Merchant Name: " + merchantDetails[0]);
					report.LogScreenshot(LogStatus.INFO, imagePath);

				} else if (result.toLowerCase().contains("already")) {
					report.Log(LogStatus.INFO, "Merchant Name Already Requested");
					report.Log(LogStatus.FAIL, "New Merchant Creation Failed");
					report.LogScreenshot(LogStatus.INFO, imagePath);
				}
			}
		} catch (Exception e) {

			report.Log(LogStatus.FATAL, "Some Error Occurred - Create Merchant Test");
			System.out.println("Exception occurred (Create Merchant Test): " + e.getMessage().toString());
			report.LogScreenshot(LogStatus.INFO, methods.takeScreenshot(driver, "Exception"));
			e.printStackTrace();

		} finally {
			report.endTest();
			// driver.close();
		}
	}

	@Test(priority = 3, dataProviderClass = DataProviderWithExcel.class, dataProvider = "Authentication", dependsOnMethods = "TestMerchantCreation")
	void MerchantApprovalTest(String approver, String approverPass) {

		report.startTest("Merchant Approval Test");

		try {

			String[] result = methods.ApproveMerchant(approver, approverPass, merchantName, driver, po);
			String msg = result[0];
			String imagePath = result[1];
			report.startChildTest("Merchant Approval Test");
			if (msg.toLowerCase().contains("approved successfully")) {

				report.Log(LogStatus.PASS, "Merchant Approved Successfully");
				report.Log(LogStatus.INFO, msg);
				report.LogScreenshot(LogStatus.INFO, imagePath);

			} else {

				report.Log(LogStatus.FAIL, "Merchant Approval Failed");
				report.LogScreenshot(LogStatus.INFO, imagePath);

			}
		} catch (Exception e) {

			System.out.println(e.getMessage().toString());
			e.printStackTrace();
			report.Log(LogStatus.FATAL, "Some Error Occurred");

		} finally {
			report.endTest();
			// driver.close();
		}
	}
	
	@Test(priority = 4,dataProviderClass = DataProviderWithExcel.class, dataProvider = "Authentication")
	void ToUpdateMerchant(String Username,String Password) throws InterruptedException {
		report.startTest("Merchant Update Test");
		try {
		methods.login(Username, Password, driver);
		report.startChildTest("Merchant Update Test");
		
		
			fileMgmt.FileMgmt.click();
			Tidcreate.ManageTerminal.click();
			Thread.sleep(2000);
			Select sel1 = new Select(Tidcreate.Searchtype_M);
			sel1.selectByVisibleText("Merchant Name");
			Tidcreate.EnterClientID.sendKeys(merchantName);
			Thread.sleep(3000);
			Tidcreate.ClickSearch.click();
			Thread.sleep(3000);
			Tidcreate.ClickMerchantName.click();
			Thread.sleep(3000);
			String currentWindow1 = driver.getWindowHandle();

			for (String handleTid : driver.getWindowHandles()) {
				Thread.sleep(1000);
				driver.switchTo().window(handleTid);
			}
			
			po.AddressLine1.clear();
			po.AddressLine1.sendKeys("TestAddress25");
			Thread.sleep(3000);
		
			Tidcreate.ClickUpdateMerchant.click();
			Thread.sleep(4000);
			String imagePath = methods.takeScreenshot(driver, "StoreApprove");
			String text1 = Tidcreate.Hubmessage.getText();
			System.out.println(text1);
			if (text1.toLowerCase().contains("updation at hub successful")) {

				report.Log(LogStatus.PASS, "Merchant Updation Successful");
				report.Log(LogStatus.INFO, text1);
				report.LogScreenshot(LogStatus.INFO, imagePath);

			} else {

				report.Log(LogStatus.FAIL, "Merchant Updation Failed");
				report.LogScreenshot(LogStatus.INFO, imagePath);

			}
			//Tidcreate.ClickUpdateMerchant.click();
			//Thread.sleep(3000);
			
			Tidcreate.blocktidSuccessfulPop.click();
			Thread.sleep(3000);
			driver.close();
			driver.switchTo().window(currentWindow1);
			
			
		}catch (Exception e) {
			System.out.println(e.getMessage().toString());
			e.printStackTrace();
			report.Log(LogStatus.FATAL, "Some Error Occurred");
		}
		finally {
			report.endTest();
			// driver.close();
		}
		
			
			
		}
//	@Test(priority = 5, dataProviderClass = DataProviderWithExcel.class, dataProvider = "Authentication")//, dependsOnMethods = "TestMerchantCreation")
	void MerchantApprovalTestAfterUpdate(String approver, String approverPass) {

		report.startTest("Merchant update Approval Test");

		try {

			String[] result = methods.ApproveMerchant(approver, approverPass, merchantName, driver, po);
			String msg = result[0];
			String imagePath = result[1];
			report.startChildTest("Merchant Approval Test");
			if (msg.toLowerCase().contains("updation at hub successful")) {

				report.Log(LogStatus.PASS, "Merchant Approved Successfully");
				report.Log(LogStatus.INFO, msg);
				report.LogScreenshot(LogStatus.INFO, imagePath);

			} else {

				report.Log(LogStatus.FAIL, "Merchant Approval Failed");
				report.LogScreenshot(LogStatus.INFO, imagePath);

			}
		} catch (Exception e) {

			System.out.println(e.getMessage().toString());
			e.printStackTrace();
			report.Log(LogStatus.FATAL, "Some Error Occurred");

		} finally {
			report.endTest();
			// driver.close();
		}
	}
		
	
	
	
	

	/*
	 * AUTHOR: Manmeet Singh
	 */

	//@Test(priority = 4, dataProviderClass = DataProviderWithExcel.class, dataProvider = "Authentication", dependsOnMethods = "MerchantApprovalTest")
	void ToVerifyStoreCreation(String username, String password, String storeName, String addrLine1, String postCode) {
		try {

			String title = driver.getTitle();
			if (title.contains("Privacy Error")) {
				System.out.println("Privacy Error Encountered. Change browser settings.");
			} else {
				report.startTest("Store Creation Test");
				report.startChildTest("Store Creation Test");
				Thread.sleep(2000);
				report.Log(LogStatus.INFO, "Logging in as Creator User");

				int retryCount = 1;
				do {
					driver.get(Constant.URL);
					methods.login(username, password, driver);
					System.out.println("Login Attempt for TestCase ToVerifyStoreCreation: " + retryCount);
					retryCount++;
					Thread.sleep(1000);
				} while (driver.getCurrentUrl().contains("LoginPage") && retryCount <= 3);

				po.MerchManagement.click();

				Thread.sleep(1000);
				so.StoreTab.click();
				Thread.sleep(1000);
				System.out.println("store tab");
				so.CreateStore.click();
				Thread.sleep(1000);
				so.SeacrhMerchant.sendKeys(merchantName);
				Thread.sleep(1000);
				so.SeacrhMerchantBtn.click();
				Thread.sleep(1000);
				so.CreateStoreBtn.click();
				Thread.sleep(1000);
				so.StoreDispName.sendKeys(merchantName);
				Thread.sleep(1000);
				so.StoreDescription.sendKeys(merchantName);
				so.StoreAddressLine1.sendKeys(addrLine1);
				so.Country.click();
				Thread.sleep(1000);
				so.State.click();
				Thread.sleep(1000);
				so.City.click();
				Thread.sleep(1000);
				so.PostalCode.sendKeys(postCode);
				// po.CloudValChckBox.click();
				so.StoreFirstContactName.sendKeys("Test user");
				so.StorePrimaryContactNum.sendKeys("9999111111");
				Thread.sleep(1000);
				so.StoreMobNum.sendKeys("9800000000");
				so.StoreEmail.sendKeys("test@pinelabs.com");
				Thread.sleep(1000);
				so.SaveStoreBtn.click();
				// System.out.println(merchantName);
				Thread.sleep(1000);
				po.ClosePopup.click();
				Thread.sleep(1000);
				po.UserDrpDwn.click();
				po.LogoutBtn.click();
				report.Log(LogStatus.PASS, "Store Creation Successful");
				// After Successful Logout, Login as approver and approve Store.//

			}
		} catch (Exception e) {
			report.Log(LogStatus.FATAL, "Store Creation Failed");
			System.out.println("Exception occurred (Create Store Test): " + e.getMessage().toString());
			e.printStackTrace();
		}
		report.endTest();
		// driver.close();
	}

	//@Test(priority = 5, dataProviderClass = DataProviderWithExcel.class, dataProvider = "Authentication", dependsOnMethods = "ToVerifyStoreCreation")
	void ToVerifyStoreApproval(String approver, String password) {
		try {

			report.startTest("Approve Store Test");
			report.startChildTest("Approve Store Test");
			report.Log(LogStatus.INFO, "Logging in as Store Approver.");

			int retryCount = 1;
			do {
				driver.get(Constant.URL);
				methods.login(approver, password, driver);
				System.out.println("Login Attempt for TestCase ToVerifyStoreApproval: " + retryCount);
				retryCount++;
				Thread.sleep(1000);
			} while (driver.getCurrentUrl().contains("LoginPage") && retryCount <= 3);

			Thread.sleep(1000);

			po.MerchManagement.click();
			Thread.sleep(1000);
			so.StoreTab.click();
			Thread.sleep(1000);
			so.ApproveStore.click();
			Thread.sleep(1000);
			so.MerchantName.sendKeys(merchantName);
			so.SearchBtn.click();
			Thread.sleep(2000);
			so.ApproveRejectBtn.click();
			Thread.sleep(5000);
			String currentWindow = driver.getWindowHandle();
			for (String handle : driver.getWindowHandles()) {
				Thread.sleep(2000);
				driver.switchTo().window(handle);
			}
			driver.manage().window().maximize();
			Thread.sleep(10000);
			so.ApproveBtn.click();
			Thread.sleep(3000);
			so.SaveBtn.click();
			Thread.sleep(1000);
			String successMsg = po.MerchantId.getText();
			String imagePath = methods.takeScreenshot(driver, "StoreApprove");
			po.ClosePopup.click();
			driver.close();
			System.out.println(successMsg + " and Store Name: " + merchantName);
			driver.switchTo().window(currentWindow);
			// po.UserDrpDwn.click();
			Thread.sleep(1000);
			// po.LogoutBtn.click();
			report.Log(LogStatus.PASS, "Store Approval Passed ");
			report.Log(LogStatus.INFO, successMsg);
			report.LogScreenshot(LogStatus.INFO, imagePath);

		} catch (Exception e) {
			report.Log(LogStatus.FATAL, "Some Exception Occurred : Approve Store Test");
			System.out.println("Exception occurred (Approve Store Test): " + e.getMessage().toString());
			e.printStackTrace();
		}
		report.endTest();
		// driver.close();
	}

	/*
	 * AUTHOR: Lalit Kumar
	 *
	 */

	//@Test(priority = 6, dataProviderClass = DataProviderWithExcel.class, dataProvider = "Authentication", dependsOnMethods = "ToVerifyStoreApproval")
	void TerminalCreationTest(String userName, String passWord, String hardwaretype, String hardwareSubtype, String PVM,
			String EDCPrinterValue, String Printerwidthvalue, String LMSIDValue, String SecondLMSID) {

		report.startTest("Terminal Creation Test for Ingenico");
		report.startChildTest("Terminal Creation Test");
		System.out.println(hardwaretype);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		try {

			driver.get("https://192.168.100.40:8050/WelcomePageUI.aspx");
			Thread.sleep(1000);
			po.MerchManagement.click();
			System.out.println("Clicking Terminal");
			Thread.sleep(1000);
			tco.ClcTerminalLink.click();
			System.out.println("Create Terminal Link");
			Thread.sleep(1000);
			tco.ClcCreateLink.click();

			tco.EnterMerchantName.sendKeys(merchantName);
			Thread.sleep(1000);
			tco.EnterStoreName.sendKeys(merchantName);

			tco.ClickSearchButtonforTerminal.click();

			tco.ClickTerminalCreate.click();

			tco.LMSID.sendKeys(LMSIDValue);

			tco.ClickValidateLMSID.click();
			String popuptext = tco.PopUpXpath.getText();
			System.out.println(popuptext);
			report.Log(LogStatus.INFO, "Validating LMS ID");
			report.Log(LogStatus.INFO, popuptext);
			try {
				if (popuptext.equalsIgnoreCase("Entered LMS ID is Valid.")) {

					Thread.sleep(3000);
					tco.clickOkonPopup.click();

					tco.HardwareType.sendKeys(hardwaretype);

					tco.HardwareSubVersionType.sendKeys(hardwareSubtype);
					String HardwareSerialnumb = Integer.toString(methods.randomNumber(8));
					tco.HardwareSerialNumber.sendKeys(HardwareSerialnumb);

					tco.PVM.click();

					tco.Printer.sendKeys(EDCPrinterValue);

					tco.PrinterWidth.sendKeys(Printerwidthvalue);
					Thread.sleep(5000);
					tco.PVM.click();
					tco.CLickCreateButton.click();
					String popuptext3 = tco.TerminalCreationSuccessPopuptext.getText();
					String imagePath = methods.takeScreenshot(driver, "CreateTerminal");
					if (popuptext3.toLowerCase().contains("successful")) {
						terminalIDforIngenico = popuptext3.substring(popuptext3.length() - 6);
						System.out.println(terminalIDforIngenico);
						if (popuptext3.equalsIgnoreCase(
								"Pos Creation On Hub Successful For Terminal ID : " + terminalIDforIngenico)) {
							report.Log(LogStatus.PASS, "Terminal Creation Test Case Passed");
							report.Log(LogStatus.INFO, popuptext3.length() + ":" + terminalIDforIngenico);
						}
						Thread.sleep(10000);
						tco.CloseterminalSuccesspop.click();

					} else {
						tco.clickOkonPopup.click();
						report.Log(LogStatus.FAIL, "Terminal Creation Test Case Failed");
						report.Log(LogStatus.FAIL, popuptext3);

					}
					report.LogScreenshot(LogStatus.INFO, imagePath);
				} else {
					report.Log(LogStatus.ERROR, "ENTERED LMS ID is INVALID, please enter valid LMS ID in TestData.");
					report.Log(LogStatus.FAIL, "TerminalCreationTest failed.");
					terminalIDforIngenico = "";
				}

			} catch (Exception e) {
				System.out.println("Exception occurred (TerminalCreationTest): " + e.getMessage().toString());
				report.Log(LogStatus.INFO, e.getMessage().toString());
				report.Log(LogStatus.FATAL, "Some Exception Occurred : Terminal Creation Test");
				terminalIDforIngenico = "";
			}

		} catch (Exception e2) {
			e2.printStackTrace();
			report.Log(LogStatus.FATAL, "Some Exception Occured : Terminal Not Created");
			report.Log(LogStatus.INFO, e2.getMessage().toString());
			terminalIDforIngenico = "";
		}
		report.endTest();
		// driver.close();
	}

	//@Test(priority = 7, dataProviderClass = DataProviderWithExcel.class, dataProvider = "Authentication", dependsOnMethods = "TerminalCreationTest")
	void TerminalCreationTestPAX(String userName, String passWord, String hardwaretype, String hardwareSubtype,
			String PVM, String EDCPrinterValue, String Printerwidthvalue, String LMSIDValue, String SecondLMSID) {

		report.startTest("Terminal Creation Test for Pax");
		report.startChildTest("Terminal Creation Test");
		System.out.println(hardwaretype);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		try {

			driver.get("https://192.168.100.40:8050/WelcomePageUI.aspx");
			Thread.sleep(1000);
			po.MerchManagement.click();
			System.out.println("Clicking Terminal");
			Thread.sleep(1000);
			tco.ClcTerminalLink.click();
			System.out.println("Create Terminal Link");
			Thread.sleep(1000);
			tco.ClcCreateLink.click();

			tco.EnterMerchantName.sendKeys(merchantName);
			Thread.sleep(1000);
			tco.EnterStoreName.sendKeys(merchantName);

			tco.ClickSearchButtonforTerminal.click();

			tco.ClickTerminalCreate.click();

			tco.LMSID.sendKeys(LMSIDValue);

			tco.ClickValidateLMSID.click();
			String popuptext = tco.PopUpXpath.getText();
			System.out.println(popuptext);
			report.Log(LogStatus.INFO, "Validating LMS ID");
			report.Log(LogStatus.INFO, popuptext);
			try {
				if (popuptext.equalsIgnoreCase("Entered LMS ID is Valid.")) {

					Thread.sleep(3000);
					tco.clickOkonPopup.click();

					tco.HardwareType.sendKeys(hardwaretype);

					tco.HardwareSubVersionType.sendKeys(hardwareSubtype);
					String HardwareSerialnumb = Integer.toString(methods.randomNumber(8));
					tco.HardwareSerialNumber.sendKeys(HardwareSerialnumb);

					tco.PVM.click();

					tco.Printer.sendKeys(EDCPrinterValue);

					tco.PrinterWidth.sendKeys(Printerwidthvalue);
					Thread.sleep(5000);
					tco.PVM.click();
					tco.CLickCreateButton.click();
					String popuptext3 = tco.TerminalCreationSuccessPopuptext.getText();
					String imagePath = methods.takeScreenshot(driver, "CreateTerminal");
					if (popuptext3.toLowerCase().contains("successful")) {
						terminalIDforPax = popuptext3.substring(popuptext3.length() - 6);
						System.out.println(terminalIDforPax);
						if (popuptext3.equalsIgnoreCase(
								"Pos Creation On Hub Successful For Terminal ID : " + terminalIDforPax)) {
							report.Log(LogStatus.PASS, "Terminal Creation Test Case Passed");
							report.Log(LogStatus.INFO, popuptext3.length() + ":" + terminalIDforPax);
						}
						Thread.sleep(10000);
						tco.CloseterminalSuccesspop.click();

					} else {
						tco.clickOkonPopup.click();
						report.Log(LogStatus.FAIL, "Terminal Creation Test Case Failed");
						report.Log(LogStatus.FAIL, popuptext3);

					}
					report.LogScreenshot(LogStatus.INFO, imagePath);
				} else {
					report.Log(LogStatus.ERROR, "ENTERED LMS ID is INVALID, please enter valid LMS ID in TestData.");
					report.Log(LogStatus.FAIL, "TerminalCreationTest failed.");
					terminalIDforIngenico = "";
				}

			} catch (Exception e) {
				System.out.println("Exception occurred (TerminalCreationTest): " + e.getMessage().toString());
				report.Log(LogStatus.INFO, e.getMessage().toString());
				report.Log(LogStatus.FATAL, "Some Exception Occurred : Terminal Creation Test");
				terminalIDforIngenico = "";
			}

		} catch (Exception e2) {
			e2.printStackTrace();
			report.Log(LogStatus.FATAL, "Some Exception Occured : Terminal Not Created");
			report.Log(LogStatus.INFO, e2.getMessage().toString());
			terminalIDforIngenico = "";
		}
		report.endTest();
		// driver.close();
	}

	//@Test(priority = 8, dataProviderClass = DataProviderWithExcel.class, dataProvider = "Authentication", dependsOnMethods = "TerminalCreationTestPAX")
	void TerminalCreationTestVerifone(String userName, String passWord, String hardwaretype, String hardwareSubtype,
			String PVM, String EDCPrinterValue, String Printerwidthvalue, String LMSIDValue, String SecondLMSID) {

		report.startTest("Terminal Creation Test for Verifone");
		report.startChildTest("Terminal Creation Test");
		System.out.println(hardwaretype);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		try {

			driver.get("https://192.168.100.40:8050/WelcomePageUI.aspx");
			Thread.sleep(1000);
			po.MerchManagement.click();
			System.out.println("Clicking Terminal");
			Thread.sleep(1000);
			tco.ClcTerminalLink.click();
			System.out.println("Create Terminal Link");
			Thread.sleep(1000);
			tco.ClcCreateLink.click();

			tco.EnterMerchantName.sendKeys(merchantName);
			Thread.sleep(1000);
			tco.EnterStoreName.sendKeys(merchantName);

			tco.ClickSearchButtonforTerminal.click();

			tco.ClickTerminalCreate.click();

			tco.LMSID.sendKeys(LMSIDValue);

			tco.ClickValidateLMSID.click();
			String popuptext = tco.PopUpXpath.getText();
			System.out.println(popuptext);
			report.Log(LogStatus.INFO, "Validating LMS ID");
			report.Log(LogStatus.INFO, popuptext);
			try {
				if (popuptext.equalsIgnoreCase("Entered LMS ID is Valid.")) {

					Thread.sleep(3000);
					tco.clickOkonPopup.click();

					tco.HardwareType.sendKeys(hardwaretype);

					tco.HardwareSubVersionType.sendKeys(hardwareSubtype);
					String HardwareSerialnumb = Integer.toString(methods.randomNumber(8));
					tco.HardwareSerialNumber.sendKeys(HardwareSerialnumb);

					tco.PVM.click();

					tco.Printer.sendKeys(EDCPrinterValue);

					tco.PrinterWidth.sendKeys(Printerwidthvalue);
					Thread.sleep(5000);
					tco.PVM.click();
					tco.CLickCreateButton.click();
					String popuptext3 = tco.TerminalCreationSuccessPopuptext.getText();
					String imagePath = methods.takeScreenshot(driver, "CreateTerminal");
					if (popuptext3.toLowerCase().contains("successful")) {
						terminalIdforVerifone = popuptext3.substring(popuptext3.length() - 6);
						System.out.println(terminalIdforVerifone);
						if (popuptext3.equalsIgnoreCase(
								"Pos Creation On Hub Successful For Terminal ID : " + terminalIdforVerifone)) {
							report.Log(LogStatus.PASS, "Terminal Creation Test Case Passed");
							report.Log(LogStatus.INFO, popuptext3.length() + ":" + terminalIdforVerifone);
						}
						Thread.sleep(10000);
						tco.CloseterminalSuccesspop.click();

					} else {
						tco.clickOkonPopup.click();
						report.Log(LogStatus.FAIL, "Terminal Creation Test Case Failed");
						report.Log(LogStatus.FAIL, popuptext3);

					}
					report.LogScreenshot(LogStatus.INFO, imagePath);
				} else {
					report.Log(LogStatus.ERROR, "ENTERED LMS ID is INVALID, please enter valid LMS ID in TestData.");
					report.Log(LogStatus.FAIL, "TerminalCreationTest failed.");
					terminalIDforIngenico = "";
				}

			} catch (Exception e) {
				System.out.println("Exception occurred (TerminalCreationTest): " + e.getMessage().toString());
				report.Log(LogStatus.INFO, e.getMessage().toString());
				report.Log(LogStatus.FATAL, "Some Exception Occurred : Terminal Creation Test");
				terminalIDforIngenico = "";
			}

		} catch (Exception e2) {
			e2.printStackTrace();
			report.Log(LogStatus.FATAL, "Some Exception Occured : Terminal Not Created");
			report.Log(LogStatus.INFO, e2.getMessage().toString());
			terminalIDforIngenico = "";
		}
		report.endTest();
		// driver.close();
	}

	//@Test(priority = 9, dataProviderClass = DataProviderWithExcel.class, dataProvider = "Authentication", dependsOnMethods = "TerminalCreationTestVerifone")
	void TIDCreationdefault(String BankName, String Purposetype, String Terminaltype) throws InterruptedException {
		report.startTest("Tid Creation");
		report.startChildTest("Tid Creation");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		try {
			driver.get("https://192.168.100.40:8050/WelcomePageUI.aspx");
			String[] resultMerchant2 = methods.TidCreationCommon(terminalIDforPax, BankName, Purposetype, Terminaltype,
					driver);

			if (resultMerchant2[0].contains("successful")) {
				// String BankId = TidSuccessText.substring(TidSuccessText.length()-5);

				Tidcreate.clickOkonPopup.click();
				driver.close();
				driver.switchTo().window(resultMerchant2[2]);
				report.Log(LogStatus.PASS, "Terminal Creation Test Case Passed");
				report.Log(LogStatus.INFO, resultMerchant2[0]);

			}

			else {

				report.Log(LogStatus.FAIL, "TID Creation Test Case Failed");
				report.Log(LogStatus.FAIL, resultMerchant2[0]);

			}

			report.LogScreenshot(LogStatus.INFO, resultMerchant2[1]);

		} catch (Exception e) {
			System.out.println(e.getMessage().toString());
			e.printStackTrace();
			report.Log(LogStatus.FATAL, "Some Error Occurred. Check console for stack trace.");
			report.Log(LogStatus.INFO, e.getMessage().toString());
		}

		finally {
			report.endTest();
		}

	}

	//@Test(priority = 10, dataProviderClass = DataProviderWithExcel.class, dataProvider = "Authentication", dependsOnMethods = "TerminalCreationTestVerifone")
	void TIDCreationLR(String BankName, String Purposetype, String Terminaltype) throws InterruptedException {
		report.startTest("Tid Creation with Loan recovery");
		report.startChildTest("Tid Creation with Loan recovery");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		try {
			driver.get("https://192.168.100.40:8050/WelcomePageUI.aspx");
			String[] resultMerchant2 = methods.TidCreationCommon(terminalIdforVerifone, BankName, Purposetype,
					Terminaltype, driver);

			if (resultMerchant2[0].contains("successful")) {
				// String BankId = TidSuccessText.substring(TidSuccessText.length()-5);

				Tidcreate.clickOkonPopup.click();
				driver.close();
				driver.switchTo().window(resultMerchant2[2]);
				report.Log(LogStatus.PASS, "Terminal Creation Test Case Passed");
				report.Log(LogStatus.INFO, resultMerchant2[0]);

			}

			else {
				Tidcreate.clickOkonPopup.click();
				driver.close();
				driver.switchTo().window(resultMerchant2[2]);
				report.Log(LogStatus.FAIL, "TID Creation Test Case Failed");
				report.Log(LogStatus.FAIL, resultMerchant2[0]);

			}

			report.LogScreenshot(LogStatus.INFO, resultMerchant2[1]);

		} catch (Exception e) {
			System.out.println(e.getMessage().toString());
			e.printStackTrace();
			report.Log(LogStatus.FATAL, "Some Error Occurred. Check console for stack trace.");
			report.Log(LogStatus.INFO, e.getMessage().toString());
		}

		finally {
			report.endTest();
		}

	}

	//@Test(priority = 11, dataProviderClass = DataProviderWithExcel.class, dataProvider = "Authentication", dependsOnMethods = "TerminalCreationTestVerifone")
	void TIDCreationLRwithNormal(String BankName, String Purposetype, String Terminaltype) throws InterruptedException {
		report.startTest("Tid Creation for LR with Normal");
		report.startChildTest("Tid Creation for LR with Normal");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		try {
			driver.get("https://192.168.100.40:8050/WelcomePageUI.aspx");
			String[] resultMerchant2 = methods.TidCreationCommon(terminalIDforIngenico, BankName, Purposetype,
					Terminaltype, driver);

			if (resultMerchant2[0].contains("successful")) {
				// String BankId = TidSuccessText.substring(TidSuccessText.length()-5);

				Tidcreate.clickOkonPopup.click();
				driver.close();
				driver.switchTo().window(resultMerchant2[2]);
				report.Log(LogStatus.PASS, "Terminal Creation Test Case Passed");
				report.Log(LogStatus.INFO, resultMerchant2[0]);

			}

			else {
				Tidcreate.clickOkonPopup.click();
				driver.close();
				driver.switchTo().window(resultMerchant2[2]);
				report.Log(LogStatus.FAIL, "TID Creation Test Case Failed");
				report.Log(LogStatus.FAIL, resultMerchant2[0]);

			}

			report.LogScreenshot(LogStatus.INFO, resultMerchant2[1]);

		} catch (Exception e) {
			System.out.println(e.getMessage().toString());
			e.printStackTrace();
			report.Log(LogStatus.FATAL, "Some Error Occurred. Check console for stack trace.");
			report.Log(LogStatus.INFO, e.getMessage().toString());
		}

		finally {
			report.endTest();
		}
	}

	//@Test(priority = 12, dataProviderClass = DataProviderWithExcel.class, dataProvider = "Authentication", dependsOnMethods = "TerminalCreationTestVerifone")
	void TIDCreationBANKEMI(String BankName, String Purposetype, String Terminaltype) throws InterruptedException {
		report.startTest("Tid Creation for BANK EMI ONLY");
		report.startChildTest("Tid Creation for BANK EMI ONLY");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		try {
			driver.get("https://192.168.100.40:8050/WelcomePageUI.aspx");
			String[] resultMerchant2 = methods.TidCreationCommon(terminalIDforIngenico, BankName, Purposetype,
					Terminaltype, driver);

			if (resultMerchant2[0].contains("successful")) {
				// String BankId = TidSuccessText.substring(TidSuccessText.length()-5);

				Tidcreate.clickOkonPopup.click();
				driver.close();
				driver.switchTo().window(resultMerchant2[2]);
				report.Log(LogStatus.PASS, "Terminal Creation Test Case Passed");
				report.Log(LogStatus.INFO, resultMerchant2[0]);

			}

			else {
				Tidcreate.clickOkonPopup.click();
				driver.close();
				driver.switchTo().window(resultMerchant2[2]);
				report.Log(LogStatus.FAIL, "TID Creation Test Case Failed");
				report.Log(LogStatus.FAIL, resultMerchant2[0]);

			}

			report.LogScreenshot(LogStatus.INFO, resultMerchant2[1]);

		} catch (Exception e) {
			System.out.println(e.getMessage().toString());
			e.printStackTrace();
			report.Log(LogStatus.FATAL, "Some Error Occurred. Check console for stack trace.");
			report.Log(LogStatus.INFO, e.getMessage().toString());
		}

		finally {
			report.endTest();
		}

	}

	//@Test(priority = 13, dataProviderClass = DataProviderWithExcel.class, dataProvider = "Authentication", dependsOnMethods = "TerminalCreationTestVerifone")
	void TIDCreationICICLRNM(String BankName, String Purposetype, String Terminaltype) throws InterruptedException {
		report.startTest("Tid Creation for ICICI BANK");
		report.startChildTest("Tid Creation for ICICI BANK");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		try {
			driver.get("https://192.168.100.40:8050/WelcomePageUI.aspx");
			String[] resultMerchant2 = methods.TidCreationCommon(terminalIDforIngenico, BankName, Purposetype,
					Terminaltype, driver);

			if (resultMerchant2[0].contains("successful")) {
				// String BankId = TidSuccessText.substring(TidSuccessText.length()-5);

				Tidcreate.clickOkonPopup.click();
				driver.close();
				driver.switchTo().window(resultMerchant2[2]);
				report.Log(LogStatus.PASS, "Terminal Creation Test Case Passed");
				report.Log(LogStatus.INFO, resultMerchant2[0]);

			}

			else {
				Tidcreate.clickOkonPopup.click();
				driver.close();
				driver.switchTo().window(resultMerchant2[2]);
				report.Log(LogStatus.FAIL, "TID Creation Test Case Failed");
				report.Log(LogStatus.FAIL, resultMerchant2[0]);

			}

			report.LogScreenshot(LogStatus.INFO, resultMerchant2[1]);

		} catch (Exception e) {
			System.out.println(e.getMessage().toString());
			e.printStackTrace();
			report.Log(LogStatus.FATAL, "Some Error Occurred. Check console for stack trace.");
			report.Log(LogStatus.INFO, e.getMessage().toString());
		}

		finally {
			report.endTest();
		}

	}

	//@Test(priority = 14)
	void BlockTid() throws InterruptedException, AWTException {
		report.startTest("Tid Block Test case");
		report.startChildTest("Tid Block Test case");

		driver.get("https://192.168.100.40:8050/WelcomePageUI.aspx");
		try {

			fileMgmt.FileMgmt.click();
			Thread.sleep(2000);
			Tidcreate.ManageTerminal.click();
			Thread.sleep(2000);
			Tidcreate.Searchtype_M.sendKeys("Client Id");
			Thread.sleep(2000);
			Tidcreate.EnterClientID.sendKeys(terminalIDforIngenico);
			Thread.sleep(2000);
			Tidcreate.ClickSearch.click();
			Thread.sleep(2000);
			Tidcreate.TidNumbers.click();
			Thread.sleep(2000);
			Tidcreate.blocktid.click();
			Robot eve = new Robot();
			eve.keyPress(KeyEvent.VK_ENTER);
			eve.keyRelease(KeyEvent.VK_ENTER);
			Tidcreate.tidPop.getText();
			
			// String imagePath = methods.takeScreenshot(driver, "TIDBLOCK");
			Thread.sleep(2000);
			Tidcreate.blocktidSuccessfulPop.click();
			report.Log(LogStatus.PASS, "TID Block Test Case Passed");
			report.Log(LogStatus.INFO, "Tid is now blocked");
			// report.LogScreenshot(LogStatus.INFO, imagePath);

		} catch (Exception e) {
			System.out.println(e.getMessage().toString());

		} finally {
			report.endTest();
		}

	}

	// @Test(priority = 7, dataProviderClass = DataProviderWithExcel.class,
	// dataProvider = "Authentication")
	void PVMUploadTest(String userName, String passWord) {
		report.startTest("PVM Upload Test");
		report.startChildTest("PVM Upload Test");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		try {

			driver.get("https://192.168.100.40:8050/WelcomePageUI.aspx");
			Thread.sleep(2000);
			misc_Objects.Mis_Tab.click();
			Thread.sleep(2000);
			misc_Objects.uploaddownload.click();
			Thread.sleep(1000);
			misc_Objects.File_Upload.click();
			Select sel = new Select(misc_Objects.File_Type);
			sel.selectByVisibleText("PVM");
			String fileID = Integer.toString(methods.randomNumber(5));
			misc_Objects.File_ID.sendKeys(fileID);
			misc_Objects.Choose_File.sendKeys(Constant.Tess_Util + "/DemoPVM_1_15092020.xml");
			sel = new Select(misc_Objects.Hardware_Family);
			sel.selectByVisibleText("Ingenico");
			Thread.sleep(10000);
			misc_Objects.Submit.click();
			Thread.sleep(10000);

			String popUpMessage = misc_Objects.popUpMessage.getText();
			System.out.println(popUpMessage);
			try {
				String imagePath = methods.takeScreenshot(driver, "PVMUpload");
				if (popUpMessage.equalsIgnoreCase("PVM Uploaded Successfully")) {

					report.Log(LogStatus.PASS, "PVM Uploaded Successfully");

				} else {
					report.Log(LogStatus.FAIL, "PVM Upload Failed");

				}
				report.Log(LogStatus.INFO, popUpMessage);
				report.LogScreenshot(LogStatus.INFO, imagePath);
				misc_Objects.okbtn.click();

			} catch (Exception e) {

				System.out.println(e.getMessage().toString());
				e.printStackTrace();
				report.Log(LogStatus.FATAL, "Some Error Occurred. Check console for stack trace.");
				report.Log(LogStatus.INFO, e.getMessage().toString());

			}
		} catch (Exception e2) {
			System.out.println(e2.getMessage().toString());
			e2.printStackTrace();
			report.Log(LogStatus.FATAL, "Some Error Occurred. Check console for stack trace.");
			report.Log(LogStatus.INFO, e2.getMessage().toString());
		}

		report.endTest();
		// driver.close();
	}

//	 @Test(priority = 8, dataProviderClass = DataProviderWithExcel.class,
	// dataProvider = "Authentication", dependsOnMethods = "TerminalCreationTest")
	void PVMAssociationTest(String userName, String passWord) {
		report.startTest("PVM Association Test");
		report.startChildTest("PVM Association Test");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		try {

			driver.get("https://192.168.100.40:8050/WelcomePageUI.aspx");
			Thread.sleep(2000);
			fileMgmt.FileMgmt.click();
			fileMgmt.File_Search.click();

			Select sel = new Select(fileMgmt.FileType);
			sel.selectByVisibleText("PVM");

			Thread.sleep(2000);
			sel = new Select(fileMgmt.Search_Category);
			sel.selectByVisibleText("File");
			fileMgmt.Association_FileName.sendKeys("DemoPVM_1_15092020.xml");
			report.Log(LogStatus.INFO, "Searching for PVM");
			String currentWindow = driver.getWindowHandle();

			System.out.println("File Management - PVM Association");
			Thread.sleep(2000);
			if (terminalIDforIngenico != "") {

				// fileMgmt.FileSearch_POSId.sendKeys(terminalID);
				fileMgmt.Search.click();
				fileMgmt.Association.click();
				Thread.sleep(1000);
				for (String handle : driver.getWindowHandles()) {
					Thread.sleep(1000);
					driver.switchTo().window(handle);
				}
				Thread.sleep(3000);
				driver.manage().window().maximize();
				Thread.sleep(3000);
				sel = new Select(fileMgmt.Association_SearchType);
				sel.selectByVisibleText("Pos");
				Thread.sleep(1000);
				fileMgmt.Association_POSId.sendKeys(terminalIDforIngenico);
				fileMgmt.Association_Search.click();

				Thread.sleep(1000);
				fileMgmt.ApplyAssociation.click();
				Thread.sleep(10000);

				String associationPopUpMessage = fileMgmt.associationPopUpMessage.getText();
				System.out.println(associationPopUpMessage);

				// String PosID =
				// associationPopUpMessage.substring(associationPopUpMessage.length() - 6);
				// System.out.println(PosID);

				try {
					String imagePath = methods.takeScreenshot(driver, "PVMAssociation");
					if (associationPopUpMessage.toLowerCase().contains("association successful")) {
						report.Log(LogStatus.PASS, "PVM Association Passed");
						report.Log(LogStatus.INFO, associationPopUpMessage);

					} else {
						report.Log(LogStatus.FAIL, "PVM Association Failed");
						report.Log(LogStatus.INFO, associationPopUpMessage);

					}
					report.LogScreenshot(LogStatus.INFO, imagePath);
					fileMgmt.okButton.click();
				} catch (Exception e) {

					System.out.println(e.getMessage().toString());
					e.printStackTrace();
					report.Log(LogStatus.FATAL, "Some Error Occurred");
					report.Log(LogStatus.INFO, e.getMessage().toString());

				}
				driver.close();
				driver.switchTo().window(currentWindow);

			} else {
				report.Log(LogStatus.ERROR, "Valid terminal ID not found. Check Console.");
				System.out.println("Valid terminal ID not found.");
			}

		} catch (Exception e) {
			System.out.println("Some Exception Occurred: (PVMAssociationTest) " + e.getMessage().toString());
			e.printStackTrace();
			report.Log(LogStatus.FATAL, "Some Exception Occurred");
			report.Log(LogStatus.INFO, e.getMessage().toString());

		} finally {
			report.endTest();
		}

		// driver.close();

	}

}
