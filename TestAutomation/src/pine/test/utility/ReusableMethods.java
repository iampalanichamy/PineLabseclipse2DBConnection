package pine.test.utility;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;



import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import pine.test.pageObjects.File_ManagementObject;
import pine.test.pageObjects.PageObjects;
import pine.test.pageObjects.TIDcreation;
import pine.test.pageObjects.loginPageObjects;

public class ReusableMethods {

	public boolean login(String userName, String passWord, WebDriver driver) {
		boolean isLoginSuccess = false;
		try {
			loginPageObjects lgnPo = new loginPageObjects(driver);
			lgnPo.lgnId.sendKeys(userName);
			lgnPo.password.sendKeys(passWord);
			System.out.println("Password Entered");
			Thread.sleep(1000);
			String CAPTCHA = GetCaptchaText(driver.findElement(By.xpath("//img[@class='captcha-img']")));
			Thread.sleep(1000);
			lgnPo.captchaTxt.sendKeys(CAPTCHA);
			Thread.sleep(4000);
			if (driver.getCurrentUrl().contains("WelcomePageUI.aspx")) {
				isLoginSuccess = true;
			}
		} catch (Exception e) {

			System.out.println("Exception occurred in LoginFunction: " + e.getMessage().toString());
			e.printStackTrace();
		}
		return isLoginSuccess;
	}

	public String Unique_Pan() {
		long l = System.currentTimeMillis();

		String s = l + "";
		String s2 = "";

		System.out.println(s.length());

		for (int i = s.length() - 1; i > 8; i--) {
			s2 += s.charAt(i);
		}

		String pancardNo = "AVIPJ" + s2 + "K";
		System.out.println(pancardNo);
		return pancardNo;
	}

	public String GetCaptchaText(WebElement element) {
		String captcha_val = null;
		try {
			File newFile = WebElementExtender.captureElementPicture(element);
			FileHandler.copy(newFile, new File(Constant.Path_TestData + "/test-output/Captcha.png"));
			ITesseract tess = new Tesseract();
			tess.setDatapath(Constant.Tess_Util);
			System.out.println("Tesseract");
			System.out.println("Value");
			captcha_val = tess.doOCR(newFile);
			Thread.sleep(3000);
			
			System.out.println("CAPTCHA: " + captcha_val);
		} catch (Exception e) {
			System.out.println(e.getMessage().toString());
			e.printStackTrace();
		}
		return captcha_val;

	}

	public String[] CreateMerchant(String merchantName, String addrLine1, String postCode, WebDriver driver) {
			String []nameAndScreenPath =  new String [2];
		try {
			
			PageObjects po = new PageObjects(driver);
			po.MerchManagement.click();
			Thread.sleep(1000);
			po.MerchantTab.click();
			Thread.sleep(1000);
			po.CreateMerchant.click();
			Thread.sleep(1000);

			int random = randomNumber(4);
			String merchantSuffix = Integer.toString(random);

			merchantName = merchantName + "_" + merchantSuffix;
			//nameAndScreenPath[0]=merchantName;
			po.MerchantDispName.sendKeys(merchantName);
			po.AddressLine1.sendKeys(addrLine1);
			po.Country.click();
			Thread.sleep(1000);
			po.State.click();
			Thread.sleep(1000);
			po.City.click();
			Thread.sleep(4000);

			po.PostalCode.sendKeys(postCode);
			po.merchPAN.sendKeys(Unique_Pan());
			po.CloudValChckBox.click();

			po.MerchPrimaryContactName.sendKeys("User Merchant");
			po.MerchPrimaryContactNum.sendKeys("9886899363");
			po.MerchMobNum.sendKeys("9886899363");
			po.MerchEmail.sendKeys("test@pinelabs.com");

			po.CreateMerchantBtn.click();
			System.out.println(merchantName);
			Thread.sleep(1000);
			if (po.HubMessageMerchant.isDisplayed()) {
				if (po.HubMessageMerchant.getText()
						.contains("Merchant Display Name Already Requested By Some Other Merchant.")) {
					merchantName = merchantName + ": Already Requested";
					
				} else if (po.HubMessageMerchant.getText().contains("Merchant Created Successfully")) {
					merchantName = merchantName + ": Created";
				}
			}
			nameAndScreenPath[0] = merchantName;
			nameAndScreenPath[1]= takeScreenshot(driver, "CreateMerchant");
			Thread.sleep(1000);
			po.ClosePopup.click();
			System.out.println(merchantName);
			Thread.sleep(1000);
			po.UserDrpDwn.click();
			po.LogoutBtn.click();
			

		} catch (Exception e) {
			System.out.println(e.getMessage().toString());
			e.printStackTrace();
		}
		return nameAndScreenPath;
	}

	public String [] ApproveMerchant(String approver, String approverPass, String merchantName, WebDriver driver, PageObjects po) {
		
		//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver,100);
		//wait.until(ExpectedConditions.visibilityOfAllElements(po));
		
		String successMsg = "";
		String [] result = new String [2];
		try {
			
			int retryCount = 1;
			do {
				driver.get(Constant.URL);
				login(approver, approverPass, driver);
				System.out.println("Login Attempt for TestCase ApproveMerchant: "+ retryCount);
				retryCount++;
				Thread.sleep(1000);
				
			} while(driver.getCurrentUrl().contains("LoginPage") && retryCount<=3);
			Thread.sleep(1000);
			po.MerchManagement.click();
			Thread.sleep(1000);

			po.MerchantTab.click();
			Thread.sleep(1000);

			po.ApproveMerchant.click();

			po.MerchantName.sendKeys(merchantName);
			
			po.SearchBtn.click();
			
			po.ApproveRejectBtn.click();

			Thread.sleep(5000);
			String currentWindow = driver.getWindowHandle();
			
			for (String handle : driver.getWindowHandles()) {
				Thread.sleep(2000);
				driver.switchTo().window(handle);
			}
			
			driver.manage().window().maximize();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ApproveBtn")));
			Thread.sleep(5000);
			System.out.println(po.helpText.getText());
			System.out.println("Trying to Find Approve Btn");
			po.ApproveBtn.click();
			System.out.println("APPROVE BTN FOUND");
			Thread.sleep(10000);
			po.SaveBtn.click();
			Thread.sleep(2000);
			successMsg = po.MerchantId.getText();
			result[0] = successMsg;
			//System.out.println(successMsg + " and Merchant Name: " + merchantName);
			Thread.sleep(1000);
			result[1] = takeScreenshot(driver, "ApproveMerchant");
			po.ClosePopup.click();
			driver.close();
			
			System.out.println(successMsg + " and Merchant Name: " + merchantName);
			driver.switchTo().window(currentWindow);
			
			po.UserDrpDwn.click();
			po.LogoutBtn.click();

		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("Exception Occurred: ApproveMerchant - "+ e.getMessage().toString());
		}
		return result;
	}
	
	public String[] TidCreationCommon(String Client_id,String BankName,String PurposetypeArea,String terminalType,WebDriver driver) throws InterruptedException {
		String []nameAndScreenPath1 =  new String [3];
		
		TIDcreation tiDcrea = new TIDcreation(driver);
		File_ManagementObject filem = new File_ManagementObject(driver);
	
		String currentWindow1 = driver.getWindowHandle();
		filem.FileMgmt.click();
		tiDcrea.ManageTerminal.click();
		Thread.sleep(2000);
		Select sel1 = new Select(tiDcrea.Searchtype_M);
		sel1.selectByVisibleText("Client ID");
		tiDcrea.EnterClientID.sendKeys(Client_id);
		Thread.sleep(3000);
		tiDcrea.ClickSearch.click();
		Thread.sleep(3000);
		tiDcrea.RegisterTid.click();
		Thread.sleep(3000);

		for (String handleTid : driver.getWindowHandles()) {
			Thread.sleep(1000);
			driver.switchTo().window(handleTid);
		}
		Thread.sleep(3000);
		driver.manage().window().maximize();
		Thread.sleep(3000);
		tiDcrea.EnterBankTID.sendKeys("42"+randomNumber(6));
		Thread.sleep(2000);
		Select sel3 = new Select(tiDcrea.SelectBank);
		sel3.selectByVisibleText(BankName);
		Thread.sleep(2000);
		
		Select sel4 = new Select(tiDcrea.TIDPurpostype);
		sel4.selectByVisibleText(PurposetypeArea);
		Thread.sleep(2000);
		tiDcrea.TerminalType.sendKeys(terminalType);

		tiDcrea.TIDSaveButton.click();
		Thread.sleep(3000);
		String TidSuccessText = tiDcrea.TIDPOPUP.getText();
		
		String imagePath = takeScreenshot(driver, "CreateTerminal");
		//if (TidSuccessText.contains("successful")) {
			// String BankId = TidSuccessText.substring(TidSuccessText.length()-5);
            Thread.sleep(3000);
            nameAndScreenPath1[0] = TidSuccessText;
            nameAndScreenPath1[1]= imagePath;
            nameAndScreenPath1[2]=currentWindow1;
           
		
//	}
		return nameAndScreenPath1;
	
	}
	
	public int randomNumber(int numberOfDigits) {
		int value = (int)Math.pow(10,numberOfDigits);
		Random rnd = new Random();
		int n = value/10 + rnd.nextInt(value);
		
		return n;
	}
	
	public String takeScreenshot(WebDriver driver, String screenshotName){
		
		
		DateTimeFormatter formatter = DateTimeFormatter.BASIC_ISO_DATE;
		String todayDate = LocalDate.now().format(formatter);
		formatter = DateTimeFormatter.ofPattern("HHmmss");
		String currentTime = LocalTime.now().format(formatter);
		String destination = "";
		try {
			 File source = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			 destination = Constant.Tests_Output+"/Screenshots/"+screenshotName+"_"+todayDate+"_"+currentTime+".png";
			 File finalDestin = new File(destination);
			 FileUtils.copyFile(source, finalDestin);

		}catch(Exception e) {
			System.out.println(e.getMessage().toString());
			e.printStackTrace();
			
		}
		 
		return destination;
				
	}
}
