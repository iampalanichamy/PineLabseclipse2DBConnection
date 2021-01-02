package PLAutomation.MCUI_Automation;

import java.lang.annotation.Repeatable;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

//import junit.framework.Test;
import junit.framework.TestCase;

import pageObjects.LoginPageObjects;
import utility.Constant;
import utility.DataProviderWithExcel;
import utility.ReusableMethods;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {
	private WebDriver driver;
	private LoginPageObjects loginPO;
	private ReusableMethods reuseMethod;

	@BeforeTest
	public void setUp() {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Selenium_drivers\\chromedriver.exe");
		driver = new ChromeDriver();

		driver.manage().window().maximize();
		// driver.get(Constant.URL);
		loginPO = new LoginPageObjects(driver);
		reuseMethod = new ReusableMethods();
	}

	public AppTest(String testName) {
		super(testName);
	}

	/*
	 * public static Test suite() { //return new TestSuite( AppTest.class ); }
	 * 
	 * /** Rigourous Test :-)
	 */
	@Test(dataProviderClass = DataProviderWithExcel.class, dataProvider = "getExcelDataMultiple")
	public void LoginPageTest(String caseType, String userName, String passWord) {
		driver.get(Constant.URL);
		loginPO.adminLoginBtn.click();
		loginPO.userBox.sendKeys(userName);
		loginPO.pwdBox.sendKeys(passWord);
		String CAPTCHA = reuseMethod
				.GetCaptchaText(driver.findElement(By.xpath("//img[contains(@src,'Captcha.aspx')]")));

		loginPO.captchaTxt.sendKeys(CAPTCHA);
		
		if(driver.getTitle().contains("Index") && caseType.equalsIgnoreCase("Negative")) {
			System.out.println("Negative Case Pass");
		}else if(driver.getTitle().contains("Pine Payment Gateway") && caseType.equalsIgnoreCase("Positive")) {
			System.out.println("Positive Case Passed. Logging Out.");
			loginPO.logoutBtn.click();
		}

	}
}
